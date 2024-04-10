public class SortRute extends Rute {
    public SortRute(int r, int k) {
        this.radNr = r;
        this.kolNr = k;
    }

    @Override
    public void finn(Rute fra) {
        // Sort rute sjekker ikke sine naboer
    }

    @Override
    public String toString() {
        return "#";
    }
}
