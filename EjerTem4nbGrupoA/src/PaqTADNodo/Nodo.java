package PaqTADNodo;

/** Clase Nodo para la implementación del nodo de lineales dinámicas */
public class Nodo<E> implements Cloneable 
{
   public E Info;              // Información almacenada en el nodo
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