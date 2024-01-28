public class Bilbruk3 {
    public static void main(String[] args) {
        Bil3 bil = new Bil3("AA12345");
        Person person = new Person(bil);

        person.skrivUt();
    }
}
