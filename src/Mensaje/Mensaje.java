/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mensaje;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mig_2
 */
public class Mensaje implements Serializable{
    private final Map<String,String> valoresMensaje;

    public Mensaje(){
        valoresMensaje = new HashMap<>();
    }
    
    public void agregarPar(String llave, String valor){
        valoresMensaje.put(llave, valor);
    }
    
    public String obtenerValor(String llave){
        return valoresMensaje.get(llave);
    }
    
}
