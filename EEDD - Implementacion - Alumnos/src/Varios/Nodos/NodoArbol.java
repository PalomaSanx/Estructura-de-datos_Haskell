package Varios.Nodos;

public class NodoArbol<E> implements Cloneable {
    
// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed"> 
    public E valor;
    public NodoArbol<E> iz;   //nodo izquierdo
    public NodoArbol<E> de;   //nodo derecho
// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed"> 
    public NodoArbol(E e, NodoArbol<E> izq, NodoArbol<E> der) {
        valor = e;
        iz = izq;
        de = der;
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed"> 
    public Object clone() {
        NodoArbol<E> o = null;
        try {
            o = (NodoArbol<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        return o;
    }
// </editor-fold>   
    
}