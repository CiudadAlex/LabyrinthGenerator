package org.leviatanplatform.labyrinth.generator.util;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

public class WallUtils {

    public static void makeOutsideWall(Labyrinth labyrinth) {

        int numRows = labyrinth.getNumRows();
        int numCols = labyrinth.getNumCols();

        makeRowWall(labyrinth, 0, 0, numCols - 1);
        makeRowWall(labyrinth, numRows - 1, 0, numCols - 1);

        makeColWall(labyrinth, 0, 0, numRows - 1);
        makeColWall(labyrinth, numCols - 1, 0, numRows - 1);
    }

    public static void makeRowWall(Labyrinth labyrinth, int row, int startCol, int endCol) {

        for (int i = startCol; i <= endCol; i++) {
            labyrinth.setSquare(row, i, Square.WALL);
        }
    }

    public static void makeColWall(Labyrinth labyrinth, int col, int startRow, int endRow) {

        for (int i = startRow; i <= endRow; i++) {
            labyrinth.setSquare(i, col, Square.WALL);
        }
    }

    public static void makeWallInDirection(Labyrinth labyrinth, int row, int col, Direction direction, int length) {

        for (int i = 0; i < length; i++) {
            labyrinth.setSquare(row + i * direction.getDeltaR(), col + i * direction.getDeltaC(), Square.WALL);
        }
    }

    public static boolean isThereWallInDirection(Labyrinth labyrinth, int row, int col, Direction direction, int length) {

        for (int i = 0; i < length; i++) {
            Square square = labyrinth.getSquare(row + i * direction.getDeltaR(), col + i * direction.getDeltaC());
            if (Square.WALL.equals(square)) {
                return true;
            }
        }

        return false;
    }
}
