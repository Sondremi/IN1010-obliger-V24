import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class FletteTraad implements Runnable {
    private final Monitor2 monitor;
    private final CountDownLatch barriere;
    private final ReentrantLock laas = new ReentrantLock();

    public FletteTraad(Monitor2 monitor, CountDownLatch barriere) {
        this.monitor = monitor;
        this.barriere = barriere;
    }
    
    @Override
    public void run() {
        laas.lock();
        try {
            while (monitor.hentAntall() >= 2 && barriere.getCount() > 0) {
                ArrayList<HashMap<String, Subsekvens>> hmMedTo = monitor.taUtTo();
                monitor.settInn(monitor.slaaSammen(hmMedTo.get(0), hmMedTo.get(1)));
                barriere.countDown();
            }
        }
        finally {
            laas.unlock();
        }
    }
    
}
