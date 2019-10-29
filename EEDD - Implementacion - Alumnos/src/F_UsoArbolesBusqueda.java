
import D_TADArbol.ABD;
import D_TADArbol.ArbolBusquedaDinamico;

public class F_UsoArbolesBusqueda {
    public static void main(String[] args) {
        
    
        // Crear un arbol de busqueda elemento a elemento
        ArbolBusquedaDinamico trece = new ArbolBusquedaDinamico(13);
        ArbolBusquedaDinamico quince = new ArbolBusquedaDinamico(15, trece, new ArbolBusquedaDinamico());
        ArbolBusquedaDinamico cinco = new ArbolBusquedaDinamico(5);
        ArbolBusquedaDinamico arbolBus = new ArbolBusquedaDinamico(10, cinco, quince);

        System.out.println("Arbol Busqueda");
        System.out.println(arbolBus);
        System.out.println();

        // Crear un arbol de busqueda a partir de uno binario (Que este ordenado)
        ABD treceB = new ABD(13);
        ABD quinceB = new ABD(15, treceB, new ABD());
        ABD cincoB = new ABD(5);
        ABD arbolBin = new ABD(10, cincoB, quinceB);
        ArbolBusquedaDinamico arbolBus2 = new ArbolBusquedaDinamico(arbolBin);

        System.out.println("Arbol Busqueda 2");
        System.out.println(arbolBus2);
        System.out.println();

        // Crear un arbol de busqueda a partir de uno binario (Que NO este ordenado)
        ABD arbolBin2 = new ABD(10, quinceB, cincoB);
        //ArbolBusquedaDinamico arbolBus3 = new ArbolBusquedaDinamico(arbolBin2);

        // Crear un arbol de busqueda insertando elementos
        ArbolBusquedaDinamico arbolBus4 = new ArbolBusquedaDinamico();
        arbolBus4.Inserta(10);
        arbolBus4.Inserta(5);
        arbolBus4.Inserta(15);
        arbolBus4.Inserta(13);

        System.out.println("Arbol Busqueda 4");
        System.out.println(arbolBus4);
        System.out.println();
    }
    
// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
     
// </editor-fold>    
       
}