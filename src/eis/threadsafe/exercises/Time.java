package eis.threadsafe.exercises;

public class Time {
    private final int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        if (hours<0 || hours>23)
            throw new IllegalArgumentException("The range for hours is 0, 23.");
        if (minutes<0 || minutes>59)
            throw new IllegalArgumentException("The range for minutes is 0, 59.");
        if (seconds<0 || seconds>59)
            throw new IllegalArgumentException("The range for seconds is 0, 59.");
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time addNoWrapping(Time delta) {
        int s = seconds, m = minutes, h = hours;
        s += delta.seconds;
        if (s > 59) {
            s -= 60;
            m++;
        }
        m += delta.minutes;
        if (m > 59) {
            m -= 60;
            h++;
        }
        h += delta.hours;
        if (h > 23) { // Overflow: set to midnight
            h = 23;
            m = 59;
            s = 59;
        }
        return new Time(h, m, s);
    }

    public Time addAndWrapAround(Time delta) {
        int s = seconds, m = minutes, h = hours;
        s += delta.seconds;
        if (s > 59) {
            s -= 60;
            m++;
        }
        m += delta.minutes;
        if (m > 59) {
            m -= 60;
            h++;
        }
        h += delta.hours;
        if (h > 23) { // Overflow: wrap around
            h -= 24;
        }
        return new Time(h, m, s);
    }
}



