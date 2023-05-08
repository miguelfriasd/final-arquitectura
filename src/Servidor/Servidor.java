/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mig_2
 */
public class Servidor {
    
    private final ServerSocket socketServidor;

    public Servidor(ServerSocket serverSocket) {
        this.socketServidor = serverSocket;
    }
    
        public void arrancarServidor(){
        try {
            while (true){
                Socket socket = socketServidor.accept();
                ServidorThread servidorThread = new ServidorThread(new ServerSocket(0),socket);
                Thread thread = new Thread(servidorThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
