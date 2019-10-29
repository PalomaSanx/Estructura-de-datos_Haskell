package B_TADCola;

import Varios.Nodos.Nodo;
import Varios.Excepciones.TADVacioException;

public class Cola_Dinamica<E> implements Cola<E>, Cloneable, Comparable<Cola<E>> {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">         
    private Nodo<E> NodoCabeza; // Nodo en cabeza de la Cola
    private Nodo<E> NodoFinal;  // Nodo en Cola
// </editor-fold> 

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">             
    public Cola_Dinamica() {
        NodoCabeza = null;
        NodoFinal = null;
    }
// </editor-fold>     

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">             
    public boolean EsVacia() {
        return (NodoCabeza == null);
    }

    public void EnCola(E x) {
        Nodo<E> Nuevo;
        if (x != null) {
            Nuevo = new Nodo<E>(x, null);
            if (NodoCabeza == null) {
                NodoCabeza = Nuevo;
            } else {
                NodoFinal.Siguiente = Nuevo;
            }
            NodoFinal = Nuevo;
        }
    }

    public E Cabeza() throws TADVacioException {
        if (NodoCabeza == null) {
            throw new TADVacioException();
        } else {
            return (E) NodoCabeza.Info;
        }
    }

    public Cola<E> Resto() throws TADVacioException {
        Cola_Dinamica<E> c;
        if (NodoCabeza == null) {
            throw new TADVacioException();
        }
        c = new Cola_Dinamica<E>();
        c.NodoCabeza = NodoCabeza.Siguiente;
        if (c.NodoCabeza == null) {
            c.NodoFinal = null;
        } else {
            c.NodoFinal = NodoFinal;
        }
        return c;
    }

    public int Longitud() {
        Nodo<E> aux = NodoCabeza;
        int l = 0;
        while (aux != null) {
            l++;
            aux = aux.Siguiente;
        }
        return l;
    }

    public Object clone() {
        Cola_Dinamica<E> c1 = null;
        Nodo<E> aux1, aux2;
        try {
            c1 = (Cola_Dinamica<E>) super.clone();
        } catch (CloneNotSupportedException e) {	//Esto no puede ocurrir al ser cloneable
        }
        if (!EsVacia()) {
            c1.NodoCabeza = (Nodo<E>) NodoCabeza.clone();
            if (NodoCabeza == NodoFinal) {
                c1.NodoFinal = c1.NodoCabeza;
            } else {
                c1.NodoFinal = (Nodo<E>) NodoFinal.clone();
                aux1 = c1.NodoCabeza;
                aux2 = NodoCabeza.Siguiente;
                while (aux2 != NodoFinal) {
                    aux1.Siguiente = (Nodo<E>) aux2.clone();
                    aux2 = aux2.Siguiente;
                    aux1 = aux1.Siguiente;
                }
                aux1.Siguiente = c1.NodoFinal;
            }
        }
        return c1;
    }

    public String toString() {
        String s = new String("[");
        Nodo<E> actual = NodoCabeza;
        while (actual != null) {
            s += actual.Info.toString();
            actual = actual.Siguiente;
            if (actual != null) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public boolean equals(Object c) {
        if (!(c instanceof Cola)) {
            return false;
        }
        Cola<E> cc = (Cola<E>) ((Cola<E>) c).clone();
        Nodo<E> aux = NodoCabeza;
        boolean iguales = true;
        while (aux != null && !cc.EsVacia() && iguales) {
            try {
                iguales = aux.Info.equals(cc.Cabeza());
                cc = cc.Resto();
                aux = aux.Siguiente;
            } catch (TADVacioException ex) {
                ex.printStackTrace();
            }
        }
        return (iguales && aux == null && cc.EsVacia());
    }

    public int compareTo(Cola<E> c) {
        int l1 = this.Longitud(),
                l2 = c.Longitud();
        if (l1 == l2) {
            return 0;
        }
        if (l1 > l2) {
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