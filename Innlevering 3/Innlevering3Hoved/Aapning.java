public class Aapning extends HvitRute {
    public Aapning(int r, int k) {
        super(r, k);
    }

    @Override
    public void finn(Rute fra) {
        System.out.println("(" + radNr + "," + kolNr + ")");
    }
}
