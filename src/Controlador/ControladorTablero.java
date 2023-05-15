/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Cliente.Client;
import Mensaje.MensajeMovimiento;
import Vista.FrmTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mig_2
 */
public class ControladorTablero {
    Client cliente;
    FrmTablero frmTablero;;

    public ControladorTablero(Client cliente, FrmTablero frmTablero) {
        this.cliente = cliente;
        this.frmTablero = frmTablero;
        
        this.frmTablero.agregarRealizarMovimientoListener(new RealizarMovimientoListener());
    }
    
    class RealizarMovimientoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Thread hilo = new Thread(new Runnable() {

                @Override
                public void run() {
                   String x = frmTablero.getCoordenadaX();
                   String y = frmTablero.getCoordenadaY();
                   String posicion = frmTablero.getPosicion();
                   MensajeMovimiento mensajeMovimiento = new MensajeMovimiento(x, y, posicion);
                   cliente.mandarMensaje(mensajeMovimiento);
                }
            });         
            hilo.start();
        }
    }
}
