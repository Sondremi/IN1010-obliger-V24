public class BlaaResept extends Resept {
    public final String farge = "blaa";

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
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
        return (int) Math.round(legemiddel.hentPris() * 0.25);
    }
}
