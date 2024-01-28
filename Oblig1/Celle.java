public class Celle {

    public boolean levende;
    public Celle[] naboer = new Celle[8];
    public int antNaboer = 0;
    public int antLevendeNaboer = 0;

    public Celle() {
        this.levende = false;
    }

    public void settDoed() {
        this.levende = false;
    }
    public void settLevende() {
        this.levende = true;
    }
    public boolean erLevende() {
        return this.levende;
    }
    public char hentStatusTegn() {
        if (this.erLevende()) {
            return 'O';
        } else {
            return '.';
        }
    }
    public void leggTilNabo(Celle nabo) {
        for (int i = 0; i < naboer.length; i++) {
            if (this.naboer[i] == null) {
                this.naboer[i] = nabo;
                this.antNaboer++;
                break;
            }
        }
    }
    public void tellLevendeNaboer() {
        this.antLevendeNaboer = 0;
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] != null && naboer[i].erLevende()) {
                this.antLevendeNaboer++;
            }
        }
    }
    public void oppdaterStatus() {
        //this.tellLevendeNaboer();
        if (this.erLevende()) {
            if (this.antLevendeNaboer < 2 || this.antLevendeNaboer > 3) {
                this.settDoed();
            } 
        } else {
            if (this.antLevendeNaboer == 3) {
                this.settLevende();
            }
        }
    }
}
