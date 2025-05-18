package Negocio1;

import java.util.ArrayList;
import java.util.Collections;

public class Casino {

    private ArrayList<Partido> myPartidos;
    private ArrayList<Jugador> myJugadores;
    private ArrayList<Crupier> myCrupiers;
    private ArrayList<Baraja> myBarajas;
    private int myCaja;
    private int mySaldo;
    private int mySaldoPagadoPoker;
    private int mySaldoPagadoBlackjack;

    //blackjack
    public Casino(int myCaja, int num) {
        this.myCaja = myCaja;
        this.myJugadores = new ArrayList<>();
        this.myCrupiers = new ArrayList<>();
        this.myBarajas = new ArrayList<Baraja>();
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
            if (e.getCedula().equalsIgnoreCase(cedula));
            registrado = true;
        }
        return registrado;
    }

    public boolean validarCedulaJugador(String cedula) {
        boolean registrado = false;

        for (Jugador e : this.myJugadores) {
            if (e.getCedula().equalsIgnoreCase(cedula));
            registrado = true;
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

    public void crearBaraja(int num) {
        for (int i = 1; i <= this.myBarajas.size(); i++) {
            this.myBarajas.add(new Baraja(i));
        }
    }

    /*blackjack si son dos jugadores*/
    public String startBlackjackDos(int apuesta1, int apuesta2, String cedula1, String cedula2, String fecha) {
        if (this.myJugadores == null) {
            return "No hay jugadores";
        }
        if (this.buscarCedula(cedula1) == null || this.buscarCedula(cedula2) == null) {
            return "No hay un jugador registrado con alguna de esas cedulas.";
        }
        if (this.validarApuesta(apuesta1) == false || this.validarApuesta(apuesta2) == false) {
            return "Una de las apuestas no es valida.";
        }
        int jugador1 = this.buscarIndiceJugador(cedula1); /*index jug 1*/
        this.myJugadores.get(jugador1).setApuesta(apuesta1);
        int jugador2 = this.buscarIndiceJugador(cedula2); /*index jug2*/
        this.myJugadores.get(jugador2).setApuesta(apuesta2);
        int numPartida=this.numPartidaDosJugadores( this.myJugadores.get(jugador1), this.myJugadores.get(jugador2), fecha);
        String nombre = "Blackjack";
        /*preguntar-->*/
        this.myBarajas.set(0, this.myBarajas.getFirst().barajarCartas(this.myBarajas.getFirst()));
        this.myPartidos.add(new PartidoBlackjack(fecha, this.myJugadores.get(jugador1), nombre,numPartida, this.myJugadores.get(jugador2), this.myBarajas.get(0)));
//crear partido blackjack        
//llama ese metodo y lo imprime en return

        return "";
    }

    private int numPartidaDosJugadores(Jugador jug1, Jugador jug2, String fecha) {
        int numPartida = 0;
        for (Partido p : this.myPartidos) {
            Juego ar[] = p.getMyJuego();
            if (ar[0].getMyJugador().equals(jug1) && ar[1].getMyJugador().equals(jug2) && p.getFecha() == fecha) {
                numPartida++;
            }
        }
         return numPartida;
    } 
    
    public int contarPartidaJugador(Jugador obj, String fecha){
    int numPartida=0;
    for(Partido p: this.myPartidos){
        Juego ju[]=p.getMyJuego();   
        if(ju[0].getMyJugador().equals(obj)||ju[1].getMyJugador().equals(obj)&&p.getFecha()== fecha){
        numPartida++;
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
            if (!e.getCedula().equalsIgnoreCase(cedula));
            buscado++;
        }
        return buscado;
    }
}
