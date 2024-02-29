public class Bil3 {
    String bilnr;

    public Bil3(String bilnr) {
        this.bilnr = bilnr;
    }

    public void skrivUt() {
        System.out.println("Jeg er en bil med bilnummer: " + bilnr);
    }

    public String hentNr() {
        return this.bilnr;
    }
}