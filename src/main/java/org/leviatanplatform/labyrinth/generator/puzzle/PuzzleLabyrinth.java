package org.leviatanplatform.labyrinth.generator.puzzle;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.util.WallUtils;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

import java.util.Random;

public class PuzzleLabyrinth implements LabyrinthGenerator {
    @Override
    public Labyrinth generate(int numRowsInitial, int numColsInitial) {

        int numRows = numRowsInitial % 2 == 0 ? numRowsInitial + 1 : numRowsInitial;
        int numCols = numColsInitial % 2 == 0 ? numColsInitial + 1 : numColsInitial;

        Random random = new Random();

        Labyrinth labyrinth = new Labyrinth(numRows, numCols);
        labyrinth.setSquareOnlyIfBlank(1, 0, Square.START);
        labyrinth.setSquareOnlyIfBlank(numRows - 2, numCols - 1, Square.TARGET);

        WallUtils.makeOutsideWall(labyrinth);

        // FIXME finish

        return labyrinth;
    }

    private void paintPiece(Labyrinth labyrinth, Piece piece, int row, int col) {

        labyrinth.setWallOnlyIfBlank(row + 1, col + 1);
        labyrinth.setWallOnlyIfBlank(row - 1, col - 1);
        labyrinth.setWallOnlyIfBlank(row + 1, col - 1);
        labyrinth.setWallOnlyIfBlank(row - 1, col + 1);

        if (!piece.isUp()) {
            Direction dir = Direction.UP;
            labyrinth.setWallOnlyIfBlank(row + dir.getDeltaR(), col + dir.getDeltaC());
        }

        if (!piece.isDown()) {
            Direction dir = Direction.DOWN;
            labyrinth.setWallOnlyIfBlank(row + dir.getDeltaR(), col + dir.getDeltaC());
        }

        if (!piece.isLeft()) {
            Direction dir = Direction.LEFT;
            labyrinth.setWallOnlyIfBlank(row + dir.getDeltaR(), col + dir.getDeltaC());
        }

        if (!piece.isRight()) {
            Direction dir = Direction.RIGHT;
            labyrinth.setWallOnlyIfBlank(row + dir.getDeltaR(), col + dir.getDeltaC());
        }
    }
}
