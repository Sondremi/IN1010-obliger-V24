public class TestLegemiddel {
    public static void main(String[] args) {
        Legemiddel[] legemidler = new Legemiddel[9];

        legemidler[0] = new Vanlig("Vanlig", 100, 10);
        legemidler[1] = new Vanlig("Vanlig2", 100, 10);

        legemidler[2] = new Vanedannende("Vanedannende", 200, 15, 10);
        legemidler[3] = new Vanedannende("Vanedannende2", 200, 15, 10);
        legemidler[4] = new Vanedannende("Vanedannende3", 200, 15, 10);

        legemidler[5] = new Narkotisk("Narkotisk", 300, 25, 15);
        legemidler[6] = new Narkotisk("Narkotisk2", 300, 25, 15);
        legemidler[7] = new Narkotisk("Narkotisk3", 300, 25, 15);
        legemidler[8] = new Narkotisk("Narkotisk4", 300, 25, 15);

        for (int i = 0; i < legemidler.length; i++) {
            System.out.println(legemidler[i].toString());
            if (testLegemiddelId(legemidler[i], i)) {
                System.out.println(legemidler[i].navn + "hadde forventet ID \n");
            } else {
                System.out.println(legemidler[i].navn + "hadde IKKE forventet ID \n");
            }
        }

    }

    private static boolean testLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        return legemiddel.ID == forventetLegemiddelId; 
    }
}
