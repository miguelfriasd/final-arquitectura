/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import EstadoPartida.ContextoPartida;
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
            String nombre;

            try{
                codigoPartida = frmUnirsePartida.getCodigoPartida();
                nombre = frmUnirsePartida.getNombre();
                ContextoPartida.getInstance().initCliente(Integer.parseInt(codigoPartida));
                ContextoPartida.getInstance().enviarMensajeUnirsePartida(nombre);
            } catch (IOException ex ) {
                frmUnirsePartida.mostrarMensajeError(ex.getMessage());
            }

        }

    }    
}
