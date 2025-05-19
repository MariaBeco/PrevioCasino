/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class PartidoPoker extends Partido{
    public PartidoPoker( String fecha, Jugador myJugador1, String nombrePartida, int numP, Jugador myJugador2,Baraja barajaU) {
        super( fecha, myJugador1, nombrePartida, numP,myJugador2,barajaU);
       
    }
    
    public String[] enviarCartasInicio(){
        super.getBarajaUsada().barajarCartas(super.getBarajaUsada());
        String []cartasIniciales=new String[4];
        for(Carta c:super.getBarajaUsada().getMyCartas()){
            for(int i=0;i<4;i++){
                cartasIniciales[i]=c.toString();
                c.setOcupada(true);
            }
        }
       return cartasIniciales;
    }
    public String hayPoker(){
        return "";
    }
}

