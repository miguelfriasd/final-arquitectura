/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.FrmMain;
import Vista.FrmUnirsePartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    
/**
 *
 * @author mig_2
 */
public class ControladorMain {
    private FrmMain FrmMain;

    public ControladorMain(FrmMain FrmMain) {
        this.FrmMain = FrmMain;
        FrmMain.agregarUnirsePartidaListener(new UnirsePartidaListener());
    }
    
    class UnirsePartidaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            FrmMain.dispose();
            FrmUnirsePartida frmUnirsePartida = new FrmUnirsePartida();
            ControladorUnirsePartida controladorUnirsePartida = new ControladorUnirsePartida(frmUnirsePartida);
            frmUnirsePartida.setVisible(true);
        }

    }
    
}
