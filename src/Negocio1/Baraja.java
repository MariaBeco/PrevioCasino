/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author saraj
 */
public class Baraja {

    private int numero;
    private boolean disponible;
    private ArrayList<Carta> myCartas;

    public Baraja(int num) {
        this.numero = num;
        this.myCartas = new ArrayList<>();
        this.crearCartas();
    }

    private ArrayList<Carta> crearCartas() {
        String palo[] = {"\u2665", "\u2666", "\u2663", "\u2660"};
        String numero[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        boolean ocupada = false;
        int valor;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numero.length; j++) {
                valor=this.valor(numero[j]);
                this.myCartas.add(new Carta(palo[i], numero[j], ocupada,valor));
            }
        }
        return this.myCartas;
    }

    private int valor(String numero) {
        int valor=0;
        switch (numero) {
            case "A":
                valor = 11;
                break;
            case "2":
                valor = 2;
                break;
            case "3":
                valor = 3;
                break;
            case "4":
                valor = 4;
                break;
            case "5":
                valor = 5;
                break;
            case "6":
                valor = 6;
                break;
            case "7":
                valor = 7;
                break;
            case "8":
                valor = 8;
                break;
            case "9":
                valor = 9;
                break;
            case "10":
                valor = 10;
                break;
            case "J":
                valor = 10;
                break;
            case "Q":
                valor = 10;
                break;
            case "K":
                valor = 10;
                break;
        }
        return valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<Carta> getMyCartas() {
        return myCartas;
    }

    public void setMyCartas(ArrayList<Carta> myCartas) {
        this.myCartas = myCartas;
    }

    public String toString() {
        String cad = "";
        for (Carta c : this.myCartas) {
            cad += c.toString() + "\n";
        }
        return "Número: " + this.numero + ", disponible: " + this.disponible + ", cartas: " + cad;
    }

    public Baraja barajarCartas(Baraja obj) {
        Collections.shuffle(obj.getMyCartas());
        return obj;
    }
}
