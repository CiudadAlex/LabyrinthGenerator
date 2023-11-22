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

        // FIXME generate
        return labyrinth;
    }

    private void extendPaths(Labyrinth labyrinth, int row, int col, Direction direction) {

        int corridorLength = 4;

        if (WallUtils.isThereWallInDirection(labyrinth, row, col, direction, corridorLength)) {
            return;
        }

        List<Direction> perpendicular = direction.getPerpendicular();
        Direction perpendicular1 = perpendicular.get(0);
        Direction perpendicular2 = perpendicular.get(1);
        WallUtils.makeWallInDirection(labyrinth, row + perpendicular1.getDeltaR(), col + perpendicular1.getDeltaC(), direction, corridorLength - 1);
        WallUtils.makeWallInDirection(labyrinth, row + perpendicular2.getDeltaR(), col + perpendicular2.getDeltaC(), direction, corridorLength - 1);
    }

}
