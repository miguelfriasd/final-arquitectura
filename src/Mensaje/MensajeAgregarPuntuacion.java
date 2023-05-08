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
public class MensajeAgregarPuntuacion implements Serializable, MensajeStrategy{
    HashMap<String, String> mensaje;
    
    public MensajeAgregarPuntuacion(String nombre, String puntuacion) {
        mensaje = new HashMap<>();
        mensaje.put("nombre", nombre);
        mensaje.put("puntuacion", puntuacion);
    }

    @Override
    public String obtenerValor(String llave) {
        return mensaje.get(llave);
    }
    
}
