// Cola.java 
package B_TADCola;

import Varios.Excepciones.TADVacioException;

public interface Cola<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">         
    /**
     * Devuelve true si la cola es vacía
     *
     * @return	true si la cola es vacía
     */
    public boolean EsVacia();

    /**
     * Añade x a la cola por el final
     *
     * @param	x Elemento a encolar
     * @see	PaqElemento.Elemento
     */
    public void EnCola(E x);

    /**
     * Devuelve el objeto en la cabeza de la cola
     *
     * @return	el Elemento en la cabeza de la cola
     * @exception	TADVacioException en caso de que la cola no contenga elementos
     * @see	PaqElemento.Elemento
     */
    public E Cabeza() throws TADVacioException;

    /**
     * Devuelve la cola sin la cabeza
     *
     * @exception	TADVacioException en caso de que la cola no contenga elementos
     */
    public Cola<E> Resto() throws TADVacioException;

    /**
     * Devuelve la longitud de la cola
     *
     * @return	longitud de la cola
     */
    public int Longitud();

    /**
     * Saca un duplicado de la cola Devuelve un objeto del tipo Cola concreto
     * que se esté empleando. Es necesario declararlo para hacerlo público
     *
     * @return	un clone de la cola
     */
    public Object clone();


    /* Los siguientes métodos no sería necesario declararlos
     * (se heredan de Object)
     * Se hace para recordar que deben reescribirse en las
     * implementaciones, pues el código heredado no sirve
     */
    /**
     * Pasa la cola a formato String
     *
     * @return Devuelve un String mostrando los elementos
     * @see String
     */
    public String toString();

    /**
     * Compara dos colas elemento a elemento
     *
     * @return Devuelve cierto si ambas colas son iguales
     * @see Object
     */
    public boolean equals(Object c);
// </editor-fold> 
    
}