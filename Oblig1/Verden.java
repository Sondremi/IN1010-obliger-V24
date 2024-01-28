public class Verden {

    Rutenett rutenett;
    int genNr = 0;
    int antRader;
    int antKolonner;


    public Verden(int antRader, int antKolonner) {
        this.rutenett = new Rutenett(antRader, antKolonner);
        this.antRader = antRader;
        this.antKolonner = antKolonner;

        this.rutenett.fyllMedTilfeldigeCeller();
        this.rutenett.kobleAlleCeller();
    }

    public void tegn() {
        System.out.println("--- Generasjon: " + this.genNr + " | Antall levende celler: " + rutenett.antallLevende() + " ---");
        this.rutenett.tegnRutenett();
    }

    public void oppdatering() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                Celle celle = rutenett.hentCelle(rad, kol);
                celle.tellLevendeNaboer();
                celle.oppdaterStatus();
            }
        }
        this.genNr++;
    }
}
