package A_TADLista;

import Varios.Nodos.Nodo;
import Varios.Excepciones.TADVacioException;
import java.util.Comparator;

public class Lista_Dinamica<E> implements Lista<E>, Cloneable, Comparable<Lista<E>> {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">         
    private int longitud; // Para almacenar en cada momento la longitud
    private Nodo<E> NodoCabeza; // Nodo en cabeza
// </editor-fold>     

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Lista_Dinamica() {
        longitud = 0;
        NodoCabeza = null;
    }

    public Lista_Dinamica(E e, Lista_Dinamica<E> c) {
        if (e != null) {
            longitud = 1 + c.longitud;
            NodoCabeza = new Nodo<E>(e, c.NodoCabeza);
        } else {
            throw new NullPointerException();
        }
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">           
    public boolean EsVacia() {
        return (longitud == 0);
    }

    public void Añade(E e) {
        Nodo<E> NuevaCabeza;
        if (e != null) {
            longitud++;
            NuevaCabeza = new Nodo<E>(e, NodoCabeza);
            NodoCabeza = NuevaCabeza;
        }
    }

    public E Cabeza() throws TADVacioException {
        if (longitud == 0) {
            throw new TADVacioException();
        } else {
            return NodoCabeza.Info;
        }
    }

    public Lista<E> Cola() throws TADVacioException {

        if (longitud == 0) {
            throw new TADVacioException();
        } else {
            Lista_Dinamica<E> l = new Lista_Dinamica<E>();
            l.NodoCabeza = NodoCabeza.Siguiente;
            l.longitud = longitud - 1;
            return l;
        }
    }

    public int Longitud() {
//        return longitud;

        int contador = 0;
        Nodo<E> actual;
        actual = NodoCabeza;
        while (actual != null) {
            contador++;
            actual = actual.Siguiente;
        }
        return contador;
    }

    public boolean EstaContenido(E e) {
        Nodo<E> actual;
        actual = NodoCabeza;
        while (actual != null) {
            if (e.equals(actual.Info)) {
                return true;
            } else {
                actual = actual.Siguiente;
            }
        }
        return false;
    }

    public void Añade(E e, int n) {
        if (n == 0) {
            Añade(e);
        }
        if (n > 0) {
            Nodo<E> act = NodoCabeza;
            while (act != null && n-- > 1) {
                act = act.Siguiente;
            }
            if (act != null) {
                act.Siguiente = new Nodo<E>(e, act.Siguiente);
                longitud++;
            }
        }
    }

    public boolean Elimina(E e) {
        boolean esta = false;
        NodoCabeza = new Nodo<E>(null, NodoCabeza);

        Nodo<E> act = NodoCabeza;
        while (act.Siguiente != null) {
            if (act.Siguiente.Info.equals(e)) {
                act.Siguiente = act.Siguiente.Siguiente;
                esta = true;
                longitud--;
            } else {
                act = act.Siguiente;
            }
        }

        NodoCabeza = NodoCabeza.Siguiente;
        return esta;

    }

    public boolean EliminaAt(int n) {
        boolean elim = false;
        if (n == 0 && NodoCabeza != null) {
            elim = true;
            NodoCabeza = NodoCabeza.Siguiente;
        }
        if (n > 0 && NodoCabeza != null) {
            Nodo<E> act = NodoCabeza;
            while (act.Siguiente != null && n-- > 1) {
                act = act.Siguiente;
            }
            if (act.Siguiente != null) {
                act.Siguiente = act.Siguiente.Siguiente;
                elim = true;
            }
        }

        return elim;
    }

    public E elemAt(int n) throws TADVacioException {
        if (n < 0) {
            throw new TADVacioException();
        }
        Nodo<E> act = NodoCabeza;
        for (int i = 0; i < n && act != null; i++) {
            act = act.Siguiente;
        }
        if (act != null) {
            return act.Info;
        } else {
            throw new TADVacioException();
        }
    }

    public int Indice(E e) {
        int ind = 0;
        Nodo<E> act = NodoCabeza;
        while (act != null && !act.Info.equals(e)) {
            act = act.Siguiente;
            ind++;
        }
        return act != null ? ind : -1;

    }

    public void Concatena(Lista<E> l) {
        Nodo<E> actual;
        Lista_Dinamica<E> l1;
        if (l == null || !(l instanceof Lista_Dinamica)) {
            throw new IllegalArgumentException("Requiere Lista_Dinamica");
        }
        l1 = (Lista_Dinamica<E>) l.clone(); //Para evitar problemas cuando se 
        // concatena con ella misma
        if (longitud == 0) {
            NodoCabeza = l1.NodoCabeza;
            longitud = l1.longitud;
        } else {
            actual = NodoCabeza; // Se posiciona sobre el final de la actual
            while (actual.Siguiente != null) {
                actual = actual.Siguiente;
            }
            // Enlaza la otra
            longitud += l1.longitud;
            actual.Siguiente = l1.NodoCabeza;
        }
    }

    public Lista<E> Sublista(int i, int j) throws TADVacioException {
        if (j > longitud) {
            throw new TADVacioException();
        }
        Lista_Dinamica<E> l = new Lista_Dinamica<E>();
        l.NodoCabeza = new Nodo<E>(null, null);
        Nodo<E> act1 = NodoCabeza,
                act2 = l.NodoCabeza;
        if (j >= i) {
            int k;
            for (k = 0; k < i; k++) {
                act1 = act1.Siguiente;
            }
            for (; k < j; k++) {
                act2.Siguiente = new Nodo<E>(act1.Info, null);
                act2 = act2.Siguiente;
                act1 = act1.Siguiente;
            }

        }
        l.NodoCabeza = l.NodoCabeza.Siguiente;
        l.longitud = j - 1;
        return l;
    }

    public void Ordenar(Comparator<E> c) {
        if (longitud > 1) {
            if (!(NodoCabeza.Info instanceof Comparable)) {
                throw new IllegalArgumentException();
            }
            Nodo<E> ord = new Nodo<E>(null, null), aux = null;

            while (NodoCabeza != null) {
                aux = ord;
                while (aux.Siguiente != null && c.compare(aux.Siguiente.Info, NodoCabeza.Info) < 0) {
                    aux = aux.Siguiente;
                }
                aux.Siguiente = new Nodo<E>(NodoCabeza.Info, aux.Siguiente);
                NodoCabeza = NodoCabeza.Siguiente;
            }
            NodoCabeza = ord.Siguiente;
        }

    }

    public void Ordenar() throws IllegalArgumentException {
        if (longitud > 1) {
            if (!(NodoCabeza.Info instanceof Comparable)) {
                throw new IllegalArgumentException();
            }
            Nodo<E> ord = new Nodo<E>(null, null), aux = null;

            while (NodoCabeza != null) {
                aux = ord;
                while (aux.Siguiente != null && ((Comparable) aux.Siguiente.Info).compareTo(NodoCabeza.Info) < 0) {
                    aux = aux.Siguiente;
                }
                aux.Siguiente = new Nodo<E>(NodoCabeza.Info, aux.Siguiente);
                NodoCabeza = NodoCabeza.Siguiente;
            }
            NodoCabeza = ord.Siguiente;
        }

    }

    public Object clone() {
        Lista_Dinamica<E> l1 = null;
        Nodo<E> aux1, aux2;
        try {
            l1 = (Lista_Dinamica<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        if (longitud != 0) {
            l1.NodoCabeza = (Nodo<E>) NodoCabeza.clone();
            aux1 = l1.NodoCabeza;
            aux2 = NodoCabeza.Siguiente;
            while (aux2 != null) {
                aux1.Siguiente = (Nodo<E>) aux2.clone();
                aux2 = aux2.Siguiente;
                aux1 = aux1.Siguiente;
            }
        }
        return l1;
    }

    public String toString() {
        String s;
        Nodo<E> actual;
        s = new String("(");
        actual = NodoCabeza;
        while (actual != null) {
            s = s.concat(actual.Info.toString());
            actual = actual.Siguiente;
            if (actual != null) {
                s = s.concat(", ");
            }
        }
        s = s.concat(")");
        return s;
    }

    public boolean equals(Object l) {
        if (!(l instanceof Lista)) {
            return false;
        }
        Lista<E> cl = (Lista<E>) ((Lista<E>) l);//.clone();
        Nodo<E> aux = NodoCabeza;
        boolean iguales = true;
        while (aux != null && !cl.EsVacia() && iguales) {
            try {
                iguales = aux.Info.equals(cl.Cabeza());
                cl = cl.Cola();
                aux = aux.Siguiente;
            } catch (TADVacioException ex) {
                ex.printStackTrace();
            }
        }
        return (iguales && aux == null && cl.EsVacia());
    }

    public int compareTo(Lista<E> l) {
        int l1 = this.Longitud(),
                l2 = l.Longitud();
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
    public Lista<E> inversa(){
        Lista_Dinamica<E> aux=new Lista_Dinamica();
        Nodo<E> nodoAux=this.NodoCabeza;
        
        while(nodoAux != null){
            aux.Añade(nodoAux.Info);
            nodoAux = nodoAux.Siguiente;
        }
        return aux;
    }
    
    
// </editor-fold>    
    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>  
    
}
