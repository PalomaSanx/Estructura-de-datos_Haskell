package C_TADPila;

import Varios.Excepciones.TADVacioException;

public interface Pila<E> {

// <editor-fold desc="METODOS ABSTRACTOS" defaultstate="collapsed">      
    /**
     * Devuelve true si la pila no contiene elementos
     *
     * @return	true si es vacía
     */
    public boolean EsVacia();

    /**
     * Apila x en la Pila
     *
     * @param e Elemento e que va a apilarse
     */
    public void APila(E e);

    /**
     * Devuelve el Elemento en el tope de la Pila
     *
     * @exception TADVacioException en caso de que la Pila no tenga elementos
     * @return Elemento que está en la cima de la pila
     * @see Object
     */
    public E Tope() throws TADVacioException;

    /**
     * Extrae el tope de la Pila
     *
     * @exception	TADVacioException en caso de que la Pila no tenga elementos
     * @return Pila sin la cima
     */
    public Pila<E> DesaPila() throws TADVacioException;

    ;
      
    /**
    * 
    * Consulta el número de elementos de la pila
    * @return el número de elementos de la pila
    * 
    */ 
    public int Altura();

    /**
     * Saca un duplicado de la Pila. Devuelve un objeto del tipo Pila concreto
     * que se este empleando
     *
     * @return un clone de la pila
     */
    public Object clone();

    /**
     * Pasa la pila a formato String. No hace falta declarar el método en la
     * interface. Sólo para recordar que debe implementase.
     *
     * @return	String con los elementos de la pila
     * @see	String
     */
    public String toString();

    /**
     * Compara dos pilas elemento a elemento. No hace falta declarar el método
     * en la interface. Sólo para recordar que debe implementase.
     *
     * @return Devuelve cierto si ambas pilas son iguales
     * @see Object
     */
    public boolean equals(Object p);
// </editor-fold>  
    
}