/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstadoPartida;

import Cliente.Client;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajeUnirse;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ContextoPartida {
    private static ContextoPartida instance;
    private List<MensajeMovimiento> listaMensajes;
    private boolean partidaEmpezada;
    private Client client;
    
    public ContextoPartida(){
        partidaEmpezada = false;
    }
    
    public void setPartidaEmpezada(){
        partidaEmpezada = true;
        listaMensajes = new LinkedList<>();
    }
    
    public void initCliente(int puerto) throws IOException{
        if (client==null) {
            this.client = new Client(new Socket("", puerto));
        }
    }
    
    public void enviarMensajeUnirsePartida(String nombre){
        client.mandarMensaje(new MensajeUnirse(nombre));
    }
    
    public void agregarMovimiento(MensajeMovimiento mensajeMovimiento){
        if (partidaEmpezada) {
            listaMensajes.add(mensajeMovimiento);
        }
    }
    
    public void resetContextoPartia(){
        instance = null;
    }
}
