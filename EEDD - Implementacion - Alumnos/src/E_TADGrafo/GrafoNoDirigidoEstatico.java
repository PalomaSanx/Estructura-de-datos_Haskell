package E_TADGrafo;

import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import Varios.Nodos.NodoGrafo;
import Varios.Excepciones.NodoYaExisteException;
import Varios.Excepciones.TADLlenoException;
import java.util.*;

public class GrafoNoDirigidoEstatico<E> implements GrafoEstatico<E>, Cloneable {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">         
    private final static int MAX_NODOS = 100; // Numero máximo de nodos
    private int numnodos;            // Número actual de nodos
    private boolean Adyacencias[][]; // Matriz de adyacencia (simétrica)
    private NodoGrafo<E> Nodos[];    // Nodos presentes
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">         
    /**
     * Crea un grafo vacío.
     */
    public GrafoNoDirigidoEstatico() {
        Adyacencias = new boolean[MAX_NODOS][MAX_NODOS];
        Nodos = new NodoGrafo[MAX_NODOS];
    }
// </editor-fold>

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">         
    private int BuscaElemento(E e) {
        if (e == null) {
            return -1;
        }
        for (int i = 0; i < MAX_NODOS; i++) {
            if (Nodos[i] != null && e.equals(Nodos[i].Info)) {
                return i;
            }
        }
        return -1;
    }
    
