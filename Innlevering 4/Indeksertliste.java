class IndeksertListe<E> extends Lenkeliste<E> { 
    // Metoden legger til en ny node etter oppgitt index
    public void leggTil(int pos, E x) {
        // Sjekker om posisjonen oppgitt er gyldig
        if (gyldigIndeks(pos)) {
            Node nyNode = new Node(x);
            
            // nyNode skal inn forst : index 0
            if (pos == 0) {
                nyNode.neste = start;
                start = nyNode;

            } else {
                // Finner noden før index pos
                Node node = finnNode(pos);

                // Oppdaterer rekkefølgen på nodene i indeksertLenkeliste
                nyNode.neste = node.neste;
                node.neste = nyNode;
                nyNode.forrige = node;
            }

            // Øker teller for antall objekter i listen
            teller++;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    } 
    
    // Metoden bytter en node på index pos i indeksertLenkelisten med en ny
    public void sett(int pos, E x) {
        if (gyldigIndeks(pos)) {
            Node node = finnNode(pos);
            node.verdi = x;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }
    
    // Metoden henter verdien på Noden i posisjon pos
    public E hent(int pos) {
        if (gyldigIndeks(pos)) {
            Node node = finnNode(pos);
            return node.verdi;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }
    
    // Metoden fjerner Noden i posisjonen pos
    public E fjern(int pos) {
        if (gyldigIndeks(pos)) {
            Node node = finnNode(pos);

            if (node == start) {
                start = start.neste;
            } else {
                node.forrige.neste = node.neste;
            }

            // Minsker teller for antall objetker i listen
            teller--;

            // Returnerer nodens verdi
            return node.verdi;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }

    public boolean erTom() {
        return start == null;
    }

    // Metoden sjekker om posisjonen oppgitt er gyldig
    private boolean gyldigIndeks(int pos) {
        return 0 <= pos && pos < stoerrelse();
    }

    // Metoden henter noden fra posisjon oppgitt
    private Node finnNode(int pos) {
        Node node = start;
        for (int i = 0; i < pos; i++) {
            node = node.neste;
        }
        return node;
    }
}