public class Stabel <E> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node<E> nyNode = new Node<>(x);
        if (start == null) {
            start = slutt = nyNode;
        } else {
            nyNode.neste = start;
            start = nyNode;
        }
        antall++;
    }
}
