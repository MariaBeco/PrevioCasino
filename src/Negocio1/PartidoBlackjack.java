/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class PartidoBlackjack extends Partido {
    private Jugador myGanador;
    private int puntajeGanador;

    
    public PartidoBlackjack( String fecha, Jugador myJugador1, String nombrePartida, int numP, Jugador myJugador2,Baraja barajaU) {
        super( fecha, myJugador1, nombrePartida, numP,myJugador2,barajaU);
       
    }
    
    
    public String[] enviarCartasInicio(){
        super.getBarajaUsada().barajarCartas(super.getBarajaUsada());
        String []cartasIniciales=new String[4];
        Juego [] juego=super.getMyJuego();
        Carta [] cartaInicialJug1=new Carta[2];
        Carta [] cartaInicialJug2=new Carta[2];
      for(Carta c:super.getBarajaUsada().getMyCartas()){
            for(int i=0;i<4;i++){
                cartasIniciales[i]=c.toString();
                c.setOcupada(true);
            }
      }  
      for(Carta c:super.getBarajaUsada().getMyCartas()){
                    for(int l=2;l<4;l++){
                         for(int i=0; i<2;i++){
                         cartaInicialJug1[i]=c;
                           }
                    cartaInicialJug2[l]=c;
                     }
           }
       return cartasIniciales;
    }
    
    
    public String hayBlackjack(){
        Juego [] juego= super.getMyJuego();
        int puntaje=0;
        String cad="";
        int contAs=0;
        for(int i=0;i<2;i++){
            Carta [] cartas=juego[i].getMyCarta();
            if(this.darleValorAs(cartas[i])==true&&contAs>1){
                cartas[i].setValor(1);         
            }
            puntaje += cartas[i].getValor();
        }
        if(puntaje==21)cad="¡Hay BlackJack!";
        else cad="No hay BlackJack,¡Sigue jugando!:D";
        return cad;
    }
    
    
    public boolean darleValorAs(Carta cart){
        //la primera a siempre vale 11 si hay mas vale 1<----
        boolean esA=false;
        if(cart.getValor()==11)esA=true;
        return esA;
    }
    //inicio juego que devuelve cuatro cartas
}
