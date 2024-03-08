public abstract class Lenkeliste<E> implements Liste<E> {
    protected Node<E> start;
    protected Node<E> slutt;
    protected int antall;
    
    @SuppressWarnings("hiding") // Quick Fix, pga gul strek under <E> ... vet ikke hva den skal bety
    protected class Node<E> {
        E data;
        Node<E> neste;

        public Node(E data) {
            this.data = data;
            this.neste = null;
        }
    }

    @Override
    public int stoerrelse() {
        return antall;
    }

    @Override
    public void leggTil(E x) {
        Node<E> nyNode = new Node<>(x);
        if (start == null) {
            start = slutt = nyNode;
        } else {
            slutt.neste = nyNode;
            slutt = nyNode;
        }
        antall++;
    }

    @Override
    public E hent() {
        if (start == null) {
            return null;
        }
        return start.data;
    }

    @Override
    public E fjern() {
        if (start == null) {
            throw new UgyldigListeindeks(-1);
        }
        E element = start.data;
        start = start.neste;
        if (start == null) {
            slutt = null;
        }
        antall--;
        return element;
    }

    @Override
    public String toString() {
        String setning = "";
        Node<E> node = start;
        while (node != null) {
            setning += node.data + " ";
            node = node.neste;
        }
        return setning;
    }

}