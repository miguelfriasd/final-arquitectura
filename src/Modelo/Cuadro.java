/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author mig_2
 */
public class Cuadro implements Serializable{
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    
    public Cuadro() {
        arriba = false;
        abajo = false;
        izquierda = false;
        derecha = false;
    }
    
    public boolean completo(){
        return arriba && abajo && izquierda && derecha;
    }
}
