package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.puzzle.PuzzleLabyrinth;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.optimizer.LabyrinthOptimizer;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;
import org.leviatanplatform.labyrinth.viewer.LabyrinthGraphicRepresentation;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        //LabyrinthGenerator labyrinthGenerator = new TShapedLabyrinthGenerator();
        LabyrinthGenerator labyrinthGenerator = new PuzzleLabyrinth();
        Integer maxNumberOfOptimizations = 10;
        generate(labyrinthGenerator, maxNumberOfOptimizations);
    }

    private static void generate(LabyrinthGenerator labyrinthGenerator, Integer maxNumberOfOptimizations) throws IOException, InterruptedException {

        Labyrinth labyrinth = labyrinthGenerator.generate(70, 150);
        labyrinth = LabyrinthOptimizer.optimize(labyrinth, maxNumberOfOptimizations);

        labyrinth = LabyrinthSolver.solve(labyrinth);

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);

        Thread.sleep(1000);

        labyrinthGraphicRepresentation.saveScreenShot();
    }


}
