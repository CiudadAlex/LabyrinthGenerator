package org.leviatanplatform.labyrinth.viewer;

import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

import java.awt.*;

public class LabyrinthGraphicRepresentationUtils {

    public static void paintCurrentListOfStars(Graphics g, Labyrinth labyrinth, int width, int height) {

        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        final int numRows = labyrinth.getNumRows();
        final int numCols = labyrinth.getNumCols();
        final int squareSize = 10;
        final int margin = 20;

        for (int r = 0; r < numRows; r++) {

            for (int c = 0; c < numCols; c++) {
                paintSquare(g, labyrinth, r, c, squareSize, margin);
            }
        }
    }

    private static void paintSquare(Graphics g, Labyrinth labyrinth, int row, int col, int squareSize, int margin) {

        Square square = labyrinth.getSquare(row, col);

        if (Square.WALL.equals(square)) {
            g.setColor(Color.black);
        } else if (Square.TARGET.equals(square)) {
            g.setColor(Color.red);
        } else if (Square.START.equals(square)) {
            g.setColor(new Color(255, 128, 0));
        } else if (Square.PATH.equals(square)) {
            g.setColor(Color.GREEN);
        } else if (Square.FILL_START.equals(square)) {
            g.setColor(new Color(255, 255, 0));
        } else if (Square.FILL_TARGET.equals(square)) {
            g.setColor(new Color(0, 255, 255));
        } else {
            g.setColor(Color.white);
        }

        int x = margin + col * squareSize;
        int y = margin + row * squareSize;

        g.fillRect(x, y, squareSize, squareSize);
    }


}