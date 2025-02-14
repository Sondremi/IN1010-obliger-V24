public class Narkotisk extends Legemiddel {
    public final int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    // Metoden returnerer legemidelets styrke
    public int hentStyrke() {
        return this.styrke;
    }
}
