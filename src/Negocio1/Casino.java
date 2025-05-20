package Negocio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Casino {

    private ArrayList<PartidoBlackjack> myPartidosB;
    private ArrayList<PartidoPoker> myPartidosP;
    private ArrayList<Jugador> myJugadores;
    private ArrayList<Crupier> myCrupiers;
    private ArrayList<Baraja> myBarajas;
    private int myCaja;
    private int mySaldo;
    private int mySaldoPagadoPoker;
    private int mySaldoPagadoBlackjack;
    Scanner sc = new Scanner(System.in);

    //blackjack
    public Casino(int myCaja, int num) {
        this.myCaja = myCaja;
        this.myPartidosB = new ArrayList<>();
        this.myJugadores = new ArrayList<>();
        this.myCrupiers = new ArrayList<>();
        this.myBarajas = new ArrayList<>();
        this.crearBaraja(num);
    }

    public String registrarEmpleados(String nombre, String cedula) {

        if (this.validarCedulaCrupier(cedula) == true) {
            return "La cedula ya fue registrada.";
        }

        this.myCrupiers.add(new Crupier(nombre, cedula));

        return "¡Registro exitoso!";
    }

    public boolean validarCedulaCrupier(String cedula) {
        boolean registrado = false;

        for (Crupier e : this.myCrupiers) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                registrado = true;
                break;
            }
        }
        return registrado;
    }

    public boolean validarCedulaJugador(String cedula) {
        boolean registrado = false;

        for (Jugador e : this.myJugadores) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                registrado = true;
                break;
            }
        }
        return registrado;
    }

    public String registrarCliente(String nombre, String cedula, String telefeno) {
        if (this.validarCedulaJugador(cedula) == true) {
            return "La cedula ya fue registrada.";
        }
        this.myJugadores.add(new Jugador(nombre, cedula, telefeno));
        return "¡Jugador registrado!";
    }

    private void crearBaraja(int num) {
        for (int i = 0; i <num; i++) {
            this.myBarajas.add(new Baraja(i));
        }
    }


    /*blackjack si son dos jugadores*/
    public String[] startBlackjackDos(int apuesta1, int apuesta2, String cedula1, String cedula2, String fecha) {
        String cad[] = new String[1];
        if (this.myJugadores == null) {
            cad[0] = "No hay jugadores";
            return cad;
        }
        if (this.buscarCedula(cedula1) == null || this.buscarCedula(cedula2) == null) {
            cad[0] = "No hay un jugador registrado con alguna de esas cedulas.";
            return cad;
        }
        if (this.validarApuesta(apuesta1) == false || this.validarApuesta(apuesta2) == false) {
            cad[0] = "Una de las apuestas no es valida.";
            return cad;
        }
        int jugador1 = this.buscarIndiceJugador(cedula1);
        /*index jug 1*/
        System.out.println("indx 1 " + jugador1);
        this.myJugadores.get(jugador1).setApuesta(apuesta1);
        int jugador2 = this.buscarIndiceJugador(cedula2);/*index jug2*/
        System.out.println("indx 2 " + jugador2);
        this.myJugadores.get(jugador2).setApuesta(apuesta2);

        int numJug1 = this.contarPartidaJugador(this.myJugadores.get(jugador1), fecha);
        int numJug2 = this.contarPartidaJugador(this.myJugadores.get(jugador2), fecha);

        System.out.println("num1 " + numJug1 + " num2 " + numJug2);
        if (numJug1 > 10 || numJug2 > 10) {
            cad[0] = "No se puede iniciar la partida porque alguno de los dos jugadores lleva 10 partidas";
            return cad;
        }
        System.out.println("error");
        this.myJugadores.get(jugador1).setNumJugadaDia(numJug1);
        this.myJugadores.get(jugador2).setNumJugadaDia(numJug2);
        System.out.println("error1");
        int numPartida = this.numPartidaDosJugadores(this.myJugadores.get(jugador1), this.myJugadores.get(jugador2), fecha);
        String nombre = "Blackjack";
        System.out.println("error2");
        if (this.myBarajas == null || this.myBarajas.isEmpty()) {
            System.out.println("No hay barajas disponibles");
        }

        this.myPartidosB.add(new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, this.myJugadores.get(jugador2), this.myBarajas.get(0)));
        System.out.println("error3");
        PartidoBlackjack p = new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, this.myJugadores.get(jugador2), this.myBarajas.get(0));

        int indexPartido = this.indexPartidoB(p);
        System.out.println("index partido " + indexPartido);
        cad = new String[5];
        cad[0] = Integer.toString(numPartida);
        String cartas[]=this.myPartidosB.get(indexPartido).enviarCartasInicio();
        for(int i=1;i<5;i++){
            cad[i]=cartas[i-1];
        }

        return cad;
    }

    //index de un partido especifico de blackjack que esta en el arreglo 
    private int indexPartidoB(PartidoBlackjack p) {
        int index = 0;
        for (PartidoBlackjack par : this.myPartidosB) {
            if (par.equals(p)) {
                index = this.myPartidosB.indexOf(p);
                break;
            }
        }
        return index;
    }

    private int numPartidaDosJugadores(Jugador jug1, Jugador jug2, String fecha) {
        int numPartida = 0;
        if (this.myPartidosB != null) {
            for (Partido p : this.myPartidosB) {
                Juego ju[] = p.getMyJuego();
                if (ju[0].getMyJugador().equals(jug1) && ju[1].getMyJugador().equals(jug2) && p.getFecha() == fecha) {
                    numPartida++;
                }
            }
        }
        if (this.myPartidosP != null) {
            for (Partido p : this.myPartidosP) {
                Juego ju[] = p.getMyJuego();
                if (ju[0].getMyJugador().equals(jug1) && ju[1].getMyJugador().equals(jug2) && p.getFecha() == fecha) {
                    numPartida++;
                }
            }
        }
        return numPartida;
    }

    public int contarPartidaJugador(Jugador obj, String fecha) {
        int numPartida = 0;
        if (this.myPartidosB != null) {
            for (Partido p : this.myPartidosB) {
                Juego ju[] = p.getMyJuego();
                if (ju[0].getMyJugador().equals(obj) || ju[1].getMyJugador().equals(obj) && p.getFecha() == fecha) {
                    numPartida++;
                }
            }
        }
        if (this.myPartidosP != null) {
            for (Partido p : this.myPartidosP) {
                Juego ju[] = p.getMyJuego();
                if (ju[0].getMyJugador().equals(obj) || ju[1].getMyJugador().equals(obj) && p.getFecha() == fecha) {
                    numPartida++;
                }
            }
        }
        return numPartida;
    }

    private boolean validarApuesta(int apuesta) {
        boolean valido = false;
        if (apuesta >= 50000 && apuesta <= 500000) {
            valido = true;
        }
        return valido;
    }

    private Jugador buscarCedula(String cedula) {
        Jugador buscado = null;
        for (Jugador e : this.myJugadores) {
            if (e.getCedula().equalsIgnoreCase(cedula));
            buscado = e;
        }
        return buscado;
    }

    private int buscarIndiceJugador(String cedula) {
        int buscado = 0;
        for (Jugador e : this.myJugadores) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                buscado = this.myJugadores.indexOf(e);
                break;
            }
        }
        return buscado;
    }

}
