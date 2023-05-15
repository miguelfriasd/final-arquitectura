/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContextoLocalPartida;

import Cliente.Client;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajeUnirse;
import Modelo.Partida;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ContextoLocalPartida {
    private List<MensajeMovimiento> listaMensajes;
    private Partida estadoPartida;
    private boolean partidaEmpezada;
    
    public ContextoLocalPartida(){
        partidaEmpezada = false;
    }
    
    public void setPartidaEmpezada(){
        partidaEmpezada = true;
        listaMensajes = new LinkedList<>();
    }
    
    public boolean partidaEmpezada(){
        return partidaEmpezada;
    }
    
    public void agregarMovimiento(MensajeMovimiento mensajeMovimiento){
        if (partidaEmpezada) {
            listaMensajes.add(mensajeMovimiento);
        }
    }
    
    public void actualizarEstadoPartida(Partida partida){
        this.estadoPartida = partida;
    }
    
    public Partida getEstadoPartida(){
        return estadoPartida;
    }
}
