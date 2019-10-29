
import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import Varios.Excepciones.TADVacioException;

public class E_UsoArbolesGenerales {

    // Uso de TAD ARBOL GENERAL a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {

        ////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////
        //////////// Arbol General utilizando Listas ///////////////
        ////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////          
        System.out.println("Arbol General utilizando Listas: ");

        Lista sieteL = new Lista_Dinamica(7, new Lista_Dinamica<>()); // [7]

        Lista seisL = new Lista_Dinamica();   // []
        seisL.Añade(sieteL);                  // [6, [7]]
        seisL.Añade(6);

        Lista cincoL = new Lista_Dinamica(5, new Lista_Dinamica<>()); // [5]

        Lista cuatroL = new Lista_Dinamica(4, new Lista_Dinamica<>()); // [4]
        Lista tresL = new Lista_Dinamica(3, new Lista_Dinamica<>()); // [3]        

        Lista dosL = new Lista_Dinamica();   // []       
        dosL.Añade(cuatroL);                 // [[4]]
        dosL.Añade(tresL);                   // [[3], [4]]
        dosL.Añade(2);                       // [2, [3], [4]]      

        Lista unoL = new Lista_Dinamica();      // []
        unoL.Añade(seisL);                      // [[6, [7]]]
        unoL.Añade(cincoL);                     // [[5], [6, [7]]]
        unoL.Añade(dosL);                       // [[2, [3], [4]], [5], [6, [7]]]
        unoL.Añade(1);                          // [1, [2, [3], [4]], [5], [6, [7]]]

        System.out.println(unoL);
        System.out.println(nNodosG(unoL));
        System.out.println(nHojasG(unoL));
        System.out.println(inordenG(unoL));
        System.out.println(perteneceG(unoL, 0));
    }

// <editor-fold desc="EJERCICIOS de Arboles Generales utilizando Listas" defaultstate="collapsed">   
//nNodosG :: ArbolG a -> Int
//nNodosG AGV = 0
//nNodosG (AG r l) = 1 + nNodosGAux l
//nNodosGAux :: [ArbolG a] -> Int
//nNodosGAux [] = 0
//nNodosGAux (h:t) = nNodosG h + nNodosGAux t
    public static int nNodosG(Lista ag) {
        try {
            return 1 + nNodosGAux(ag.Cola());

        } catch (TADVacioException e) {
            return 0;
        }
    }

    public static int nNodosGAux(Lista hijos) {
        try {
            return nNodosG((Lista) hijos.Cabeza()) + nNodosGAux(hijos.Cola());
        } catch (TADVacioException e) {
            return 0;
        }
    }

//nHojasG :: ArbolG a -> Int
//nHojasG AGV = 0
//nHojasG (AG r []) = 1
//nHojasG (AG r l) = nHojasGAux l
//nHojasGAux :: [ArbolG a] -> Int
//nHojasGAux [] = 0
//nHojasGAux (h:t) = nHojasG h + nHojasGAux t
    public static int nHojasG(Lista ag) {
        try {
            if (ag.Cola().EsVacia()) {
                return 1;
            } else {
                return nHojasGAux(ag.Cola());
            }

        } catch (TADVacioException e) {
            return 0;
        }
    }

    public static int nHojasGAux(Lista l) {
        try {
            return nHojasG((Lista) l.Cabeza()) + nHojasGAux(l.Cola());

        } catch (TADVacioException e) {
            return 0;
        }
    }

//inordenG :: ArbolG a -> [a]
//inordenG AGV = []
//inordenG (AG r (h:t)) = inordenG h ++ [r] ++ inordenGAux t
//inordenGAux:: [ArbolG a] -> [a]
//inordenGAux [] = []
//inordenGAux (h:t) = inordenG h ++ inordenGAux t 
    public static Lista inordenG(Lista l) {
        try {
            Lista aux = inordenG((Lista) l.Cola().Cabeza());
            aux.Concatena(new Lista_Dinamica(l.Cabeza(), new Lista_Dinamica<>()));
            aux.Concatena(inordenGAux(l.Cola().Cola()));
            return aux;

        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }
    }

    public static <E> Lista<E> inordenGAux(Lista<E> l) {
        try {
            Lista aux;
            aux = inordenG((Lista) l.Cabeza());
            aux.Concatena(inordenGAux(l.Cola()));
            return aux;

        } catch (TADVacioException e) {
            return new Lista_Dinamica<>();
        }

    }

//perteneceG ::(Eq a)=> ArbolG a -> a -> Bool
//perteneceG AGV _= True
//perteneceG (AG r l) x = if(x==r) then True
//			  else perteneceGAux l x
//perteneceGAux ::(Eq a)=> [ArbolG a] -> a -> Bool
//perteneceGAux [] _ = True
//perteneceGAux (h:t) x = (perteneceG h x) || (perteneceGAux t x)  
    public static Boolean perteneceG(Lista l, Object o) {
        try {
            if (o.equals(l.Cabeza())) {
                return true;
            } else {
                return perteneceGAux(l.Cola(), o);
            }

        } catch (TADVacioException e) {
            return false;
        }

    }

    public static Boolean perteneceGAux(Lista l, Object o) {
        try {
            return (perteneceG((Lista) l.Cabeza(), o)) || (perteneceGAux(l.Cola(), o));

        } catch (TADVacioException e) {
            return false;
        }

    }

//alturaG :: ArbolG a -> Int
//alturaG AGV = 0
//alturaG (AG r l) = 1 + (alturaGAux l)
//
//alturaGAux :: [ArbolG a] -> Int
//alturaGAux [] = 0
//alturaGAux (h:t) = max(alturaG h)(alturaGAux t)
    public static int alturaG(Lista l) {
        try {
            return 1 + alturaGAux(l.Cola());

        } catch (TADVacioException e) {
            return 0;
        }

    }

    public static int alturaGAux(Lista<Lista> l) {
        try {
            return Math.max(alturaG(l.Cabeza()), alturaGAux(l.Cola()));
        } catch (TADVacioException e) {
            return 0;
        }

    }

// </ editor-fold>
// <editor-fold desc="EJERCICIOS AVANZADOS" defaultstate="collapsed">                            
// </editor-fold>    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed">
// </editor-fold>    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>    
}
