/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainServer;

import Servidor.Servidor;
import Servidor.ServidorThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor(new ServerSocket(1234));
            servidor.arrancarServidor();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
