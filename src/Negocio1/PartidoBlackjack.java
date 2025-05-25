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

    public PartidoBlackjack(String fecha, Jugador myJugador1, String nombrePartida, int numP, Jugador myJugador2, Baraja barajaU, Crupier myCrupier) {
        super(fecha, myJugador1, nombrePartida, numP, myJugador2, barajaU, myCrupier);

    }

    public String[] enviarCartasInicio() {
        super.getBarajaUsada().barajarCartas(super.getBarajaUsada());
        String[] cartasIniciales = new String[4];
        int cartasSeleccionadas = 0;
        int asignarCarta = 0;
        for (Carta c : super.getBarajaUsada().getMyCartas()) {
            if (!c.isOcupada()) {
                cartasIniciales[cartasSeleccionadas] = c.toString();
                c.setOcupada(true);
                cartasSeleccionadas++;
                if(cartasSeleccionadas<=2){
                    super.getMyJuego(0).setMyCartas(c);
                }else{
                   super.getMyJuego(1).setMyCartas(c); 
                }
            }
            if (cartasSeleccionadas == 4) {
                break;
            }
        }
        
        //como asiganar el puntaje a ese partido en especifico sin tener que recorrer el arreglo
        return cartasIniciales;
    }

    public String hayBlackjack() {
        String cad = "";
        int puntaje1 = this.getMyJuego(0).getPuntaje();
        int puntaje2 = this.getMyJuego(1).getPuntaje();
        if (puntaje1 == 21 && puntaje2 == 21) {
            cad = "Ha ocurrido un empate";
        } else if (puntaje1 == 21) {
            cad = "¡ Jugador " + this.getMyJuego(0).getMyJugador().getNombre() + "hizo BlackJack!";
            this.myGanador = this.getMyJuego(0).getMyJugador();
            this.puntajeGanador = this.getMyJuego(0).getPuntaje();
            //valor de la apuesta ganada
        } else if (puntaje2 == 21) {
            cad = "¡ Jugador " + this.getMyJuego(1).getMyJugador().getNombre() + "hizo BlackJack!";
            this.myGanador = this.getMyJuego(1).getMyJugador();
            this.puntajeGanador = this.getMyJuego(1).getPuntaje();
            //valor de la apuesta ganada
        } else {
            cad = "No hay BlackJack,¡Sigue jugador 1! :D";
        }
        return cad;
    }
    
    public String otraCarta(){
        //inhabilitar botones del jugador dos
        
        return"";
    }
    private boolean esValorAs(Carta cart) {

        boolean esA = false;
        if (cart.getValor() == 11) {
            esA = true;
        }
        return esA;
    }

    public int Puntaje(int index) {
        int puntaje = 0;
        int contAs = 0;
        System.out.println("error ");
        for(Carta c:super.getMyJuego(index).getMyCartas()){
            if (this.esValorAs(c) == true && contAs == 0) {
                contAs++;
            } else if (this.esValorAs(c) == true && contAs > 1) {
                c.setValor(1);
                contAs++;
            }
            puntaje += c.getValor();
            System.out.println("valor "+c.getValor());
        }

        return puntaje;
    }
    //inicio juego que devuelve cuatro cartas   
} 
