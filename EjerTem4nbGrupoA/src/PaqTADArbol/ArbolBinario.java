package PaqTADArbol;

import PaqTADVacioException.*;

	/*
	 * Interfaz general de árboles binarios
	 * Extiende la interfaz Arbol
	 *
	 */ 
	  
public interface ArbolBinario<E> extends Arbol<E>
{

	/**
	 *
	 * Devuelve el subárbol izquierdo del árbol binario
	 * @return  ArbolBinario que es el subárbol izquierdo del árbol   
	 * @exception   TADVacioException 
	 * @see TADVacioException 
	 */
	public ArbolBinario<E> SubArbolIzqdo() throws TADVacioException;  

	/**
	 *
	 * Devuelve el subárbol derecho del árbol binario
	 * @return    ArbolBinario que es el subárbol derecho del árbol 
	 * @exception   TADVacioException  
	 */
  	public ArbolBinario<E> SubArbolDcho() throws TADVacioException;

}