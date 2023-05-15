/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Cuadro;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mig_2
 */
public class DotsAndBoxes extends JPanel {

    private Cuadro[][] boxes;

    public DotsAndBoxes() {
        // Initialize the game board with 4 rows and 4 columns
        boxes = new Cuadro[5][5];
        // Initialize each box with all sides unpainted
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Cuadro cuadro = new Cuadro();
                boxes[i][j] = cuadro;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g, boxes);
    }

    public void paintBoard(Graphics g, Cuadro[][] boxes) {
        int boxSize = 70; // size of each box
        int padding = 0; // padding between boxes
        int dotSize = 10; // size of each dot
        int startX = 170; // starting X position
        int startY = 40; // starting Y position

        // iterate through each box in the array
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                Cuadro box = boxes[i][j];

                // calculate the position of the top left corner of the box
                int x = startX + (boxSize + padding) * j;
                int y = startY + (boxSize + padding) * i;

                // draw the top left dot
                g.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);

                // draw the top right dot
                g.fillOval(x + boxSize - dotSize / 2, y - dotSize / 2, dotSize, dotSize);

                // draw the bottom left dot
                g.fillOval(x - dotSize / 2, y + boxSize - dotSize / 2, dotSize, dotSize);

                // draw the bottom right dot
                g.fillOval(x + boxSize - dotSize / 2, y + boxSize - dotSize / 2, dotSize, dotSize);

                // if the top side is painted, draw a line across the top of the box
                if (box.arriba) {
                    g.drawLine(x, y, x + boxSize, y);
                }

                // if the right side is painted, draw a line down the right side of the box
                if (box.derecha) {
                    g.drawLine(x + boxSize, y, x + boxSize, y + boxSize);
                }

                // if the bottom side is painted, draw a line across the bottom of the box
                if (box.abajo) {
                    g.drawLine(x, y + boxSize, x + boxSize, y + boxSize);
                }

                // if the left side is painted, draw a line down the left side of the box
                if (box.izquierda) {
                    g.drawLine(x, y, x, y + boxSize);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 472);
        DotsAndBoxes panel = new DotsAndBoxes();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
