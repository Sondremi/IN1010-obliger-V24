public class Lege {
    String navn;

    public Lege(String navn) {
        this.navn = navn;
    }
    public String hentNavn() {
        return this.navn;
    }
    public String toString() {
        return ("Navn: " + navn);
    }
}
