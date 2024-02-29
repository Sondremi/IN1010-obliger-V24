public class Milresept extends HvitResept {
    public Milresept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }
    public String toString() {
        return "Militærresept | " + super.toString();
    }
    @Override
    public int prisAaBetale() {
        return 0;
    }
    
}
