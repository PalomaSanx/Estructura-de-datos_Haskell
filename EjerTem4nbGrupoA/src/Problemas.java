
import PaqTADArbol.*;
import PaqTADLineales.PaqLista.*;
import PaqTADVacioException.TADVacioException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Ejercicios {
    // Métodos sobre eedds 'fuera' de las clases 
    // que las implementan

    /*
   * nNodos :: Arbol a -> Int
     nNodos AV = 0
     nNodos (AB r i d) = 1 + (nNodos i) + (nNodos d)
     */
    public static <E> int nNodos(ArbolBinario<E> a) {
//      if(a.EsVacio())
//          return 0;
        try {
            return 1 + nNodos(a.SubArbolIzqdo()) + nNodos(a.SubArbolDcho());
        } catch (TADVacioException ex) {
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
        } catch (TADVacioException ex) {
            return 0;
        }
    }

    /*
     preorden :: Arbol a -> [a]
     preorden AV          = []
     preorden (AB r i d) = (r : preorden i) ++ (preorden d)
     */
    public static <E> Lista<E> preorden(ArbolBinario<E> a) {
        try {
            Lista<E> aux = preorden(a.SubArbolIzqdo());
            aux.Concatena(preorden(a.SubArbolDcho()));
            aux.Añade(a.Raiz());
            return aux;
        } catch (TADVacioException ex) {
            return new Lista_Dinamica();
        }
    }

    /**
     * elemAt :: String -> Arbol a -> a
     *
     * elemAt _ AV = error "arbol vacio" elemAt [] (AB r i d) = r elemAt (h:t)
     * (AB _ i d) = if (h=='0') then elemAt t i else elemAt t d
     */
    public static <E> E elemAt(String s, ArbolBinario<E> a) throws TADVacioException {
        if (s.isEmpty()) {
            return a.Raiz();
        }
        if (s.charAt(0) == '0') {
            return elemAt(s.substring(1), a.SubArbolIzqdo());
        } else {
            return elemAt(s.substring(1), a.SubArbolDcho());
        }
    }

    // árbol de caracteres a partir de su inorden (de todos los posibles, el más equilibrado)
    public static ABD<Character> abc(String s) {
        int m = s.length() / 2;
        if (s.isEmpty()) {
            return new ABD();
        }
        return new ABD(s.charAt(m), // raíz
                abc(s.substring(0, m)), // subarb. izdo
                abc(s.substring(m + 1)) // subarb. dcho.
        );
    }

    /*
-- nivel n-simo de un árbol:
nivel :: Int -> Arbol a -> [a]
nivel _  AV        = []
nivel 1 (AB r _ _) = [r]  
nivel n (AB _ i d) = (nivel (n-1) i) ++ (nivel (n-1) d)   
     */
    public static <E> Lista<E> nivel(int n, ArbolBinario<E> a) {
        Lista<E> lis = new Lista_Dinamica();
        try {
            if (n == 1) {

                lis.Añade(a.Raiz());
                return lis;
            } else {
                lis = nivel(n - 1, a.SubArbolIzqdo());
                lis.Concatena(nivel(n - 1, a.SubArbolDcho()));
                return lis;
            }
        } catch (TADVacioException ex) {
            return lis;
        }
    }

    /*
-- recorrido por niveles (todos los niveles de un árbol):
     reNiveles :: Arbol a -> [a]	
     recNiveles a = niveles 1 (altura a)
     niveles :: Arbol a -> [a]
     niveles AV _ _ = []
     niveles a i n = if (i<=n) then ... else ... -- hacer
     */
    public static <E> Lista<E> recNiveles(ArbolBinario<E> a) {
        // todos lo niveles concatenados
        Lista<E> recorrido = new Lista_Dinamica<E>();
        int altura = altura(a);
        for (int i = 1; i <= altura; i++) {
            recorrido.Concatena(nivel(i, a));
        }
        return recorrido;
    }

    public static <E> Lista<E> recNiveles2(ArbolBinario<E> a) {
        // todos lo niveles concatenados
        return recNivelesAux(a, 1);
    }

    // niveles a partir de n
    public static <E> Lista<E> recNivelesAux(ArbolBinario<E> a, int n) {
        Lista<E> aux = new Lista_Dinamica();
        aux = nivel(n, a);
        if (!a.EsVacio()) {
            aux.Concatena(recNivelesAux(a, n + 1));
        }
        return aux;
    }

    /**
     * moticulo (heap) heap :: (Ord a) => Arbol a -> Bool heap AV = True heap
     * (AB r AV AV)= True heap (AB r i AV) = (heap i)&&(r>=(raiz i)) heap (AB r
     * AV d) = (heap d)&&(r>=(raiz d)) heap (AB r i d) = (heap i) && (heap d) &&
     * (r>=(raiz i))&&(r>=>(raiz d))
     */
// public static <E extends Comparable<E>> boolean heap(Arbol<E> a){
//     return      ....
//            &&   ....
//            &&   (... || ... )
//            &&   (... || ... )          
// }
    /*
  mayor :: (Ord a) => Arbol a -> a
  mayor AV  = error "arbol vacio"
  mayor (AB r AV AV) = r
  mayor (AB r AV d)  = max r (mayor d)
  mayor (AB r i AV)  = max r (mayor i)
  mayor (AB r i d)   = max r (max (mayor i) (mayor d))
     */
    public static <E extends Comparable<E>> E mayor(ArbolBinario<E> a) throws TADVacioException {
        E mayor = a.Raiz();

        // comparar con el mayor del izdo
        // comparar con el mayor del dcho
        return mayor;
    }

    /*
  maximo, minimo :: (Ord a) => Arbol a -> a  
  maximo (AB r _ AV) = r
  maximo (AB _ _ d )  = maximo d
  minimo (AB r AV _) = r
  minimo (AB _ i _ ) = minimo i
     */
    public static <E extends Comparable<E>> E maximo(ArbolBinario<E> a) throws TADVacioException {
        if (a.SubArbolDcho().EsVacio()) {
            return a.Raiz();
        } else {
            return maximo(a.SubArbolDcho());
        }
    }

    public static <E extends Comparable<E>> E maximoit(ArbolBinario<E> a) throws TADVacioException {
        while (!a.SubArbolDcho().EsVacio()) {
            a = a.SubArbolDcho();
        }
        return maximo(a.SubArbolDcho());
    }

    public static <E extends Comparable<E>> E minimo(ArbolBinario<E> a) throws TADVacioException {
        if (a.SubArbolIzqdo().EsVacio()) {
            return a.Raiz();
        } else {
            return minimo(a.SubArbolIzqdo());
        }
    }

 /*
 busqueda :: (Ord a) => Arbol a -> Bool
 busqueda AV = True
 busqueda (AB r i d) =   busqueda i 
                      && busqueda d 
                      && ( (vacio i) || (r > maximo i))
                      && ( (vacio d) || (r < minimo d))                                      
 */
  public static <E extends Comparable<E>> boolean busqueda(ArbolBinario<E> a){
        try {
            ArbolBinario<E> i = a.SubArbolIzqdo(),
                            d = a.SubArbolDcho();
                         E  r = a.Raiz();
            return busqueda(i) &&
                   busqueda(d) &&
                   (i.EsVacio() || r.compareTo(maximo(i)) > 0) &&
                   (d.EsVacio() || r.compareTo(minimo(d)) < 0); 
        } catch (TADVacioException ex) {
            return true;  // caso arbol vacio
        }
  } 
    

    /*
inserta, extrae :: (Ord a) => a -> Arbol a -> Arbol a

inserta x AV = AB x AV AV
inserta x (AB r i d) |  x < r    =  AB r (inserta x i) d
                     |  x > r    =  AB r i (inserta x d)
                     |  True     = error "el elemento ya esta en el arbol"
     */
public static <E extends Comparable<E>> ArbolBinario<E> inserta(E x,ArbolBinario<E> a){
        try {
            ArbolBinario<E> i = a.SubArbolIzqdo(),
                            d = a.SubArbolDcho();
                         E  r = a.Raiz();
           
             if(x.compareTo(r) < 0) return new ABD<E>(r, (ABD) inserta(x, i), (ABD) d);
             else if(x.compareTo(r) > 0) return new ABD<E>(r, (ABD) i, (ABD) inserta(x,d));
             else throw new IllegalArgumentException("el elemento ya esta en el arbol");                
        } catch (TADVacioException ex) {
            return new ABD<E>(x);  // caso arbol vacio
        }
  }    

/*
extrae x AV = AV
extrae x (AB r i d) | x < r    = AB r (extrae x i) d
                    | x > r    = AB r i (extrae x d)
                    | vacio i  = d
                    | vacio d  = i
                    | True     = AB (minimo d) i (extrae (minimo d) d)
       -- o bien    | True     = AB m (extrae m i) d
                  where m = maximo i
*/
    
public static <E extends Comparable<E>> ArbolBinario<E> extrae(E x,ArbolBinario<E> a){
        try {
            ArbolBinario<E> i = a.SubArbolIzqdo(),
                            d = a.SubArbolDcho();
                         E  r = a.Raiz();
           
             if(x.compareTo(r) < 0) return new ABD<E>(r, (ABD) extrae(x, i), (ABD) d);
             if(x.compareTo(r) > 0) return new ABD<E>(r, (ABD) i, (ABD) extrae(x,d));
             if(i.EsVacio())return d;
             if(d.EsVacio())return i;
             else{
                 E m = maximo(i);
                 return new ABD(m,(ABD)extrae(m,i),(ABD)d);
             }          
        } catch (TADVacioException ex) {
            return new ABD<E>();  // caso arbol vacio
        }    
  }    

/*
    hoja :: (Eq a) => a -> Arbol a -> Bool
    hoja _  AV  = False
    hoja x (AB r i d) = (r==x) && (vacio i)  && (vacio d) 
                        || (x<r) && (hoja x i) 
                        || (x>r) && (hoja x d)
*/
public static <E> boolean hoja(E x, ArbolBinario<E> a){
        try {
            E r = a.Raiz();
            ArbolBinario<E> i = a.SubArbolIzqdo(),
                    d = a.SubArbolDcho();
//            if(a.EsVacio()){
//                return false;
//            }         
            return (r.equals(x))&& (i.EsVacio())  && (d.EsVacio())
                    || hoja(x,i)
                    || hoja(x,d);
        } catch (TADVacioException ex) {
            return false;
        }
}

/*
padre :: a -> Arbol a -> a
padre x AV  = error "no existe"
padre x (AB r i d) | (x==r)                          = error "no existe el padre"
                   | (not (vacio i)) && x==(raiz i)  = r
                   | (not (vacio d)) && x==(raiz d)  = r    
                   | (elem x i)                      = padre x i
                   | True                            = padre x d
*/


 // Arboles generales:
/*
  numElem :: ArbolG a -> Int
  numElem AGV = 0
  numElem (AG r ls) = 1 + (numElemLista ls) 

  numElemLista :: [ArbolG a] -> Int
  numElemLista []      = 0
  numElemLista (h : t) = (numElem h) + (numElemLista t)
*/

public static int numElemBucle(Lista ag){
    try{
        int n=0;
       ag = ag.Cola();
        while(!ag.EsVacia()){
            n = n + numElemBucle( (Lista) ag.Cabeza());
            ag=ag.Cola();
        }      
        return 1+n;            
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int numElem(Lista ag){
    try{
        return 1+numElemLista(ag.Cola());
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int numElemLista(Lista<Lista> l){
    try{
        return numElem(l.Cabeza())+ numElemLista(l.Cola());
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int altura(Lista ag){
    try{
        return 1+alturaAux(ag.Cola());
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int alturaAux(Lista<Lista> l){
    try{
        return Math.max(altura(l.Cabeza()), alturaAux(l.Cola()));
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int alturaBucle(Lista ag){  
            int n=0;
        //  if(ag.EsVacia())
        //   return 0;
        try {        
            ag = ag.Cola();  // quitamos la raiz
            while(!ag.EsVacia()){
                n = Math.max(n,altura((Lista)ag.Cabeza()));
                ag = ag.Cola();
            }
            return n+1;
        } catch (TADVacioException ex) {
          return 0;
        }
}

public static int rango(Lista ag){
    try{
        return Math.max(rangoAux(ag.Cola()), ag.Cola().Longitud());
    }catch(TADVacioException ex){
        return 0;
    }
}

public static int rangoAux(Lista<Lista> l){
    try{
        return Math.max(rango(l.Cabeza()), rangoAux(l.Cola()));
    }catch(TADVacioException ex){
        return 0;
    }
}


// Auxiliar para crear un Arbol general de caracteres en forma de lista, sin 
// elementos repetidos, a partir del preorden y el postorden
// Si no es posible, se lanza la exc. IllegalArgument
public static Lista agc(String pre, String pos) throws IllegalArgumentException
    {
        Lista aginv = new Lista_Dinamica(); // para guardar el resultado invertido
        String preh=new String(), 
               posh=new String();
        int i=0,lenpre=pre.length(),
                lenpos=pos.length();
        char c;
        if (lenpre!=lenpos) throw new IllegalArgumentException();
        if (lenpre != 0)
        {
            aginv.Añade(pre.charAt(0)); // raiz
            i=0; // posicion en posor        
            while(i<lenpos-1){
                c=pre.charAt(i+1);
                preh="";
                posh="";
                do{    
                    if (i==lenpos) throw new IllegalArgumentException();
                    preh+=pre.charAt(i+1);
                    posh+=pos.charAt(i);
                    i++;  
                }while(pos.charAt(i-1)!=c);
                aginv.Añade(agc(preh,posh)); // localizado un nuevo subarbol
            }      
          }
        Lista ag = new Lista_Dinamica(); 
        while(!aginv.EsVacia()){          // invertir la lista obtenida
            try {
                ag.Añade(aginv.Cabeza()); 
                aginv = aginv.Cola();
            } catch (TADVacioException ex) {}
        }
        return ag;
    }

    public static void main(String arg[]) {
        ABD<Integer> a = new ABD<Integer>(5),
                b = new ABD<Integer>(4),
                c = new ABD<Integer>(1, a, b);
        System.out.println("Arbol c: " + c);
        try {
            System.out.println("Raiz de c: " + c.Raiz());
            System.out.println("Subarb. izdo de c: " + c.SubArbolIzqdo());
        } catch (TADVacioException ex) {
        }

        System.out.println("Nr. nodos de c: " + nNodos(c));
        System.out.println("Altura de c: " + altura(c));
        System.out.println("Preorden de c: " + preorden(c));
        try {
            System.out.println("Elemento en la posición \"0\" de c: " + c.elemAt("0"));
            System.out.println("Elemento en la posición \"1\" de c: " + elemAt("1", c));
        } catch (TADVacioException ex) {
        }

        System.out.println("Ejemplo de árbol de caracteres: " + abc("abcdef"));

        ABD<Character> ac = abc("asdfsdfgdzfgjjjlp");
        System.out.println("Otro ejemplo de árbol de caracteres: " + ac);
        System.out.println("Nivel 5: " + nivel(5, ac));
        System.out.println("Recorrido por niveles: " + recNiveles(ac));

//      System.out.println(heap(ac));
        ABD<Character> acheap = abc("enfzamb");
        System.out.println("Ejemplo de monticulo: " + acheap);
//      System.out.println(heap(acheap));

        System.out.println("Arbol c: " + c);
        System.out.println("De búsqueda? " + busqueda(c));
        System.out.println("Arbol ac: " + ac);
        System.out.println("De búsqueda? " + busqueda(ac));

      ABD<Character> abus = abc("acfhkmpxz");
      System.out.println("Arbol abus: " + abus);
      System.out.println("De búsqueda? " + busqueda(abus));

      ABD<Character> abus2 = abc("accfhkmpxz");
      System.out.println("Arbol abus2: " + abus2);
      System.out.println("De búsqueda? " + busqueda(abus2));
      
      ABD<Character> abus3 = abc("aclfhkmpxz");
      System.out.println("Arbol abus3: " + abus3);
      System.out.println("De búsqueda? " + busqueda(abus3));
      
//      System.out.println(inserta('b',abus));
      abus.inserta('b');
      System.out.println("Insertamos b en abus: " + abus);  
      try{
//        System.out.println(inserta('h',abus));  
          System.out.println("Insertamos h en abus: ");  
          abus.inserta('h');  
      }catch(IllegalArgumentException ex){
         System.out.println("Excepcion!!: " + ex);   
      }
      
      System.out.println("Extraemos h de abus: " + extrae('h',abus));
      System.out.println("El original no cambia: " + abus);  
      System.out.println("Extraemos b de abus (no está): " + extrae('b',abus));  
      
      System.out.println("--- Arboles generales ----------------------------");
      Lista ag1 = agc("abdefcgihj","dfebgicjha");
      System.out.println("Arbol ag1: " + ag1);
      System.out.println("Elementos de ag1: " + numElem(ag1));
      System.out.println("Altura de ag1: " + altura(ag1));
      System.out.println("Rango de ag1: " + rango(ag1));
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
//      System.out.println("altura ag1: " + altura(ag1));
//      System.out.println("rango ag1: " + rango(ag1));
//      
//      Lista ag2 = agc("abxydefcgihj","xydfebgicjha");
//      System.out.println(ag2);
//      System.out.println("rango ag2: " + rango(ag2));
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    
    }

   
}
