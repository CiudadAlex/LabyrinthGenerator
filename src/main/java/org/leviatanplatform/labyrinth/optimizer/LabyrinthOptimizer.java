package org.leviatanplatform.labyrinth.optimizer;

import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Position;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;

import java.util.Collections;
import java.util.List;

public class LabyrinthOptimizer {

    public static Labyrinth optimize(Labyrinth labyrinthOriginal, Integer maxNumberOfOptimizations) {

        int iteration = 0;
        Labyrinth labyrinth = labyrinthOriginal;

        while (iterateMore(iteration, maxNumberOfOptimizations)) {

            Labyrinth labyrinthMod = addWallRemainingSolvableReturnNullIfNotPossible(labyrinth);

            if (labyrinthMod != null) {
                labyrinth = labyrinthMod;
            } else {
                break;
            }
        }

        return labyrinth;
    }

    private static boolean iterateMore(int iteration, Integer maxNumberOfOptimizations) {
        return maxNumberOfOptimizations == null || iteration <= maxNumberOfOptimizations;
    }

    private static Labyrinth addWallRemainingSolvableReturnNullIfNotPossible(Labyrinth labyrinth) {

        Labyrinth labyrinthAux = labyrinth.makeAClone();
        List<Position> listPosition = LabyrinthSolver.getSolutionPositions(labyrinthAux);

        if (listPosition == null) {
            return null;
        }

        Collections.shuffle(listPosition);

        for (Position position : listPosition) {

            if (hasSolutionIfPositionIsWall(labyrinthAux, position)) {
                labyrinthAux.setWallOnlyIfBlank(position);
                return labyrinthAux;
            }
        }

        return null;
    }

    private static boolean hasSolutionIfPositionIsWall(Labyrinth labyrinth, Position position) {

        Labyrinth labyrinthAux = labyrinth.makeAClone();
        labyrinthAux.setWallOnlyIfBlank(position);
        return LabyrinthSolver.hasSolution(labyrinthAux);
    }
}
