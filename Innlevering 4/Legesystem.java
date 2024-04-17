import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Legesystem {
    public static void main(String[] args) throws UlovligUtskrift, UlovligFormat {
        // Oppretter IndekserteLister for alle typene
        IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
        IndeksertListe<Lege> leger = new IndeksertListe<>();
        IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
        IndeksertListe<Resept> resepter = new IndeksertListe<>();

        // Leser inn data fra filen
        lesData(pasienter, leger, legemidler, resepter);

        // Kommandoløkke
        Scanner tastatur = new Scanner(System.in);
        int input = -1;
        
        while (input != 0) {
            if (input == 1) skrivUtFullstendigOversikt(pasienter, leger, legemidler, resepter);
            else if(input == 2) nyttElement(pasienter, leger, legemidler, resepter, tastatur);
            else if (input == 3) brukPasientResept(pasienter, tastatur);
            else if (input == 4) statistikk(pasienter, leger, legemidler, resepter, tastatur);
            else if (input == 5) skrivTilFil(pasienter, leger, legemidler, resepter);
            else {
                if (input != -1) {
                    System.out.println("Ugyldig input!!");
                }
            }

            System.out.println("\n--------------- MENY ---------------");
            System.out.println("1. Skriv ut fullstendig oversikt"); 
            System.out.println("2. Legg til nytt element"); 
            System.out.println("3. Bruk en pasients resept"); 
            System.out.println("4. Skriv ut statistikk"); 
            System.out.println("5. Skriv alle data til fil");
            System.out.println("0. Avslutt");
            System.out.print("> ");

            input = tastatur.nextInt();
        }
        tastatur.close();
    }

    // Metoden skriver ut en fullstendig oversikt
    public static void skrivUtFullstendigOversikt(IndeksertListe<Pasient> pasienter, IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler, 
    IndeksertListe<Resept> resepter){
        System.out.println("\nPasienter:");
        // Går gjennom alle pasienter i listen og skriver ut 
        for (Pasient pasient : pasienter) {
            System.out.println(pasient);
        }

        System.out.println("\nLegemidler:");
        // Går gjennom alle legemideler i listen og skriver ut
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel);
        }

        System.out.println("\nLeger:");
        // Skriver ut alle legene i listen
        for (Lege lege : leger) {
            System.out.println(lege);
        }

        System.out.println("\nResepter:");
        // Skriver ut alle respter i listen
        for (Resept resept : resepter) {
            System.out.println(resept);
        } 
    }

    // Metoden legger til et nytt element : pasient, lege, legemiddel eller resept
    public static void nyttElement(IndeksertListe<Pasient> pasienter, IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler,
     IndeksertListe<Resept> resepter, Scanner tastatur) throws UlovligFormat {
        System.out.println("\nHvilket nytt element vil du opprette? (Pasient, Lege, Legemiddel, Resept) ");
        System.out.print("> ");
        tastatur.nextLine();
        String valg = tastatur.nextLine().toLowerCase().strip();

        if (valg.equals("pasient")) {
            Pasient pasient = null;
            System.out.println("\nOppgi navn og personnummer på pasienten (fornavn etternavn, personnummer)");
            System.out.print("> ");
            String[] info = tastatur.nextLine().split(",");

            if (info.length == 2) {
                pasient = new Pasient(info[0], info[1].strip());
                pasienter.leggTil(pasient);
                System.out.println("\nPasient " + pasient.hentNavn() + " ble lagt til.");
            } else {
                throw new UlovligFormat();
            }
        } else if (valg.equals("lege")) {
            Lege lege = null;
            System.out.println("\nOppgi legens navn og eventuelt kontrollkode (navn, kontrollkode)");
            System.out.print("> ");
            String info = tastatur.nextLine();
            if (info.contains(",")) {
                String[] infodelt = info.split(",");
                if (infodelt.length == 2) {
                    lege = new Spesialist(infodelt[0], infodelt[1].strip());
                } else {
                    throw new UlovligFormat();
                }
            } else {
                lege = new Lege(info);
            }
            if (lege != null) {
                leger.leggTil(lege);
                System.out.println("\nLege " + lege.hentNavn() + " ble lagt til.");
            }

        } else if (valg.equals("legemiddel")) {
            Legemiddel legemiddel = null;
            System.out.println("\nHvilket type legemiddel vil du opprette? (vanlig, vanedannende, narkotisk)");
            System.out.print("> ");
            String type = tastatur.nextLine().toLowerCase().strip();

            // Oppretter vanlig legemiddel
            if (type.equals("vanlig")) {
                System.out.println("\nOppgi legemiddelets info slik: (navn, pris, virkestoff)");
                System.out.print("> ");
                String[] info = tastatur.nextLine().split(",");
                if (info.length == 3) {
                    legemiddel = new Vanlig(info[0], Integer.parseInt(info[1].strip()), Double.parseDouble(info[2].strip()));
                } else {
                    throw new UlovligFormat();
                }
            
            // Oppretter vanedannende eller narkotisk legemiddel
            } else if (type.equals("vanedannende") || type.equals("narkotisk")) {
                System.out.println("\nOppgi legemiddelets info slik: (navn, pris, virkestoff, styrke)");
                System.out.print("> ");
                String[] info = tastatur.nextLine().split(",");
                if (info.length == 4) {
                    if (type.equals("vanedannende")) {
                        legemiddel = new Vanedannende(info[0], Integer.parseInt(info[1].strip()), Double.parseDouble(info[2].strip()), Integer.parseInt(info[3].strip()));
                    } else {
                        legemiddel = new Narkotisk(info[0], Integer.parseInt(info[1].strip()), Double.parseDouble(info[2].strip()), Integer.parseInt(info[3].strip()));
                    }
                } else {
                    throw new UlovligFormat();
                }
            } else {
                throw new UlovligFormat();
            }

            if (legemiddel != null) {
                legemidler.leggTil(legemiddel);
                System.out.println("\nLegemiddel " + legemiddel.hentNavn() + " ble lagt til.");
            }

        } else if (valg.equals("resept")) {
            Resept resept = null;
            // Bruker oppgir type resept som skal opprettes
            System.out.println("\nHvilken type resept vil du legge til? (hvit, blaa, p, mil)");
            System.out.print("> ");
            String type = tastatur.nextLine().toLowerCase().strip();

            // Kontrollerer ugyldig input
            if (!(type.equals("hvit") || type.equals("blaa") || type.equals("p") || type.equals("mil"))) {
                throw new UlovligFormat();
            }

            // Bruker oppgir navnet på legen som skal skrive resepten
            System.out.println("\nHvilken lege skal skrive ut resepten?");
            // Skriver ut tilgjengelige leger
            for (Lege lege : leger) {
                System.out.println(lege.hentNavn());
            }
            System.out.print("> ");
            String legeNavn = tastatur.nextLine();
            Lege lege = finnLege(legeNavn, leger);

            // Kontrollerer ugyldig input
            if (lege == null) {
                throw new UlovligFormat();
            }

            System.out.println("\nOppgi pasientens ID");
            // Skriver ut pasienters ID og navn : bruker oppgir ID
            for (Pasient pasient : pasienter) {
                System.out.println(pasient.hentId() + " " + pasient.hentNavn());
            }
            System.out.print("> ");
            int pasientID = tastatur.nextInt();
            Pasient pasient = finnPasient(pasientID, pasienter);

            // Kontrollerer ugyldig input
            if (pasient == null) {
                throw new UlovligFormat();
            }

            System.out.println("\nOppgi legemiddelets ID");
            // Skriver ut legemiddelenes ID og navn : bruker oppgir ID
            for (Legemiddel legemiddel : legemidler) {
                System.out.println(legemiddel.hentID() + " " + legemiddel.hentNavn());
            }
            System.out.print("> ");
            int legemiddelID = tastatur.nextInt();
            Legemiddel legemiddel = finnLegemiddel(legemiddelID, legemidler);

            // Kontrollerer ugyldig input
            if (legemiddel == null) {
                throw new UlovligFormat();
            }

            if (type.equals("mil")) {
                resept = new MilResept(legemiddel, lege, pasient);
            } else {
                System.out.println("\nOppgi reseptens antall reit");
                System.out.print("> ");
                int reit = tastatur.nextInt();

                // Oppretter riktig resept
                if (type.equals("hvit")) {
                    resept = new HvitResept(legemiddel, lege, pasient, reit);
                } else if (type.equals("blaa")) {
                    resept = new BlaaResept(legemiddel, lege, pasient, reit);
                } else if (type.equals("p")) {
                    resept = new PResept(legemiddel, lege, pasient, reit);
                } else {
                    System.out.println("Ikke gyldig type.");
                }
            }

            if (resept != null) {
                resepter.leggTil(resept);
                System.out.println("\nResept lagt til.");
            }
        } else {
            System.out.println("\nUgyldig resepttype.");
        }
    }

    // Metode for å bruke resepter til pasienter
    public static void brukPasientResept(IndeksertListe<Pasient> pasienter, Scanner valg){
        System.out.println("\nHvilken pasient vil du se resepter for?");
        int storstID = 0;
        // Skriver ut id, navn og fødselsnummer til hver pasient under hverandre
        for (Pasient pasient : pasienter){
            System.out.println(pasient.hentId() + ": " + pasient.hentNavn() + "(fnr: " + pasient.hentFnr() + ")");
            if (pasient.hentId() > storstID) {
                storstID = pasient.hentId();
            }
        }
        // Variabel for pasitenten vi vil ta ut resept for
        Pasient valgtPasient = null;
        System.out.print("> ");
        // Tar inn ent int id for pasienten vil vil velge
        int id = valg.nextInt(); 
        if (id >= 0 && id <= storstID) {
            // Går gjennom pasientene og sjekker om valgt id er en pasient sin id
            for (Pasient pasient : pasienter){
                if (id == pasient.hentId()){
                    // Hvis id er lik pasienten sin id blir variablen valgtPasient denne pasienten
                    valgtPasient = pasient;
                    // Siden vi har funnet pasienten bryter vi ut av løkken
                    break; 
                }
            }
            System.out.println("\nHvilken resept vil du bruke? ");
            // Henter listen med resptene til den valgte pasienten
            Lenkeliste<Resept> pasientResepter = valgtPasient.hentResepter();
            // Går gjennom listen med resepter og skriver ut passende informasjon
            for (Resept resept : pasientResepter){
                System.out.println(resept.hentId() + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
            }
            System.out.print("> ");
            // Gir brukeren muligheten til å velge resept ved å taste inn resepten sin id
            int reseptId = valg.nextInt();
            // Går gjennom reseptene på nytt
            for (Resept resept : pasientResepter){
                // hvis valgt id er lik en resept sin id brukes resepten og det printes ut passende info
                if (reseptId == resept.hentId()){
                    if (resept.bruk() == true){
                        System.out.println("\nBrukte resept paa " + resept.hentLegemiddel().hentNavn() + ". Antall gjenværende reit: " + resept.hentReit());
                    }
                    // hvis pasienten ikke kan ta ut fler respeter 
                    else{
                        System.out.println("\nKunne ikke bruke resept paa " + resept.hentLegemiddel() + " (ingen gjenværende reit)");
                    }
                }
                else{
                    throw new UgyldigListeindeks(reseptId);
                }
            }
        }
        // Hvis valgt id ikke er gyldig 
        else{
            throw new UgyldigListeindeks(id);
        }
    }

    // Metoden printer ut statistikk om narkotiske og vanedannende legemidler etc
    public static void statistikk(IndeksertListe<Pasient> pasienter, IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler, 
        IndeksertListe<Resept> resepter, Scanner scanner) {
        int antVane = 0, antNark = 0;

        // Meny for statestikk()
        System.out.println("\n----------Statistikk-----------");
        System.out.println("1: Totalt antall utskrevne vanedannende legemidler");
        System.out.println("2: Totalt antall utskrevne narkotiske legemidler");
        System.out.println("3: Statisikk om mulig missbruk av narkotika");

        System.out.print("> ");
        int input = scanner.nextInt();

        // Printer ut totalt antall utskrevne resepter på vanedannende legemidler
        if (input == 1) {
            for (Resept res : resepter) {
                if (res.hentLegemiddel() instanceof Vanedannende) antVane++;
            }
            System.out.println("\nResepter på vanedannede legemidler: " + antVane);
        } 
        // Skriver ut totalt antall utskrevene resepter på narkotiske legemidler
        else if (input == 2) {
            for (Resept res : resepter) {
                if (res.hentLegemiddel() instanceof Narkotisk) antNark++;
            }
            System.out.println("\nResepter på narkotiske legemidler: " + antNark);
        } 
        // Skriver ut mistenkt missbruk av narkotiske legemidler
        else if (input == 3) {
            IndeksertListe<Lege> alfabetisk = new IndeksertListe<>();
            System.out.println();

            // Legger leger som har skrevet resept med narkotiske legemiddler i en egen alfabetisk liste
            for (Lege lege : leger) {
                for (Resept res : lege.hentUtskrevneResepter()) {
                    if (res.hentLegemiddel() instanceof Narkotisk) {
                        if (alfabetisk.erTom()) {
                            alfabetisk.leggTil(lege);
                        }
                        int pos = 0;
                        while (alfabetisk.hent(pos).compareTo(lege) < 0) {
                            pos++;
                        }
                        alfabetisk.leggTil(pos, lege);

                    }
                }
            }
            // Lister opp navnene på alle leger som har skrevet ut minst en resept på narkotiske legemidler og antallet per lege.
            for (Lege lege : alfabetisk) {
                int ant = 0;
                for (Resept res : lege.hentUtskrevneResepter()) {
                    if (res.hentLegemiddel() instanceof Narkotisk) {
                        ant++;
                    }
                }
                System.out.println(lege.hentNavn() + " har skrevet ut " + ant + " narkotiske legemidler");
            }


            // Finner navnene på alle pasienter som har misnt en gyldig resept på narkotiske legemidler og, for disse skriver ut antallet per pasient.
            for (Pasient pas : pasienter) {
                int ant = 0;
                for (Resept res : pas.hentResepter()) {
                    if (res.hentLegemiddel() instanceof Narkotisk) {
                        ant++;
                    }
                }
                if (ant > 0) {
                    System.out.println(pas.hentNavn() + " har " + ant + " gyldige narkotiske legemidler");
                }
            }
        }
    }

    // Metoden skriver data inn i NyLegedata, dette blir den oppdaterte versjonen av filen som leses inn.
    public static void skrivTilFil(IndeksertListe<Pasient> pasienter, IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler, 
    IndeksertListe<Resept> resepter) {
        PrintWriter skriver = null;
        try {
            skriver = new PrintWriter("NyLegedata.txt");
            // Skriver pasienter inn i filen på samme måte som filen det ble lest inn fra
            skriver.println("# Pasienter (navn, fnr)");
            for (Pasient pas : pasienter) {
                skriver.println(pas.hentNavn() + "," + pas.hentFnr());
            }

            // Skriver legemidler inn i filen på samme måte som filen det ble lest inn fra
            skriver.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for (Legemiddel legem : legemidler) {
                if (legem instanceof Vanlig) {
                    skriver.println(legem.hentNavn() + "," + "vanlig" + "," + legem.hentPris() + "," + legem.hentVirkestoff());
                }
                else if (legem instanceof Narkotisk) {
                    Narkotisk nark = (Narkotisk) legem;
                    skriver.println(legem.hentNavn() + "," + "narkotisk" + "," + legem.hentPris() + "," + legem.hentVirkestoff() + "," + nark.hentStyrke());
                }
                else {
                    Vanedannende vane = (Vanedannende) legem;
                    skriver.println(legem.hentNavn() + "," + "vanedannende" + "," + legem.hentPris() + "," + legem.hentVirkestoff() + "," + vane.hentStyrke());
                }
            }

            // Skriver leger inn i filen på samme måte som filen det ble lest inn fra
            skriver.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for (Lege lege : leger) {
                if (lege instanceof Spesialist) {
                    Spesialist spes = (Spesialist) lege;
                    skriver.println(lege.hentNavn() + "," + spes.hentKontrollkode());
                } else {
                    skriver.println(lege.hentNavn() + "," + "0");
                }
            }

            // Skriver resepter inn i filen på samme måte som filen det ble lest inn fra
            skriver.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for (Resept res : resepter) {
                if (res instanceof MilResept) {
                    skriver.println(res.hentId() + "," + res.hentLege().hentNavn() + "," + res.hentPasientId() + "," + "militaer");
                }
                else if (res instanceof HvitResept) {
                    skriver.println(res.hentId() + "," + res.hentLege().hentNavn() + "," + res.hentPasientId() + "," + "hvit" + "," + res.hentReit());
                }
                else if (res instanceof BlaaResept) {
                    skriver.println(res.hentId() + "," + res.hentLege().hentNavn() + "," + res.hentPasientId() + "," + "blaa" + "," + res.hentReit());
                }
                else {
                    skriver.println(res.hentId() + "," + res.hentLege().hentNavn() + "," + res.hentPasientId() + "," + "p" + "," + res.hentReit());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("");
        }
        skriver.close();
        System.out.println("\nSkrev til fil.");
    }

    // Metoden leser inn data fra filen og oppdaterer IndeksertLenkelistene
    public static void lesData(IndeksertListe<Pasient> pasienter, IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler, 
    IndeksertListe<Resept> resepter) throws UlovligUtskrift {
        // Prøver å opprette en Scanner for å lese filen
        try (Scanner leser = new Scanner(new File("legedata.txt"))) {
            String datatype = null;

            // Itererer til vi er igjennom filen
            while (leser.hasNext()) {
                // Lagrer neste linje fra filen som en string
                String linje = leser.nextLine();
                
                // Sjekker om linjen inneholder # og da en type
                if (linje.startsWith("#")) {
                    // Lagrer typen
                    datatype = linje.toLowerCase().split(" ")[1];
                } else {
                    // Sjekker hvilken type som skal legges til
                    if (datatype.equals("pasienter")) {
                        lesPasient(linje, pasienter);
                    } else if (datatype.equals("leger")) {
                        lesLege(linje, leger);
                    } else if (datatype.equals("legemidler")) {
                        lesLegemiddel(linje, legemidler);
                    } else if (datatype.equals("resepter")) { 
                        lesResept(linje, resepter, pasienter, leger, legemidler);
                    }
                }
            }
        // Catcher om filen ikke finnes
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " | Ugyldig fil!");
        }
    }

    // Metoden legger en pasient til i IndeksertListe med pasienter
    private static void lesPasient(String linje, IndeksertListe<Pasient> pasienter) {
        String[] deler = linje.split(",");
        Pasient pasient = new Pasient(deler[0], deler[1]);
        pasienter.leggTil(pasient);
    }

    // Metoden legger en lege til i IndeksertListe med leger
    private static void lesLege(String linje, IndeksertListe<Lege> leger) {
        String[] deler = linje.split(",");
        // Hvis kontrollkoden er 0 er det ikke en spesialist
        if (deler[1].strip().equals("0")) {
            Lege lege = new Lege(deler[0]);
            leger.leggTil(lege);
        } else {
            Spesialist lege = new Spesialist(deler[0], deler[1]);
            leger.leggTil(lege);
        }
    }

    // Metoden legger et legemiddel til i IndeksertListe med legemidler
    private static void lesLegemiddel(String linje, IndeksertListe<Legemiddel> legemidler) {
        String[] deler = linje.split(",");
        // Lagrer typen legemiddel
        String type = deler[1].toLowerCase().strip();

        // Lagrer legemidelets navn, pris og virkestoff
        int pris = Integer.parseInt(deler[2].strip());
        double virkestoff = Double.parseDouble(deler[3].strip());
        String navn = deler[0];

        // Oppretter og legger til riktig type legemiddel i indeksertLenkeliste
        if (type.equals("vanlig")) {
            Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
            legemidler.leggTil(vanlig);
        } else if (type.equals("vanedannende")) {
            // Lagrer legemidelets styrke
            int styrke = Integer.parseInt(deler[4].strip());
            Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
            legemidler.leggTil(vanedannende);
        } else if (type.equals("narkotisk")) {
            int styrke = Integer.parseInt(deler[4].strip());
            Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
            legemidler.leggTil(narkotisk);
        }
    }
    
    // Metoden legger en resept til i IndeksertListe med resepter
    public static void lesResept(String linje, IndeksertListe<Resept> resepter, IndeksertListe<Pasient> pasienter, 
    IndeksertListe<Lege> leger, IndeksertListe<Legemiddel> legemidler) throws UlovligUtskrift {
        // Deler opp linjen
        String[] deler = linje.split(",");
                        
        // Henter typen resept
        String type = deler[3].toLowerCase().strip();

        // Kaller på metoder for å hente ut variabler som skal brukes som parametere når resepter opprettes
        // Hvis resepten ikke oppgir reit er det en militaer resept som har 3 reit
        int reit = 3;
        if (deler.length == 5) {
            reit = Integer.parseInt(deler[4].strip());
        } 
        Lege lege = finnLege(deler[1], leger);
        Pasient pasient = finnPasient(Integer.parseInt(deler[2]), pasienter);
        Legemiddel legemiddel = finnLegemiddel(Integer.parseInt(deler[0]), legemidler);

        // Oppretter et resept-objekt og setter det til null
        Resept resept = null;
        // Oppretter en resept av riktig type;
        if (type.equals("hvit")) {
            resept = lege.skrivHvitResept(legemiddel, pasient, reit);
        } else if (type.equals("blaa")) {
            resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
        } else if (type.equals("p")) {
            resept = lege.skrivPResept(legemiddel, pasient, reit);
        } else if (type.equals("militaer")) {
            resept = lege.skrivMilResept(legemiddel, pasient);
        }

        // Hvis riktig resept har blitt opprettet : legger resepten i IndeksertListe med resepter og resept blir lagt til pasient
        if (resept != null) {
            resepter.leggTil(resept);
            pasient.leggTilResept(resept);
        }
    }

    // Metoden tar inn et navn og en liste med leger
    // Returnerer lege-objekt som passer navnet
    private static Lege finnLege(String navn, IndeksertListe<Lege> leger) {
        for (Lege lege : leger) {
            if (lege.hentNavn().equals(navn)) {
                return lege;
            }
        }
        return null;
    }

    // Metoden tar inn en pasientID og en liste med pasienter
    // Returnerer pasient-objekt om ID'ene passer
    private static Pasient finnPasient(int pasientID, IndeksertListe<Pasient> pasienter) {
        for (Pasient pasient : pasienter) {
            if (pasient.hentId() == pasientID) {
                return pasient;
            }
        }
        return null;
    }

    // Metoden tar inn et legemiddelID og en liste med legemidler
    // Returnerer legemiddel-objekt om ID'ene passer
    private static Legemiddel finnLegemiddel(int legemiddelID, IndeksertListe<Legemiddel> legemidler) {
        for (Legemiddel legemiddel : legemidler) {
            if (legemiddel.hentID() == legemiddelID) {
                return legemiddel;
            }
        }
        return null;
    }
}
