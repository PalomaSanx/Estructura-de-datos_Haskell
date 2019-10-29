package A_TADLista;

import Varios.Excepciones.TADVacioException;
import java.util.Arrays;
import java.util.Comparator;

public class ListaConArrays<E> implements Lista<E>, Cloneable, Comparable<Lista<E>> {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private final int tamañoInicial = 4;//Cantidad reservada inicialmente
    private final int incremento = 4; //Cantidad a incrementar si falta
    private int tamaño;    // Tamaño actual de la lista lista     
    private E elementos[]; // Array con los elementos de la lista
    private int libre;     // Primera posición libre
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public ListaConArrays() {
        // Devuelve una lista vacía de tamaño tamañoInicial
        tamaño = tamañoInicial;
        elementos = (E[]) new Object[tamaño];
        libre = 0;
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">           
    public boolean EsVacia() {
        return libre == 0;
    }

    private void Incrementa() {
        tamaño += incremento;
        elementos = Arrays.copyOf(elementos, tamaño);

//              o bien:
//		E copia[]=(E[]) new Object[tamaño];
//	 	System.arraycopy(elementos,0,copia,0,elementos.length);
//		elementos=copia;
    }

    public void Añade(E e) {
        if (libre == tamaño) {
            Incrementa();
        }
        elementos[libre] = e;
        libre++;
    }

    public E Cabeza() throws TADVacioException {
        if (libre == 0) {
            throw new TADVacioException();
        } else {
            return elementos[libre - 1];
        }
    }

    public Lista<E> Cola() throws TADVacioException {
        if (libre == 0) {
            throw new TADVacioException();
        } else {
            ListaConArrays<E> l = new ListaConArrays<E>();
            l.elementos = elementos;
            l.libre = libre - 1;
            return l;
        }
    }

    public boolean EstaContenido(E e) {
        for (int i = 0; i < libre; i++) {
            if (e.equals(elementos[i])) {
                return true;
            }
        }
        return false;
    }

    public int Longitud() {
        return libre;
    }

    public void Concatena(Lista<E> l) {  // suponemos l lista con arrays --supos demasiado fuerte-
        E[] copia;
        ListaConArrays<E> le = (ListaConArrays<E>) l.clone();

        //Nos aseguramos de que quepa la concatenacion con cierta holgura
        if ((le.libre) > (tamaño - libre)) {
            tamaño = le.libre + libre + incremento;
        }
        copia = (E[]) new Object[tamaño];
        System.arraycopy(le.elementos, 0, copia, 0, le.libre);
        System.arraycopy(elementos, 0, copia, le.libre, libre);
        elementos = copia;
        libre += le.libre;
    }

    public Object clone() {
        ListaConArrays<E> o = null;
        try {
            o = (ListaConArrays<E>) super.clone();
            o.elementos = (E[]) elementos.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println("No se pudo clonar");
        }
        return o;
    }

    public String toString() {
        String s;
        int i;

        s = new String("(");
        for (i = libre - 1; i >= 0; i--) {
            s += elementos[i];
            if (i > 0) {
                s += ", ";
            }
        }
        s += ")";
        return s;
    }
// </editor-fold>

// <editor-fold desc="METODOS AUN NO IMPLEMENTADOS" defaultstate="collapsed">       
    public void Añade(E e, int n) {
    }

    public boolean Elimina(E e) {
        return true;
    }

    public boolean EliminaAt(int n) {
        return true;
    }

    public E elemAt(int n) throws TADVacioException {
        return null;
    }

    public int Indice(E e) {
        return 0;
    }

    public Lista<E> Sublista(int i, int j) throws TADVacioException {
        return null;
    }

    public boolean equals(Lista<E> l) {
        return true;
    }

    public int compareTo(Lista<E> l) {
        return 0;
    }

    public void Ordenar(Comparator<E> c) {
    }

    public void Ordenar() throws IllegalArgumentException {
    }
// </editor-fold>
    
}
