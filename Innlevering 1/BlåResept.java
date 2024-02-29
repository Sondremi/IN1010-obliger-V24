public class BlåResept extends Resept {
    public final String farge = "blaa";

    public BlåResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    public String toString() {
        return "Farge: " + this.farge + " | " + super.toString();
    }

    @Override
    public String farge() {
        return farge;
    }
    @Override
    public int prisAaBetale() {
        return (int) Math.round(hentLegemiddel().hentPris() * 0.25);
    }
}
