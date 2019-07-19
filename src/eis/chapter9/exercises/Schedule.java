package eis.chapter9.exercises;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;
import java.util.TreeSet;

public class Schedule<E extends Event> {
    private boolean launched;
    private SortedSet<TimedEvent> events = new TreeSet<>();
    
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
        if (launched)
            throw new IllegalStateException("Cannot add event while running.");
        if (startTime.isAfter(stopTime))
            throw new IllegalArgumentException("End time is earlier than start time.");
        TimedEvent timedEvent = new TimedEvent(event, startTime, stopTime);
        if (!events.add(timedEvent))
            throw new IllegalArgumentException("Overlapping event.");
    }
    
    public void launch() {
        launched = true;
        Thread helper = new Thread() {
            public void run() {
                for (TimedEvent timedEvent: events) {
                    try {
                        long delay = LocalTime.now().until(timedEvent.startTime, ChronoUnit.MILLIS);
                        long duration = timedEvent.startTime.until(timedEvent.stopTime, ChronoUnit.MILLIS);
                        sleep(delay);
                        timedEvent.event.start();
                        sleep(duration);
                        timedEvent.event.stop();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };
        helper.start();
    }
    
    public E currentEvent() {
        return null;
    }
}
