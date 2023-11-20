package org.leviatanplatform.labyrinth.solver;

import org.junit.jupiter.api.Test;
import org.leviatanplatform.labyrinth.model.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LabyrinthSolverTest {

    private char[][] MAP = new char[][] {
            { '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', '#', ' ', '#' },
            { '#', '#', '#', '#', ' ', '#' },
            { '#', '#', '#', '#', 'T', '#' },
            { '#', '#', '#', '#', '#', '#' }
    };

    @Test
    void testFindFastestWayToX() {

        Direction direction = LabyrinthSolver.findFastestWayToX(MAP, 1, 1, 'T');
        assertEquals(Direction.RIGHT, direction);

        direction = LabyrinthSolver.findFastestWayToX(MAP, 1, 4, 'T');
        assertEquals(Direction.DOWN, direction);
    }
}