package org.leviatanplatform.labyrinth.solver;

import org.junit.jupiter.api.Test;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.leviatanplatform.labyrinth.model.Square.TARGET;
import static org.leviatanplatform.labyrinth.model.Square.WALL;

class LabyrinthSolverTest {

    private Square[][] MAP = new Square[][] {
            { WALL, WALL, WALL, WALL,   WALL, WALL },
            { null, null, null, null,   null, WALL },
            { WALL, WALL, WALL, WALL,   null, WALL },
            { WALL, WALL, WALL, WALL,   null, WALL },
            { WALL, WALL, WALL, WALL, TARGET, WALL },
            { WALL, WALL, WALL, WALL,   WALL, WALL }
    };

    @Test
    void testFindFastestWayToX() {

        Labyrinth labyrinth = new Labyrinth(MAP);

        Direction direction = LabyrinthSolver.findFastestWayDirectionToTarget(labyrinth, 1, 1);
        assertEquals(Direction.RIGHT, direction);

        direction = LabyrinthSolver.findFastestWayDirectionToTarget(labyrinth, 1, 4);
        assertEquals(Direction.DOWN, direction);
    }
}