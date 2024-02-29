public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }
    public String toString() {
        return ("Navn: " + navn + ". Kontrollkode: " + kontrollkode);
    }
    @Override
    public String hentKontrollkode() {
        return this.kontrollkode;
    }
}