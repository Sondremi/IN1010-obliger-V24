import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Oppgi antall rader: ");
        int antRader = s.nextInt();

        System.out.println("Oppgi antall kolonner: ");
        int antKolonner = s.nextInt();

        // Fjerner newline-karakteren (\n) fra inputbufferen
        s.nextLine();

        Verden verden = new Verden(antRader, antKolonner);

        while (true) {
            verden.oppdatering();
            verden.tegn();

            System.out.println("Ønsker du å fortsette? (j/n): ");
            String valg = s.nextLine();
            if (valg.equals("n")) {
                break;
            }
        }
        s.close();
    }
}