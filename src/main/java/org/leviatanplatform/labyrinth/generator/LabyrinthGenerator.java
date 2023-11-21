package org.leviatanplatform.labyrinth.generator;

import org.leviatanplatform.labyrinth.model.Labyrinth;

public interface LabyrinthGenerator {

    Labyrinth generate(int numRows, int numCols);
}
