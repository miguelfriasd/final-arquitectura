/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Cliente.Client;
import EstadoPartida.EstadoPartida;
import Logica.ControlPartida;
import Vista.FrmUnirsePartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 *
 * @author mig_2
 */
public class ControladorUnirsePartida {
    
    FrmUnirsePartida frmUnirsePartida;
    

    public ControladorUnirsePartida(FrmUnirsePartida frmUnirsePartida) {
        this.frmUnirsePartida = frmUnirsePartida;
        
        frmUnirsePartida.agregarUnirsePartidaListener(new UnirsePartidaListener());
    }
    
    class UnirsePartidaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String codigoPartida;

            try{
                codigoPartida = frmUnirsePartida.getCodigoPartida();
                EstadoPartida.getInstance().initCliente(Integer.parseInt(codigoPartida));
                
            } catch (IOException ex ) {
                frmUnirsePartida.mostrarMensajeError(ex.getMessage());
            }

        }

    }    
}
