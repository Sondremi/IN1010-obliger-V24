abstract class Rute {
    protected int radNr;
    protected int kolNr;
    protected Labyrint minLabyrint; // Holder referanse til hvilken labyrint ruten hører til
    protected Rute[] naboer = new Rute[4];
    protected boolean markert; // Markerer en rute hvis veien har gått der for å unngå loops

    public void finn(Rute fra) {}
}
