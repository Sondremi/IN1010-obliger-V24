import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

public class Oblig2Del2B {
    public static void main(String[] args) {
        SubsekvensRegister subRegister = new SubsekvensRegister();
        Monitor2 register = new Monitor2(subRegister);

        String mappenavn = args[0]; // legg til ../ før mappenavnet hvis koden kjørers direkte fra Oblig2Del2B mappen
        ArrayList<String> filnavn = hentFilnavn(mappenavn); // Kaller hjelpemetoden for å hente ut alle filnavnene

        int antallFiler = filnavn.size(); 
        int antTraader = 8;        

        // lesebarriere venter med å gå videre før alle filene er lest
        CountDownLatch leseBarriere = new CountDownLatch(antallFiler);
        CountDownLatch fletteBarriere = new CountDownLatch(antallFiler - 1); // -1 pga metadata

        // Oppretter tråder for å lese alle filene
        for (String filnavnet : filnavn) {
            LeseTrad lesetraad = new LeseTrad(register, (mappenavn + "/" + filnavnet), leseBarriere);
            new Thread(lesetraad).start();
        }

        try {
            leseBarriere.await();
        } catch (InterruptedException e) {
            System.err.println("Main : lesetraad feil");
            System.exit(1);
        }

        System.out.println("Lesing fullført");

        for (int i = 0; i < antTraader; i++) {
            FletteTraad flettetraad = new FletteTraad(register, fletteBarriere);
            new Thread(flettetraad).start();
        }

        try {
            fletteBarriere.await();
        } catch (InterruptedException e) {
            System.err.println("Main : flettetraad feil");
            System.exit(1);
        }

        System.out.println("Fletting fullført");


        // Skriver ut subsekvensen med størst antall
        System.out.println(hentStorsteSub(register));
    }

    // Hjelpemetode for å lage en ArrayList som inneholder alle filnavnene fra metadata.csv
    public static ArrayList<String> hentFilnavn(String mappenavn) {
        ArrayList<String> filnavn = new ArrayList<>();
        Scanner leser = null;
        try {
            leser = new Scanner(new File(mappenavn + "/metadata.csv"));
            while (leser.hasNextLine()) {
                filnavn.add(leser.nextLine());
            }
            return filnavn;
        } catch (FileNotFoundException e) {
            System.out.println("Finner ikke riktig sti.");
        }
        return null;
    }

    // Hjelpemetode for å hente ut den Subsekvensen som har størst antall
    public static Subsekvens hentStorsteSub(Monitor2 register) {
        int storst = 0;
        Subsekvens storstSubsekvens = null;
        HashMap<String, Subsekvens> flettetRegister = register.hentHashBeholder();

        // Går gjennom hashbeholderen og finner subsekvensen med størst antall
        for (Map.Entry<String, Subsekvens> x : flettetRegister.entrySet()) {
            Subsekvens subsekvens = x.getValue();

            if (subsekvens.hentAntall() > storst) {
                storst = subsekvens.hentAntall();
                storstSubsekvens = subsekvens;
            }
        }
        
        return storstSubsekvens;
    }
}
