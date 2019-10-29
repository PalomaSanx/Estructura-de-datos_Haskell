package C_TADPila;

import Varios.Nodos.Nodo;
import Varios.Excepciones.TADVacioException;

public class Pila_Dinamica<E> implements Pila<E>, Cloneable, Comparable<Pila<E>> {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">       
    private Nodo<E> NodoCabeza; // Nodo en cabeza
// </editor-fold>     

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">       
    /**
     * Construye una pila vacía
     *
     */
    public Pila_Dinamica() {
        NodoCabeza = null;
    }
// </editor-fold>     

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">       
    public boolean EsVacia() {
        return (NodoCabeza == null);
    }

    public void APila(E e) {

        if (e != null) {
            NodoCabeza = new Nodo<E>(e, NodoCabeza);
        }
    }

    public E Tope() throws TADVacioException {
        if (NodoCabeza == null) {
            throw new TADVacioException();
        } else {
            return NodoCabeza.Info;
        }
    }

    public Pila DesaPila() throws TADVacioException {
        if (NodoCabeza == null) {
            throw new TADVacioException();
        } else {
            Pila_Dinamica<E> p = new Pila_Dinamica<E>();
            p.NodoCabeza = NodoCabeza.Siguiente;
            return p;
        }

    }

    public int Altura() {
        int a = 0;
        Nodo<E> n = NodoCabeza;
        while (n != null) {
            a++;
            n = n.Siguiente;
        }
        return a;
    }

    public Object clone() {
        Pila_Dinamica<E> p1 = null;
        Nodo<E> aux1, aux2;
        try {
            p1 = (Pila_Dinamica<E>) super.clone();
        } catch (CloneNotSupportedException e) {	//Esto no puede ocurrir al ser cloneable
        }

        if (NodoCabeza != null) {
            p1.NodoCabeza = (Nodo<E>) NodoCabeza.clone();
            aux1 = p1.NodoCabeza;
            aux2 = NodoCabeza.Siguiente;
            while (aux2 != null) {
                aux1.Siguiente = (Nodo<E>) aux2.clone();
                aux2 = aux2.Siguiente;
                aux1 = aux1.Siguiente;
            }
        }
        return p1;
    }

    public String toString() {
        String s;
        Nodo<E> actual;
        s = new String("<");
        actual = NodoCabeza;
        while (actual != null) {
            s += actual.Info.toString();
            actual = actual.Siguiente;
            if (actual != null) {
                s = s.concat(", ");
            }
        }
        s += ">";
        return s;
    }

    public boolean equals(Object p) {
        if (!(p instanceof Pila)) {
            return false;
        }
        Pila<E> pl = (Pila<E>) ((Pila<E>) p).clone();
        Nodo<E> aux = NodoCabeza;
        boolean iguales = true;
        while (aux != null && !pl.EsVacia() && iguales) {
            try {
                iguales = aux.Info.equals(pl.Tope());
                pl = pl.DesaPila();
                aux = aux.Siguiente;
            } catch (TADVacioException ex) {
                ex.printStackTrace();
            }
        }
        return (iguales && aux == null && pl.EsVacia());
    }

    public int compareTo(Pila<E> p) {
        int p1 = this.Altura(),
                p2 = p.Altura();
        if (p1 == p2) {
            return 0;
        }
        if (p1 > p2) {
            return 1;
        }
        return -1;
    }
// </editor-fold>  

    /*
     IMPLEMENTACIONES DEL CURSO
     */
    
// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
// </editor-fold>    
    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>  
    
}