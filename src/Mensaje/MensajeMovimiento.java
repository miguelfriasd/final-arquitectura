/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mensaje;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author mig_2
 */
public class MensajeMovimiento implements MensajeStrategy, Serializable{

    HashMap<String, String> mensaje;
    
    public MensajeMovimiento(String coordenadaX, String coordenadaY, String posicion) {
        mensaje = new HashMap<>();
        mensaje.put("coordenadaX", coordenadaX);
        mensaje.put("coordenadaY", coordenadaY);
        mensaje.put("posicion", posicion);
    }

    @Override
    public String obtenerValor(String llave) {
        return mensaje.get(llave);
    }
  
}
