
import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import Varios.Excepciones.TADVacioException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class A_UsoListas {

    // Uso de TAD LISTA a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {
        Lista<Integer> l = new Lista_Dinamica<>();

        for (int i = 1; i < 5; i++) {
            l.Añade(i); // o bien l.Añade(i). Java transforma 
        }
        l.Añade(1);
        
        l.Añade(1);
        
        l.Añade(1);
        
        // int a Integer (y viceversa).

        System.out.println("Lista : " + l);
        System.out.println(inversaIte(l));
        System.out.println(((Lista_Dinamica) l).inversa());
        System.out.println("----->" + interL(l));
        System.out.println("->" + norrep(l));

    }

// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
    public static <E> void mostrar(Lista<E> l) {
        try {
            if (l == null) {
                throw new IllegalArgumentException();
            } else {
                while (!l.EsVacia()) {
                    System.out.println(l.Cabeza()); //LEE LA CABEZA
                    l = l.Cola();//ELIMINA LA CABEZA
                }

            }
        } catch (Exception e) {

        }
    }

    public static <E> Lista<E> inversaIte(Lista<E> l) {
        try {
            if (l == null) {
                throw new IllegalArgumentException();
            }

            Lista<E> aux = new Lista_Dinamica();
            while (!l.EsVacia()) {
                aux.Añade(l.Cabeza());
                l = l.Cola();

            }
            return aux;

        } catch (Exception e) {
            return null;
        }

    }

    /* ultimo:: [a] ->a
        ultimo []=error "lista vacia"
        ultimo [x]= x
        ultimo (h:t)= ultimo t
     */
    public static <E> E ultimoREC(Lista<E> l) {
        try {
            if (l == null) {
                throw new IllegalArgumentException();
            } else if (l.EsVacia()) {
                throw new IllegalArgumentException();
            } else if (l.Longitud() == 1) {
                return l.Cabeza();
            } else {
                return ultimoREC(l.Cola());
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*pertenece:: [a]->a->Bool
    pertenece [] _ = False
    pertenece (h:t) x = if(x==h)||pertenece t x
     */
    public static <E> boolean pertenece(Lista<E> l, E x) {
        try {
            if (l == null) {
                throw new IllegalArgumentException();
            } else if (l.EsVacia()) {
                return false;
            } else {
                return l.Cabeza().equals(x) || pertenece(l.Cola(), x);
            }
        } catch (Exception e) {
            return false;
        }
    }

    /* quita::[a]->a->[a]
        quita [] _ = []
        quita (h:t) x= if(h==x) then quita t x
    else h:(quita t x)
    
     */
    public static <E> Lista<E> quitaREC(Lista<E> l, E x) {
        try {
            if (l == null) {
                throw new IllegalArgumentException();
            } else if (l.EsVacia()) {
                return new Lista_Dinamica();
            } else if (l.Cabeza().equals(x)) {
                return quitaREC(l.Cola(), x);
            } else {
                return new Lista_Dinamica(l.Cabeza(), (Lista_Dinamica) quitaREC(l.Cola(), x));
            }
        } catch (Exception e) {
            return null;
        }

    }

    /*izquierda:: [a]->a->[a]
      izquierda [] _ = []
      izquierda (h:t) x = if(x<0) then error "----"
    else if((mayorIgual(longitud(h:t) x)) then (h:t)
    else h:(izquierda t (x-1))
     */
    public static <E> Lista<E> izquierdaREC(Lista<E> l, int x) {
        try {
            if (l == null || x < 0) {
                throw new IllegalArgumentException();
            } else if (l.EsVacia()) {
                return new Lista_Dinamica();
            } else if (x >= l.Longitud()) {
                return l;
            } else if (x == 0) {
                return new Lista_Dinamica<>();
            } else {
                return new Lista_Dinamica<>(l.Cabeza(), (Lista_Dinamica) izquierdaREC(l.Cola(), x - 1));
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*suma:: [Integer] -> Int
    suma [] = 0
    suma (h:t) = h+suma t*/
    public static Integer sumaL(Lista<Integer> l) {
        try {
            if (l.EsVacia()) {
                return 0;
            } else {
                return (l.Cabeza() + sumaL(l.Cola()));
            }

        } catch (TADVacioException e) {
            return 0;
        }
    }
////ordenadaL::(Ord a)=>[a] -> Bool
////ordenadaL [] = True
////ordenadaL [x] = True
////ordenadaL (h1:h2:t)= if (h1>h2) then False
//			else ordenadaL (h2:t)

    //COMPARABLE=> SI A<B será negeativo; si A==B será 0; y si A>B será positivo.
    public static <E extends Comparable> Boolean ordenadaL(Lista<E> l) {
        try {
            if (l.EsVacia() || l.Longitud() == 1) {
                return true;
            } else {
                if (l.Cabeza().compareTo(l.Cola().Cabeza()) > 0) {
                    return false;
                } else {
                    return ordenadaL(l.Cola());
                }
            }

        } catch (TADVacioException e) {
            return false;
        }
    }
//insertarOrdenL ::(Ord a)=> a -> [a] -> [a]
//insertarOrdenL x [] = [x]
//insertarOrdenL x (h:t) = if(h>x) then (x:h:t) else h:(insertarOrdenL x t)

    public static <E extends Comparable> Lista<E> insertarOdenL(Lista<E> l, E x) {
        try {
            if (l.EsVacia()) {
                return new Lista_Dinamica(x, new Lista_Dinamica());
            } else {
                if ((x.compareTo(l.Cabeza())) < 0) {
                    return new Lista_Dinamica<>(x, (Lista_Dinamica) l);
                } else {
                    return new Lista_Dinamica<>(l.Cabeza(), (Lista_Dinamica) insertarOdenL(l.Cola(), x));
                }
            }
        } catch (TADVacioException e) {
            return null;
        }
    }

// </editor-fold>    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
//interL:: (Ord a)=> [a] -> [a] -- si x es mayor que y se intercalan.
//interL [] = []
//interL [x] = [x]
//interL (h1:h2:t) = if(h1>h2) then h2:(interL (h1:t))
//					else h1:(interL (h2:t))
    public static <E extends Comparable> Lista<E> interL(Lista<E> l) {
        try {

            if (l.EsVacia()) {
                return new Lista_Dinamica<>(null, null);
            } else if (l.Longitud() == 1) {
                return new Lista_Dinamica<>(l.Cabeza(), new Lista_Dinamica<E>());
            } else {
                E h1 = l.Cabeza();
                E h2 = l.Cola().Cabeza();
                Lista<E> t = l.Cola().Cola();
                if (h1.compareTo(h2) > 0) {

                    return new Lista_Dinamica(h2, (Lista_Dinamica) interL(new Lista_Dinamica<E>(h1, (Lista_Dinamica<E>) t)));
                } else {
                    return new Lista_Dinamica(h1, (Lista_Dinamica) interL(new Lista_Dinamica<E>(h2, (Lista_Dinamica<E>) t)));
                }
            }
        } catch (TADVacioException ex) {
            return null;
        }

    }

    public static <E> Lista<E> norrep(Lista<E> l) {
        try {

            if (l.EsVacia()) {
                return new Lista_Dinamica<>();
            } else if (l.Longitud() == 1) {
                return new Lista_Dinamica<>(l.Cabeza(), new Lista_Dinamica<E>());
            } else {
                E h1 = l.Cabeza();
                E h2 = l.Cola().Cabeza();
                Lista<E> t = l.Cola();
                if (h1.equals(h2)) {
                    return new Lista_Dinamica(new Lista_Dinamica<E>(), (Lista_Dinamica) norrep(t));
                } else {
                    t.Elimina(h1);
                    return new Lista_Dinamica(h1, (Lista_Dinamica<E>) norrep(t));
                }
            }
        } catch (TADVacioException e) {
            return l;
        }
    }

    public static <E> Lista<E> aplicaf(Lista<E> l) {
        try {
            if (l.EsVacia()) {
                return l;
            } else {
                Lista<E> Cola = l.Cola();
                E cabeza = l.Cabeza();
                //   return new Lista_Dinamica<E>(f(cabeza), (Lista_Dinamica<E>) aplicaf(Cola));
                return null;
            }
        } catch (TADVacioException e) {
            return null;
        }
    }

// </editor-fold>     
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>         
}
