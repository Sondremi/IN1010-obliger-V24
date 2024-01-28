public class Rutenett {
    
    public int antRader;
    public int antKolonner;

    // Oppretter en todimensjonal array som skal lagre referanser til Celle klassen
    Celle[][] rutene;

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
        this.rutene[rad][kol] = celle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int rad = 0; rad < this.rutene.length; rad++) {
            for (int kol = 0; kol < this.rutene[rad].length; kol++) {    
                this.lagCelle(rad, kol);
            }
        }
    }
    
    public Celle hentCelle(int rad, int kol) {
        if (rad < 0 || rad >= this.antRader || kol < 0 || kol >= this.antKolonner) {
            return null;
        } return this.rutene[rad][kol];
    }

    public void tegnRutenett() {
        for (int rad = 0; rad < this.rutene.length; rad++) {
            for (int kol = 0; kol < this.rutene[rad].length; kol++) {
                System.out.print("| " + this.rutene[rad][kol].hentStatusTegn() + " ");
            }
            System.out.println("|");
            System.out.print("+---".repeat(this.rutene[rad].length));
            System.out.println("+");
        }
    }
    

    public void settNaboer(int rad, int kol) {
        Celle celle = this.hentCelle(rad, kol);

        for (int naboRad = rad-1; naboRad < rad+2; naboRad++) {
            for (int naboKol = kol-1; naboKol < kol+2; naboKol++) {
                if (naboRad >= 0 && naboRad < this.antRader && naboKol >= 0 && naboKol < this.antKolonner) {
                    if (naboRad != rad || naboKol != kol) {
                        celle.leggTilNabo(this.hentCelle(naboRad, naboKol));
                    }
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                this.settNaboer(rad, kol);
            }
        }
    }

    public int antallLevende() {
        int antLevende = 0;
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                Celle celle = this.hentCelle(rad, kol);
                if (celle.erLevende()) {
                    antLevende++;
                }
            }
        }
        return antLevende;
    }
}
