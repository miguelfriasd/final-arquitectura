/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author mig_2
 */
public class Partida{
    int numJugadores;
    int jugadorActual;
    Tablero tablero;
    List<Jugador> listaJugadores;

    public Partida(List<Jugador> listaJugadores) {
        this.numJugadores = listaJugadores.size();
        this.tablero = new Tablero(0, 0);
        this.listaJugadores = listaJugadores;
        this.jugadorActual = 0;
    }
    
    public void pasarTurno(){
        if (jugadorActual == numJugadores) {
            jugadorActual = 0;
            return;
        }
        jugadorActual++;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
    
}
