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
        for (int i = 1; i<=this.myBarajas.size(); i++) {
            this.myBarajas.add(new Baraja(i));
        }
    }

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
        Collections.shuffle(this.myBarajas.getFirst().getMyCartas());

        
        return "";
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
}
