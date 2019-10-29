
import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import D_TADArbol.ArbolBinario;
import D_TADArbol.ABD;
import Varios.Excepciones.TADVacioException;

public class D_UsoArbolesBinarios {

    // Uso de TAD ARBOL BINARIO a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {
        ABD<Integer> uno = new ABD<>(1);
        ABD<Integer> dos = new ABD<>(2);
        ABD<Integer> tres = new ABD<>(3);
        ABD<Integer> cuatro = new ABD<>(4);
        ABD<Integer> cinco = new ABD<>(5);

        ABD<Integer> subArbol1 = new ABD<>(6, tres, cuatro);
        ABD<Integer> subArbol2 = new ABD<>(7, subArbol1, uno);
        ABD<Integer> subArbol3 = new ABD<>(8, dos, cinco);

        ArbolBinario<Integer> arbol = new ABD<>(9, subArbol2, subArbol3);

        System.out.println(arbol);

        System.out.println("-----------------------");

        ABD<Character> d = new ABD("d");
        ABD<Character> e = new ABD("e");
        ABD<Character> c = new ABD("c", d, e);
        ABD<Character> g = new ABD("g");
        ABD<Character> f = new ABD("f", new ABD(), g);
        ABD<Character> b = new ABD("b", c, f);
        ArbolBinario<Character> arbol2 = new ABD("a", new ABD(), b);

        System.out.println(arbol2);
        System.out.println("-----------------------");
        System.out.println(((ABD) arbol).nNodos());
        System.out.println("-----------------------");
        System.out.println(nHojas(arbol));
        System.out.println("-----------------------");
        System.out.println(listaHojas(arbol2));
        System.out.println("-----------------------");
        System.out.println(preorden(arbol));
        
        System.out.println("------------------->>---------");
        System.out.println(arbol);
        System.out.println(huecos(arbol, 4));
    }

// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
    /* nNodos :: Arbol a -> Int
    nNodos AV = 0
    nNodos (AB r i d) = 1+ nNodos i + nNodos d
     */
    public static <E> int nNodos(ArbolBinario<E> a) {
        try {

            return 1 + nNodos(a.SubArbolIzqdo()) + nNodos(a.SubArbolDcho());

        } catch (TADVacioException e) {
            return 0;
        }
    }

    /*
    nHojas :: Arbol a -> Int
    nHojas AV = 0
    nHojas (AB r AV AV) = 1
    nHojas (AB r i d) = nHojas i + nHojas d 
     */
    public static <E> int nHojas(ArbolBinario<E> a) {
        try {
            if (a.SubArbolIzqdo().EsVacio() && a.SubArbolDcho().EsVacio()) {
                return 1;
            } else {
                return nHojas(a.SubArbolIzqdo()) + nHojas(a.SubArbolDcho());
            }

        } catch (TADVacioException e) {
            return 0;
        }
    }

    /*
    listaHojas:: Arbol a -> [a]
    listaHojas AV = []
    listaHojas (AB r AV AV) = [r]
    listaHojas (AB r i d) = listaHojas i ++ listaHojas d 
     */
    public static <E> Lista<E> listaHojas(ArbolBinario<E> a) {
        try {
            if (a.SubArbolIzqdo().EsVacio() && a.SubArbolDcho().EsVacio()) {
                return new Lista_Dinamica<>(a.Raiz(), new Lista_Dinamica<E>());
            } else {
                Lista<E> e = listaHojas(a.SubArbolIzqdo());
                e.Concatena(listaHojas(a.SubArbolDcho()));
                return e;
            }

        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }

    }

    /*
    preorden :: Arbol a -> [a]
    preorden AV = []
    preorden (AB r i d)= [r] ++ preorden i ++ preorden d
     */
    public static <E> Lista<E> preorden(ArbolBinario<E> a) {
        try {
            Lista<E> e = new Lista_Dinamica<>(a.Raiz(), new Lista_Dinamica<E>());
            e.Concatena(preorden(a.SubArbolDcho()));
            e.Concatena(preorden(a.SubArbolIzqdo()));
            return e;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    public static <E> String preordenS(ArbolBinario<E> a) {
        try {
            String s = new String();
            s = s + a.Raiz();
            s = s + preordenS(a.SubArbolIzqdo());
            s = s + preordenS(a.SubArbolDcho());

            return s;
        } catch (TADVacioException e) {
            return new String();
        }
    }

    /*
    nodosNivel :: Int -> Arbol a -> Int
    nodosNivel 0 _ = error "nivel no existente"
    nodosNivel _ AV = 0
    nodosNivel 1 t = 1
    nodosNivel x (AB r i d) =  (nodosNivel (x-1) i)+ (nodosNivel (x-1) d)
     */
    public static <E> int nNodosNivel(ArbolBinario<E> a, int x) {
        try {
            if (a.EsVacio()) {
                throw new TADVacioException();
            } else if (x <= 0) {
                throw new IllegalArgumentException();
            } else if (x == 1) {
                return 1;
            } else {
                return nNodosNivel(a.SubArbolIzqdo(), x - 1) + nNodosNivel(a.SubArbolDcho(), x - 1);
            }

        } catch (TADVacioException e) {
            return 0;
        }

    }

    /*
    altura :: Arbol a -> Int
    altura AV = 0
    altura (AB _ i d) = 1 + max (altura i) (altura d)
    
     */
    public static <E> int altura(ArbolBinario<E> a) {
        try {
            return 1 + Math.max(altura(a.SubArbolIzqdo()), altura(a.SubArbolDcho()));
        } catch (TADVacioException e) {
            return 0;
        }
    }
    
    public static <E> int huecos(ArbolBinario<E> a, int x){
        try{
            if(x==0){
                return 0;
            }
            
            else if(a.SubArbolIzqdo().EsVacio() && a.SubArbolDcho().EsVacio()){
                return 0;
            }
            else if(a.SubArbolDcho().EsVacio() && (!a.SubArbolIzqdo().EsVacio())){
                return 1+(huecos (a.SubArbolIzqdo(),(x-1)));
            }
            else if(a.SubArbolIzqdo().EsVacio() && (!a.SubArbolDcho().EsVacio())){
                return 1+(huecos(a.SubArbolDcho(),(x-1)));
            }
            else{
                return huecos(a.SubArbolIzqdo(),(x-1))+huecos(a.SubArbolDcho(),(x-1));
            }
            
            
        }catch(TADVacioException e){
            return 0;
        }
    }

// </editor-fold>
// <editor-fold desc="EJERCICIOS AVANZADOS" defaultstate="collapsed">
// </editor-fold>
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed">
    
    //Entregable 3 2016, etc
   // hecho en hojas
    
    
    
    
    
// </editor-fold>    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>    
}
