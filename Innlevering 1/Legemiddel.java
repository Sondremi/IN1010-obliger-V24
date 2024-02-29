abstract class Legemiddel {
    public final String navn;
    public int pris;
    public final double virkestoff;
    public static int IDteller = 0;
    public final int ID;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        ID = IDteller++;
    }
    public String toString() {
        return ("Navn: " + navn + ". Pris: " + pris + ". Virkestoff: " + virkestoff + ". ID: " + ID);
    }

    public int hentPris() {
        return this.pris;
    }
    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }
}