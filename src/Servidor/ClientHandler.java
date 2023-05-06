/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelo.Jugador;
import Logica.ControlPartida;
import Mensaje.Mensaje;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajeStrategy;
import Mensaje.MensajeUnirse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class ClientHandler implements Runnable{
    
    private static ArrayList<ClientHandler> listaClientes;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Jugador jugador;


    public ClientHandler(Socket socket) {
        try{
            this.socket=socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            listaClientes.add(this);
        } catch(IOException e){
       
        }
    }

    
    
    @Override
    public void run() {
        MensajeUnirse mensajeUnirse = null;
        
        while (socket.isConnected() && mensajeUnirse == null) {            
            mensajeUnirse = recibirMensajeUnirse();
        }
        
        while (socket.isConnected() && !ControlPartida.partidaEmpezada()) {            
            
        }
        
        while (socket.isConnected()) {            
            try {
                MensajeStrategy mensaje = (MensajeStrategy)inputStream.readObject();
                if (mensaje instanceof MensajeMovimiento && ()) {
                    Mensaje contenidoMensaje = mensaje.getMensaje();
                    String coordenadaX = mensaje.getMensaje().obtenerValor("coordenadaX");
                    String coordenada
                    // send acknowledgement back to client
//                    ProtocoloMensaje.Mensaje acknowledgement = ProtocoloMensaje.createAcknowledgement(true);
//                    outputStream.writeObject(acknowledgement);
//                    outputStream.flush();
                }
            } catch (IOException e) {
                cerrarTodo();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void mensajeBroadcast(String mensaje){
        for(ClientHandler clientHandler : listaClientes){
            try {
                clientHandler.outputStream.writeObject(this);
                clientHandler.outputStream.flush();
            } catch (IOException ex) {
                cerrarTodo();
            }
        }
    }
    
    private void removerClientHandler(){
        listaClientes.remove(this);   
    }
    
    private void cerrarTodo(){
        removerClientHandler();
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream !=null) {
                outputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private MensajeUnirse recibirMensajeUnirse(){
        try {
            MensajeUnirse mensajeRecibido = (MensajeUnirse)inputStream.readObject();
            String nombreJugador = mensajeRecibido.getMensaje().obtenerValor("nombre");
            InetAddress ipJugador = socket.getInetAddress();
            jugador = new Jugador(nombreJugador, ipJugador); 
            ControlPartida.getInstance().agregarJugador(jugador);
            return mensajeRecibido;
        } catch (IOException ex) {
            cerrarTodo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
