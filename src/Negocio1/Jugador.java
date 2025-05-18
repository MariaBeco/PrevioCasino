/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class Jugador extends Persona  {
    private int apuesta;
    private int numJugadasDia=0;
    private String telefono;
    public Jugador() {
    }

    public Jugador(String nombre,String cedula,String telefeno) {
        super(nombre, cedula);
        this.telefono=telefeno;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }
    public int getNumJugadaDia() {
        return numJugadasDia;
    }

    public void setNumJugadaDia(int numJugadaDia) {
        this.numJugadasDia = numJugadaDia;
    }
    @Override
    public String toString() {
        return "Jugador " +super.toString()+ " apuesta= " + apuesta+" Número de jugadas al dia"+this.numJugadasDia;
    }
    
}
