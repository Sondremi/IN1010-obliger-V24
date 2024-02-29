abstract class Resept {
    public static int IDteller = 0;
    public final int ID;
    public final Legemiddel legemiddel;
    public final Lege utskrivendeLege;
    public final int pasientId;
    public int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        this.ID = IDteller++;
    }
    public String toString() {
        return ("Legemiddel: " + legemiddel + " | PasientId: " + pasientId + ". Reit: " + reit);
    }
    
    public int hentId() {
        return ID;
    }
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
    public Lege hentLege() {
        return utskrivendeLege;
    }
    public int hentPasientId() {
        return pasientId;
    }
    public int hentReit() {
        return reit;
    }
    public boolean bruk() {
        if (reit > 0) {
            reit -= 1;
            return true;
        }
        return false;
    }
    abstract public String farge();
    abstract public int prisAaBetale();
}
