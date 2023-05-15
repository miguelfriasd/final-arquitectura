/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Cliente.Client;
import Mensaje.MensajeMovimiento;
import Modelo.Partida;
import Servidor.IObserver;
import Vista.PnlTablero;
import Vista.FrmTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mig_2
 */
public class ControladorTablero implements IObserver{
    Client cliente;
    FrmTablero frmTablero;;

    public ControladorTablero(Client cliente, FrmTablero frmTablero) {
        this.cliente = cliente;
        this.frmTablero = frmTablero;
        
        this.frmTablero.agregarRealizarMovimientoListener(new RealizarMovimientoListener());
    }

    @Override
    public void actualizar() {
        Partida partida = cliente.getContextoLocalPartida().getEstadoPartida();
        frmTablero.actualizarTablero(new PnlTablero(partida.getTablero()));
        frmTablero.setTurno(partida.getJugadorActual().getNombre());
        System.out.println("Tablero actualizada");
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
