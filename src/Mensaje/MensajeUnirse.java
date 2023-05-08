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
public class MensajeUnirse implements Serializable, MensajeStrategy{
    
    HashMap<String, String> mensaje;

    public MensajeUnirse(String nombreUsuario){
        mensaje = new HashMap<>();
        mensaje.put("nombre",  nombreUsuario);
    }

    @Override
    public String obtenerValor(String llave) {
        return mensaje.get(llave);
    }
    
    
    

    
}
