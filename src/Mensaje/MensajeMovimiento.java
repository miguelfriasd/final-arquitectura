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
public class MensajeMovimiento implements MensajeStrategy, Serializable{

    Mensaje mensaje;
    
    public MensajeMovimiento(String coordenadaX, String coordenadaY, String posicion) {
        mensaje = new Mensaje();
        mensaje.agregarPar("x", coordenadaX);
        mensaje.agregarPar("y", coordenadaY);
        mensaje.agregarPar("posicion", posicion);
    }
    
    @Override
    public Mensaje getMensaje() {
        return mensaje;
    }
  
}
