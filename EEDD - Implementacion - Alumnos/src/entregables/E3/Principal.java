package entregables.E3;

import A_TADLista.*;
import D_TADArbol.ABD;
import D_TADArbol.ArbolBinario;
import Varios.Excepciones.TADVacioException;
import java.util.List;

/**
 *
 * @author PALOMA
 */
public class Principal {

       public static void main(String[] args) {
        ArbolBinario<Integer> a, b, c, d, e, f, g, h, i, j;

        a = new ABD<Integer>(1, new ABD<Integer>((int) 'a'), new ABD<Integer>());
        b = new ABD<Integer>(1, new ABD<Integer>((int) 'b'), new ABD<Integer>());
        c = new ABD<Integer>(2, new ABD<Integer>((int) 'c'), new ABD<Integer>());
        d = new ABD<Integer>(3, new ABD<Integer>((int) 'd'), new ABD<Integer>());
        e = new ABD<Integer>(5, new ABD<Integer>((int) 'e'), new ABD<Integer>());
        f = new ABD<Integer>(10, new ABD<Integer>((int) 'f'), new ABD<Integer>());
        g = new ABD<Integer>(16, new ABD<Integer>((int) 'g'), new ABD<Integer>());
        h = new ABD<Integer>(17, new ABD<Integer>((int) 'h'), new ABD<Integer>());
        i = new ABD<Integer>(95, new ABD<Integer>((int) 'i'), new ABD<Integer>());
        j = new ABD<Integer>(100, new ABD<Integer>((int) 'j'), new ABD<Integer>());
        
        Lista<ArbolBinario<Integer>> la;
        la = new Lista_Dinamica<ArbolBinario<Integer>>();

        //insertamos los símbolos de menos a mayor en función de la probabilidad de aparición 
          la.Añade(a);
          la.Añade(b);
           la.Añade(c);
            la.Añade(d);
             la.Añade(e);
              la.Añade(f);
               la.Añade(g);
                la.Añade(h);
                 la.Añade(i);
                  la.Añade(j);
        
        
        System.out.println(huffmanTree(la));

    }

    public static ArbolBinario<Integer> huffmanTree(Lista<ArbolBinario<Integer>> la) {
        try {
            ArbolBinario<Integer> aux, h1, h2;
            while (la.Longitud() > 1) {
                h1 = la.Cabeza();
                la = la.Cola();
                h2 = la.Cabeza();
                la = la.Cola();

                aux = new ABD(h1.Raiz() + h2.Raiz(), h1, h2);
                la = inserta(la, aux);
            }
            return la.Cabeza();

        } catch (TADVacioException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Lista<ArbolBinario<Integer>> inserta(Lista<ArbolBinario<Integer>> la, ArbolBinario<Integer> a) throws TADVacioException {
        if (la.EsVacia() || a.Raiz() < la.Cabeza().Raiz()) {
            la.Añade(a);
        } else {
            ArbolBinario<Integer> aux2 = la.Cabeza();
            la = la.Cola();
            la = inserta(la, a);
            la.Añade(aux2);
        }
        return la;
    }

}
