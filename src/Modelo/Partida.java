/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mig_2
 */
public class Partida implements Serializable{
    private final int numFilas;
    private final int numCols;
    private final Cuadro[][] tablero;
    private int jugadorActual;
    private final int numJugadoresActual;
    private final List<Jugador> listaJugadores;
    private final int numCuadrosMax;
    private int numCuadrosActual;


    public Partida(int numFilas, int numCols, List<Jugador> listaJugadores) {
        this.numFilas = numFilas;
        this.numCols = numCols;
        tablero = new Cuadro[numFilas][numCols];
        inicializarTablero();
        this.listaJugadores = listaJugadores;
        numJugadoresActual = listaJugadores.size();
        jugadorActual = 0;
        numCuadrosActual = 0; 
        numCuadrosMax = this.numCols * this.numFilas;
    }

    private void inicializarTablero() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numCols; j++) {
                tablero[i][j] = new Cuadro();
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print("+");
                if (tablero[i][j].arriba) {
                    System.out.print("-");
                } else {
                    System.out.print(" ");
                }
                System.out.print("+");
            }
            System.out.println();
            for (int j = 0; j < numCols; j++) {
                if (tablero[i][j].izquierda) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print(" ");
                if (tablero[i][j].derecha) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < numCols; j++) {
            System.out.print("+");
            if (tablero[numFilas - 1][j].abajo) {
                System.out.print("-");
            } else {
                System.out.print(" ");
            }
            System.out.print("+");
        }
        System.out.println("");
        System.out.println("Turno de:" + listaJugadores.get(jugadorActual).getNombre());
        System.out.println("---------------------------------");
        for (Jugador jugador : listaJugadores) {
            System.out.println("Nombre: " + jugador.getNombre() + "| Score: " + jugador.getScore());
        }
    }

    public boolean colocarLinea(int col, int fila, String linea, Jugador jugador) {
        boolean movimientoValido = false;
        if (fila >=0 && fila < numFilas && col >= 0 && col < numFilas && jugador == getJugadorActual()) {
            switch (linea) {
                case "arriba":
                    if (!tablero[fila][col].arriba) {
                        tablero[fila][col].arriba = true;
                        if (tablero[fila][col].completo()) {
                            agregarPunto();
                        }
                        if (fila >0) {
                            tablero[fila-1][col].abajo = true;
                            if (tablero[fila-1][col].completo()) {
                                agregarPunto();
                            }
                        } 
                        pasarTurno();
                        movimientoValido = true;
                    }   
                    break;
                case "abajo":
                    if (!tablero[fila][col].abajo) {
                        tablero[fila][col].abajo = true;
                        if (tablero[fila][col].completo()) {
                            agregarPunto();
                        }
                        if ( fila < numFilas - 2) {
                            tablero[fila+1][col].arriba = true;
                            if (tablero[fila+1][col].completo()) {
                                agregarPunto();
                            }
                        }
                        pasarTurno();
                        movimientoValido = true;
                    }   
                    break;
                case "izquierda":
                    if (!tablero[fila][col].izquierda) {
                        tablero[fila][col].izquierda = true;
                        if (tablero[fila][col].completo()) {
                            agregarPunto();
                        }
                        if (col > 0) {
                            tablero[fila][col-1].derecha = true;
                            if (tablero[fila][col-1].completo()) {
                                agregarPunto();
                            }
                        }
                        pasarTurno();
                        movimientoValido = true;
                    }   
                    break;
                case "derecha":
                    if (!tablero[fila][col].derecha) {
                        tablero[fila][col].derecha = true;
                        if (tablero[fila][col].completo()) {
                            agregarPunto();
                        }
                        if (col < numCols - 2) {
                            tablero[fila][col+1].izquierda = true;
                            if (tablero[fila][col+1].completo()) {
                                agregarPunto();
                            }
                        }
                        pasarTurno();
                        movimientoValido = true;
                    }   
                    break;
                default:
                    break;
            }
        }
        return movimientoValido;
    }
    
    public Jugador getJugadorActual(){
        return listaJugadores.get(jugadorActual);
    }
    
    public Cuadro[][] getTablero(){
        return tablero;
    }
    
    private void agregarPunto(){
        this.numCuadrosActual++;
        Jugador jugador = getJugadorActual();
        jugador.aumentarScore();
    }
 
    
    private void pasarTurno(){
        if (jugadorActual == numJugadoresActual-1) {
            jugadorActual = 0;
            return;
        }
        jugadorActual++;
    }    

    public boolean isGameOver() {
        return numCuadrosMax == numCuadrosActual;
    }
   
}
