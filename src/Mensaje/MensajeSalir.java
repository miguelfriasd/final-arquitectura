/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mensaje;

import java.io.Serializable;

/**
 *
 * @author mig_2
 */
public class MensajeSalir implements MensajeStrategy, Serializable{

    
    @Override
    public Mensaje getMensaje() {
        return new Mensaje();
    }
    
}
