package PaqTADNodo;

/** Clase Nodo para la implementaci�n del nodo de lineales din�micas */
public class Nodo<E> implements Cloneable 
{
   public E Info;              // Informaci�n almacenada en el nodo
   public Nodo<E> Siguiente;   // Nodo siguiente
    
   public Nodo(E e, Nodo<E> n)
   {
      Info=e;
      Siguiente=n;
    }
	
   public Object clone()
   {
	Object o=null;
	try
	{
	  o= super.clone();
	}catch(CloneNotSupportedException e){System.out.println(e);}
        	
        return o;
  }
}