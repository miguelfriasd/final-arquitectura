/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Controlador.ControladorUnirsePartida;
import Logica.ControlPartida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class Servidor{
    
    private final ServerSocket socketServidor;
    private final ControlPartida controlPartida;

    public Servidor(ServerSocket serverSocket) {
        this.socketServidor = serverSocket;
        this.controlPartida = new ControlPartida();
    }
    
    public void arrancarServidor(){
        try {
            while (!controlPartida.partidaLlena() && !controlPartida.partidaEmpezada()){
                Socket socket = socketServidor.accept();
                ClientHandler clientHandler = new ClientHandler(socket,controlPartida);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
            cerrarSocketServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cerrarSocketServidor(){
        try {
            if (socketServidor != null) {
                socketServidor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
