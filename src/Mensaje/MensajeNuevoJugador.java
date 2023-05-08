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
public class MensajeNuevoJugador implements MensajeStrategy, Serializable{
    HashMap<String, String> mensaje;
    
    public MensajeNuevoJugador(String nombre) {
        mensaje = new HashMap<>();
        mensaje.put("jugador", nombre);

    } 

    @Override
    public String obtenerValor(String llave) {
        return mensaje.get(llave);
    }
}
