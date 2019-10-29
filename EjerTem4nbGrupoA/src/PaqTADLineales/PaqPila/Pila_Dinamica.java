//  Pila.java

package PaqTADLineales.PaqPila;

import PaqTADVacioException.*;
import PaqTADNodo.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pila_Dinamica<E> implements Pila<E>, Cloneable, Comparable<Pila<E>>
{ 
     private Nodo<E>  NodoCabeza; // Nodo en cabeza


	/**
	 *   Construye una pila vacía
	 *
	 */
     public Pila_Dinamica()
	 {
	 	NodoCabeza=null; 
	 }
	 	
         
     public boolean EsVacia()
     {
         return (NodoCabeza == null);
     }
	 
     public void APila(E e)
    {
        
         if (e!=null)
             NodoCabeza=new Nodo<E>(e, NodoCabeza);
     }
	 
     public E Tope() throws TADVacioException
     {
         if (NodoCabeza == null) throw new TADVacioException();
         else return NodoCabeza.Info;
     }
	 
     public Pila DesaPila() throws TADVacioException
     {
         if (NodoCabeza == null) throw new TADVacioException();
         else
         {
             Pila_Dinamica<E> p = new Pila_Dinamica<E>();
             p.NodoCabeza = NodoCabeza.Siguiente;
             return p;
         }
             
     }
	 
      public int Altura() {
        int a = 0;
        Nodo<E> n = NodoCabeza;
        while (n != null)
        {
            a++;
            n=n.Siguiente;
        }
        return a;
    }
      
     public Object clone()
     {
	Pila_Dinamica<E> p1=null;
	Nodo<E> aux1, aux2;         
	try
	{
	  p1=(Pila_Dinamica<E>)super.clone();
	}catch (CloneNotSupportedException e)
		{	//Esto no puede ocurrir al ser cloneable
                }
       
        if (NodoCabeza!=null)
	{
	  p1.NodoCabeza=(Nodo<E>)NodoCabeza.clone();
	  aux1=p1.NodoCabeza;
          aux2=NodoCabeza.Siguiente;
          while (aux2 != null)
	  {
            aux1.Siguiente=(Nodo<E>)aux2.clone();
	    aux2=aux2.Siguiente;
	    aux1=aux1.Siguiente;
	  }
	}
	return p1;
     }
	   
     public String toString()
    {
         String s;
         Nodo<E> actual;
         s=new String("<");
         actual=NodoCabeza;
	 while (actual != null)
	 {
              s+=actual.Info.toString();
              actual=actual.Siguiente;
            if (actual != null) s=s.concat(", ");
         }
         s+=">";
         return s;
     }
	 
  
    public boolean equals(Object p) {
        if (!(p instanceof Pila)) return false;
        Pila<E> pl = (Pila<E>)((Pila<E>)p).clone();
        Nodo<E> aux = NodoCabeza;
        boolean iguales = true;
        while(aux!=null && !pl.EsVacia() && iguales)
        {
            try {
                iguales = aux.Info.equals(pl.Tope());
                pl=pl.DesaPila();
                aux = aux.Siguiente;
            } catch (TADVacioException ex) { ex.printStackTrace(); }
        }
        return (iguales && aux==null && pl.EsVacia());
    }
   
 
    public int compareTo(Pila<E> p) {
        int p1 = this.Altura(),
            p2 = p.Altura();
        if (p1==p2) return 0;
        if (p1>p2) return 1;
        return -1;
    }
    
    public E Menor() throws TADVacioException
    {
        if (NodoCabeza==null) throw new TADVacioException();
        E m = NodoCabeza.Info;
        Nodo<E> aux = NodoCabeza.Siguiente;
        while(aux!=null)
        {
           if (((Comparable)aux.Info).compareTo(m)<0) m=aux.Info;
           aux=aux.Siguiente;
        }
        return m;
    }
    
    public Pila<E> Inversa()
    {
        Pila_Dinamica<E> out = new Pila_Dinamica<E>();
        Nodo<E> n = NodoCabeza;
        while(n!=null)
        {
            out.NodoCabeza = new Nodo<E>(n.Info,out.NodoCabeza);
            n=n.Siguiente;
        }
        return out;
    }
    
    public void concat(Pila<E> p)
    {    
        Pila<E> pp = (Pila<E>) p.clone();
        NodoCabeza=new Nodo<E>(null,NodoCabeza);   
        Nodo<E> n = NodoCabeza;
        while(n.Siguiente!=null) n=n.Siguiente; 
        while(!pp.EsVacia())
        {
            try {
                n.Siguiente = new Nodo<E>(pp.Tope(), null);
                pp=pp.DesaPila();
            } catch (TADVacioException ex) {
                Logger.getLogger(Pila_Dinamica.class.getName()).log(Level.SEVERE, null, ex);
            }
            n=n.Siguiente;
        }  
        NodoCabeza=NodoCabeza.Siguiente;
    }
    
public Pila<E> concat2(Pila<E> p)
    {    
        Pila<E> pp = (Pila<E>) p.clone();
        Pila_Dinamica<E> qq = (Pila_Dinamica<E>) this.clone();
        qq.NodoCabeza=new Nodo<E>(null,qq.NodoCabeza);   
        Nodo<E> n = qq.NodoCabeza;
        while(n.Siguiente!=null) n=n.Siguiente; 
        while(!pp.EsVacia())
        {
            try {
                n.Siguiente = new Nodo<E>(pp.Tope(), null);
                pp=pp.DesaPila();
            } catch (TADVacioException ex) {
                Logger.getLogger(Pila_Dinamica.class.getName()).log(Level.SEVERE, null, ex);
            }
            n=n.Siguiente;
        }  
        qq.NodoCabeza=qq.NodoCabeza.Siguiente;
        return qq;
    }
    
  }    
            
            
           

    
