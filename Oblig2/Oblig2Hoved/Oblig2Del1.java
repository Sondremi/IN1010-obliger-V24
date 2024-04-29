import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Oblig2Del1 {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        String mappenavn = args[0]; // kjører programmet fra mappen Oblig2Del1 så mappenavnet må starte med ../
        ArrayList<String> filnavn = hentFilnavn(mappenavn);

        for (String fil : filnavn) {
            register.settInn(SubsekvensRegister.lesFil(mappenavn + "/" + fil));
        }

        while (register.hentAntall() >= 2) {
            register.settInn(register.slaaSammen(register.taUt(), register.taUt()));
        }

        int storst = 0;
        Subsekvens storstSubsekvens = null;

        // Går gjennom hashbeholderen og finner subsekvensen med størst antall
        HashMap<String, Subsekvens> flettetRegister = register.hentHashBeholder();
        for (Map.Entry<String, Subsekvens> x : flettetRegister.entrySet()) {
            Subsekvens subsekvens = x.getValue();

            if (subsekvens.hentAntall() > storst) {
                storst = subsekvens.hentAntall();
                storstSubsekvens = subsekvens;
            }
        }
        System.out.println(storstSubsekvens);
    }

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
}
