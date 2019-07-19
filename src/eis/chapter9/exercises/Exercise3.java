package eis.chapter9.exercises;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Exercise3 {

    public static void main(String[] args) {
        Schedule<PrintedEvent> schedule = new Schedule<>();
        
        schedule.addEvent(new PrintedEvent(1), LocalTime.now().plus(5, ChronoUnit.SECONDS), LocalTime.now().plus(8, ChronoUnit.SECONDS));
        schedule.addEvent(new PrintedEvent(2), LocalTime.now().plus(12, ChronoUnit.SECONDS), LocalTime.now().plus(14, ChronoUnit.SECONDS));
        schedule.addEvent(new PrintedEvent(3), LocalTime.now().plus(9, ChronoUnit.SECONDS), LocalTime.now().plus(10, ChronoUnit.SECONDS));
        try {
            schedule.addEvent(new PrintedEvent(4), LocalTime.now().plus(8, ChronoUnit.SECONDS), LocalTime.now().plus(10, ChronoUnit.SECONDS));
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        schedule.launch();
    }

}

class PrintedEvent implements Event {
    private final int id;
    
    public PrintedEvent(int id) {
        this.id = id;
    }
    
    @Override
    public void start() {
        System.out.println("Starting " + id);
    }
    @Override
    public void stop() {
        System.out.println("Stopping " + id);
    }
}