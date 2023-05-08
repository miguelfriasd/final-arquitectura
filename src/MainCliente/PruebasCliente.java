/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCliente;

import Cliente.Client;
import Mensaje.MensajeUnirse;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mig_2
 */
public class PruebasCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket);
            while (socket.isConnected()) {                
                client.mandarMensaje(new MensajeUnirse("Miguel"));
            }
        } catch (IOException ex) {
            Logger.getLogger(PruebasCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
