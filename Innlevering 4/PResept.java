public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String toString() {
        return "P-resept | " + super.toString();
    }
    
    @Override
    public int prisAaBetale() {
        // Pasient får 108kr rabatt, men pris kan ikke være mindre enn 0
        if (legemiddel.hentPris() > 108) {
            return legemiddel.hentPris() - 108;
        } else {
            return 0;
        }
    }
}
