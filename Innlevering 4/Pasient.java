public class Pasient {
    private final String navn;
    private final String fNr; // fødselsnummer
    private Lenkeliste<Resept> resepter = new Lenkeliste<>(); // Lenkeliste holder på pasientens resepter
    private final int pasientID;
    private static int pasientTeller = 0;

    public Pasient(String navn, String fNr) {
        this.navn = navn;
        this.fNr = fNr;
        pasientID = pasientTeller;
        pasientTeller++;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + ", fødselsnr: " + fNr + ", pasient ID: " + pasientID;
    }
    
    // Metoden legger en ny resept til i listen over resepter
    public void leggTilResept(Resept r) {
        resepter.leggTil(r);
    }

    // Metoden returnerer pasientens ID
    public int hentId(){
        return pasientID;
    }

    // Metoden returnerer pasientens navn
    public String hentNavn(){
        return navn;
    }

    // Metoden returnerer pasientens fødselsnummer
    public String hentFnr(){
        return fNr;
    }

    // Metoden returnerer en Lenkeliste med pasientens resepter
    public Lenkeliste<Resept> hentResepter(){
        return resepter;
    }
}
