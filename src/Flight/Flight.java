package Flight;

public class Flight {
    private Time departureTime;
    private Time arrivalTime;
    
    public Flight(Time departureTime, Time arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }
    public Time getArrivalTime() {
        return arrivalTime;
    }
}
