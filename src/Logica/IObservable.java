/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logica;

import Servidor.IObserver;

/**
 *
 * @author mig_2
 */
public interface IObservable {
    public void registrarObserver(IObserver observer);
    public void eliminarObserver(IObserver observer);
    public void notifyObservers();
}
