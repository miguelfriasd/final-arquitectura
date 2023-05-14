/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Cliente.Client;
import ContextoLocalPartida.ContextoLocalPartida;
import Mensaje.MensajeUnirse;
import Vista.FrmUnirsePartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;


/**
 *
 * @author mig_2
 */
public class ControladorUnirsePartida {
    
    FrmUnirsePartida frmUnirsePartida;
    private Client client;
    

    public ControladorUnirsePartida(FrmUnirsePartida frmUnirsePartida) {
        this.frmUnirsePartida = frmUnirsePartida;
        
        frmUnirsePartida.agregarUnirsePartidaListener(new UnirsePartidaListener());
    }
    
    class UnirsePartidaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String codigoPartida;
            String nombre;

            try{
                codigoPartida = frmUnirsePartida.getCodigoPartida();
                nombre = frmUnirsePartida.getNombre();
                Client cliente = new Client(new Socket("localhost", Integer.parseInt(codigoPartida)));
                cliente.mandarMensaje(new MensajeUnirse(nombre));
            } catch (IOException ex ) {
                frmUnirsePartida.mostrarMensajeError(ex.getMessage());
            }

        }

    }    
}
