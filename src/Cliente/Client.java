/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import ContextoLocalPartida.ContextoLocalPartida;
import Mensaje.MensajeContextoPartida;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajePartidaEmpezada;
import Mensaje.MensajeStrategy;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class Client {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ContextoLocalPartida contextoLocalPartida;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.contextoLocalPartida = new ContextoLocalPartida();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandarMensaje(MensajeStrategy mensaje){
        try {
            outputStream.writeObject(mensaje);
            outputStream.flush();
            outputStream.reset();
        } catch (IOException ex) {
            cerrarTodo();
        }  
    }
    
    public void listenerMensajes(){
        new Thread(new Runnable() {
                @Override
                public void run() {
                    while (socket.isConnected() && !contextoLocalPartida.partidaEmpezada()) { 
                        MensajeStrategy mensaje;
                        try {
                            mensaje = (MensajeStrategy)inputStream.readObject();
                            if (mensaje instanceof MensajePartidaEmpezada) {
                                contextoLocalPartida.setPartidaEmpezada();
                            }                            
                        } catch (IOException | ClassNotFoundException ex) {
                            cerrarTodo();
                        }
                    }
                    System.out.println("Partida Empezada");
                    while (socket.isConnected() && contextoLocalPartida.partidaEmpezada()) { 
                        
                        MensajeStrategy mensaje;
                        try {
                            mensaje = (MensajeStrategy)inputStream.readObject();
                            if (mensaje instanceof MensajeContextoPartida) {
                                ((MensajeContextoPartida) mensaje).getPartida().imprimirTablero();
                            }
                            else if (mensaje instanceof MensajePartidaEmpezada) {
                                contextoLocalPartida.setPartidaEmpezada();
                            }                            
                        } catch (IOException | ClassNotFoundException ex) {
                            cerrarTodo();
                        }
                    }
                    cerrarTodo();
                }
        }).start();
    }

    public ContextoLocalPartida getContextoLocalPartida() {
        return contextoLocalPartida;
    }
    
    public boolean isConnected(){
        return socket.isConnected();
    }
    
    private void cerrarTodo(){
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
}
