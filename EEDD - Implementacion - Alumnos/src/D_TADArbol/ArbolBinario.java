package D_TADArbol;

import Varios.Excepciones.TADVacioException;

public interface ArbolBinario<E> extends Arbol<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">      
    /**
     *
     * Devuelve el subárbol izquierdo del árbol binario
     *
     * @return ArbolBinario que es el subárbol izquierdo del árbol
     * @exception TADVacioException
     * @see TADVacioException
     */
    public ArbolBinario<E> SubArbolIzqdo() throws TADVacioException;

    /**
     *
     * Devuelve el subárbol derecho del árbol binario
     *
     * @return ArbolBinario que es el subárbol derecho del árbol
     * @exception TADVacioException
     */
    public ArbolBinario<E> SubArbolDcho() throws TADVacioException;
// </editor-fold> 
    
}