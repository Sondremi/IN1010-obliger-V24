class IndeksertListe <E> extends Lenkeliste<E> { 
    public void leggTil(int pos, E x) {
        if (0 <= pos && pos <= stoerrelse()) {
            Node<E> nyNode = new Node<>(x);
            
            if (pos == 0) {
                nyNode.neste = start;
                start = nyNode;
                if (slutt == null) {
                    slutt = nyNode;
                }
            } else {
                Node<E> foran = start;
                for (int i = 1; i < pos; i++) {
                    foran = foran.neste;
                }
                nyNode.neste = foran.neste;
                foran.neste = nyNode;
                
                if (pos == stoerrelse()) {
                    slutt = nyNode;
                }
            }
            antall++;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    } 
    
    public void sett(int pos, E x) {
        if (0 <= pos && pos < stoerrelse()) {
            Node<E> node = start;
            for (int i = 0; i < pos; i++) {
                node = node.neste;
            }
            node.data = x;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }
    
    public E hent(int pos) {
        if (0 <= pos && pos < stoerrelse()) {
            Node<E> node = start;
            for (int i = 0; i < pos; i++) {
                node = node.neste;
            }
            return node.data;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }
    
    public E fjern(int pos) {
        if (0 <= pos && pos < stoerrelse()) {
            Node<E> foran = null;
            Node<E> node = start;
            for (int i = 0; i < pos; i++) {
                foran = node;
                node = node.neste;
            }

            if (foran == null) {
                start = start.neste;
                if (start == null) {
                    slutt = null;
                }
            } else {
                foran.neste = node.neste;
                if (node == slutt) {
                    slutt = foran;
                }
            }
            antall--;
            return node.data;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }
}