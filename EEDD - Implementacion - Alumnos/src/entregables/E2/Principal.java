package entregables.E2;

import A_TADLista.Lista;
import A_TADLista.Lista_Dinamica;
import C_TADPila.Pila;
import C_TADPila.Pila_Dinamica;
import Varios.Excepciones.TADVacioException;

/**
 *
 * @author PALOMA
 */
public class Principal {

    public static void main(String[] args) {
        Pila p = new Pila_Dinamica();//FN: 7+((5-1)*3)
        p.APila('+');
        p.APila('*');
        p.APila(3);
        p.APila('-');
        p.APila(1);
        p.APila(5);
        p.APila(7);
        System.out.println("Pila:" + p);
        System.out.println("Valor:" + RPN(p));
    }

    public static int RPN(Pila exp) {
        try {
            Pila<Integer> aux = new Pila_Dinamica<>();
            Object o;
            Integer r1, r2;

            while (!exp.EsVacia()) {
                o = exp.Tope();
                exp = exp.DesaPila();
                if (o instanceof Integer) {
                    aux.APila((Integer) o);

                } else {
                    r2 = aux.Tope();
                    aux = aux.DesaPila();
                    r1 = aux.Tope();
                    aux = aux.DesaPila();
                    switch ((Character) o) {
                        case '+':
                            aux.APila(r1 + r2);
                            break;
                        case '-':
                            aux.APila(r1 - r2);
                            break;
                        case '*':
                            aux.APila(r1 * r2);
                            break;
                        case '/':
                            aux.APila(r1 / r2);
                            break;
                        case '%':
                            aux.APila(r1 % r2);
                            break;

                    }
                }
            }
            return aux.Tope();

        } catch (TADVacioException e) {
            return 0;
        }

    }

    public static Lista RPN_2016_A(Lista expr) {
        try {
            Lista<Integer> aux = new Lista_Dinamica<>();
            Object o;
            Integer r1, r2;

            if (expr.Longitud() > 1) {
                o = expr.Cabeza();
                expr = expr.Cola();
                if (o instanceof Integer) {
                    aux.Añade((Integer) o);

                } else {
                    r2 = aux.Cabeza();
                    aux = aux.Cola();
                    r1 = aux.Cabeza();
                    aux = aux.Cola();

                    aux.Añade(r1 + r2);
                    while (!aux.EsVacia()) {
                        expr.Añade(aux.Cabeza());
                        aux = aux.Cola();
                    }
                }
            }
            return expr;

        } catch (TADVacioException e) {
            return null;
        }

    }

    public static Boolean RPN_2016_B(Lista exp) {
        try {
            Lista<Integer> aux = new Lista_Dinamica<>();
            Object o;
            Integer r1, r2;
            if (exp.EsVacia()) {
                return true;
            }
            while (!exp.EsVacia()) {
                o = exp.Cabeza();
                exp = exp.Cola();
                if (o instanceof Integer) {
                    aux.Añade((Integer) o);

                } else if (o instanceof Character) {
                    if (aux.Longitud() < 2) {
                        return false;
                    }

                    aux = aux.Cola();//quita r2
                    aux = aux.Cola(); //quita r1

                    aux.Añade(0);//simula una operación y la almacena.

                }
            }
            if (aux.Longitud() > 1) {
                return false;
            }
            return true;

        } catch (TADVacioException e) {
            return false;
        }

    }

    public static int RPN_2015_A(Lista exp) {
        try {
            Lista<Integer> aux = new Lista_Dinamica<>();
            Object o;
            Integer r1, r2;

            while (!exp.EsVacia()) {
                o = exp.Cabeza();
                exp = exp.Cola();
                if (o instanceof Integer) {
                    aux.Añade((Integer) o);

                } else {

                    switch ((Character) o) {
                        case '+':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();
                            r1 = aux.Cabeza();
                            aux = aux.Cola();
                            aux.Añade(r1 + r2);
                            break;
                        case '!':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();
                            int prod = 1;
                            for (int i = r2; i > 0; i++) {
                                prod *= i;

                            }
                            aux.Añade(prod);
                            break;
                    }
                }
            }
            return aux.Cabeza();

        } catch (TADVacioException e) {
            return 0;
        }

    }

    public static int RPN_2014_B(Lista exp) {
        try {
            Lista<Integer> aux = new Lista_Dinamica<>();
            Object o;
            Integer r1, r2;

            while (!exp.EsVacia()) {
                o = exp.Cabeza();
                exp = exp.Cola();
                if (o instanceof Integer) {
                    aux.Añade((Integer) o);

                } else {

                    switch ((Character) o) {
                        case '+':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();
                            r1 = aux.Cabeza();
                            aux = aux.Cola();
                            aux.Añade(r1 + r2);
                            break;
                        case 'a':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();

                            aux.Añade(Math.abs(r2));
                            break;
                    }
                }
            }
            return aux.Cabeza();

        } catch (TADVacioException e) {
            return 0;
        }

    }
    
        public static int RPN_2012(Lista exp) {
        try {
            Lista<Integer> aux = new Lista_Dinamica<>();
            Object o;
            Integer r1, r2;

            while (!exp.EsVacia()) {
                o = exp.Cabeza();
                exp = exp.Cola();
                if (o instanceof Integer) {
                    aux.Añade((Integer) o);

                } else {

                    switch ((Character) o) {
                        case 'a':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();
                            r1 = aux.Cabeza();
                            aux = aux.Cola();
                           // aux.Añade(a(r1,r2));
                            break;
                        case 'b':
                            r2 = aux.Cabeza();
                            aux = aux.Cola();

                           // aux.Añade(b(r1));
                            break;
                    }
                }
            }
            return aux.Cabeza();

        } catch (TADVacioException e) {
            return 0;
        }

    }

}
