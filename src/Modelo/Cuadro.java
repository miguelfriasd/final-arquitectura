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
    boolean arriba;
    boolean abajo;
    boolean izquierda;
    boolean derecha;
    
    Cuadro() {
        arriba = false;
        abajo = false;
        izquierda = false;
        derecha = false;
    }
    
    public boolean completo(){
        return arriba && abajo && izquierda && derecha;
    }
}
