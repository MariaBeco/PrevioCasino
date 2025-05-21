/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

abstract public class Persona {
    private int apuesta;
    private String nombre;
    private String cedula;

    public Persona() {
    }

    public Persona(String nombre,String cedula) {
        this.nombre = nombre;
        this.cedula=cedula;
    }
    
    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return  " nombre: " + this.nombre+", cedula: "+this.cedula+ " apuesta: " + apuesta;
    }
    
}
