
import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import E_TADGrafo.*;
import Varios.Excepciones.TADVacioException;
import Varios.Excepciones.*;

public class G_UsoGrafos {

    // Uso de TAD GRAFO a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {
        GrafoNoDirigidoEstatico<Character> g = new GrafoNoDirigidoEstatico<>();
        g.creaGrafo("01060712233445510");
        
        System.out.println(g);
        System.out.println(camMLxNoNodo(g, '0'));
        System.out.println(camMLxyNoNodo(g, '0', '6'));
        System.out.println(camMLxNoNodoG3(g, '2'));
        System.out.println(camMLxCrec(g, '3'));
        System.out.println(camMLxMay(g, '5', '1'));
        System.out.println(camMLxLong(g, '0', 4));
        System.out.println(camMLxpNoNodo(g, '0', '7'));
        System.out.println(camMLxNoArco(g, '4'));
        System.out.println("------------------->");
        
        System.out.println(aislados(g));
    }

// <editor-fold desc="EJEMPLOS" defaultstate="collapsed">
    public static int nNodos(GrafoEstatico g) {
        return g.nodos().Longitud();
    }

    public static <E> Lista<E> lNodos(GrafoEstatico g) {
        return g.nodos();
    }

    public static <E> boolean existeNodo(GrafoEstatico g, E e) {
        return g.EstaContenido(e);
    }

    public static <E> void añadirNodo(GrafoEstatico g, E e) {
        try {
            g.InclNodo(e);

        } catch (TADLlenoException | NodoYaExisteException ex) {
            System.out.println(ex);
        }
    }

    public static <E> boolean existeArco(GrafoEstatico g, E x1, E x2) {
        return g.EsAdyacente(x1, x2);
    }

    public static <E> int nArcos(GrafoEstatico g) {
        try {
            Lista<E> nodos = g.nodos();
            int c = 0;
            for (int i = 0; i < nodos.Longitud(); i++) {
                for (int j = i + 1; j < nodos.Longitud(); j++) {
                    if (g.EsAdyacente(nodos.elemAt(i), nodos.elemAt(j))) {
                        c++;
                    }
                }
            }
            return c;
        } catch (TADVacioException e) {
            return 0;
        }
    }

    public static <E> Lista<Lista<E>> listaArcos(GrafoEstatico g) {
        try {
            Lista<E> nodos = g.nodos();
            Lista<Lista<E>> grande = new Lista_Dinamica<Lista<E>>();

            for (int i = 0; i < nodos.Longitud(); i++) {
                for (int j = i + 1; j < nodos.Longitud(); j++) {
                    if (g.EsAdyacente(nodos.elemAt(i), nodos.elemAt(j))) {
                        Lista<E> peq = new Lista_Dinamica<>();
                        peq.Añade(nodos.elemAt(i));
                        peq.Añade(nodos.elemAt(j));
                        grande.Añade(peq);
                    }
                }
            }

            return grande;

        } catch (TADVacioException e) {
            return new Lista_Dinamica();
        }

    }

    public static <E> void añadirArco(GrafoEstatico g, E x1, E x2) {
        g.InclArco(x1, x2);
    }

    public static <E> Lista<E> adyacentes(GrafoEstatico g, E e) {
        return g.adyacentes(e);
    }

    //ORDEN
    //RANGO
    public static <E> boolean EsCompleto(GrafoEstatico g) {

        if (nNodos(g) * (nNodos(g) - 1) == nArcos(g) * 2) {
            return true;
        }
        return false;
    }

    public static <E> int orden(GrafoEstatico g, E x) {
        return adyacentes(g, x).Longitud();
    }

