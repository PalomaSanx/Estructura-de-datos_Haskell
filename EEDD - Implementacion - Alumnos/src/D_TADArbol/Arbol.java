package D_TADArbol;

import Varios.Excepciones.TADVacioException;

/**
 * Interfaz general de árboles.
 *
 * @param <E>
 */
public interface Arbol<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">      
    /**
     *
     * Devuelve true si el árbol no contiene nodos.
     *
     * @return boolean true si el árbol es vacío
     */
    public boolean EsVacio();

    /**
     *
     * Devuelve el Elemento contenido en el nodo raíz del árbol. En caso de que
     * no exista tal elemento por estar vacío el árbol, se lanza la excepción
     * apropiada
     *
     * @return Elemento en la raíz del árbol
     * @exception TADVacioException
     */
    public E Raiz() throws TADVacioException;

    /**
     *
     * Obtiene un duplicado del árbol, sin clonar los elementos.
     *
     * @return un Object que es un clon del árbol
     */
    public Object clone();

    /**
     *
     * Pasa el árbol a formato String.
     *
     * @return un String con la información del árbol
     */
    public String toString();

    /**
     *
     * Compara con otro objeto.
     *
     * @param o Objeto con el que se compara.
     * @return true si el objeto es un árbol y coincide con this elemento a
     * elemento
     */
    public boolean equals(Object o);
// </editor-fold> 
    
}