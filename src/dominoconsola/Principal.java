package dominoconsola;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal {

    static Scanner entrada = new Scanner(System.in);

    static Ficha ficha1 = new Ficha(0, 0, "0");
    static Ficha ficha2 = new Ficha(0, 1, "1");
    static Ficha ficha3 = new Ficha(0, 2, "2");
    static Ficha ficha4 = new Ficha(0, 3, "3");
    static Ficha ficha5 = new Ficha(0, 4, "4");
    static Ficha ficha6 = new Ficha(0, 5, "5");
    static Ficha ficha7 = new Ficha(0, 6, "6");
    static Ficha ficha8 = new Ficha(1, 1, "7");
    static Ficha ficha9 = new Ficha(1, 2, "8");
    static Ficha ficha10 = new Ficha(1, 3, "9");
    static Ficha ficha11 = new Ficha(1, 4, "10");
    static Ficha ficha12 = new Ficha(1, 5, "11");
    static Ficha ficha13 = new Ficha(1, 6, "12");
    static Ficha ficha14 = new Ficha(2, 2, "13");
    static Ficha ficha15 = new Ficha(2, 3, "14");
    static Ficha ficha16 = new Ficha(2, 4, "15");
    static Ficha ficha17 = new Ficha(2, 5, "16");
    static Ficha ficha18 = new Ficha(2, 6, "17");
    static Ficha ficha19 = new Ficha(3, 3, "18");
    static Ficha ficha20 = new Ficha(3, 4, "19");
    static Ficha ficha21 = new Ficha(3, 5, "20");
    static Ficha ficha22 = new Ficha(3, 6, "21");
    static Ficha ficha23 = new Ficha(4, 4, "22");
    static Ficha ficha24 = new Ficha(4, 5, "23");
    static Ficha ficha25 = new Ficha(4, 6, "24");
    static Ficha ficha26 = new Ficha(5, 5, "25");
    static Ficha ficha27 = new Ficha(5, 6, "26");
    static Ficha ficha28 = new Ficha(6, 6, "27");

    static String jugador1;
    static String jugador2;
    
    LocalDateTime inicio_juego;
    LocalDateTime fin_juego;
    
    static ArrayList<Ficha> fichas = new ArrayList<>();

    static ArrayList<Ficha> jugador1Fichas = new ArrayList<>();
    static ArrayList<Ficha> jugador2Fichas = new ArrayList<>();

    static ArrayList<Ficha> fichasJugadas = new ArrayList<>();
    ConectarDB db = new ConectarDB();   
    Ventana v;
    
    public static void main(String[] args) {
        Principal principal = new Principal();
        Ventana v = new Ventana();
        
    }

    public void agregarFichas() {

        fichas.add(ficha1);
        fichas.add(ficha2);
        fichas.add(ficha3);
        fichas.add(ficha4);
        fichas.add(ficha5);
        fichas.add(ficha6);
        fichas.add(ficha7);
        fichas.add(ficha8);
        fichas.add(ficha9);
        fichas.add(ficha10);
        fichas.add(ficha11);
        fichas.add(ficha12);
        fichas.add(ficha13);
        fichas.add(ficha14);
        fichas.add(ficha15);
        fichas.add(ficha16);
        fichas.add(ficha17);
        fichas.add(ficha18);
        fichas.add(ficha19);
        fichas.add(ficha20);
        fichas.add(ficha21);
        fichas.add(ficha22);
        fichas.add(ficha23);
        fichas.add(ficha24);
        fichas.add(ficha25);
        fichas.add(ficha26);
        fichas.add(ficha27);
        fichas.add(ficha28);

        Collections.shuffle(fichas);

    }

    public void inicioJuego() {
        
        LocalDateTime myObj = LocalDateTime.now();
        inicio_juego = myObj;
        //mostrarFichasJugadores(fichas, jugador1Fichas, jugador2Fichas);
        agregarFichas();
        repartirFichas(fichas, jugador1Fichas, jugador2Fichas);
        //mostrarFichasJugadas();
        //mostrarFichasDelPozo();
        quienJuegaPrimero();

        //fichasDelPozo();
        /*System.out.println("*********DOMINO*********");
        System.out.println("Ingrese el nombre del jugador 1");
        jugador1 = entrada.nextLine();
        System.out.println("Ingrese el nombre del jugador 2");
        jugador2 = entrada.nextLine();
        */
    }

    public void repartirFichas(ArrayList<Ficha> fichas, ArrayList<Ficha> jugador1Fichas,
            ArrayList<Ficha> jugador2Fichas) {

        
        int limiteJugador1 = 7;
        int limiteJugador2 = 14;

        for (int i = 0; i < limiteJugador1; i++) {
            jugador1Fichas.add(fichas.get(i));
            fichas.remove(jugador1Fichas.get(i));
        }

        for (int x = limiteJugador1; x < limiteJugador2; x++) {
            jugador2Fichas.add(fichas.get(x));
            fichas.remove(jugador2Fichas.get(x - 7));
        }
    }

    public void mostrarFichasJugadores(ArrayList<Ficha> fichas, ArrayList<Ficha> jugador1Fichas,
            ArrayList<Ficha> jugador2Fichas) {

        System.out.println();
        System.out.println();

        System.out.println("Fichas para el jugador " + jugador1 + " :");

        for (Ficha elemento : jugador1Fichas) {

            System.out.print("[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "], ");
        }

        System.out.println();
        System.out.println();

        System.out.println("Fichas para el jugador " + jugador2 + " :");

        for (Ficha elemento : jugador2Fichas) {

            System.out.print("[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "], ");
        }

    }

    public void mostrarFichasJugadas() {

        System.out.println();
        System.out.println();

        System.out.println("Fichas jugadas:");

        for (Ficha elemento : fichasJugadas) {
            System.out.print("[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "]");

        }
        System.out.println();
        System.out.println();
    }

    public void mostrarFichasDelPozo() {

        System.out.println("Fichas del pozo");

        for (Ficha elemento : fichas) {
            System.out.print("[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "], ");
        }
        
        if(fichas.isEmpty()){
            System.out.println("Partida finalizada por falta de fichas en el pozo.");
            comprobarGanador();
            System.exit(0);
        }
        
      

        System.out.println();
        System.out.println();
    }

    public boolean quienJuegaPrimero() {

        
        boolean turno = true;
        int numIzqJug1 = 0;
        int numDerJug1 = 0;
        int numIzqJug2 = 0;
        int numDerJug2 = 0;

        for (Ficha jug1 : jugador1Fichas) {
            if (jug1.getNumero1() == jug1.getNumero2() && jug1.getNumero1() > numIzqJug1 && jug1.getNumero2() > numDerJug1) {
                numIzqJug1 = jug1.getNumero1();
                numDerJug1 = jug1.getNumero2();
            }
        }

        for (Ficha jug2 : jugador2Fichas) {
            if (jug2.getNumero1() == jug2.getNumero2() && jug2.getNumero1() > numIzqJug2 && jug2.getNumero2() > numDerJug2) {
                numIzqJug2 = jug2.getNumero1();
                numDerJug2 = jug2.getNumero2();
            }
        }
        if (numIzqJug1 > numIzqJug2) {
            //ventana.fichasJugador1();
            turno = true;
            //turno(true);
        } else {
            //ventana.fichasJugador2();
            turno = false;
            //turno(false);
        }

        return turno;
    }
    
    public boolean turno(boolean turno){
        boolean turnoJug = true;
        if (turnoJug && turno) {
            //System.out.println("Turno del jugador 1" + jugador1);
            turnoJug = false;
            //aux2 = false;
            //mostrarFichasJugador1();
            //v.fichasJugador1();
        }else{
            //System.out.println("Turno del jugador 2" + jugador2);
            turnoJug = true;
            //aux2 = true;
            //mostrarFichasJugador2();
            //v.fichasJugador2();
        }
        
        return turnoJug;
        
    }

    public void mostrarFichasJugador1() {
        
        int i = 1;
        for (Ficha ficha : jugador1Fichas) {
            System.out.println(i + "." + "[" + ficha.getNumero1() + "|" + ficha.getNumero2() + "]");
            i++;
        }
        do{
            try{

                if (ComprobarMovimiento(jugador1Fichas)) {
                    System.out.println("Elija una ficha a colocar");
                    int fichaSeleccionada = entrada.nextInt();

                        for (int x = 0; x < jugador1Fichas.size(); x++) {

                            if (fichaSeleccionada - 1 == x) {
                                if (fichasJugadas.isEmpty()) {
                                    fichasJugadas.add(jugador1Fichas.get(x));
                                    jugador1Fichas.remove(x);
                                    mostrarFichasJugadas();
                                    turno(false);
                                } else if (colocarFicha(jugador1Fichas.get(x))) {
                                    jugador1Fichas.remove(x);
                                    mostrarFichasJugadas();
                                    sinFichas(jugador1Fichas,jugador2Fichas);
                                    turno(false);
                                } else {
                                    System.out.println("Error! La ficha no se puede colocar!");
                                    System.out.println("Ingrese opcion válida");
                                    mostrarFichasJugadas();
                                    mostrarFichasJugador1();
                                }
                            } else if (fichaSeleccionada >= jugador1Fichas.size() + 1) {
                                System.out.println("Error! Ingrese un numero valido");
                                mostrarFichasJugador1();
                            }
                        }

                } else {
                    System.out.println("No puede colocar mas fichas!");
                    int seleccion;
                    System.out.println("1. Robar del pozo");
                    System.out.println("2. Pasar");
                    seleccion = entrada.nextInt();
                    if (seleccion == 1) {
                        mostrarFichasDelPozo();
                        RobarDelPozo(jugador1Fichas);
                        mostrarFichasJugadas();
                        System.out.println("Turno del jugador " + jugador1);
                        if (ComprobarMovimiento(jugador1Fichas)) {
                            mostrarFichasJugador1();
                        } else {
                            mostrarFichasJugador1();
                        }
                    } else if (seleccion == 2) {
                        mostrarFichasJugadas();
                        turno(false);
                    }
                }  
            }catch(Exception e){
                System.out.println("No es una opción válida. Ingrese un número");
                entrada.next();

            }
        
        }while(entrada.hasNextInt());
      
    }

    public void mostrarFichasJugador2() {

        int i = 1;
        for (Ficha ficha : jugador2Fichas) {
            System.out.println(i + "." + "[" + ficha.getNumero1() + "|" + ficha.getNumero2() + "]");
            i++;
        }
        do{
            try{
                if (ComprobarMovimiento(jugador2Fichas)) {
                    System.out.println("Elija una ficha a colocar");
                    int fichaSeleccionada = entrada.nextInt();

                    for (int x = 0; x < jugador2Fichas.size(); x++) {
                        if (fichaSeleccionada - 1 == x) {
                            if (fichasJugadas.isEmpty()) {
                                fichasJugadas.add(jugador2Fichas.get(x));
                                jugador2Fichas.remove(x);
                                mostrarFichasJugadas();
                                turno(true);
                            } else if (colocarFicha(jugador2Fichas.get(x))) {
                                jugador2Fichas.remove(x);
                                mostrarFichasJugadas();
                                sinFichas(jugador1Fichas,jugador2Fichas);
                                turno(true);
                            } else {
                                System.out.println("Error! La ficha no se puede colocar!");
                                System.out.println("Ingrese opcion válida");
                                mostrarFichasJugadas();
                                mostrarFichasJugador2();
                            }
                        } else if (fichaSeleccionada >= jugador2Fichas.size() + 1) {
                            System.out.println("Error! Ingrese un numero valido");
                            mostrarFichasJugador2();
                        }

                    }
                } else {
                    System.out.println("No puede colocar mas fichas!");
                    int seleccion;
                    System.out.println("1. Robar del pozo");
                    System.out.println("2. Pasar");
                    seleccion = entrada.nextInt();
                    if (seleccion == 1) {
                        mostrarFichasDelPozo();
                        RobarDelPozo(jugador2Fichas);
                        mostrarFichasJugadas();
                        System.out.println("Turno del jugador " + jugador2);
                        if (ComprobarMovimiento(jugador2Fichas)) {
                            mostrarFichasJugador2();
                        } else {
                            mostrarFichasJugador2();
                        }
                    } else if (seleccion == 2) {
                        mostrarFichasJugadas();
                        turno(true);
                    }
                }
            }catch(Exception e){
                    System.out.println("No es una opción válida. Ingrese un número");
                    entrada.next();

            }
        
        }while(entrada.hasNextInt());
    }

    public static boolean colocarFicha(Ficha ficha) {

        boolean validado;
        if (fichasJugadas.get(0).numero1 == ficha.numero1) {
            darVueltaFicha(ficha);
            fichasJugadas.add(0, ficha);
            validado = true;
        } else if (fichasJugadas.get(fichasJugadas.size() - 1).numero2 == ficha.numero1) {
            fichasJugadas.add(ficha);
            validado = true;
        } else if (fichasJugadas.get(0).numero1 == ficha.numero2) {
            fichasJugadas.add(0, ficha);
            validado = true;
        } else if (fichasJugadas.get(fichasJugadas.size() - 1).numero2 == ficha.numero2) {
            darVueltaFicha(ficha);
            fichasJugadas.add(ficha);
            validado = true;
        } else {
            validado = false;
        }
        return validado;
    }
    
    public static boolean fichaADer(Ficha ficha){
        
        boolean validado;
        if (fichasJugadas.get(fichasJugadas.size() - 1).numero2 == ficha.numero1) {
            fichasJugadas.add(ficha);
            validado = true;
        }else if (fichasJugadas.get(fichasJugadas.size() - 1).numero2 == ficha.numero2) {
            darVueltaFicha(ficha);
            fichasJugadas.add(ficha);
            validado = true;
        }else{
            validado = false;
        }
        
        return validado;
        
    }
    
    public static boolean fichaAIzq(Ficha ficha){
        boolean validado;
        
        if (fichasJugadas.get(0).numero1 == ficha.numero1) {
            darVueltaFicha(ficha);
            fichasJugadas.add(0, ficha);
            validado = true;
        }else if (fichasJugadas.get(0).numero1 == ficha.numero2) {
            fichasJugadas.add(0, ficha);
            validado = true;
        }else{
            validado = false;
        }
        
        return validado;
        
    }
    

    public static void darVueltaFicha(Ficha ficha) {

        int aux = 0;
        aux = ficha.numero1;
        ficha.numero1 = ficha.numero2;
        ficha.numero2 = aux;
    }

    public void RobarDelPozo(ArrayList<Ficha> jugadorFichas) {

        if(fichas.isEmpty()&& (!ComprobarMovimiento(jugador1Fichas) && !ComprobarMovimiento(jugador2Fichas))){
            JOptionPane.showMessageDialog(null, "No hay más fichas en el pozo\n Y no se pueden hacer más movimientos");
            comprobarGanador();
        }if(fichas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay más fichas en el pozo!");
        }else {
            jugadorFichas.add(fichas.get(fichas.size() - 1));
            fichas.remove(fichas.size() - 1);
        }

    }

    public boolean ComprobarMovimiento(ArrayList<Ficha> jugadorFichas) {
        boolean movimiento = true;
        if (fichasJugadas.isEmpty()) {
            movimiento = true;
        } else {
            for (int i = 0; i < jugadorFichas.size(); i++) {
                if (fichasJugadas.get(0).numero1 == jugadorFichas.get(i).numero1 || fichasJugadas.get(fichasJugadas.size() - 1).numero2 == jugadorFichas.get(i).numero1
                        || fichasJugadas.get(0).numero1 == jugadorFichas.get(i).numero2 || fichasJugadas.get(fichasJugadas.size() - 1).numero2 == jugadorFichas.get(i).numero2) {
                    movimiento = true;
                    break;
                } else {
                    movimiento = false;
                }
            }
        }

        return movimiento;
    }
    
    public void sinFichas(ArrayList <Ficha> jugador1Fichas, ArrayList <Ficha> jugador2Fichas){
        
        if(jugador1Fichas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Jugador 1 no tiene más fichas\n Fin del juego");
            comprobarGanador();
        }else if(jugador2Fichas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Jugador 2 no tiene más fichas\n Fin del Juego");
            comprobarGanador();
        }else if(fichas.isEmpty() && (!ComprobarMovimiento(jugador1Fichas) && !ComprobarMovimiento(jugador2Fichas))){
            comprobarGanador();
        }
        
        
    }

    public int contarPuntosJug1(){
        int puntosJug1 = 0;
        
        for (Ficha fichas : jugador1Fichas) {
            puntosJug1 += fichas.numero1;
            puntosJug1 += fichas.numero2;
        }
        
        return puntosJug1;
    }
    
    public int contarPuntosJug2(){
        int puntosJug2 = 0;
        
        for (Ficha fichas : jugador2Fichas) {
            puntosJug2 += fichas.numero1;
            puntosJug2 += fichas.numero2;
        }
        
        return puntosJug2;
    }
    
    public void comprobarGanador(){
        if(contarPuntosJug1() < contarPuntosJug2()){
            //System.out.println("¡" +jugador1 + " ganó la partida!");
            //System.out.println("Puntos del jugador " + jugador1 + ": " + contarPuntosJug1());
            //System.out.println("Puntos del jugador " + jugador2 + ": " + contarPuntosJug2());
            JOptionPane.showMessageDialog(null, "¡" +jugador1 + " ganó la partida!\n"
                    + "Puntos del jugador " + jugador1 + ": " + contarPuntosJug1() + "\nPuntos del jugador " + jugador2 + ": " + contarPuntosJug2());
            finJuego();
        }else if (contarPuntosJug1() > contarPuntosJug2()){
            //System.out.println("¡" +jugador2 +" ganó la partida!");
            //System.out.println("Puntos del jugador " + jugador2 + ": " + contarPuntosJug2());
            //System.out.println("Puntos del jugador " + jugador1 + ": " + contarPuntosJug1());
            JOptionPane.showMessageDialog(null, "¡" +jugador2 + " ganó la partida!\n"
                    + "Puntos del jugador " + jugador2 + ": " + contarPuntosJug2() + "\nPuntos del jugador " + jugador1 + ": " + contarPuntosJug1());
            finJuego();
        }else{
            //System.out.println("¡Es un empate!");
            //System.out.println("Puntos del jugador " + jugador1 + ": " + contarPuntosJug1());
            //System.out.println("Puntos del jugador " + jugador2 + ": " + contarPuntosJug2());
            JOptionPane.showMessageDialog(null, "¡Es un empate!\n"
                    + "Puntos del jugador " + jugador1 + ": " + contarPuntosJug1() + "\nPuntos del jugador " + jugador2 + ": " + contarPuntosJug1());
            finJuego();
        }
    }
    
    public void finJuego(){
        LocalDateTime myObj = LocalDateTime.now();
        fin_juego = myObj;
        db.ConectarDB(jugador1, jugador2, inicio_juego,fin_juego ,contarPuntosJug1(), contarPuntosJug2());
    }
    
    public String fichasJugadasString() {
        String ficha = " ";
        for (Ficha elemento : fichasJugadas) {
            ficha = "[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "]";
        }
        return ficha;

    }
    
    public String fichasDelPozo() {
        String ficha = " ";

        for (Ficha elemento : fichas) {
            ficha = "[" + elemento.getNumero1() + "|" + elemento.getNumero2() + "]";
        }
        
        return ficha;

    }
}

