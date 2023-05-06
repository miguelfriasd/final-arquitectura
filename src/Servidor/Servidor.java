/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Logica.ControlPartida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mig_2
 */
public class Servidor {
    
    private ServerSocket socketServidor;

    public Servidor(ServerSocket serverSocket) {
        this.socketServidor = serverSocket;
    }
    
    public void arrancarServidor(){
        try {
            while (!ControlPartida.partidaLlena()){
                Socket socket = socketServidor.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cerrarSocketServidor(){
        try {
            if (socketServidor != null) {
                socketServidor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
