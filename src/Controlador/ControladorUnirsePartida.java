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
    private final ContextoPartida contextoPartida;
    

    public ControladorUnirsePartida(FrmUnirsePartida frmUnirsePartida, ContextoPartida contextoPartida) {
        this.frmUnirsePartida = frmUnirsePartida;
        this.contextoPartida = contextoPartida;
        
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
                contextoPartida.initCliente(Integer.parseInt(codigoPartida));
                contextoPartida.enviarMensajeUnirsePartida(nombre);
            } catch (IOException ex ) {
                frmUnirsePartida.mostrarMensajeError(ex.getMessage());
            }

        }

    }    
}
