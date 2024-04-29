import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    private final SubsekvensRegister subRegister;
    private final Lock laas = new ReentrantLock();
    private final Condition toIgjen = laas.newCondition();

    public Monitor2(SubsekvensRegister subRegister) {
        this.subRegister = subRegister;
    }

    public void settInn(HashMap<String, Subsekvens> ny) {
        laas.lock();
        try {
            subRegister.settInn(ny);
            if (subRegister.hentAntall() >= 2) {
                toIgjen.signalAll();
            }
        }
        finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> taUt() {
        laas.lock();
        try {
            return subRegister.taUt();
        }
        finally {
            laas.unlock();
        }
    }

    // Henter ut 2
    public ArrayList<HashMap<String, Subsekvens>> taUtTo() {
        laas.lock();
        try {
            while (subRegister.hentAntall() < 2) {
                toIgjen.await();
            }
            
            ArrayList<HashMap<String, Subsekvens>> toHm = new ArrayList<>();
            toHm.add(taUt());
            toHm.add(taUt());
            
            return toHm;
        } catch (InterruptedException e) {
            System.err.println("Monitor2 feil : taUtTo");
            System.exit(1);
            return null;
        }
        finally {
            laas.unlock();
        }
    }

    public int hentAntall() {
        laas.lock();
        try {
            return subRegister.hentAntall();
        }
        finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> hentHashBeholder() {
        laas.lock();
        try {
            return subRegister.hentHashBeholder();
        }
        finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> lesFil(String mappenavn) {
        laas.lock();
        try {
            return SubsekvensRegister.lesFil(mappenavn);
        }
        finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> hm1, HashMap<String, Subsekvens> hm2) {
        laas.lock();
        try {
            return subRegister.slaaSammen(hm1, hm2);
        }
        finally {
            laas.unlock();
        }
    }

}