package entregables.E4;

import A_TADLista.*;

public class Principal {

    public static void main(String[] args) {

        int I = Integer.MAX_VALUE; // infinito
        int[][] a = {
            {0, 2, 20, 4, I, I},
            {I, 0, I, 2, 6, I},
            {15, I, 0, I, 2, I},
            {I, 3, I, 0, I, 2},
            {I, 9, I, I, 0, 3},
            {I, 4, I, 0, I, 0}
        };// suponemos 0's en la diagonal
        Lista<Integer>[][] caminos = Floyd(a);
        int d = caminos.length;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (!caminos[i][j].EsVacia()) {
                    System.out.println("camino desde " + i + " hasta " + j + ": "
                            + caminos[i][j] + " distancia = " + a[i][j]);
                }
            }
        }

    }

    public static Lista<Integer>[][] Floyd(int[][] ady) {
        int n = ady.length;
        int I = Integer.MAX_VALUE;
        int pesoOriginal, pesoNuevo;
        Lista<Integer>[][] caminos = new Lista_Dinamica[n][n];

        //1ªPARTE// AÑADIR NDODO FINAL DEL CAMINO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                caminos[i][j] = new Lista_Dinamica();
                if (ady[i][j] != I && i != j) {
                    caminos[i][j].Añade(j);
                }
            }
        }
        //2ªPARTE// NODOS INTERMEDIOS
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pesoOriginal = ady[i][j];
                    if (ady[i][k] == I || ady[k][j] == I) {
                        pesoNuevo = I;
                    } else {
                        pesoNuevo = ady[i][k] + ady[k][j];
                    }
                    if (pesoNuevo < pesoOriginal) {
                        ady[i][j] = pesoNuevo;//modifica matriz
                        caminos[i][j] = new Lista_Dinamica();
                        caminos[i][j].Concatena(caminos[i][k]);//Utiliza el concatena y no añade ya que sino no conservaria los intermedios.
                        caminos[i][j].Concatena(caminos[k][j]);
                    }

                }
            }

        }
        //3ªPARTE// AÑADIR NODO INICIAL
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ady[i][j] != I && i != j) {
                    caminos[i][j].Añade(i);
                }
            }
        }
        return caminos;
    }
    
     public static int[][] Floyd_2011_A(int[][] ady) {
     
        int n = ady.length;
        int I = Integer.MAX_VALUE;
        int pesoOriginal, pesoNuevo;
        int[][] cantidad = new int[n][n];

        //1ªPARTE// AÑADIR NDODO FINAL DEL CAMINO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                if (ady[i][j] != I && i != j) {
                    
                    cantidad[i][j]=1;
                }
            }
        }
        //2ªPARTE// NODOS INTERMEDIOS
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pesoOriginal = ady[i][j];
                    if (ady[i][k] == I || ady[k][j] == I) {
                        pesoNuevo = I;
                    } else {
                        pesoNuevo = ady[i][k] + ady[k][j];
                    }
                    if (pesoNuevo < pesoOriginal) {
                        ady[i][j] = pesoNuevo;//modifica matriz
                        cantidad[i][j]=cantidad[i][k]+cantidad[k][j];
                        
                    }

                }
            }

        }
        //3ªPARTE// AÑADIR NODO INICIAL
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ady[i][j] != I && i != j) {
                    cantidad[i][j]++;
                }
            }
        }
        return cantidad;
    }
     
     
     
     public static boolean [][] Floyd_2012(int[][] ady) {
        int n = ady.length;
        int I = Integer.MAX_VALUE;
        int pesoOriginal, pesoNuevo;
        boolean [][] caminos = new boolean[n][n];

        //1ªPARTE// AÑADIR NDODO FINAL DEL CAMINO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               
                if (ady[i][j] != I && i != j) {
                    caminos[i][j]=true;
                }
            }
        }
        //2ªPARTE// NODOS INTERMEDIOS
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pesoOriginal = ady[i][j];
                    if (ady[i][k] == I || ady[k][j] == I) {
                        pesoNuevo = I;
                    } else {
                        pesoNuevo = ady[i][k] + ady[k][j];
                    }
                    if (pesoNuevo < pesoOriginal) {
                        ady[i][j] = pesoNuevo;//modifica matriz
                        caminos[i][j] = caminos[i][k]&caminos[k][j];
                        
                    }

                }
            }

        }
       
        
        return caminos;
    }
    
    
}
