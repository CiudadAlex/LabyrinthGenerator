package org.leviatanplatform.labyrinth.generator;

import org.leviatanplatform.labyrinth.generator.util.WallUtils;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.model.Tuple;

import java.util.List;
import java.util.Random;

public class TShapedLabyrinthGenerator implements LabyrinthGenerator {

    @Override
    public Labyrinth generate(int numRows, int numCols) {

        Random random = new Random();

        Labyrinth labyrinth = new Labyrinth(numRows, numCols);
        labyrinth.setSquareOnlyIfBlank(1, 0, Square.START);
        labyrinth.setSquareOnlyIfBlank(numRows - 2, numCols - 1, Square.TARGET);

        WallUtils.makeOutsideWall(labyrinth);
        extendPaths(random, labyrinth, 1, 0, Direction.RIGHT);

        return labyrinth;
    }

    private void extendPaths(Random random, Labyrinth labyrinth, int row, int col, Direction direction) {

        int corridorLength = 4;

        if (WallUtils.isThereWallInDirection(labyrinth, row, col, direction, corridorLength)) {
            return;
        }

        Tuple<Direction> tuplePerpendicular = direction.getPerpendicular();
        Direction perpendicular1 = tuplePerpendicular.getObj1();
        Direction perpendicular2 = tuplePerpendicular.getObj2();
        makeCorridorWall(labyrinth, row, col, direction, perpendicular1, corridorLength);
        makeCorridorWall(labyrinth, row, col, direction, perpendicular2, corridorLength);
        labyrinth.setWallOnlyIfBlank(row + direction.getDeltaR() * (corridorLength + 1), col + direction.getDeltaC() * (corridorLength + 1));

        int newRow = row + corridorLength * direction.getDeltaR();
        int newCol = col + corridorLength * direction.getDeltaC();

        List<Direction> listPerpendicular = random.nextBoolean() ? List.of(perpendicular1, perpendicular2) : List.of(perpendicular2, perpendicular1);

        for (Direction perpendicular : listPerpendicular) {
            extendPaths(random, labyrinth, newRow, newCol, perpendicular);
        }
    }

    private void makeCorridorWall(Labyrinth labyrinth, int row, int col, Direction direction, Direction perpendicular, int corridorLength) {
        int rowStart = row + perpendicular.getDeltaR() + direction.getDeltaR();
        int colStart = col + perpendicular.getDeltaC() + direction.getDeltaC();
        WallUtils.makeWallInDirection(labyrinth, rowStart, colStart, direction, corridorLength - 1);
    }

}
