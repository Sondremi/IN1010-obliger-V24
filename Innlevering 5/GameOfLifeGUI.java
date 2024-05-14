import java.util.Scanner;

public class GameOfLifeGUI {
    public static void main(String[] args) {
        GUIUtsyn guiUtsyn = null;

        // Funksjonalitet for å kunne kjøre programmet både i intern og ekstern terminal
        if (args.length == 0) {
            Scanner s = new Scanner(System.in);
    
            System.out.print("Oppgi antall rader: ");
            int antRader = s.nextInt();
    
            System.out.print("Oppgi antall kolonner: ");
            int antKolonner = s.nextInt();
    
            s.close();
            guiUtsyn = new GUIUtsyn(antRader, antKolonner);

        } else if (args.length == 2) {
            guiUtsyn = new GUIUtsyn(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

        guiUtsyn.init();
    }
}