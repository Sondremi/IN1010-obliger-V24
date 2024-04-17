public class HvitResept extends Resept {
    public final String farge = "hvit";

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String toString() {
        return "Farge på resept: " + this.farge + " | " + super.toString();
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
}
