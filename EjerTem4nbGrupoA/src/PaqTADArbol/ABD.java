package PaqTADArbol;

import PaqTADVacioException.*;
import PaqTADNodo.*;

public class ABD<E> implements ArbolBinario<E>,Cloneable
{
   NodoArbol<E> raiz;     // Raíz del árbol binario
	
   public ABD(){ // Crea un árbol vacío
		raiz=null;
   }
	
   public ABD(E e)
   { // Crea un árbol con e como raíz 
		raiz=new NodoArbol<E>(e,null,null);
   }
	
   ABD(NodoArbol<E> n)
   {
	raiz=n;
   }
	
   public ABD(E x, ABD<E> i, ABD<E> d){
    // Crea un árbol que tiene en el nodo raíz el valor x, y
    // tiene como hijo izquierdo el subárbol iz, y como hijo
    // derecho el subárbol de (Operación Cons).
	raiz=new NodoArbol<E>(x, i.raiz, d.raiz);
   }
   
//    public ABD(E x, ArbolBinario<E> i, ArbolBinario<E> d){
//    
//   }
	
   public boolean EsVacio()
   {
	return raiz==null;
   }
	
    public E Raiz() throws TADVacioException
    {
	if (raiz==null)
		throw new TADVacioException();
	else
		return raiz.valor;
    }
	
    public ArbolBinario<E> SubArbolIzqdo() throws TADVacioException
    {
	if (raiz == null) throw new TADVacioException();
	else return new ABD<E>(this.raiz.iz);
    }
	
    public ArbolBinario<E> SubArbolDcho() throws TADVacioException
    {
	if (raiz == null) throw new TADVacioException();
	else return new ABD<E>(raiz.de);
    }
	
    @Override
    public Object clone()
    {
        ABD<E> a=null;
	try
	{
	   a=(ABD<E>)super.clone();
	}catch (CloneNotSupportedException e)
	{
	   System.out.println(e);
	}
	
	try
	{
	  if (!EsVacio())
	  {
		a.raiz=(NodoArbol<E>)raiz.clone();
		if (!SubArbolIzqdo().EsVacio())
			a.raiz.iz=((ABD<E>)SubArbolIzqdo().clone()).raiz;
		if (!SubArbolDcho().EsVacio())
				a.raiz.de=((ABD<E>)SubArbolDcho().clone()).raiz;	
	}
	}catch(TADVacioException e)
	{	System.out.println(e);
	}	
	return a;
    }
	
//    public String toString()
//    {
//	String s;
//	s=new String("( ");
//	if (raiz != null)
//	{
//	  try
//	  {
//		s+=this.raiz.valor.toString() + " ";
//		s+=this.SubArbolIzqdo().toString();
//		s+=this.SubArbolDcho().toString();
//	   }catch (TADVacioException e){ System.out.println(e);	}			
//	}
//	s+=" )";
//	return s;
//    }
	
    public String toString()
    {
        return tstr("    ");
    }

    private String tstr(String s){
         try {
            return ((ABD<E>)this.SubArbolDcho()).tstr(s+"     ")
                    +  "\n" + s + this.Raiz() 
                    + ((ABD<E>)this.SubArbolIzqdo()).tstr(s+"     ");
        } catch (TADVacioException ex) { 
            return "";
        }
    }


    @Override
   public boolean equals(Object o)
   {
       if(!(o instanceof ArbolBinario))
           throw new IllegalArgumentException();
       ArbolBinario<E> a = (ArbolBinario<E>)o;
       boolean iguales = raiz==null && a.EsVacio();
       
       if(raiz!=null && !a.EsVacio())
       {
           try {  
                iguales=this.Raiz().equals(a.Raiz())
                && this.SubArbolIzqdo().equals(a.SubArbolIzqdo())
                && this.SubArbolDcho().equals(a.SubArbolDcho());
            } catch (TADVacioException ex) {ex.printStackTrace(); }
       }
       
       return iguales;
   }
	
 // EJERCICIOS
   
   public E elemAt(String s) throws TADVacioException
   { // cadena de 0's y 1's; 0 izq, 1 dcha.
       NodoArbol<E> aux = this.raiz;
       while(aux != null && !s.isEmpty()){
           if(s.charAt(0)=='0'){
               aux= aux.iz;
               s = s.substring(1);
           }
           else
           {
               aux= aux.de;
               s = s.substring(1);
           } 
       }
       if (aux == null) throw new TADVacioException();
       else return aux.valor;
   }
 
   // suponiendo this árbol de búsqueda:
   public E maximo() throws TADVacioException{
       NodoArbol<E> aux = this.raiz;
       if(aux == null) throw new TADVacioException();
       while(aux.de != null){
           aux = aux.de;
       }
       return aux.valor;
   }
   
   // suponemos elementos comparables y this de búsqueda:
   public void inserta(E x){
         NodoArbol<E> aux = this.raiz;
         boolean insertado = false;
         if(aux == null) {
             this.raiz = new NodoArbol<E>(x,null,null);
             insertado = true;
         }
         while(!insertado){
             if(((Comparable)x).compareTo(aux.valor) < 0){ 
                 if(aux.iz == null) {
                     aux.iz = new NodoArbol<E>(x,null,null);
                     insertado = true;
                 }
                 else aux = aux.iz;
             }
             else if(((Comparable)x).compareTo(aux.valor) > 0){
                 if(aux.de == null) {
                     aux.de = new NodoArbol<E>(x,null,null);
                     insertado = true;
                 }
                 else aux = aux.de;
             }
             else 
                throw new IllegalArgumentException("el elemento ya esta en el arbol"); 
         }   
  }    
  
   
   
   
} // fin de la clase
