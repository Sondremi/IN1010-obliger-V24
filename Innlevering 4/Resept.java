abstract class Resept {
    public int reit;
    public final Pasient pasient;
    public final Lege utskrivendeLege;
    public final Legemiddel legemiddel;
    public final int ID;
    public static int IDteller = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.ID = IDteller++;
    }
 
    @Override
    public String toString() {
        return ("Legemiddel: " + legemiddel + " | Pasient: " + pasient + ". Reit: " + reit);
    }
    
    // Metoden returnerer reseptens ID
    public int hentId() {
        return ID;
    }

    // Metoden returnerer reseptens legemiddel
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    // Metoden returnerer legen som skrev ut resepten
    public Lege hentLege() {
        return utskrivendeLege;
    }

    // Metoden returnerer ID'en til pasienten som resepten står på
    public int hentPasientId() {
        return pasient.hentId();
    }

    // Metoden returnerer antall reit resepten har
    public int hentReit() {
        return reit;
    }

    // Metoden forsøker å bruke resepten 1 gang : minsker reit
    public boolean bruk() {
        if (reit > 0) {
            reit --;
            return true;
        }
        return false;
    }

    // Metoden returnerer reseptens farge
    abstract public String farge();

    // Metoden returnerer prisen pasienten må betale for resepten
    abstract public int prisAaBetale();
}
