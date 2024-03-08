public class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node<E> nyNode = new Node<>(x);
        
        if (start == null) {
            start = slutt = nyNode;
        } else if (x.compareTo(start.data) < 0) {
            nyNode.neste = start;
            start = nyNode;
        } else {
            Node<E> foran = start;
            while (foran.neste != null && x.compareTo(foran.neste.data) > 0) {
                foran = foran.neste;
            }
            nyNode.neste = foran.neste;
            foran.neste = nyNode;

            if (nyNode.neste == null) {
                slutt = nyNode;
            }
        }
        antall++;
    }

}
