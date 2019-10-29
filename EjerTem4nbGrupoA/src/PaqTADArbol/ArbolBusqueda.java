package PaqTADArbol;

import PaqTADVacioException.*;

public interface ArbolBusqueda<E extends Comparable<E>> extends ArbolBinario<E>
{
    
  
  public ArbolBusqueda<E> SubArbolIzqdo() throws TADVacioException;


  public ArbolBusqueda<E> SubArbolDcho() throws TADVacioException;

  /**
   *
   * Inserta un elemento en el árbol, y queda com hoja. 
   * @param e Elemento a insertar
   * @exception   TADVacioException 
   */
  public void Inserta (E e);

    /**
    *
    * Extrae un elemento del árbol.
    * @param e Elemento a extraer.
    */
  public void Extrae(E e);

    /**
    *
    * Devuelve el menor elemento del árbol.
    * Si es vacío, se lanza la excepción indicada.
    * @exception   TADVacioException 
    */
  public E Minimo() throws TADVacioException;

    /**
    *
    * Devuelve el mayor elemento del árbol.
    * Si es vacío, se lanza la excepción indicada.
    * @exception   TADVacioException 
    */
  public E Maximo() throws TADVacioException;

}
