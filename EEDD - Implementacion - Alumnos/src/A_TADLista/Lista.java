package A_TADLista;

import Varios.Excepciones.TADVacioException;
import java.util.Comparator;

public interface Lista<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">       
    /**
     *
     * Consulta si la lista es vacia
     *
     * @return true si la lista no contiene elementos
     *
     */
    public boolean EsVacia();

    /**
     *
     * A?ade un elemento a la lista por la cabeza
     *
     * @param e Elemento que se a?ade a la lista
     *     
*/
    public void Añade(E e);

    /**
     * A?ade un elemento a la lista en la posici?n n, contando desde la cabeza a
     * partir de 0. Si la posici?n no existe, la lista queda inalterada.
     *
     * @param e Elemento que se a?ade a la lista
     * @param n Posici?n en la que queda
     *     
*/
    public void Añade(E e, int n);

    /**
     * Elimina la primera aparici?n del elemento e de la lista
     *
     * @param e Elemento a eliminar
     * @return true si existe el elemento y se ha eliminado
     */
    public boolean Elimina(E e);

    /**
     * Elimina el elemento en la posici?n n. Si la posici?n no existe, la lista
     * no se modifica
     *
     * @param n posici?n del elemento a eliminar
     * @return true si existe la posici?n y se ha eliminado
     */
    public boolean EliminaAt(int n);

    /**
     *
     * Devuelve el elemento en cabeza de la lista
     *
     * @return Elemento que est? en la cabeza de la lista
     * @exception TADVacioException si la lista es vac?a
     * @see PaqTADVacioException.TADVacioException
     *     
*/
    public E Cabeza() throws TADVacioException;

    /**
     *
     * Devuelve el elemento de la posici?n n de la lista
     *
     * @return Elemento que est? en la posici?n n de la lista
     * @exception TADVacioException si la posici?n no existe
     * @see PaqTADVacioException.TADVacioException
     *     
*/
    public E elemAt(int n) throws TADVacioException;

    /**
     *
     * Devuelve la primera posici?n de un elemento
     *
     * @param e Elemento del que devuelve la posici?n
     * @return la primera posici?n del elemento, o -1 si no est?
     *     
*/
    public int Indice(E e);

    /**
     * Devuelve la lista sin el primer elemento (cabeza). Si la lista es vac?a
     * lanzar? la excepci?n TADVacioException.
     *
     * @return la lista resultante de eliminar el elemento en cabeza
     * @exception TADVacioException si la lista es vac?a
     * @see PaqTADVacioException.TADVacioException
     *     
*/
    public Lista<E> Cola() throws TADVacioException;

    /**
     * Devuelve la sublista (clonando) desde la posici?n i incluida hasta la
     * posici?n j excluida. Si i o j-1 est?n fuera del rango se lanza excepci?n
     * TADVacioException.
     *
     * @param i posici?n inicial
     * @param j posici?n final
     * @return la sublista resultante
     * @exception TADVacioException si se sale del rango
     * @see PaqTADVacioException.TADVacioException
     */
    public Lista<E> Sublista(int i, int j) throws TADVacioException;

    /**
     *
     * Consulta si un elemento esta en la lista
     *
     * @param e Elemento que se desea consultar
     * @return true si el elemento e pertenece a la lista
     *     
*/
    public boolean EstaContenido(E e);

    /**
     *
     * Consulta el n?mero de elementos de la lista
     *
     * @return el n?mero de elementos de la lista
     *     
*/
    public int Longitud();

    /**
     *
     * Concatena un clon de l a la lista sobre su final
     *
     * @param l Lista a concatenar con this
     *     
*/
    public void Concatena(Lista<E> l);

    /**
     * Ordena la lista, de menor a mayor, usando un comparador para comparar
     * elementos.
     *
     * @param c comparador
     * @see Comparator
     */
    public void Ordenar(Comparator<E> c);

    /**
     * Ordena la lista, de menor a mayor, si sus elementos son comparables
     * (clase Comparable>
     *
     * @exception IllegalArgumentException si no son comparables
     * @see IllegalArgumentException
     * @see Comparable
     */
    public void Ordenar() throws IllegalArgumentException;

    /**
     *
     * Clona la lista. Los elementos no se clonan
     *
     * @return Object clon de la lista concreta de la que se trate
     *     
*/
    public Object clone();

    /**
     *
     * Obtiene el String de la lista. No hace falta declarar el m?todo en la
     * interface. S?lo para recordar que debe implementase.
     *
     * @return	String con los elementos de los que consta la lista
     * @see	String
     *     
*/
    public String toString();

    /**
     * Compara dos listas elemento a elemento. No hace falta declarar el m?todo
     * en la interface. S?lo para recordar que debe implementase.
     *
     * @return Devuelve cierto si ambas listas son iguales
     * @see Object
     */
    public boolean equals(Object l);
// </editor-fold>
    
}
