/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

import java.util.ArrayList;

/**
 *
 * @author saraj
 */
public class Juego {
    private Jugador myJugador;
    private ArrayList <Carta> myCartas=new ArrayList<>();
    private int puntaje;
    private String nombre;
    

    public Juego(Jugador myJugador, String nombre) {
        this.myJugador = myJugador;
        this.nombre = nombre;
    }

    public Jugador getMyJugador() {
        return myJugador;
    }

    public void setMyJugador(Jugador myJugador) {
        this.myJugador = myJugador;
    }

    public ArrayList<Carta> getMyCartas() {
        return myCartas;
    }

    public void setMyCartas(ArrayList<Carta> myCarta) {
        this.myCartas = myCarta;
    }
    
    public void setMyCartas(Carta myCarta) {
        this.myCartas.add(myCarta);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public String toString() {
        return "Juego " + "myJugador= " + myJugador + ", puntaje= " + puntaje + ", nombre= " + nombre;
    }
    
}
