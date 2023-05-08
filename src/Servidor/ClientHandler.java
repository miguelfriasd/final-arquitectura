/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelo.Jugador;
import Logica.ControlPartida;
import Mensaje.Mensaje;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajeSalir;
import Mensaje.MensajeStrategy;
import Mensaje.MensajeUnirse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class ClientHandler implements Runnable{
    
    private static List<ClientHandler> listaClientes;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Jugador jugador;


    public ClientHandler(Socket socket) {
        try{
            this.socket=socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            if (listaClientes==null) {
                listaClientes = new ArrayList<>();
            }
            listaClientes.add(this);
        } catch(IOException e){
       
        }
    }

    
    
    @Override
    public void run(){   
        MensajeUnirse mensajeUnirse = null;
               
        while (socket.isConnected() && mensajeUnirse == null && !ControlPartida.getInstance().partidaEmpezada()) {
            mensajeUnirse = recibirMensajeUnirse();
            if (mensajeUnirse == null) {
                System.out.println("Mensaje nulo");
            }
        }
        
        System.out.println("Jugador unido");
        
        if (ControlPartida.getInstance().partidaEmpezada() && mensajeUnirse == null || !socket.isConnected()) {
            cerrarTodo();
        }
        
        while (socket.isConnected() && ControlPartida.getInstance().partidaEmpezada()) {                
            try {
                MensajeStrategy mensaje = (MensajeStrategy)inputStream.readObject();
                if (mensaje instanceof MensajeMovimiento) {
                    Mensaje contenidoMensaje = mensaje.getMensaje();
                    String coordenadaX = contenidoMensaje.obtenerValor("coordenadaX");
                    String coordenadaY = contenidoMensaje.obtenerValor("coordenadaY");
                    String posicion = contenidoMensaje.obtenerValor("posicion");
                    ControlPartida.getInstance().realizarMovimiento(Integer.parseInt(coordenadaX), Integer.parseInt(coordenadaY), posicion, jugador);
                    mensajeBroadcast(mensaje);
                }
                else if (mensaje instanceof MensajeSalir) {
                    ControlPartida.getInstance().eliminarJugador(jugador);
                    cerrarTodo();
                }
            } catch (IOException e) {
                cerrarTodo();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cerrarTodo();
    }
    
    private void mensajeBroadcast(MensajeStrategy mensaje){
        for(ClientHandler clientHandler : listaClientes){
            try {
                clientHandler.outputStream.writeObject(mensaje);
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
        try{
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
