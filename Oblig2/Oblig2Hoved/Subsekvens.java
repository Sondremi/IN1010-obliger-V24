public class Subsekvens {
    public final String subsekvens;
    private int antall;

    public Subsekvens(String subsekvens) {
        this.subsekvens = subsekvens;
        this.antall++;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)",subsekvens,antall);
    }

    // Metoden returnerer Subsekvensens antall
    public int hentAntall() {return antall;}

    // Metoden returnerer String subsekvensen
    public String hentSubsekvens() {return subsekvens;}

    // Metoden tar inn et nytt antall og legger det til subsekvensens antall
    public void oppdaterAntall(int antall) {this.antall += antall;}

}