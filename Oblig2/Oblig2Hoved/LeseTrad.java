import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private final Monitor2 monitor;
    private final String filnavn;
    private final CountDownLatch barriere;

    public LeseTrad(Monitor2 monitor, String filnavn, CountDownLatch barriere) {
        this.monitor = monitor;
        this.filnavn = filnavn;
        this.barriere = barriere;
    }

    @Override
    public void run() {
        monitor.settInn(monitor.lesFil(filnavn)); 
        barriere.countDown();
    }
}