//    public static<E> int rango(GrafoEstatico g){
//        try{
//        Lista<E> nodos= g.nodos();
//        int max= 0;
//        
//        while(!nodos.EsVacia()){
//            if(max < orden(g.nodos().Cabeza())){
//               max=orden(g,nodos.Cabeza()); 
//            }
//            nodos=nodos.Cola();
//        }
//        }catch(TADVacioException e){
//            return 0;
//        }
//        
//    }
// </editor-fold>
// <editor-fold desc="BASICOS" defaultstate="collapsed">
// </editor-fold>
// <editor-fold desc="AVANZADOS" defaultstate="collapsed">
// </editor-fold>
// <editor-fold desc="CAMINOS" defaultstate="collapsed">    
    public static <E> Lista<E> camMLxNoNodo(Grafo<E> g, E primero) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxNoNodo(g, primero, new Lista_Dinamica<E>());
        }
    }

    public static <E> Lista<E> camMLxNoNodo(Grafo<E> g, E actual, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxNoNodo(g, adyacente, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //-----------------------------------
    public static <E> Lista<E> camMLxyNoNodo(Grafo<E> g, E primero, E ultimo) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero) || !g.EstaContenido(ultimo)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxyNoNodo(g, primero, ultimo, new Lista_Dinamica<E>());
        }
    }

    public static <E> Lista<E> camMLxyNoNodo(Grafo<E> g, E actual, E ultimo, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia() && !actual.equals(ultimo)) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxyNoNodo(g, adyacente, ultimo, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }

    }

    //------------------------------------------
    public static <E> Lista<E> camMLxLNoNodo(Grafo<E> g, E primero, Lista<E> ultimos) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxLNoNodo(g, primero, ultimos, new Lista_Dinamica<E>());
        }
    }

    public static <E> Lista<E> camMLxLNoNodo(Grafo<E> g, E actual, Lista<E> ultimos, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia() && !ultimos.EstaContenido(actual)) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxLNoNodo(g, adyacente, ultimos, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //--------------------
    public static <E> Lista<E> camMLxNoNodoG3(Grafo<E> g, E primero) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxNoNodoG3(g, primero, new Lista_Dinamica<E>());
        }
    }

    public static <E> Lista<E> camMLxNoNodoG3(Grafo<E> g, E actual, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente) && g.adyacentes(adyacente).Longitud() >= 3) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxNoNodoG3(g, adyacente, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //-------------------------
    public static <E extends Comparable> Lista<E> camMLxCrec(Grafo<E> g, E primero) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxCrec(g, primero, new Lista_Dinamica<E>());
        }
    }

    public static <E extends Comparable> Lista<E> camMLxCrec(Grafo<E> g, E actual, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente) && adyacente.compareTo(actual) >= 0) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxCrec(g, adyacente, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //----------------------------------------
    public static <E extends Comparable> Lista<E> camMLxMay(Grafo<E> g, E primero, E nodo) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxMay(g, primero, nodo, new Lista_Dinamica<E>());
        }
    }

    public static <E extends Comparable> Lista<E> camMLxMay(Grafo<E> g, E actual, E nodo, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente) && adyacente.compareTo(nodo) > 0) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxMay(g, adyacente, nodo, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //------------------------
    public static <E extends Comparable> Lista<E> camMLxLong(Grafo<E> g, E primero, int longi) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxLong(g, primero, longi, new Lista_Dinamica<E>());
        }
    }

    public static <E extends Comparable> Lista<E> camMLxLong(Grafo<E> g, E actual, int longi, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia() && visitados.Longitud() < longi - 1) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxLong(g, adyacente, longi, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //-----------------
    public static <E extends Comparable> Lista<E> camMLxpNoNodo(Grafo<E> g, E primero, E paso) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxpNoNodo(g, primero, paso, new Lista_Dinamica<E>());
        }
    }

    public static <E extends Comparable> Lista<E> camMLxpNoNodo(Grafo<E> g, E actual, E paso, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxpNoNodo(g, adyacente, paso, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud() && devolucion.EstaContenido(paso)) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    //------
    public static <E> Lista<E> camMLxNoArco(Grafo<E> g, E primero) {//camino mas largo desde un nodo 'x' sin repetidos
        if (!g.EstaContenido(primero)) {
            return new Lista_Dinamica<>();
        } else {
            return camMLxNoArco(g, primero, new Lista_Dinamica<E>());
        }
    }

    public static <E> Lista<E> camMLxNoArco(Grafo<E> g, E actual, Lista_Dinamica<E> visitados) {
        try {
            Lista<E> camino, adyacentes, devolucion;
            camino = new Lista_Dinamica<>();
            adyacentes = g.adyacentes(actual);
            while (!adyacentes.EsVacia()) {//1-PRIMER PUNTO IMPORTANTE ¿cuando hemos termiando de probar caminos?
                E adyacente = adyacentes.Cabeza();
                adyacentes = adyacentes.Cola();
                if (!estaArco(visitados, actual, adyacente) && !visitados.EstaContenido(adyacente)) {//2-SEGUNDO PUNTO IMPORTANTE ¿Qué nodos metemos al camino?
                    devolucion = camMLxNoArco(g, adyacente, new Lista_Dinamica<E>(actual, visitados));
                    if (camino.Longitud() < devolucion.Longitud()) { //3-PUNTO IMPORTANTE-> Otros:(tamaño,etc)...
                        camino = devolucion;
                    }
                }
            }
            camino.Añade(actual);//para que este por orden, porque el añade lo 'añade' al final
            return camino;
        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    public static <E> boolean estaArco(Lista<E> visitados, E actual, E adyacente) {
        try {
            while (!visitados.EsVacia()) {
                E h = visitados.Cabeza();
                visitados = visitados.Cola();
                E h2 = visitados.Cabeza();
                visitados = visitados.Cola();
                if (h.equals(actual) && h2.equals(adyacente) || h.equals(adyacente) && h2.equals(actual)) {
                    return true;
                }
            }

        } catch (TADVacioException e) {
            return false;
        }
        return false;
    }

// </editor-fold>
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
//nNodosAislados :: (Eq a)=> Grafo a -> Int
//nNodosAislados g = nNodosAisladosAux (listaNodosGr g) g
//
//nNodosAisladosAux :: (Eq a)=> [a] -> Grafo a -> Int
//nNodosAisladosAux (h:h2:t) g = if(length(sucesores h g)==0) then 1+nNodosAisladosAux t g
//							else nNodosAisladosAux t g
    public static <E> int aislados(Grafo<E> g) {
        try {
            Lista<E> aux = g.nodos();
            int c = 0;

            while (!aux.EsVacia()) {
                if (g.adyacentes(aux.Cabeza()).Longitud()== 0) {
                    c = c++;
                    aux = aux.Cola();
                }else{
                    
                    aux=aux.Cola();
                }
            }
            return c;

        } catch (TADVacioException e) {
            return 0;
        }

    }

// </editor-fold>     
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">    
// </editor-fold> 
}
