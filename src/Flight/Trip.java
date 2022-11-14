package Flight;

import java.util.ArrayList;

public class Trip {
    private ArrayList<Flight> flights;

    public Trip() {
        flights = new ArrayList<Flight>();
    }

    public void addFlight(Flight flight)
    {
        flights.add(flight);
    }

    public void removeFlight(Flight flight)
    {
        flights.remove(flight);
    }

    public int getDuration()
    {
        return flights.isEmpty() ? 0 : flights.get(0).getDepartureTime().minutesUntil(flights.get(flights.size()-1).getArrivalTime());
    }

    public int getShortestLayover()
    {
        int shortestLayover = flights.size() < 2 ? -1 : flights.get(0).getArrivalTime().minutesUntil(flights.get(1).getDepartureTime());

        for (int i = 1; i < flights.size()-1; i++)
        {
            int layover = flights.get(i).getArrivalTime().minutesUntil(flights.get(i+1).getDepartureTime());
            if (layover < shortestLayover)
            {
                shortestLayover = layover;
            }
        }

        return shortestLayover;
    }
}
