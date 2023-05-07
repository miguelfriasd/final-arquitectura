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
public class MensajeAgregarPuntuacion implements Serializable, MensajeStrategy{
    Mensaje mensaje;
    
    public MensajeAgregarPuntuacion(String nombre, String puntuacion) {
        mensaje = new Mensaje();
        mensaje.agregarPar("nombre", nombre);
        mensaje.agregarPar("puntuacion", puntuacion);
    }
    
    @Override
    public Mensaje getMensaje() {
        return mensaje;
    }
}
