package org.leviatanplatform.labyrinth.generator;

import org.leviatanplatform.labyrinth.generator.util.WallBuilder;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

public class TShapedLabyrinthGenerator implements LabyrinthGenerator {

    @Override
    public Labyrinth generate(int numRows, int numCols) {

        Labyrinth labyrinth = new Labyrinth(numRows, numCols);
        WallBuilder.makeOutsideWall(labyrinth);

        labyrinth.setSquare(1, 0, Square.START);
        labyrinth.setSquare(numRows - 2, numCols - 1, Square.TARGET);

        // FIXME generate
        return labyrinth;
    }


}
