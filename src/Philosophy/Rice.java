package Philosophy;

public class Rice {
    public synchronized void eatRice(Philosopher eater) throws InterruptedException {
        eater.setPhilosopherState(PhilosopherState.Eat);
        Thread.sleep(eater.eatTime);

        eater.setPhilosopherState(PhilosopherState.Think);
        eater.setEatenRiceCount(eater.getEatenRiceCount() + 100);
    }
}
