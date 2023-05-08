/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCliente;

import Controlador.ControladorMain;
import Vista.FrmMain;

/**
 *
 * @author mig_2
 */
public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmMain mainFrame = new FrmMain();
        ControladorMain controladorMain = new ControladorMain(mainFrame);
        mainFrame.setVisible(true);
    }
}
