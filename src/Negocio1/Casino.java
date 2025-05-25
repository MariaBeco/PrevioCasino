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
    //private int mySaldo;
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

    public int getMyCaja() {
        return myCaja;
    }

    public void setMyCaja(int myCaja) {
        this.myCaja = myCaja;
    }

    public String registrarEmpleados(String nombre, String cedula) {

        if (this.validarCedula(cedula) == true) {
            return "La cedula ya fue registrada.";
        }

        this.myCrupiers.add(new Crupier(nombre, cedula));

        return "¡Registro exitoso!";
    }

    public String registrarCliente(String nombre, String cedula, String telefeno) {
        if (this.validarCedula(cedula) == true) {
            return "La cedula ya fue registrada.";
        }
        this.myJugadores.add(new Jugador(nombre, cedula, telefeno));
        return "¡Jugador registrado!";
    }

    public ArrayList<String> listadoJug() {
        ArrayList<String> lista = new ArrayList<>();
        for (Jugador jug : this.myJugadores) {
            lista.add(jug.getNombre() + "-" + jug.getCedula());
        }
        return lista;
    }

    public ArrayList<String> listadoCru() {
        ArrayList<String> lista = new ArrayList<>();
        for (Crupier cru : this.myCrupiers) {
            lista.add(cru.getNombre() + "-" + cru.getCedula());
        }
        return lista;
    }

    private void crearBaraja(int num) {
        for (int i = 0; i < num; i++) {
            this.myBarajas.add(new Baraja(i));
        }
    }

    public String validarHayCrupier() {
        if (this.myCrupiers == null || this.myCrupiers.isEmpty()) {
            return "No se puede iniciar sin un crupier.";
        }
        if (this.myJugadores.isEmpty()) {
            return "No se puede iniciar sin un jugador.";
        }
        return "";
    }

    public boolean validarCedula(String cedula) {
        boolean registrado = false;

        for (Crupier e : this.myCrupiers) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                registrado = true;
                break;
            }
        }

        for (Jugador e : this.myJugadores) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                registrado = true;
                break;
            }
        }
        return registrado;
    }

    /*blackjack si son dos jugadores*/
    public String[] startBlackjackDos(int apuesta1, int apuesta2, String cedula1, String cedula2, String fecha) {
        String cad[] = new String[1];
        if (this.myJugadores == null) {
            cad[0] = "No hay jugadores";
            return cad;
        }
        if (this.validarApuesta(apuesta1) == false || this.validarApuesta(apuesta2) == false) {
            cad[0] = "Una de las apuestas no es valida.";
            return cad;
        }

        this.myCaja += apuesta1 + apuesta2;

        int jugador1 = this.buscarIndiceJugador(cedula1);/*index jug 1*/
        this.myJugadores.get(jugador1).setApuesta(apuesta1);
        int jugador2 = this.buscarIndiceJugador(cedula2);/*index jug2*/
        this.myJugadores.get(jugador2).setApuesta(apuesta2);

        int numJug1 = this.contarPartidaJugador(this.myJugadores.get(jugador1), fecha);
        int numJug2 = this.contarPartidaJugador(this.myJugadores.get(jugador2), fecha);

        if (numJug1 > 10 || numJug2 > 10) {
            cad[0] = "No se puede iniciar la partida porque alguno de los dos jugadores lleva 10 partidas";
            return cad;
        }
        this.myJugadores.get(jugador1).setNumJugadaDia(numJug1);
        this.myJugadores.get(jugador2).setNumJugadaDia(numJug2);

        int numPartida = this.myPartidosB.size();
        String nombre = "Blackjack";

        this.myPartidosB.add(new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, this.myJugadores.get(jugador2), this.myBarajas.get(0), myCrupiers.get(0)));
        PartidoBlackjack p = new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, this.myJugadores.get(jugador2), this.myBarajas.get(0), myCrupiers.get(0));

        int indexPartido = this.indexPartidoB(p);
        cad = new String[5];
        cad[0] = Integer.toString(numPartida + 1);
        String cartas[] = this.myPartidosB.get(indexPartido).enviarCartasInicio();
        for (int i = 1; i < 5; i++) {
            cad[i] = cartas[i - 1];
        }
        return cad;
    }

    public String[] StartBlackjackUno(int apuesta1, int apuesta2, String cedula1, String cedula2, String fecha) {
        String cad[] = new String[1];
        if (this.myJugadores == null) {
            cad[0] = "No hay jugadores";
            return cad;
        }

        if (this.validarApuesta(apuesta1) == false) {
            cad[0] = "La apuesta no es valida.";
            return cad;
        }

        this.myCaja += apuesta1;

        int jugador1 = this.buscarIndiceJugador(cedula1);/*index jug 1*/
        this.myJugadores.get(jugador1).setApuesta(apuesta1);
        int crupier = this.buscarIndiceCrupier(cedula2);/*index jug2*/
        this.myCrupiers.get(crupier).setApuesta(apuesta1);

        int numJug1 = this.contarPartidaJugador(this.myJugadores.get(jugador1), fecha);
        if (numJug1 > 11) {
            cad[0] = "No se puede iniciar la partida porque el jugador lleva 10 partidas";
        }

        int numPartida = this.myPartidosB.size();
        this.myJugadores.get(jugador1).setNumJugadaDia(numJug1);

        String nombre = "Blackjack";
        Jugador jugNull = null;
        this.myPartidosB.add(new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, jugNull, this.myBarajas.get(0), this.myCrupiers.get(crupier)));
        PartidoBlackjack p = new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre, numPartida, jugNull, this.myBarajas.get(0), this.myCrupiers.get(crupier));

        int indexPartido = this.indexPartidoB(p);
        cad = new String[5];
        cad[0] = Integer.toString(numPartida + 1);
        String cartas[] = this.myPartidosB.get(indexPartido).enviarCartasInicio();
        for (int i = 1; i < 5; i++) {
            cad[i] = cartas[i - 1];
        }
        return cad;
    }

    //index de un partido especifico de blackjack que esta en el arreglo 
    /*private boolean validarApuestaCrupier(int apuesta1,int apuesta2){
        boolean validar=false;
        if(apuesta2>=apuesta1){
            validar=true;
        }
        return validar;
    }*/

    public boolean dineroNoSuficiente() {
        boolean noSuficiente = false;
        if (this.myCaja <= 150000) {
            noSuficiente = true;
        }
        return noSuficiente;
    }

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

  /*  private int numPartidaDosJugadores(Jugador jug1, Jugador jug2, String fecha) {
        int numPartida = 0;
        if (this.myPartidosB != null) {
            for (Partido p : this.myPartidosB) {
                if (p.getMyJuego(0).getMyJugador().equals(jug1) && p.getMyJuego(1).getMyJugador().equals(jug2) && p.getFecha() == fecha) {
                    numPartida++;
                }
            }
        }
        return numPartida;
    }*/

    private int contarPartidaJugador(Jugador obj, String fecha) {
        int numPartida = 0;
        if (this.myPartidosB != null) {
            for (Partido p : this.myPartidosB) {
                if (p.getMyJuego(0).getMyJugador().equals(obj) || p.getMyJuego(1).getMyJugador().equals(obj) && p.getFecha() == fecha) {
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
            break;
        }
        return buscado;
    }

    private Crupier buscarCedulaCrupier(String cedula) {
        Crupier buscado = null;
        for (Crupier e : this.myCrupiers) {
            if (e.getCedula().equalsIgnoreCase(cedula));
            buscado = e;
            break;
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

    private int buscarIndiceCrupier(String cedula) {
        int buscado = 0;
        for (Crupier e : this.myCrupiers) {
            if (e.getCedula().equalsIgnoreCase(cedula)) {
                buscado = this.myCrupiers.indexOf(e);
                break;
            }
        }
        return buscado;
    }

    public String haberBlackJack() {
        String p = this.myPartidosB.getLast().hayBlackjack();
        return p;
    }

    public String mostrarPuntaje(int index) {
        String puntaje1 = Integer.toString(this.myPartidosB.getLast().Puntaje(index));
        System.out.println("pun 1 "+puntaje1);
        return puntaje1;
    }

    public String llamarOtraCarta() {
        String c = this.myPartidosB.getLast().otraCarta();
        return c;
    }

    public String llamarPuntaje(Carta carta, int index) {
        // int p=this.myPartidosB.getLast().Puntaje(carta, index);
        return "Su puntaje es: ";
    }
}
