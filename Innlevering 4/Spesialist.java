public class Spesialist extends Lege implements Godkjenningsfritak {
    private String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String toString() {
        return (super.toString() + ", Kontrollkode: " + kontrollkode);
    }

    // Metoden returnerer spesialistens kontrollkode
    @Override
    public String hentKontrollkode() {
        return this.kontrollkode;
    }
}