/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class Juego {
    private Jugador myJugador;
    private Carta myCarta[]=new Carta[5];
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

    public Carta[] getMyCarta() {
        return myCarta;
    }

    public void setMyCarta(Carta[] myCarta) {
        this.myCarta = myCarta;
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
        return "Juego " + "myJugador= " + myJugador + ", myCarta= " + myCarta + ", puntaje= " + puntaje + ", nombre= " + nombre;
    }
    
}
