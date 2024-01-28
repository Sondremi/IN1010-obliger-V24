public class Person {
    private Bil3 personbil;

    public Person(Bil3 personbil) {
        this.personbil = personbil;

    }

    public void skrivUt() {
        System.out.println("Personen har en bil med nummer: " + personbil.hentNr());
    }
}
