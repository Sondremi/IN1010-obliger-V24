import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Oblig2Hele {
    public static void main(String[] args) {
        // Tar tiden på programmet
        final long startTime = System.nanoTime();

        // Bare skriv inn navnet på mappen som arg
        // legg til ../ før mappenavnet hvis koden kjørers direkte fra Oblig2Del2B mappen
        String mappenavn = args[0]; 

        // Kaller hjelpemetoden for å hente ut alle filnavnene
        ArrayList<String> filnavn = hentFilnavn(mappenavn); 

        // Hadde problemer med at de to monitorene ikke hadde hver sin beholder, men at de delte 1 felles beholder
        // Så endte med å først opprette SubsekvensRegister også bruker den som parameter når jeg oppretter Monitor
        SubsekvensRegister subRegisterFriskt = new SubsekvensRegister();
        SubsekvensRegister subRegisterSykt = new SubsekvensRegister();
        Monitor2 frisktRegister = new Monitor2(subRegisterFriskt);
        Monitor2 syktRegister = new Monitor2(subRegisterSykt);

        int antallFiler = filnavn.size();
        int antTraader = 8;
  
        // CountDownLatch venter med å gå videre før alle tråder er ferdige
        CountDownLatch leseBarriere = new CountDownLatch(antallFiler);
        
        // Oppretter tråder for å lese alle filene
        for (String filnavnet : filnavn) {
            LeseTrad lesetraad;
            if (filnavnet.split(",").length == 2) {
                String[] delt = filnavnet.split(",");
                if (delt[1].equals("True")) {
                    lesetraad = new LeseTrad(syktRegister, (mappenavn + "/" + delt[0]), leseBarriere);
                } else {
                    lesetraad = new LeseTrad(frisktRegister, (mappenavn + "/" + delt[0]), leseBarriere);
                }
                new Thread(lesetraad).start();
            } else {
                System.err.println("Filen mangler True/False");
            }
        }

        // Venter til alle lesetrådene er ferdige
        try {
            leseBarriere.await();
        } catch (InterruptedException e) {
            System.err.println("Main : lesetraad feil");
            System.exit(1);
        }

        System.out.println("Lesing fullført");
        

        // CountDownLatch venter med å gå videre før alle tråder er ferdige
        CountDownLatch fletteBarriereFrisk = new CountDownLatch(frisktRegister.hentAntall() - 1); // -1 pga index
        CountDownLatch fletteBarriereSyk = new CountDownLatch(syktRegister.hentAntall() - 1);

        // Oppretter tråder for å flette friske subsekvensene
        for (int i = 0; i < antTraader; i++) {
            FletteTraad flettetraad = new FletteTraad(frisktRegister, fletteBarriereFrisk);
            new Thread(flettetraad).start();
        }

        // Venter til alle flettetrådene er ferdige
        try {
            fletteBarriereFrisk.await();
        } catch (InterruptedException e) {
            System.err.println("Main : flettetraad feil");
            System.exit(1);
        }
        
        System.out.println("Flettet friske fullført");

        // Oppretter tråder for å flette syke subsekvensene
        for (int i = 0; i < antTraader; i++) {
            FletteTraad flettetraad = new FletteTraad(syktRegister, fletteBarriereSyk);
            new Thread(flettetraad).start();
        }
        
        // Venter til alle flettetrådene er ferdige
        try {
            fletteBarriereSyk.await();
        } catch (InterruptedException e) {
            System.err.println("Main : flettetraad feil");
            System.exit(1);
        }

        System.out.println("Flettet syke fullført");


        // Går gjennom de syke subsekvensene og sammenligner med de friske
        HashMap<String, Subsekvens> friskeSubsekvenser = frisktRegister.hentHashBeholder();
        HashMap<String, Subsekvens> sykeSubsekvenser = syktRegister.hentHashBeholder();
        Subsekvens forekommerOftest = null;
        int antallForskjell = 0;

        for (String sykSub : sykeSubsekvenser.keySet()) {
            int sykSubAntall = sykeSubsekvenser.get(sykSub).hentAntall();
            if (friskeSubsekvenser.containsKey(sykSub)) {
                int friskSubAntall = friskeSubsekvenser.get(sykSub).hentAntall();
                int forskjell = sykSubAntall - friskSubAntall;
                
                if (sykSubAntall > friskSubAntall && forskjell > antallForskjell) {
                    forekommerOftest = sykeSubsekvenser.get(sykSub);
                    antallForskjell = forskjell;
                } 
            // Hvis subsekvensen ikke finnes i frisk register, forekommer den 0 ganger der
            } else {
                if (sykSubAntall > antallForskjell) {
                    forekommerOftest = sykeSubsekvenser.get(sykSub);
                    antallForskjell = sykSubAntall;
                }
            }
        }

        System.out.println("Subsekvensen som forekom flest ganger var: " + forekommerOftest + 
            " med " + antallForskjell + " flere forekomster");


        // Skriver ut subsekvensene som forekommer minst 7 ganger fler hos de som har vært syke
        System.out.println("\nDominante subsekvenser (forekommer minst 7 ganger mer hos de som har vært syke):");
        for (String sykSub : sykeSubsekvenser.keySet()) {
            int sykSubAntall = sykeSubsekvenser.get(sykSub).hentAntall();
            if (friskeSubsekvenser.containsKey(sykSub)) {
                int friskSubAntall = friskeSubsekvenser.get(sykSub).hentAntall();
                
                if (sykSubAntall - friskSubAntall >= 7) {
                    System.out.println(sykeSubsekvenser.get(sykSub));
                }
            } else {
                if (sykSubAntall >= 7) {
                    System.out.println(sykeSubsekvenser.get(sykSub));
                }
            }
        }

        // Skriver ut tiden programmet har brukt
        final long duration = System.nanoTime() - startTime;
        float durationSec = duration / 1000000000;
        System.out.println("Sekunder brukt: " + durationSec);
    }

    
    // Hjelpemetode for å lage en ArrayList som inneholder alle filnavnene fra metadata.csv
    public static ArrayList<String> hentFilnavn(String mappenavn) {
        ArrayList<String> filnavn = new ArrayList<>();
        Scanner leser = null;
        try {
            leser = new Scanner(new File(mappenavn + "/metadata.csv"));
            while (leser.hasNextLine()) {
                String linje = leser.nextLine();
                // Sjekker om det er lagt til ,True eller ,False i filen
                if (linje.split(",").length == 2) {
                    String[] delt = linje.split(",");
                    filnavn.add(delt[0] + "," + delt[1]);
                } else {
                    filnavn.add(leser.nextLine());
                }
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
