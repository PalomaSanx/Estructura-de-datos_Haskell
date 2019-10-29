package Varios.Nodos;

public class Nodo<E> implements Cloneable {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed"> 
    public E Info;              // Información almacenada en el nodo
    public Nodo<E> Siguiente;   // Nodo siguiente
// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed"> 
    public Nodo(E e, Nodo<E> n) {
        Info = e;
        Siguiente = n;
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed"> 
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        return o;
    }
// </editor-fold>
    
}