public class TestResepter {
    public static void main(String[] args) {
        Vanlig vanlig = new Vanlig("Vanlig", 1000, 10);
        Vanlig vanlig2 = new Vanlig("Vanlig billig", 100, 10);
        Vanedannende vanedannende = new Vanedannende("Vanedannende", 200, 15, 10);
        Narkotisk narkotisk = new Narkotisk("Narkotisk", 300, 25, 15);

        Lege lege = new Lege("Sondre");

        BlåResept b1 = new BlåResept(vanlig, lege, 01, 0);
        BlåResept b2 = new BlåResept(vanedannende, lege, 01, 1);

        HvitResept h1 = new HvitResept(vanedannende, lege, 01, 2);
        HvitResept h2 = new HvitResept(narkotisk, lege, 01, 5);

        Milresept m1 = new Milresept(narkotisk, lege, 01);
        Milresept m2 = new Milresept(vanedannende, lege, 01);

        Presept p1 = new Presept(vanlig, lege, 01, 10);
        Presept p2 = new Presept(vanlig2, lege, 01, 5);

        Resept[] resepter = new Resept[8];
        resepter[0] = b1; resepter[1] = b2; resepter[2] = h1; resepter[3] = h2; resepter[4] = m1; resepter[5] = m2; 
        resepter[6] = p1; resepter[7] = p2;

        for (int i = 0; i < resepter.length; i++) {
            testResept(resepter[i]);
            System.out.println(resepter[i].toString() + "\n");
        }
    }
    
    public static void testResept(Resept resept) {
        System.out.println("Farge: " + resept.farge());
        System.out.println("Pris å betale: " + resept.prisAaBetale());
    }
}
