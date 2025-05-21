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
    public PartidoPoker( String fecha, Jugador myJugador1, String nombrePartida, int numP, Jugador myJugador2,Baraja barajaU,Crupier myCrupier ) {
        super( fecha, myJugador1, nombrePartida, numP,myJugador2,barajaU,myCrupier);
       
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
        Juego [] juego= super.getMyJuego();
        String cad="";
        int contAs=0;
        /*
        for(int i=0;i<5;i++){
            Carta [] cartas=juego[i].getMyCarta();
            cartas[i].setValor(this.reasignarValores(cartas[i]));
            
            if(this.esValorAs(cartas[i])==true&&contAs<1){
                contAs++;
            }else if(this.esValorAs(cartas[i])==true&&contAs>1){
                cartas[i].setValor(1);
            }
             
        }*/
        
        return "";
    }
    
    public boolean esValorAs(Carta cart){
        boolean esA=false;
        if(cart.getValor()==15)esA=true;
        return esA;
    }
    
    public int reasignarValores(Carta carta){
        int nuevoValor=0;
        
        switch(carta.getNumero()){
            case "A":
                nuevoValor=15;
                break;
            case "K":
                nuevoValor=12;
                break;
            case "Q":
                nuevoValor=13;
                break;
            case "J":
                nuevoValor=11;
                break;
            case "10":
                nuevoValor=10;
                break;
            case "9":
                nuevoValor=9;
                break;
            case "8":
                nuevoValor=8;
                break;
            case "7":
                nuevoValor=7;
                break;
            case "6":
                nuevoValor=6;
                break;
            case "5":
                nuevoValor=5;
                break;
            case "4":
                nuevoValor=4;
                break;
            case "3":
                nuevoValor=3;
                break;
            case "2":
                nuevoValor=2;
                break;
            case "1":
                nuevoValor=1;
                break;
        }
        return nuevoValor;
    }
}

