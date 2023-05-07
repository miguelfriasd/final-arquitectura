/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Tablero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ControlPartida {
    
    private Partida partida;
    private Tablero tablero;
    private boolean partidaEmpezada;
    private int numJugadoresActual;
    private final int NUM_JUGADORES_MAXIMO = 4;
    private List<Jugador> listaJugadores;
    private static ControlPartida instancia;
    
    
    private ControlPartida() {
        numJugadoresActual = 0;
        partidaEmpezada = false;
        listaJugadores = new ArrayList<>();
    }
    
    public static ControlPartida getInstance(){
        return (instancia == null) ? (instancia = new ControlPartida()) : instancia;
    }
    
    public synchronized boolean realizarMovimiento(int coordenadaX, int coordenadaY, String posicion, Jugador jugador){
        if (partidaEmpezada && partida.getJugadorActual() == jugador) {
            return tablero.colocarLinea(coordenadaX, coordenadaY, posicion);
        }
        return false;
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
            return true;
        }
        return false;
    }
    
    public boolean empezarPartida(){
        if (!partidaEmpezada && (numJugadoresActual > 0)) {
            partida = new Partida(listaJugadores);
            tablero = new Tablero(numJugadoresActual*10 , numJugadoresActual*10);
            partidaEmpezada = true;
            return true;
        }
        return false;
    }
    
    public synchronized Jugador obtenerJugadorActual(){
        if (partidaEmpezada) {
            return partida.getJugadorActual();
        }
        throw new IllegalCallerException();
    }
    
    public void eliminarJugador(Jugador jugador){
        partida.getTurnosJugadores().remove(jugador);
        this.listaJugadores.remove(jugador);
    }
    
}
