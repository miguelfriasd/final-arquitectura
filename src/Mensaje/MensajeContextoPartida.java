/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mensaje;

import Modelo.Partida;
import java.io.Serializable;

/**
 *
 * @author mig_2
 */
public class MensajeContextoPartida implements Serializable, MensajeStrategy{

    private final Partida partida;
    
    public MensajeContextoPartida(Partida partida) {
        this.partida = partida;
    }

    public Partida getPartida() {
        return partida;
    }
    
    @Override
    public String obtenerValor(String llave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
