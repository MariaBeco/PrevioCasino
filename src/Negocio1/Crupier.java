/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio1;

/**
 *
 * @author saraj
 */
public class Crupier extends Persona {
    private int bonificacion;
    
    public Crupier() {
    }

    public Crupier(String nombre,String cedula) {
        super(nombre, cedula);
    }

    public int getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(int bonificacion) {
        this.bonificacion = bonificacion;
    }
    
    @Override
    public String toString() {
        return " Crupier "+ super.toString();
    }
    
}
