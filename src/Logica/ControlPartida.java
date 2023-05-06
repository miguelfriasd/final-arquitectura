/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Modelo.Jugador;
import Modelo.Partida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class ControlPartida {
    
    private static Partida partida;
    private static boolean partidaEmpezada;
    private static int numJugadoresActual;
    private static final int NUM_JUGADORES_MAXIMO = 4;
    private static List<Jugador> listaJugadores;
    private static ControlPartida instancia;
    
    
    private ControlPartida() {
        numJugadoresActual = 0;
        partidaEmpezada = false;
        listaJugadores = new ArrayList<>();
    }
    
    public static ControlPartida getInstance(){
        return (instancia == null) ? new ControlPartida() : instancia;
    }
    
    public void realizarMovimiento(String movimiento){
        if (partidaEmpezada) {
            
        }
    }
    
    private boolean movimientoValido(String mensaje){
        return true;
    }
    
    public static boolean partidaLlena(){
        return numJugadoresActual == NUM_JUGADORES_MAXIMO;
    }
    
    public static boolean partidaEmpezada(){
        return partidaEmpezada;
    }
    
    public boolean agregarJugador(Jugador jugador){
        if (numJugadoresActual < NUM_JUGADORES_MAXIMO) {
            listaJugadores.add(jugador);
            numJugadoresActual++;
            return true;
        }
        return false;
    }
    
    public boolean iniciarPartida(){
        if (!partidaEmpezada && (numJugadoresActual > 0)) {
            partida = new Partida(listaJugadores);
            partidaEmpezada = true;
            return true;
        }
        return false;
    }
    
}
