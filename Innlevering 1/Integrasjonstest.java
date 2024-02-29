public class Integrasjonstest {
    public static void main(String[] args) {
        // Oppretter legemiddel-objekter
        Legemiddel[] legemidler = new Legemiddel[3];
        legemidler[0] = new Vanlig("Vanlig", 1000, 10);
        legemidler[1] = new Vanedannende("Vanedannende", 200, 15, 10);
        legemidler[2] = new Narkotisk("Narkotisk", 300, 25, 15);
       
        // Oppretter en lege og spesialist
        Lege[] leger = new Lege[2];
        Lege lege1 = new Lege("Sondre");
        Spesialist spesialist = new Spesialist("Niklas", "007");
        leger[0] = lege1;
        leger[1] = spesialist;

        // Oppretter resept-objekter
        Resept[] resepter = new Resept[4];
        resepter[0] = new BlåResept(legemidler[0], lege1, 01, 0);
        resepter[1] = new HvitResept(legemidler[1], lege1, 01, 2);
        resepter[2] = new Milresept(legemidler[2], spesialist, 01);
        resepter[3] = new Presept(legemidler[0], lege1, 01, 10);

        for (Legemiddel legemiddel: legemidler) {
            System.out.println(legemiddel.toString());
        }
        for (Lege lege: leger) {
            System.out.println(lege.toString());
        }
        for (Resept resept: resepter) {
            System.out.println(resept.toString());
        }
        
    }
}