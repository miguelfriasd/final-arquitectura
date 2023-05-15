/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContextoLocalPartida;

import Cliente.Client;
import Logica.IObservable;
import Mensaje.MensajeMovimiento;
import Mensaje.MensajeUnirse;
import Modelo.Partida;
import Servidor.IObserver;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ContextoLocalPartida implements IObservable{
    private List<IObserver> observers;
    private Partida estadoPartida;
    private boolean partidaEmpezada;
    
    public ContextoLocalPartida(){
        partidaEmpezada = false;
        observers = new ArrayList<>();
    }
    
    public void setPartidaEmpezada(){
        partidaEmpezada = true;
    }
    
    public boolean partidaEmpezada(){
        return partidaEmpezada;
    }
    
    public void actualizarEstadoPartida(Partida partida){
        this.estadoPartida = partida;
        notifyObservers();
    }
    
    public Partida getEstadoPartida(){
        return estadoPartida;
    }

    @Override
    public void registrarObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.actualizar();
        }
    }
}
