package E_TADGrafo;

import A_TADLista.Lista;
import java.util.NoSuchElementException;

public interface Grafo<E> {
    
// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">      
    public boolean EsVacio ();

    public void InclArco (E e1, E e2) throws NoSuchElementException;

    public boolean EstaContenido (E e);

    public void BorraNodo (E e) throws NoSuchElementException;

    public void BorraArco(E e1, E e2) throws NoSuchElementException;

    public boolean EsAdyacente(E e1, E e2) throws NoSuchElementException;

    public Object clone();

    public String toString();
        
    public boolean equals(Object o);
    
    public Lista<E> adyacentes(E x);
    
     public Lista<E> nodos();
// </editor-fold>	
    
}
