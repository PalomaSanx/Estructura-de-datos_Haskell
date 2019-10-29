import C_TADPila.Pila;
import C_TADPila.Pila_Dinamica;

public class C_UsoPilas {

    // Uso de TAD PILA a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {
        Pila<Character> p = new Pila_Dinamica<>();
        // int a Integer (y viceversa).
        for (char ca = 'a'; ca < 'g'; ca++) {
            p.APila(ca);   // o bien p.Apila(ca);
        }

        System.out.println("Pila  : " + p);
    }
    
// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
// </editor-fold>    
    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>     

}