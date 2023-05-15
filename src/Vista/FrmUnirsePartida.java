/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mig_2
 */
public class FrmUnirsePartida extends javax.swing.JFrame {
    /**
     * Creates new form FrmUnirsePartida
     */
    public FrmUnirsePartida() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCodigoPartida = new javax.swing.JTextField();
        lblCodigoPartida = new javax.swing.JLabel();
        btnUnirse = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtEstadoPartida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCodigoPartida.setText("Codigo de partida:");

        btnUnirse.setText("Unirse");

        lblNombre.setText("Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre)
                    .addComponent(lblCodigoPartida)
                    .addComponent(txtCodigoPartida)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUnirse)
                        .addGap(78, 78, 78))
                    .addComponent(txtEstadoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(txtEstadoPartida, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCodigoPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUnirse)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
        public String getCodigoPartida(){
            return txtCodigoPartida.getText();
        }
        
        public String getNombre(){
            return txtNombre.getText();
        }
    
    	public void agregarUnirsePartidaListener(ActionListener listenUnirsePartida){
            btnUnirse.addActionListener(listenUnirsePartida);
	}
        
        public void mostrarMensajeError(String mensaje){	
            JOptionPane.showMessageDialog(this, mensaje);
	}
        
        public void setTextEstadoPartida(String texto){
            txtEstadoPartida.setText(texto);
        }
        
        public void desactivarBotonUnirse(){
            btnUnirse.setEnabled(false);   
        }
        public void desactivarCamposTexto(){
            txtNombre.setEnabled(false);
            txtCodigoPartida.setEnabled(false);
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUnirse;
    private javax.swing.JLabel lblCodigoPartida;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtCodigoPartida;
    private javax.swing.JLabel txtEstadoPartida;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
