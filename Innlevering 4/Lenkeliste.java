import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lenkeliste<E> implements Liste<E> {
    protected Node start;
    protected int teller;

    class Node {
        protected E verdi;
        protected Node neste;
        protected Node forrige;
    
        public Node(E verdi) {
            this.verdi = verdi;
        } 
    }

    // Iterator : kan iterere gjennom lenkelisten
    @Override 
    public Iterator<E> iterator() {
        return new LenkeListeIterator();
    }

    protected class LenkeListeIterator implements Iterator<E> {
        Node noden = start;

        @Override
        public boolean hasNext() {
            return noden != null;
        }

        @Override 
        public E next() {
            if (noden == null) {
                throw new NoSuchElementException("next");
            }
            E returVerdi = noden.verdi;
            noden = noden.neste;
            return returVerdi;
        }
    }

    // Metoden legger til en ny Node fremst i lenkelisten
    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        if (start == null) {
            start = nyNode;
        } else {
            Node node = start;
            while (node.neste != null) {
                node = node.neste;
            }
            node.neste = nyNode;
            nyNode.forrige = node;
        }

        // Øker teller for antall objekter i listen
        teller++;
    }
    
    // Metoden returnerer lenkelistens størrelse
    @Override
    public int stoerrelse() {
        return teller;
    }

    // Metoden returnerer det første objektet i listen
    @Override
    public E hent() {
        return start.verdi;
    }

    // Metoden fjerner og returnerer det første objektet i listen
    @Override
    public E fjern() {
        Node tmp = start;
        start.neste = start;
        return tmp.verdi;
    }
}
