package  PaqTADLineales.PaqLista;

import PaqTADVacioException.*;
import java.util.Comparator;

/**
 *
 * Interfaz general de listas con las operaciones necesaria para implementar el TAD lista
 * La clase que implemente esta interface deberá contener un constructor sin parámetros para crear una lista vacia
 * @author Estructuras de Datos y de la Información 
 * @version Curso 2008-2009
 * 
 */

public interface Lista<E>{
 
 /**
 * 
 * Consulta si la lista es vacia
 * @return true si la lista no contiene elementos
 * 
 */ 
public boolean EsVacia();
    
/**
* 
* Añade un elemento a la lista por la cabeza
* @param  e Elemento que se añade a la lista
*
*/
public void Añade(E e);
    
/**
* Añade un elemento a la lista en la posición n,
* contando desde la cabeza a partir de 0. Si la posición
* no existe, la lista queda inalterada.
* @param  e Elemento que se añade a la lista
* @param  n Posición en la que queda
* 
*/
public void Añade(E e, int n);

/**
* Elimina la primera aparición del elemento e de la lista 
* @param  e Elemento a eliminar
* @return true si existe el elemento y se ha eliminado
*/
public boolean Elimina(E e);

/**
* Elimina el elemento en la posición n.
* Si la posición no existe, la lista no se modifica
* @param  n posición del elemento a eliminar
* @return true si existe la posición y se ha eliminado
*/
public boolean EliminaAt(int n);


/**
* 
* Devuelve el elemento en cabeza de la lista
* @return Elemento que está en la cabeza de la lista
* @exception TADVacioException si la lista es vacía
* @see PaqTADVacioException.TADVacioException
* 
*/
public E Cabeza() throws TADVacioException;
       
/**
* 
* Devuelve el elemento de la posición n de la lista
* @return Elemento que está en la posición n de la lista
* @exception TADVacioException si la posición no existe
* @see PaqTADVacioException.TADVacioException
* 
*/
public E elemAt(int n) throws TADVacioException;

/**
* 
* Devuelve la primera posición de un elemento
* @param e Elemento del que devuelve la posición
* @return la primera posición del elemento, o -1 si no está
* 
*/
public int Indice(E e);

/**
* Devuelve la lista sin el primer elemento (cabeza).
* Si la lista es vacía lanzará la excepción TADVacioException.
* @return la lista resultante de eliminar el elemento en cabeza
* @exception TADVacioException si la lista es vacía
* @see PaqTADVacioException.TADVacioException
* 
*/
public Lista<E> Cola()throws TADVacioException;

/**
* Devuelve la sublista (clonando) desde la posición i incluida hasta
* la posición j excluida. Si i o j-1 están fuera del rango se
* lanza excepción TADVacioException. 
* @param i posición inicial 
* @param j posición final
* @return la sublista resultante 
* @exception TADVacioException si se sale del rango
* @see PaqTADVacioException.TADVacioException
*/ 
public Lista<E> Sublista(int i, int j) throws TADVacioException;
/**
* 
* Consulta si un elemento esta en la lista 
* @param e Elemento que se desea consultar
* @return true si el elemento e pertenece a la lista
* 
*/
public boolean EstaContenido(E e);
    
/**
* 
* Consulta el número de elementos de la lista
* @return el número de elementos de la lista
* 
*/ 
public int Longitud();
    
/**
* 
* Concatena un clon de l a la lista sobre su final
* @param l Lista a concatenar con this
* 
*/
public void Concatena(Lista<E> l);

/**
* Ordena la lista, de menor a mayor, usando un comparador 
* para comparar elementos.
* @param c comparador 
* @see Comparator
*/ 
public void Ordenar(Comparator<E> c);

/**
* Ordena la lista, de menor a mayor, si sus elementos 
* son comparables (clase Comparable>
* @exception IllegalArgumentException si no son comparables
* @see IllegalArgumentException
* @see Comparable
*/ 
public void Ordenar() throws IllegalArgumentException;
	
/**
* 
* Clona la lista. Los elementos no se clonan
* @return Object clon de la lista concreta de la que se trate
* 
*/
public Object clone();
    
/**
* 
* Obtiene el String de la lista.
* No hace falta declarar el método en la interface. 
* Sólo para recordar que debe implementase.
* @return	String con los elementos de los que consta la lista
* @see		String
*     
*/
public String toString();
     
/**
*   Compara dos listas elemento a elemento.
*   No hace falta declarar el método en la interface. 
*   Sólo para recordar que debe implementase.
*   @return 	Devuelve cierto si ambas listas son iguales 	   
*   @see 	Object	   
*/
public boolean equals(Object l);


}
