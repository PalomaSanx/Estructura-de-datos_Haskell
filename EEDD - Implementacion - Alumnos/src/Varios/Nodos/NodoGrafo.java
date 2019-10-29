package Varios.Nodos;

public class NodoGrafo<E> implements Cloneable {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed"> 
    public E Info;       // Información almacenada en el nodo
    public boolean Visitado;  // Nos indica si el nodo ya ha sido visitado.
// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed"> 
    public NodoGrafo(E e, boolean v) {
        Info = e;
        Visitado = v;
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed"> 
    public Object clone() {
        NodoGrafo<E> o = null;
        try {
            o = (NodoGrafo<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        return o;
    }
// </editor-fold>    
    
}