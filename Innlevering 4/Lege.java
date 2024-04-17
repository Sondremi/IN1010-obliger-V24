public class Lege implements Comparable<Lege> {
    protected String navn; 
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>(); // En IndeksertListe av Resepter som legen har skrevet ut

    public Lege(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn);
    }

    // Metoden returnerer legens navn
    public String hentNavn() {
        return this.navn;
    }

    // Metoden returnerer en IndeksertListe av legens utskrevne resepter.
    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    // Metoden sammenligner denne legen med en annen lege basert på navn (alfabatisk).
    @Override
    public int compareTo(Lege annen) {
        return hentNavn().compareToIgnoreCase(annen.hentNavn());
    }

    // Metoden skriver ut en HvitResept og legger den til i listen over utskrevne resepter.
    // Kaster feilmelding om en vanlig lege prøver å skrive ut et narkotisk legemiddel
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (!(this instanceof Spesialist) && legemiddel instanceof Narkotisk) {    
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            HvitResept hr = new HvitResept(legemiddel, this, pasient, reit);
            utskrevneResepter.leggTil(hr);
            return hr;
        }
    }

    // Metoden skriver ut en BlaaResept og legger den til i listen over utskrevne resepter.
    // Kaster feilmelding om en vanlig lege prøver å skrive ut et narkotisk legemiddel
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (!(this instanceof Spesialist) && legemiddel instanceof Narkotisk) {    
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            BlaaResept br = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevneResepter.leggTil(br);
            return br;
        }
    }

    // Metoden skriver ut en MilitaerResept og legger den til i listen over utskrevne resepter.
    // Kaster feilmelding om en vanlig lege prøver å skrive ut et narkotisk legemiddel
    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (!(this instanceof Spesialist) && legemiddel instanceof Narkotisk) {    
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            MilResept mr = new MilResept(legemiddel, this, pasient);
            utskrevneResepter.leggTil(mr);
            return mr;
        }
    }

    // Metoden skriver ut en P-Resept og legger den til i listen over utskrevne resepter.
    // Kaster feilmelding om en vanlig lege prøver å skrive ut et narkotisk legemiddel
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (!(this instanceof Spesialist) && legemiddel instanceof Narkotisk) {    
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            PResept pr = new PResept(legemiddel, this, pasient, reit);
            utskrevneResepter.leggTil(pr);
            return pr;
        }
    }
}
