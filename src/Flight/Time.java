package Flight;

public class Time {
    private int minutes;

    public Time(int minutes) {
        this.minutes = minutes;
    }

    public Time(int hours, int minutes) {
        this.minutes = hours * 60 + minutes;
    }

    public int minutesUntil(Time other) {
        return other.minutes - minutes;
    }
}
