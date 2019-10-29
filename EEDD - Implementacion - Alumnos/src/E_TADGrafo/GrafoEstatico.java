package E_TADGrafo;

import Varios.Excepciones.TADLlenoException;
import Varios.Excepciones.NodoYaExisteException;
import java.util.NoSuchElementException;

public interface GrafoEstatico<E> extends Grafo<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">      
    public void InclNodo(E e)
            throws TADLlenoException, NoSuchElementException, NodoYaExisteException;
// </editor-fold>    

  
    
}
