/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstadoPartida;

import Cliente.Client;
import Mensaje.MensajeMovimiento;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class EstadoPartida {
    private static EstadoPartida instance;
    private List<MensajeMovimiento> listaMensajes;
    private boolean partidaEmpezada;
    private Client client;
    
    private EstadoPartida(){
        partidaEmpezada = false;
    }
    
    public static EstadoPartida getInstance(){
        return (instance == null)? (instance = new EstadoPartida()) : instance;
    }
    
    public void setPartidaEmpezada(){
        partidaEmpezada = true;
        listaMensajes = new LinkedList<>();
    }
    
    public void initCliente(int puerto) throws IOException{
        this.client = new Client(new Socket("", puerto));
    }
    
    public void agregarMovimiento(MensajeMovimiento mensajeMovimiento){
        if (partidaEmpezada) {
            listaMensajes.add(mensajeMovimiento);
        }
    }
    
    public void resetEstado(){
        instance = null;
    }
}
