package Philosophy;

public class Philosopher implements Runnable {
    private final String name;
    public final long eatTime;
    private int eatenRiceCount;
    private PhilosopherState philosopherState = PhilosopherState.Think;

    public Philosopher(String name, long eatTime) {
        this.name = name;
        this.eatTime = eatTime;
    }

    public void setEatenRiceCount(int eatenRiceCount) {this.eatenRiceCount = eatenRiceCount;}
    public int getEatenRiceCount() { return eatenRiceCount; }
    public void setPhilosopherState(PhilosopherState philosopherState) { this.philosopherState = philosopherState; }
    public String getPhilosopherState()
    {
        return name +
                (philosopherState == PhilosopherState.Think ? " (думает)" : " (кушает)") +
                "; Съел: " + eatenRiceCount + "гр. риса.";
    }

    @Override
    public void run() {
        do {
            if (!Thread.currentThread().isInterrupted())
            {
                try {
                    Table.rice.eatRice(this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                return;
            }
        } while(true);
    }
}
