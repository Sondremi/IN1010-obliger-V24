import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private Rute[][] ruter;
    private int antRader;
    private int antKolonner;

    public Labyrint(String fil) {
        Scanner leser = null;
        try {
            leser = new Scanner(new File(fil));
        } catch (FileNotFoundException e) {
            System.out.println("Filen finnes ikke: " + e.getMessage());
        }

        // Henter ut antall rader og kolonner fra første rad i filen
        String[] rad0Delt = leser.nextLine().split(" ");
        this.antRader = Integer.parseInt(rad0Delt[0]);
        this.antKolonner = Integer.parseInt(rad0Delt[1]);

        // Oppretter rutenettet
        ruter = new Rute[antRader][antKolonner];

        int naavaerendeRad = 0;

        while (leser.hasNextLine()) {
            String[] linjeDelt = leser.nextLine().split("");
            
            for (int i = 0; i < linjeDelt.length; i++) {
                // Sort rute
                if (linjeDelt[i].equals("#")) {
                    ruter[naavaerendeRad][i] = new SortRute(naavaerendeRad, i);
                // Hvit rute
                } else if (linjeDelt[i].equals(".")) {
                    // Sjekker om den hvite ruten er på en av endene, det er da en åpning
                    if (naavaerendeRad == 0 || naavaerendeRad == antRader-1 || i == 0 || i == antKolonner-1) {
                        ruter[naavaerendeRad][i] = new Aapning(naavaerendeRad, i);
                    } else {
                        ruter[naavaerendeRad][i] = new HvitRute(naavaerendeRad, i);
                    }
                } else {
                    System.out.println("Filen inneholder feil format.");
                }
            }
            naavaerendeRad++;
        }

        // Kobler sammen naboene
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                Rute rute = finnRute(i, j);
                rute.minLabyrint = this;
                Rute[] naboer = finnNaboer(rute);
                rute.naboer[0] = naboer[0];
                rute.naboer[1] = naboer[1];
                rute.naboer[2] = naboer[2];
                rute.naboer[3] = naboer[3];
            }
        }

    }
    public Rute finnRute(int rad, int kol) {
        if (rad < 0 || rad >= antRader || kol < 0 || kol >= antKolonner) {
            return null;
        } else {
            return ruter[rad][kol];
        }
    }

    public Rute[] finnNaboer(Rute rute) {
        Rute[] naboer = new Rute[4];
        naboer[0] = finnRute(rute.radNr - 1, rute.kolNr); // Nord
        naboer[1] = finnRute(rute.radNr + 1, rute.kolNr); // Syd
        naboer[2] = finnRute(rute.radNr, rute.kolNr - 1); // Vest
        naboer[3] = finnRute(rute.radNr, rute.kolNr + 1); // Øst
        return naboer;
    }

    public void finnUtveiFra(int rad, int kol) {
        Rute rute = null;
        if (rad >= 0 && rad < antRader && kol >= 0 && kol < antKolonner) {
            rute = finnRute(rad, kol);

            if (rute.toString().equals("#")) {
                System.out.println("Kan ikke starte i sort rute");
            } else {
                rute.finn(null);
            }
        } else {
            System.out.println("Ikke gyldige koordinater.");
        }
    }

    @Override
    public String toString() {
        String utskrift = "";

        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                utskrift += ruter[i][j];
            }
            utskrift += "\n";
        }
        return utskrift;
    }
}