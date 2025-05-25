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

    public Jugador getMyGanador() {
        return myGanador;
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
                if (cartasSeleccionadas <= 2) {
                    super.getMyJuego(0).setMyCartas(c);
                } else {
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

    public String hayBlackjackCrupier() {
        String cad = "";
        int gano;
        int puntaje1 = this.getMyJuego(0).getPuntaje();
        int puntaje2 = this.getMyJuego(1).getPuntaje();
        if (puntaje1 == 21 && puntaje2 == 21) {
            cad = "Ha ocurrido un empate";
        } else if (puntaje1 == 21) {
            gano = this.premiarJugBlackjack();
            cad = "¡ Jugador " + this.getMyJuego(0).getMyJugador().getNombre() + "hizo BlackJack!, ha ganado " + gano;
            this.myGanador = this.getMyJuego(0).getMyJugador();
            this.puntajeGanador = this.getMyJuego(0).getPuntaje();
        } else if (puntaje2 == 21) {
            this.premiarCru();
            cad = "¡ La casa ha ganado la partida!";
            this.myGanador = this.getMyJuego(1).getMyJugador();
            this.puntajeGanador = this.getMyJuego(1).getPuntaje();
        } else {
            cad = "No hay BlackJack,¡Sigue jugador 1! :D";
        }
        return cad;
    }

    public int premiarJugBlackjack() {
        int ganancia = super.getMyJuego(0).getMyJugador().getApuesta() + super.getMyJuego(1).getMyJugador().getApuesta();
        ganancia *= 3;
        return ganancia;
    }
    
    public int premiarGanado(int index){
        int ganancia = super.getMyJuego(0).getMyJugador().getApuesta() + super.getMyJuego(1).getMyJugador().getApuesta();
        ganancia += (super.getMyJuego(index).getMyJugador().getApuesta())/2;
        return ganancia;   
    }

    public int premiarCru() {
        int ganancia = super.getMyJuego(0).getMyJugador().getApuesta() + super.getMyJuego(1).getMyJugador().getApuesta();
        int bonificacion = ganancia * (15 / 100);
        super.getMyCrupier().setBonificacion(bonificacion);
        return bonificacion;
    }

    public String otraCartaJugador(int index) {
        //inhabilitar botones del jugador dos
        String carta = "";
        if(this.perdio(index)){
            return "Perdio";//significa que su puntaje es mayor de 21
        }
        if (super.getMyJuego(index).getMyCartas().size() == 5) {
            return "Solo5";//significa que ya tiene las 5 cartas
        }
        for (Carta c : super.getBarajaUsada().getMyCartas()) {
            if (!c.isOcupada()) {
                c.setOcupada(true);
                carta = c.toString();
                super.getMyJuego(index).setMyCartas(c);
                super.getMyJuego(index).setPuntaje(index);
                break;
            }
        }
        
        return carta;
    }
    
    public boolean gano(int index) {
        boolean igual21 = false;
        if (super.getMyJuego(index).getPuntaje() ==21) {
            igual21 = true;
        }
        /*falta acomodar ya que si este saca 21 se debe esperar a que el otro tire a ver si saca 21 pq podria haber empete, entons no alcance a pensar como se haria ahi
        o sea como tal falto mirar que pasa si saca 21 con esa carta, que ya no puede sacar mas cartas y que sigue jug y luego de eso sacar el ganador
        ademas de crear un metodo de como seria cuando gana por pedir cartas ya que se le daria el valor de la apuesta del jug *50% de ese(aja esta esta en el pdf)
        falta mirar lo de las estadisticas no se como se haria y crear el boton de nuevo, regresar a la principal por si se quiere agregar mas judadores o cambiar de modo
        y haciendo eso ver pq deja de servir lo del puntaje con deepseek o preguntarle a juan camilo que ya lo estaban terminando*/
        return igual21;
    }
    
    public boolean perdio(int index) {
        boolean mayor21 = false;
        if (super.getMyJuego(index).getPuntaje() > 21) {
            mayor21 = true;
        }
        return mayor21;
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
        for (Carta c : super.getMyJuego(index).getMyCartas()) {
            if (this.esValorAs(c) == true && contAs == 0) {
                contAs++;
            } else if (this.esValorAs(c) == true && contAs > 1) {
                c.setValor(1);
                contAs++;
            }
            puntaje += c.getValor();
            System.out.println("valor " + c.getValor());
        }

        return puntaje;
    }
    //inicio juego que devuelve cuatro cartas   
}
