/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

import java.util.Scanner;

/**
 *
 * @author mig_2
 */
public class Main {

    /**
     * @param args the command linea arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a timbiriche!");
        System.out.print("Introduzca el numero de filas: ");
        int numFilas = scanner.nextInt();
        System.out.print("Introduzca el numero columnas: ");
        int numCols = scanner.nextInt();

        Tablero juego = new Tablero(numFilas, numCols);

        while (!juego.isGameOver()) {
            juego.imprimirTablero();
            String prompt = juego.isPlayer1Turn() ? "Turno del jugador 1: " : "Turno del jugador 2: ";
            System.out.print(prompt);
            int fila = scanner.nextInt();
            int col = scanner.nextInt();
            String linea = scanner.next();

            if (!juego.colocarLinea(fila,col,linea)) {
                System.out.println("Movimiento invalido!");
            }
        }

        System.out.println(juego);
        int player1Score = juego.getPlayer1Score();
        int player2Score = juego.getPlayer2Score();
        if (player1Score > player2Score) {
            System.out.println("Player 1 gana con un score de:" + player1Score);
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 gana con un score de:" + player2Score);
        } else {
            System.out.println("Es un empate con un score de:" + player1Score);
        }
    }

}
    
