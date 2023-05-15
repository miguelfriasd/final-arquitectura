/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelo.Jugador;
import Logica.ControlPartida;
import Mensaje.MensajeContextoPartida;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajePartidaEmpezada;
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
        
        while (!controlPartida.partidaEmpezada()) {            
        }
        enviarMensajePartidaEmpezada();
        
        
        while (socket.isConnected() && controlPartida.partidaEmpezada()) {
            MensajeStrategy mensaje;
            try {
                mensaje = (MensajeStrategy)inputStream.readObject();
                if (mensaje instanceof MensajeMovimiento) {
                    MensajeMovimiento mensajeMovimiento = (MensajeMovimiento)mensaje;
                    int coordenadaX = Integer.parseInt(mensajeMovimiento.obtenerValor("coordenadaX"));
                    int coordenadaY = Integer.parseInt(mensajeMovimiento.obtenerValor("coordenadaY"));
                    String posicion = mensajeMovimiento.obtenerValor("posicion");
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
                outputStream.reset();
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
    
    private void enviarMensajePartidaEmpezada(){
        try {
            outputStream.writeObject(new MensajePartidaEmpezada());
            outputStream.writeObject(new MensajeContextoPartida(controlPartida.getPartida()));
            outputStream.flush();
            outputStream.reset();
        } catch (IOException ex) {
           cerrarTodo();
        }
    }

    @Override
    public void actualizar() {
        try {
            outputStream.writeObject(new MensajeContextoPartida(controlPartida.getPartida()));
            outputStream.flush();
            outputStream.reset();
        } catch (IOException ex) {
            cerrarTodo();
        }
    }

}
