package org.leviatanplatform.labyrinth.generator;

import org.leviatanplatform.labyrinth.generator.util.WallUtils;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

import java.util.List;
import java.util.Random;

public class TShapedLabyrinthGenerator implements LabyrinthGenerator {

    @Override
    public Labyrinth generate(int numRows, int numCols) {

        Random random = new Random();
        // FIXME randomize
        //random.nextInt();

        Labyrinth labyrinth = new Labyrinth(numRows, numCols);
        WallUtils.makeOutsideWall(labyrinth);

        labyrinth.setSquare(1, 0, Square.START);
        labyrinth.setSquare(numRows - 2, numCols - 1, Square.TARGET);

        extendPaths(labyrinth, 1, 0, Direction.RIGHT);

        return labyrinth;
    }

    private void extendPaths(Labyrinth labyrinth, int row, int col, Direction direction) {

        int corridorLength = 4;

        if (WallUtils.isThereWallInDirection(labyrinth, row, col, direction, corridorLength)) {
            // FIXME get to the end
            return;
        }

        List<Direction> perpendicular = direction.getPerpendicular();
        Direction perpendicular1 = perpendicular.get(0);
        Direction perpendicular2 = perpendicular.get(1);
        makeCorridorWall(labyrinth, row, col, direction, perpendicular1, corridorLength);
        makeCorridorWall(labyrinth, row, col, direction, perpendicular2, corridorLength);

        int newRow = row + corridorLength * direction.getDeltaR();
        int newCol = col + corridorLength * direction.getDeltaC();
        extendPaths(labyrinth, newRow, newCol, perpendicular1);
        extendPaths(labyrinth, newRow, newCol, perpendicular2);
    }

    private void makeCorridorWall(Labyrinth labyrinth, int row, int col, Direction direction, Direction perpendicular, int corridorLength) {
        WallUtils.makeWallInDirection(labyrinth, row + perpendicular.getDeltaR(), col + perpendicular.getDeltaC(), direction, corridorLength - 1);
    }

}
