import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    private static SubsekvensRegister subRegister = new SubsekvensRegister();
    private final Lock laas = new ReentrantLock();

    public void settInn(HashMap<String, Subsekvens> ny) {
        laas.lock();
        try {
            subRegister.settInn(ny);
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
