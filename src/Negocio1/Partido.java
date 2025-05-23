/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

public class Partido {
    private Juego myJuego[]=new Juego[2];
    private Crupier myCrupier;
    private int numero;
    private String nombrePartida;
    private String fecha;
    private int pagoApuestas;
    private Baraja barajaUsada;
    
    public Partido(String fecha, Jugador myJugador1, String nombrePartida,int numero, Jugador myJugador2,Baraja barajaU,Crupier myCrupier) {
        this.fecha = fecha;
        this.barajaUsada=barajaU;
        this.nombrePartida=nombrePartida;
        this.numero=numero;
        this.myCrupier=myCrupier;
        this.crearJuegoDosJugadores(myJugador1,  nombrePartida, myJugador2);
    }
    
     private void crearJuegoDosJugadores(Jugador myJugador1, String nombrePartida, Jugador myJugador2){
            this.myJuego[0]=new Juego(myJugador1, nombrePartida);
            this.myJuego[1]=new Juego(myJugador2, nombrePartida);
        }
 
    public Crupier getMyCrupier() {
        return myCrupier;
    }

    public void setMyCrupier(Crupier myCrupier) {
        this.myCrupier = myCrupier;
    }
     
    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public Baraja getBarajaUsada() {
        return barajaUsada;
    }

    public void setBarajaUsada(Baraja barajaUsada) {
        this.barajaUsada = barajaUsada;
    }
     
    public Juego getMyJuego(int index) {
        return myJuego[index];
    }

    public void setMyJuego(Juego[] myJuego) {
        this.myJuego = myJuego;
    }

    public Juego[] getMyJuego() {
        return myJuego;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public int getPagoApuestas() {
        return pagoApuestas;
    }

    public void setPagoApuestas(int pagoApuestas) {
        this.pagoApuestas = pagoApuestas;
    }
    
}