    private int buscaPosicion() {
        for (int i = 0; i < MAX_NODOS; i++) {
            if (Nodos[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean EstaContenido(E e) {
        return (BuscaElemento(e) != -1);
    }
    
    public boolean EsVacio() {
        return (numnodos == 0);
    }
    
    public void InclNodo(E e)
            throws TADLlenoException, NoSuchElementException, NodoYaExisteException {
        if (e == null) {
            throw new NoSuchElementException();
        }
        if (numnodos == MAX_NODOS) {
            throw new TADLlenoException();
        }
        if (BuscaElemento(e) == -1) {
            Nodos[buscaPosicion()] = new NodoGrafo<E>(e, false);
            numnodos++;
        } else {
            throw new NodoYaExisteException();
        }
    }
    
    public void InclArco(E e1, E e2) throws NoSuchElementException {
        int pos1 = BuscaElemento(e1),
                pos2 = BuscaElemento(e2);
        if (pos1 == -1 || pos2 == -1) {
            throw new NoSuchElementException();
        } else {
            Adyacencias[pos1][pos2] = true;
            Adyacencias[pos2][pos1] = true; // Es no-dirigido.
        }
    }
    
    public boolean EsAdyacente(E e1, E e2) throws NoSuchElementException {
        int pos1 = BuscaElemento(e1),
                pos2 = BuscaElemento(e2);
        if (pos1 == -1 || pos2 == -1) {
            throw new NoSuchElementException();
        } else {
            return Adyacencias[pos1][pos2];
        }
    }
    
    public void BorraNodo(E e) throws NoSuchElementException {
        int pos = BuscaElemento(e);
        if (pos == -1) {
            throw new NoSuchElementException();
        } else {
            Nodos[pos] = null;  //para que lo borre el recolector de basura.
            numnodos--;
            for (int j = 0; j < MAX_NODOS; j++) {
                Adyacencias[pos][j] = Adyacencias[j][pos] = false;
            }
        }
    }
    
    public void BorraArco(E e1, E e2) throws NoSuchElementException {
        int pos1 = BuscaElemento(e1),
                pos2 = BuscaElemento(e2);
        if (pos1 == -1 || pos2 == -1) {
            throw new NoSuchElementException();
        } else {
            Adyacencias[pos1][pos2] = Adyacencias[pos2][pos1] = false;
        }
    }
    
    public Object clone() {
        GrafoNoDirigidoEstatico<E> gr = null;
        try {
            gr = (GrafoNoDirigidoEstatico<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        
        gr.Adyacencias = (boolean[][]) Adyacencias.clone();
        for (int i = 0; i < MAX_NODOS; i++) {
            gr.Adyacencias[i] = (boolean[]) Adyacencias[i].clone();
        }
        gr.Nodos = (NodoGrafo<E>[]) Nodos.clone();
        for (int i = 0; i < MAX_NODOS; i++) {
            if (Nodos[i] != null) {
                gr.Nodos[i] = (NodoGrafo<E>) Nodos[i].clone();
            }
        }
        
        return gr;
    }
    
    public String toString() {
        String resultado = new String();
        for (int i = 0; i < MAX_NODOS; i++) {
            if (Nodos[i] != null) {
                for (int j = i; j < MAX_NODOS; j++) {
                    if (Nodos[j] != null) {
                        if (EsAdyacente(Nodos[i].Info, Nodos[j].Info)) {
                            Nodos[i].Visitado = true;
                            Nodos[j].Visitado = true;
                            resultado += Nodos[i].Info + " --- " + Nodos[j].Info + "\n";
                        }
                    }
                }
                if (!Nodos[i].Visitado) {
                    resultado += Nodos[i].Info + "\n";
                }
            }
        }
        for (int i = 0; i < MAX_NODOS; i++) {
            if (Nodos[i] != null) {
                Nodos[i].Visitado = false;
            }
        }
        return resultado;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof GrafoNoDirigidoEstatico)) {
            return false;
        }
        GrafoNoDirigidoEstatico<E> g = (GrafoNoDirigidoEstatico<E>) o;
        boolean iguales = numnodos == g.numnodos;
        for (int i = 0; i < MAX_NODOS && iguales; i++) {
            iguales = Nodos[i] != null && g.Nodos[i] != null
                    || Nodos[i] == null && g.Nodos[i] == null;
            if (Nodos[i] != null && g.Nodos[i] != null) {
                iguales = Nodos[i].Info.equals(g.Nodos[i].Info);
            }
            for (int j = i; j < MAX_NODOS && iguales; j++) {
                iguales = Adyacencias[i][j] == g.Adyacencias[i][j];
            }
        }
        return iguales;
    }

    /*    
     Crea un grafo a partir de un String de aristas:
     Ejemplo: abbcad ? a-b, b-c, a-d    
     */
    public void creaGrafo(String aristas) {
        
        int l = aristas.length();
        for (int i = 0; i < l; i = i + 2) {
            try {
                
                Character x = new Character(aristas.charAt(i));
                Character y = new Character(aristas.charAt(i + 1));
                
                if (!this.EstaContenido((E) x)) {
                    this.InclNodo((E) x);
                }
                
                if (!this.EstaContenido((E) y)) {
                    this.InclNodo((E) y);
                }
                
                this.InclArco((E) x, (E) y);
                
            } catch (NoSuchElementException | TADLlenoException | NodoYaExisteException ex) {
            }
        }
        
    }
// </editor-fold>    

    /*
    IMPLEMENTACIONES DEL CURSO
     */
// <editor-fold desc="EJEMPLOS" defaultstate="collapsed">    
    public Lista<E> adyacentes(E x) {
        Lista<E> aux = new Lista_Dinamica();
        
        int posicion = BuscaElemento(x);
        
        if (posicion >= 0) {
            for (int i = 0; i < MAX_NODOS; i++) {
                if (Adyacencias[posicion][i]) {
                    aux.Añade(Nodos[i].Info);
                }                
            }
        }
        
        return aux;
    }
    
    public Lista<E>nodos() {
        Lista<E> aux=(Lista<E>)new Lista_Dinamica<E>();
        for(int i=0;i<MAX_NODOS;i++){
            if(Nodos[i]!=null){
                aux.Añade(Nodos[i].Info);
            }
        }
        return aux;
    }
// </editor-fold> 

// <editor-fold desc="BASICOS" defaultstate="collapsed">    
// </editor-fold> 
// <editor-fold desc="AVANZADOS" defaultstate="collapsed">    
// </editor-fold> 
// <editor-fold desc="CAMINOS" defaultstate="collapsed">    
// </editor-fold> 
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed">        
// </editor-fold> 
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIO" defaultstate="collapsed">        
// </editor-fold>
}
