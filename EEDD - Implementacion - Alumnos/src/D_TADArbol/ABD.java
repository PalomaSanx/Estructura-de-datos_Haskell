package D_TADArbol;

import Varios.Nodos.NodoArbol;
import Varios.Excepciones.TADVacioException;

public class ABD<E> implements ArbolBinario<E>, Cloneable {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">             
    NodoArbol<E> raiz;     // Ra?z del ?rbol binario
// </editor-fold>    

// <editor-fold desc="CONTRUCTORES" defaultstate="collapsed">             
    public ABD() { // Crea un ?rbol vac?o
        raiz = null;
    }

    public ABD(E e) { // Crea un ?rbol con e como ra?z 
        raiz = new NodoArbol<E>(e, null, null);
    }

    ABD(NodoArbol<E> n) {
        raiz = n;
    }

    public ABD(E x, ABD<E> i, ABD<E> d) {
        // Crea un ?rbol que tiene en el nodo ra?z el valor x, y
        // tiene como hijo izquierdo el sub?rbol iz, y como hijo
        // derecho el sub?rbol de (Operaci?n Cons).
        raiz = new NodoArbol<E>(x, i.raiz, d.raiz);
    }

    public ABD(E x, ArbolBinario<E> i, ArbolBinario<E> d) {
        // Como el anterior, pero i, d son arboles cualquiera (no neces. din?micos).
        this.raiz = new ABD<E>(x, cast(i), cast(d)).raiz;
    }    
// </editor-fold>        

// <editor-fold desc="METODOS YA IMPLEMENTADOS" defaultstate="collapsed">      
    private ABD<E> cast(ArbolBinario<E> a) {
        try {
            // crea un arbol binario din?mico a partir de otro binario cualquiera
            return new ABD<E>(a.Raiz(), cast(a.SubArbolIzqdo()), cast(a.SubArbolDcho()));
        } catch (TADVacioException ex) {
            return new ABD<E>(); // si a es vacio
        }
    }
    
    public boolean EsVacio() {
        return raiz == null;
    }

    public E Raiz() throws TADVacioException {
        if (raiz == null) {
            throw new TADVacioException();
        } else {
            return raiz.valor;
        }
    }

    public ArbolBinario<E> SubArbolIzqdo() throws TADVacioException {
        if (raiz == null) {
            throw new TADVacioException();
        } else {
            return new ABD<E>(raiz.iz);
        }
    }

    public ArbolBinario<E> SubArbolDcho() throws TADVacioException {
        if (raiz == null) {
            throw new TADVacioException();
        } else {
            return new ABD<E>(raiz.de);
        }
    }

    @Override
    public Object clone() {
        ABD<E> a = null;
        try {
            a = (ABD<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        try {
            if (!EsVacio()) {
                a.raiz = (NodoArbol<E>) raiz.clone();
                if (!SubArbolIzqdo().EsVacio()) {
                    a.raiz.iz = ((ABD<E>) SubArbolIzqdo().clone()).raiz;
                }
                if (!SubArbolDcho().EsVacio()) {
                    a.raiz.de = ((ABD<E>) SubArbolDcho().clone()).raiz;
                }
            }
        } catch (TADVacioException e) {
            System.out.println(e);
        }
        return a;
    }

    public String toString() {
        return tstr("    ");
    }

    private String tstr(String s) {
        try {
            return ((ABD<E>) this.SubArbolDcho()).tstr(s + "     ")
                    + "\n" + s + this.Raiz()
                    + ((ABD<E>) this.SubArbolIzqdo()).tstr(s + "     ");
        } catch (TADVacioException ex) {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArbolBinario)) {
            throw new IllegalArgumentException();
        }
        ArbolBinario<E> a = (ArbolBinario<E>) o;
        boolean iguales = raiz == null && a.EsVacio();

        if (raiz != null && !a.EsVacio()) {
            try {
                iguales = this.Raiz().equals(a.Raiz())
                        && this.SubArbolIzqdo().equals(a.SubArbolIzqdo())
                        && this.SubArbolDcho().equals(a.SubArbolDcho());
            } catch (TADVacioException ex) {
                ex.printStackTrace();
            }
        }

        return iguales;
    }

    /*
     Metodo que les doy en el fichero de alumnos para que se muestre
     el arbol de una forma mas legible.
     */
    public String miToString() {
        String s;
        s = new String("");
        if (raiz != null) {
            try {
                s += raiz.valor.toString();
                if (!SubArbolIzqdo().EsVacio()) {
                    ABD i = (ABD) this.SubArbolIzqdo();
                    s += " <" + i.miToString() + ">";
                }
                if (!SubArbolDcho().EsVacio()) {
                    ABD d = (ABD) this.SubArbolDcho();
                    s += " [" + d.miToString() + "]";
                }
            } catch (TADVacioException e) {
                System.out.println(e);
            }
        }
        return s;
    }

    /*
     Metodo que les doy en el fichero de alumnos que pinta el arbol
     en pantalla.
     */
    public void dibujar() {
        this.dibujar(this, new String());
    }

    private void dibujar(ArbolBinario<E> a, String indentacion) {
        try {
            if (a.Raiz() != null) {
                if (!a.SubArbolDcho().EsVacio()) {
                    this.dibujar(a.SubArbolDcho(), indentacion + "     ");
                }

                System.out.println(indentacion + "|" + a.Raiz().toString());

                if (!a.SubArbolIzqdo().EsVacio()) {
                    this.dibujar(a.SubArbolIzqdo(), indentacion + "     ");
                }

            }
        } catch (TADVacioException e) {
        }
    }
// </editor-fold> 

/*
    IMPLEMENTACIONES DEL CURSO
*/
    
// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">                 
 public int nNodos(){
    return nNodos(this.raiz); 
 }
 
 private int nNodos(NodoArbol<E> r){
     if(r==null){
         return 0;
     }else{
         return 1+nNodos(r.iz)+nNodos(r.de);
     }
 }
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS AVANZADOS" defaultstate="collapsed">                            

// </editor-fold>
    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
 
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
    
// </editor-fold>  
    
} 