/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mensaje;

import java.io.Serializable;

/**
 *
 * @author mig_2
 */
public class MensajeNuevoJugador implements MensajeStrategy, Serializable{
    Mensaje mensaje;
    
    public MensajeNuevoJugador(String nombre) {
        mensaje = new Mensaje();
        mensaje.agregarPar("jugador", nombre);

    } 
    
    @Override
    public Mensaje getMensaje() {
        return mensaje;
    }
}
