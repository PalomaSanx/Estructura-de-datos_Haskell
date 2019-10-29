import B_TADCola.Cola;
import B_TADCola.Cola_Dinamica;

public class B_UsoColas {

    // Uso de TAD COLA a Alto Nivel (Desde fuera de la clase)
    public static void main(String[] args) {
        Cola<Boolean> c = new Cola_Dinamica<>();

        boolean b = true;
        for (int j = 1; j < 4; j++) {
            c.EnCola(b);
            b = !b;
        }

        System.out.println("Cola  : " + c);
    }
    
// <editor-fold desc="EJERCICIOS" defaultstate="collapsed">
// </editor-fold>    
    
// <editor-fold desc="EJERCICIOS DE CONTROLES" defaultstate="collapsed"> 
// </editor-fold>     
    
// <editor-fold desc="EJERCICIOS DE EXAMENES EXTRAORDINARIOS" defaultstate="collapsed">
// </editor-fold>     

}