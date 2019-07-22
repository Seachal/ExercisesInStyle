package eis.chapter9.exercises;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Exercise 3 from chapter 9.
 * 
 * @author Marco Faella
 * @version 1.0
 * @param <E> The type of events contained in this schedule
 */
public class Schedule<E extends Event> {
    private volatile boolean active;
    private volatile Optional<E> currentEvent = Optional.empty();
    private final SortedSet<TimedEvent> events = new TreeSet<>();
    
    // Not static because it uses E (easier this way) 
    private class TimedEvent implements Comparable<TimedEvent> {
        E event;
        LocalTime startTime, stopTime;
        @Override
        public int compareTo(TimedEvent other) {
            if (stopTime.isBefore(other.startTime)) return -1;
            if (other.stopTime.isBefore(startTime)) return 1;
            return 0;
        }
        TimedEvent(E event, LocalTime startTime, LocalTime stopTime) {
            this.event = event;
            this.startTime = startTime;
            this.stopTime = stopTime;
        }
    }
    
    public void addEvent(E event, LocalTime startTime, LocalTime stopTime) {
        if (active)
            throw new IllegalStateException("Cannot add event while active.");
        if (startTime.isAfter(stopTime))
            throw new IllegalArgumentException("Stop time is earlier than start time.");
        TimedEvent timedEvent = new TimedEvent(event, startTime, stopTime);
        if (!events.add(timedEvent))
            throw new IllegalArgumentException("Overlapping event.");
    }
    
    public void launch() {
        active = true;
        Thread helper = new Thread() {
            public void run() {
                for (TimedEvent timedEvent: events) {
                    try {
                        long delay = LocalTime.now().until(timedEvent.startTime, ChronoUnit.MILLIS);
                        long duration = timedEvent.startTime.until(timedEvent.stopTime, ChronoUnit.MILLIS);
                        sleep(delay);
                        timedEvent.event.start();
                        currentEvent = Optional.of(timedEvent.event);
                        sleep(duration);
                        currentEvent = Optional.empty();
                        timedEvent.event.stop();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                active = false;
            }
        };
        helper.start();
    }
    
    public boolean isActive() {
        return active;
    }
    
    public Optional<E> currentEvent() {
        if (active)
            return currentEvent;
        else
            throw new IllegalStateException();
    }
}
