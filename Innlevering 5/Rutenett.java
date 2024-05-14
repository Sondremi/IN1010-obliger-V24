public class Rutenett {
    public int antRader;
    public int antKolonner;

    // Oppretter en todimensjonal array som skal lagre referanser til Celle klassen
    public Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;

        rutene = new Celle[antRader][antKolonner];
    }

    public void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        if (Math.random()<=0.3333) {
            celle.settLevende();
        }
        rutene[rad][kol] = celle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int rad = 0; rad < rutene.length; rad++) {
            for (int kol = 0; kol < rutene[rad].length; kol++) {    
                lagCelle(rad, kol);
            }
        }
    }
    
    public Celle hentCelle(int rad, int kol) {
        if (rad < 0 || rad >= antRader || kol < 0 || kol >= antKolonner) {
            return null;
        } return rutene[rad][kol];
    }

    public void tegnRutenett() {
        for (int rad = 0; rad < rutene.length; rad++) {
            for (int kol = 0; kol < rutene[rad].length; kol++) {
                System.out.print("| " + rutene[rad][kol].hentStatusTegn() + " ");
            }
            System.out.println("|");
            System.out.print("+---".repeat(rutene[rad].length));
            System.out.println("+");
        }
    }
    
    public void settNaboer(int rad, int kol) {
        Celle celle = hentCelle(rad, kol);

        for (int naboRad = rad-1; naboRad < rad+2; naboRad++) {
            for (int naboKol = kol-1; naboKol < kol+2; naboKol++) {
                if (naboRad >= 0 && naboRad < antRader && naboKol >= 0 && naboKol < antKolonner) {
                    if (naboRad != rad || naboKol != kol) {
                        celle.leggTilNabo(hentCelle(naboRad, naboKol));
                    }
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                settNaboer(rad, kol);
            }
        }
    }

    public int antallLevende() {
        int antLevende = 0;
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                Celle celle = hentCelle(rad, kol);
                if (celle.erLevende()) {
                    antLevende++;
                }
            }
        }
        return antLevende;
    }
}
