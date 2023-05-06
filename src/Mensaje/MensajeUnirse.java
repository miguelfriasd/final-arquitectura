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
public class MensajeUnirse implements Serializable, MensajeStrategy{

    Mensaje mensaje;

    public MensajeUnirse(String nombreUsuario){
        mensaje = new Mensaje();
        mensaje.agregarPar("nombre", nombreUsuario);
    }
    
    
    
    @Override
    public Mensaje getMensaje() {
        return mensaje;
    }
    
}
