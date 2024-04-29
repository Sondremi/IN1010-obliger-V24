import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class SubsekvensRegister {
    private ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    // Metoden setter inn en Hashmap med subsekvenser bakerst i beholderen
    public void settInn(HashMap<String, Subsekvens> ny) {
        try {
            hashBeholder.add(ny);
        } catch (NullPointerException e) {
            System.out.println("SubsekvensRegister feil : settInn");
        }
    }

    // Metoden fjerner og returnerer første HashMap i beholderen
    public HashMap<String, Subsekvens> taUt() { 
        try {
            return hashBeholder.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("SubsekvensRegister feil : taUt");
            return null;
        }
    }

    // Metoden returnerer antall HashMap i beholderen
    public int hentAntall() {return hashBeholder.size();}

    // Hjelpemetode for å returnere hashbeholderen
    public HashMap<String, Subsekvens> hentHashBeholder() {return hashBeholder.get(0);}

    // Metoden leser én fil med én persons immunrepertoar og lager en HashMap av subsekvensene i filen
    // Returnerer en referanse til den nye HashMap-en
    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
        HashMap<String, Subsekvens> hashSub = new HashMap<>();
        Scanner leser = null;
        try {
            leser = new Scanner(new File(filnavn));

            while (leser.hasNextLine()) {
                String linje = leser.nextLine();
                int subTeller = 0;
                String sub = "";

                while (linje.length() >= 3) {
                    String[] linjeDelt = linje.split("");
                    for (String l : linjeDelt) {
                        if (subTeller < 3) {
                            sub += l;
                            subTeller++;
                        } else {
                            hashSub.putIfAbsent(sub, new Subsekvens(sub));
                            sub = "";
                            subTeller = 0;
                            linje = linje.substring(1);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Finner ikke stien.");
        }
        return hashSub;
    }

    // Metoden tar inn to HashMap, fletter dem sammen og returnerer en flettet HashMap
    public HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> hm1, HashMap<String, Subsekvens> hm2) {
        try {
            HashMap<String, Subsekvens> flettet = new HashMap<>(hm1);
            
            for (Map.Entry<String, Subsekvens> entry : hm2.entrySet()) {
                String sub = entry.getKey();
                Subsekvens subsekvens = entry.getValue();
                
                if (flettet.containsKey(sub)) {
                    flettet.get(sub).oppdaterAntall(subsekvens.hentAntall());
                } else {
                    flettet.putIfAbsent(sub, subsekvens);
                }
            }
            return flettet;
        } catch (NullPointerException e) {
            System.err.println("SubsekvensRegister feil : slaaSammen");
            System.exit(1);
            return null;
        }
    }

}