public class HvitRute extends Rute {
    public HvitRute(int r, int k) {
        this.radNr = r;
        this.kolNr = k;
    }

    @Override
    public void finn(Rute fra) {
        // Alle hvite ruter sjekker sine naboruter til en åpning er funnet,
        // så printes koordinatene til åpningen ut

        this.markert = true;

        for (Rute nabo : naboer) {
            if (nabo != fra && !nabo.markert) {
                nabo.finn(nabo);
            }
        }
    }

    @Override
    public String toString() {
        return ".";
    }
}
