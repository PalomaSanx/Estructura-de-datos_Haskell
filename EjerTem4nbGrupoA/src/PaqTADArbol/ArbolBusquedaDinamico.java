package PaqTADArbol;

import PaqTADVacioException.*;
import PaqTADNodo.*;

public class ArbolBusquedaDinamico<E extends Comparable<E>> 
        extends ABD<E> 
        implements ArbolBusqueda<E>
{
    
 public ArbolBusquedaDinamico() 
 {		
  super();
 }
	
 public ArbolBusquedaDinamico(E e)
 {		
  super(e);
 }
	
 private ArbolBusquedaDinamico(NodoArbol<E> n)
 {
  super(n);
 }


public ArbolBusquedaDinamico(E e, 
       ArbolBusquedaDinamico<E> iz, 
       ArbolBusquedaDinamico<E> de)  // throws NullPointerException
 { // Crea un arbol de busqueda con e como 'raiz', 'iz' como subarbol 
   // izquierdo y 'de' como subarbol derecho. Si no se puede construir así 
   // uno de búsqueda, lanzamos la excepción.
  try
  {
    if ( iz.Pertenece(e) || de.Pertenece(e) ) throw new NullPointerException(); 
    if ( !iz.EsVacio() && e.compareTo(iz.Maximo())<0  
      || !de.EsVacio() && e.compareTo(de.Minimo())>0 ) throw new NullPointerException();
    raiz=new NodoArbol<E>(e,iz.raiz,de.raiz);
           
  }catch(TADVacioException ex) {System.out.println(ex);}
 }	

public ArbolBusquedaDinamico(ArbolBinario<E> a)
{// Convierte un árbol binario en árbol de búsqueda, 
 // si puede serlo (aunque 'a' pueda serlo, no admite lo métodos 
 // propios de uno de búsqueda)
  if (a.EsVacio()) raiz=null;
  else
  {
    try{
       raiz = new ArbolBusquedaDinamico<E>(a.Raiz(),
                   new ArbolBusquedaDinamico(a.SubArbolIzqdo()),
                   new ArbolBusquedaDinamico(a.SubArbolDcho())).raiz;          
        } catch (TADVacioException ex) {
                System.out.println(ex);
        }
    }  
}

public ArbolBusqueda<E> SubArbolIzqdo() throws TADVacioException
{
	if (raiz == null) throw new TADVacioException();
	else return new ArbolBusquedaDinamico<E>(raiz.iz);
}

public ArbolBusqueda<E> SubArbolDcho() throws TADVacioException
{
	if (raiz == null) throw new TADVacioException();
	else return new ArbolBusquedaDinamico<E>(raiz.de);
}

public void Inserta (E x)
 {
   // Inserta x en el árbol, según su valor. 
   NodoArbol<E> nuevo = new NodoArbol<E>(x, null, null); // Sera una hoja.
   if (raiz == null) raiz = nuevo;
   else
   {  // Ha de localizar el punto de inserción, descendiendo:
     NodoArbol<E> actual = raiz;   // Comenzamos desde la raíz
     NodoArbol<E> padre = null;    // Padre del actual
     while (! x.equals(actual.valor))
     {  // Cada iteración baja un nivel
        padre=actual;
        if (x.compareTo(actual.valor)<0)
	{  // Hay que seguir por la izquierda
           actual=actual.iz;
           if (actual == null)  
	   { // Ya tiene el punto de inserción
                padre.iz =nuevo;
                return;
	   }
        }
        else 
	{  // Hay que seguir por la derecha
           actual = actual.de;
           if (actual == null) 
	   { // Ya tiene el punto de inserción
             padre.de = nuevo;
             return;
           }
        }
      }
   }
 }
 	
 public void Extrae (E x) 
 {  // Extrae x del árbol
   NodoArbol<E> actual=raiz;
   NodoArbol<E> padre=raiz;
   boolean EsHijoIzqdo=true;
      
   if (raiz != null)
   {  // Busca el nodo
     while (!x.equals(actual.valor)) 
	 {
       padre=actual;
       if (x.compareTo(actual.valor)<0)
	   {
         EsHijoIzqdo=true;
         actual=actual.iz;
       }
       else 
	   {
         EsHijoIzqdo=false;
         actual=actual.de;
       }
       if (actual == null) return;
     } 	 
	 // ya tiene en actual el nodo a borrar:
     // Si no tiene hijos, lo borra simplemente
     if ((actual.iz==null) && (actual.de==null)) 
	 {
       // Si es la raíz vaciamos el árbol, si no miramos de qué lado  
       // proviene
       if (actual==raiz) raiz=null;
       else if (EsHijoIzqdo) padre.iz=null;
       else padre.de=null; 
     }
     else 
	   if (actual.de==null)
         // Si sólo tiene un hijo, este sube para ocupar su lugar
         if (raiz==actual) raiz=actual.iz;
         else if (EsHijoIzqdo) padre.iz=actual.iz;
         else padre.de=actual.iz;
     else 
	   if (actual.iz==null)
         // Si sólo tiene un hijo, este sube para ocupar su lugar
         if (raiz==actual) raiz=actual.de;
         else if (EsHijoIzqdo) padre.iz=actual.de;
         else padre.de=actual.de;
     else
	 { // Tiene los dos hijos, localiza el sucesor y reemplaza
       NodoArbol sig = Sucesor(actual);
       if (raiz==actual) raiz=sig;
       else if (EsHijoIzqdo) padre.iz=sig;
       else padre.de=sig;
       sig.iz=actual.iz;
	 }  // fin del if ((actual.iz==null) ... 
   } // fin de if(raiz != null)
 }
  
 private NodoArbol<E> Sucesor(NodoArbol<E> n)
 {
   // Baja por las ramas izquierdas de su subárbol derecho para obtener
   // el nodo sucesor de n, lo borra y lo devuelve
   NodoArbol PadreDeSucesor = n;
   NodoArbol sig = n;
   NodoArbol actual = n.de; // Empieza desde el subárbol derecho
   while (actual != null) 
   {
      PadreDeSucesor=sig;
      sig=actual;
      actual=actual.iz;
   }
 	// Procede a borrarlo, ahora ya sabe que como mucho tendrá hijo
    // derecho
   if (sig != n.de)
   { 
      PadreDeSucesor.iz=sig.de; // sube su hijo derecho
      sig.de=n.de; // Su nuevo hijo derecho es el que tenia el nodo borrado
   }
   return sig;
 }
	
 
 public E Minimo() throws TADVacioException
 {
  // Devuelve el elemento mínimo (algoritmo iterativo)
  if (raiz==null) throw new TADVacioException();
  else
  {
    if (raiz.iz==null) return raiz.valor;
    else 
    {
       NodoArbol<E> actual=raiz.iz;
       while (actual.iz != null)   actual=actual.iz;  
       return actual.valor;  
    }
  }
 }
	
 public E Maximo() throws TADVacioException
 {
  // Devuelve el elemento máximo
  if (raiz==null) throw new TADVacioException();
  else
  {
    if (raiz.de==null) return raiz.valor;
    else 
    {
        NodoArbol<E> actual=raiz.de;
        while (actual.de != null) actual=actual.de;                    
        return (actual.valor);
    }
  }
 }

 public boolean Pertenece(E x)
 {
    // Devuelve true si x está en el árbol (algoritmo iterativo)
     NodoArbol<E> actual = raiz;
     if (raiz==null) return false;
     else
     {
       while (!x.equals(actual.valor))
       {
       	if (x.compareTo(actual.valor)<0)
	    actual=actual.iz;
        else  actual=actual.de;
        if (actual==null) return false;
       }
       return true;
      }
 }

}
