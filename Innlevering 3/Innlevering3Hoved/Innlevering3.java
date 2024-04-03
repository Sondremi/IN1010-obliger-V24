import java.util.Scanner;

public class Innlevering3 {
    public static void main(String[] args) {
        Labyrint labyrint = new Labyrint(args[0]);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Slik ser labyrinten ut:");
        System.out.println(labyrint);

        String inp = "";
        
        while (!inp.equals("-1")) {
            System.out.println("\nSkriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            
            inp = scanner.nextLine();
            String[] koordinater = inp.split(" ");

            if (koordinater.length == 2) {
                int rad = Integer.parseInt(koordinater[0]);
                int kol = Integer.parseInt(koordinater[1]);

                System.out.println("Aapninger: ");
                labyrint.finnUtveiFra(rad, kol);
            } else if (!inp.contains("-1")) {
                System.out.println("Ikke gyldige koordinater.");
                // throw new IndexOutOfBoundsException(); ønsker ikke at programmet skal stoppe så sender heller ut en melding 
            }
        }
        scanner.close();
    }
}
