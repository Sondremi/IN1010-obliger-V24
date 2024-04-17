abstract class Legemiddel {
    public int pris;
    public final String navn;
    public final double virkestoff;
    public final int ID;
    public static int IDteller = 0;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        ID = IDteller++;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn + ". Pris: " + pris + ". Virkestoff: " + virkestoff + ". ID: " + ID);
    }

    // Metoden returnerer legemidelets pris
    public int hentPris() {
        return this.pris;
    }

    // Metoden returnerer legemidelets virkestoff
    public double hentVirkestoff() {
        return virkestoff;
    }

    // Metoden returnerer legemidelets navn
    public String hentNavn(){
        return this.navn;
    }
    
    // Metoden returnerer legemidelets ID
    public int hentID() {
        return this.ID;
    }

    // Metoden gir en ny pris til legemidelet 
    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }
}