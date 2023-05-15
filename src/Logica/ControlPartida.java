/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Modelo.Jugador;
import Modelo.Partida;
import Servidor.IObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ControlPartida implements IObservable{
    
    private Partida partida;
    private boolean partidaEmpezada;
    private int numJugadoresActual;
    private final int NUM_JUGADORES_MAXIMO = 2;
    private final List<Jugador> listaJugadores;
    private final List<IObserver> listaObservers;
    
    
    public ControlPartida() {
        numJugadoresActual = 0;
        partidaEmpezada = false;
        listaJugadores = new ArrayList<>();
        listaObservers = new ArrayList<>();
    }
    
    public synchronized void realizarMovimiento(int coordenadaX, int coordenadaY, String posicion, Jugador jugador){
        boolean movimientoValido =  partida.colocarLinea(coordenadaX, coordenadaY, posicion, jugador);
        if (movimientoValido) {
            System.out.println("Movimiento valido de " + jugador.getNombre());
            partida.imprimirTablero();
            notifyObservers();
        }
    }
    
    public synchronized boolean partidaLlena(){
        return numJugadoresActual == NUM_JUGADORES_MAXIMO;
    }
    
    public synchronized boolean partidaEmpezada(){
        return partidaEmpezada;
    }
    
    public synchronized boolean agregarJugador(Jugador jugador){
        if (numJugadoresActual < NUM_JUGADORES_MAXIMO) {
            listaJugadores.add(jugador);
            numJugadoresActual++;
            if (numJugadoresActual == NUM_JUGADORES_MAXIMO) {
                empezarPartida();
            }
            return true;
        }

        return false;
    }
    
    private boolean empezarPartida(){
        if (!partidaEmpezada && (numJugadoresActual > 0)) {
            partida = new Partida(5 , 5, this.listaJugadores);
            partidaEmpezada = true;
            return true;
        }
        return false;
    }   
    
    public Partida getPartida(){
        return partida;
    }
    
    public void eliminarJugador(Jugador jugador){
        this.listaJugadores.remove(jugador);
    }

    @Override
    public void registrarObserver(IObserver observer) {
        listaObservers.add(observer);
    }

    @Override
    public void eliminarObserver(IObserver observer) {
        listaObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : listaObservers) {
            observer.actualizar();
        }
    }
    
}
