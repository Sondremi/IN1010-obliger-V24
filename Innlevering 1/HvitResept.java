public class HvitResept extends Resept {
    public final String farge = "hvit";

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
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
        return hentLegemiddel().hentPris();
    }
    
}
