/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class Carta {
   private String numero;
   private String palo;
   private boolean ocupada;

    public Carta(String numero, String palo,boolean ocupada) {
        this.numero = numero;
        this.palo = palo;
        this.ocupada=ocupada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Carta" + "numero= " + numero + ", palo= " + palo + ", ocupada= " + ocupada;
    }
}
