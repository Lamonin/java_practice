package Philosophy;

import java.util.ArrayList;

public class Table {
    private final ArrayList<Philosopher> philosophers;
    public static Rice rice;

    public Table() {
        philosophers = new ArrayList<>();

        philosophers.add(new Philosopher("Диоген", 1000));
        philosophers.add(new Philosopher("Платон", 2000));
        philosophers.add(new Philosopher("Антон", 1500));
        philosophers.add(new Philosopher("Пифагор", 1200));
        philosophers.add(new Philosopher("Аристотель", 1800));

        rice = new Rice();

        for (var ph:philosophers) {
            (new Thread(ph)).start();
        }
    }

    public String getPhilosophersInfo()
    {
        var builder = new StringBuilder();

        for (var ph:philosophers) {
            if (!builder.isEmpty())
            {
                builder.append("\n");
            }

            builder.append(ph.getPhilosopherState());
        }

        return builder.toString();
    }
}
