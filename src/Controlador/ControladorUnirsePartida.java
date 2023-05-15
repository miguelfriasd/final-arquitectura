/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Cliente.Client;
import ContextoLocalPartida.ContextoLocalPartida;
import Mensaje.MensajeUnirse;
import Vista.FrmTablero;
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
    

    public ControladorUnirsePartida(FrmUnirsePartida frmUnirsePartida) {
        this.frmUnirsePartida = frmUnirsePartida;
        
        frmUnirsePartida.agregarUnirsePartidaListener(new UnirsePartidaListener());
    }
    
    class UnirsePartidaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Thread hilo = new Thread(new Runnable() {

                @Override
                public void run() {
                    String codigoPartida;
                    String nombre;
                    frmUnirsePartida.desactivarBotonUnirse();
                    frmUnirsePartida.desactivarCamposTexto();
                    frmUnirsePartida.setTextEstadoPartida("Intentando unirse a la partida....");

                    try{
                        codigoPartida = frmUnirsePartida.getCodigoPartida();
                        nombre = frmUnirsePartida.getNombre();
                        Client cliente = new Client(new Socket("localhost", Integer.parseInt(codigoPartida)));
                        cliente.listenerMensajes();
                        cliente.mandarMensaje(new MensajeUnirse(nombre));
                        while (cliente.isConnected() && !cliente.getContextoLocalPartida().partidaEmpezada()) {
                            System.out.println("Esperando partida");
                        }
                        if (cliente.getContextoLocalPartida().partidaEmpezada()) {
                            frmUnirsePartida.dispose();
                            FrmTablero frmTablero = new FrmTablero();
                            frmTablero.setVisible(true);
                            ControladorTablero controladorTablero = new ControladorTablero(cliente, frmTablero);
                            controladorTablero.actualizar();
                            cliente.getContextoLocalPartida().registrarObserver(controladorTablero);
                        }
                    } catch (IOException ex ) {
                        frmUnirsePartida.mostrarMensajeError(ex.getMessage());
                    }
                }
            });         
            hilo.start();
        }
    }    
}
