package org.leviatanplatform.labyrinth.generator.puzzle;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.util.WallUtils;
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
}
