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
        int cartasSeleccionadas=0;
        int asignarCarta=0;
        Juego [] juego=super.getMyJuego();
        Carta [] cartaInicialJug1=new Carta[2];
        Carta [] cartaInicialJug2=new Carta[2];
      for(Carta c:super.getBarajaUsada().getMyCartas()){
          if(!c.isOcupada()){
             cartasIniciales[cartasSeleccionadas]=c.toString();
             c.setOcupada(true);
             cartasSeleccionadas++;
          }  
            if(cartasSeleccionadas==4){
                    break;
                }
      }  
  
      /*for(Carta c:super.getBarajaUsada().getMyCartas()){
                    for(int l=2;l<4;l++){
                         for(int i=0; i<2;i++){
                         cartaInicialJug1[i]=c;
                           }
                    cartaInicialJug2[l]=c;
                     }
           }*/
       return cartasIniciales;
    }
    
    
    public String hayBlackjack(){
        Juego [] juego= super.getMyJuego();
        int puntaje=0;
        String cad="";
        int contAs=0;
        for(int i=0;i<2;i++){
            Carta [] cartas=juego[i].getMyCarta();
            if(this.esValorAs(cartas[i])==true&&contAs>1){
                cartas[i].setValor(1);         
            }
            puntaje += cartas[i].getValor();
        }
        if(puntaje==21)cad="¡Hay BlackJack!";
        else cad="No hay BlackJack,¡Sigue jugando!:D";
        return cad;
    }
     public String hayBlackjack(Juego[] juego){
         String cad="";
         Juego myJug[]=super.getMyJuego();
         Carta[] myCarta1=myJug[0].getMyCarta();
         Carta[]myCarta2=myJug[1].getMyCarta();
         int puntaje1=this.Puntaje(myCarta1);
         int puntaje2=this.Puntaje(myCarta2);
         if(puntaje1==21&&puntaje2==21){
             cad="Ha ocurrido un empate";
         }else if(puntaje1==21){
             cad="¡ Jugador "+juego[0].getMyJugador().getNombre()+"hizo BlackJack!";
             
         }
     return cad;
     }
    
    public boolean esValorAs(Carta cart){
 
        boolean esA=false;
        if(cart.getValor()==11)esA=true;
        return esA;
    }
    
    public int Puntaje(Carta []myCartas){
        int puntaje=0;
        int contAs=0; 
        Carta [] cartas=myCartas;
        for(int i=0;i<5;i++){
            if(cartas[i]!=null){
                if(this.esValorAs(cartas[i])==true&&contAs<1){
                contAs++;
            }else if(this.esValorAs(cartas[i])==true&&contAs>1){
                cartas[i].setValor(1);
                contAs++;
            }
            puntaje += cartas[i].getValor();
            }
        }

        return puntaje;  
    }
    //inicio juego que devuelve cuatro cartas
}
