package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.puzzle.PuzzleLabyrinth;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        //LabyrinthGenerator labyrinthGenerator = new TShapedLabyrinthGenerator();
        LabyrinthGenerator labyrinthGenerator = new PuzzleLabyrinth();
        Integer maxNumberOfOptimizations = null;
        LabyrinthGenerationManager.generate(labyrinthGenerator, maxNumberOfOptimizations, 70, 150);
    }

}
