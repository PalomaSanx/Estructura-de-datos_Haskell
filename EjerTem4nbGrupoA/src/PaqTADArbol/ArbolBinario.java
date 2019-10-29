package PaqTADArbol;

import PaqTADVacioException.*;

	/*
	 * Interfaz general de �rboles binarios
	 * Extiende la interfaz Arbol
	 *
	 */ 
	  
public interface ArbolBinario<E> extends Arbol<E>
{

	/**
	 *
	 * Devuelve el sub�rbol izquierdo del �rbol binario
	 * @return  ArbolBinario que es el sub�rbol izquierdo del �rbol   
	 * @exception   TADVacioException 
	 * @see TADVacioException 
	 */
	public ArbolBinario<E> SubArbolIzqdo() throws TADVacioException;  

	/**
	 *
	 * Devuelve el sub�rbol derecho del �rbol binario
	 * @return    ArbolBinario que es el sub�rbol derecho del �rbol 
	 * @exception   TADVacioException  
	 */
  	public ArbolBinario<E> SubArbolDcho() throws TADVacioException;

}