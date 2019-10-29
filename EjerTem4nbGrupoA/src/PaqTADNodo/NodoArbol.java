package PaqTADNodo;

public class NodoArbol<E> implements Cloneable{
    
    public E valor;
    public NodoArbol<E> iz;   //nodo izquierdo
    public NodoArbol<E> de;   //nodo derecho

    public NodoArbol( E e, NodoArbol<E> izq, NodoArbol<E> der){
      valor=e;
      iz=izq;
      de=der;
    }

    public Object clone(){
	NodoArbol<E> o=null;
	try
	{
	   o=(NodoArbol<E>) super.clone();	   
	}catch(CloneNotSupportedException e) {System.out.println(e);}
		
	return o;
     }	
}
