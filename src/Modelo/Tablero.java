/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mig_2
 */
public class Tablero {
    private final int numFilas;
    private final int numCols;
    private final Cuadro[][] tablero;
    private boolean turnoPlayer1;
    private final int numCuadrosMax;
    private int numCuadrosActual;
    private int player1Score;
    private int player2Score;

    public Tablero(int numFilas, int numCols) {
        this.numFilas = numFilas;
        this.numCols = numCols;
        tablero = new Cuadro[numFilas][numCols];
        inicializarTablero();
        turnoPlayer1 = true;
        player1Score = 0;
        player2Score = 0;
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
        System.out.println();
    }

    public boolean colocarLinea(int fila, int col, String linea) {
        boolean movimientoValido = false;
        if (fila >=0 && fila < numFilas && col >= 0 && col < numFilas) {
            switch (linea) {
                case "arriba":
                    if (!tablero[fila][col].arriba) {
                        tablero[fila][col].arriba = true;
                        if (tablero[fila][col].completo()) {
                            darCuadro(fila, col);
                        }
                        if (fila >0) {
                            tablero[fila-1][col].abajo = true;
                            if (tablero[fila-1][col].completo()) {
                                darCuadro(fila-1, col);
                            }
                        } else {
                            turnoPlayer1 = !turnoPlayer1;
                        }
                        movimientoValido = true;
                    }   break;
                case "abajo":
                    if (!tablero[fila][col].abajo) {
                        tablero[fila][col].abajo = true;
                        if (tablero[fila][col].completo()) {
                            darCuadro(fila, col);
                        }
                        if ( fila < numFilas - 2) {
                            tablero[fila+1][col].arriba = true;
                            if (tablero[fila+1][col].completo()) {
                                darCuadro(fila+1, col);
                            }
                        } else {
                            turnoPlayer1 = !turnoPlayer1;
                        }
                        movimientoValido = true;
                    }   break;
                case "izquierda":
                    if (!tablero[fila][col].izquierda) {
                        tablero[fila][col].izquierda = true;
                        if (tablero[fila][col].completo()) {
                            darCuadro(fila, col);
                        }
                        if (col > 0) {
                            tablero[fila][col-1].derecha = true;
                            if (tablero[fila][col-1].completo()) {
                                darCuadro(fila, col-1);
                            }
                        } else {
                            turnoPlayer1 = !turnoPlayer1;
                        }
                        movimientoValido = true;
                    }   break;
                case "derecha":
                    if (!tablero[fila][col].derecha) {
                        tablero[fila][col].derecha = true;
                        if (tablero[fila][col].completo()) {
                            darCuadro(fila, col);
                        }
                        if (col < numCols - 2) {
                            tablero[fila][col+1].izquierda = true;
                            if (tablero[fila][col+1].completo()) {
                                darCuadro(fila, col+1);
                            }
                        } else {
                            turnoPlayer1 = !turnoPlayer1;
                        }
                        movimientoValido = true;
                    }   break;
                default:
                    break;
            }
        }
        return movimientoValido;
    }
    
    private void darCuadro(int fila, int col) {
        numCuadrosActual++;
        if (turnoPlayer1) {
            tablero[fila][col].dueño = 1;
            player1Score++;
        } else {
            tablero[fila][col].dueño = 2;
            player2Score++;
        }
    }

    public boolean isGameOver() {
        return numCuadrosMax == numCuadrosActual;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public boolean isPlayer1Turn() {
        return turnoPlayer1;
    }    
}
