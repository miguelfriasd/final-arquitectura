/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelo.Jugador;
import Logica.ControlPartida;
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
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class ClientHandler implements Runnable, IObserver{
    
    private static List<ClientHandler> listaClientes;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Jugador jugador;
    private ControlPartida controlPartida;


    public ClientHandler(Socket socket, ControlPartida controlPartida) {
        try{
            this.socket=socket;
            this.controlPartida =controlPartida;
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
        
        while (socket.isConnected() && mensajeUnirse == null && !controlPartida.partidaEmpezada()) {
            try {
                mensajeUnirse = (MensajeUnirse)inputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                cerrarTodo();
            }
            if (mensajeUnirse != null) {
                procesarMensajeUnirse(mensajeUnirse);
            }
        }
        
        synchronized (this) {          
            if (!controlPartida.partidaEmpezada()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                notifyAll();            
            }
        }
        
        while (socket.isConnected() && controlPartida.partidaEmpezada()) {
            MensajeStrategy mensaje;
            try {
                mensaje = (MensajeStrategy)inputStream.readObject();
                if (mensaje instanceof MensajeMovimiento && controlPartida.obtenerJugadorActual() == this.jugador) {
                    int coordenadaX = Integer.parseInt(mensaje.obtenerValor("coordenadaX"));
                    int coordenadaY = Integer.parseInt(mensaje.obtenerValor("coordenadaY"));
                    String posicion = mensaje.obtenerValor("posicion");
                    controlPartida.realizarMovimiento(coordenadaX, coordenadaY, posicion, jugador);
                }                            
            } catch (IOException | ClassNotFoundException ex) {
                cerrarTodo();
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
    
    
    private void procesarMensajeUnirse(MensajeUnirse mensajeUnirse){
        String nombreJugador = mensajeUnirse.obtenerValor("nombre");
        InetAddress ipJugador = socket.getInetAddress();
        jugador = new Jugador(nombreJugador, ipJugador); 
        controlPartida.agregarJugador(jugador);
    }

    @Override
    public void update(String value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
