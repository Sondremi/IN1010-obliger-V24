public class Presept extends HvitResept {

    public Presept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    public String toString() {
        return "P-resept | " + super.toString();
    }
    public int prisAaBetale() {
        if (hentLegemiddel().hentPris() > 108) {
            return hentLegemiddel().hentPris() - 108;
        } else {
            return 0;
        }
    }
}
