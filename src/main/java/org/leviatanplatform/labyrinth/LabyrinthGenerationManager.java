package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.optimizer.LabyrinthOptimizer;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;
import org.leviatanplatform.labyrinth.viewer.LabyrinthGraphicRepresentation;

import java.io.IOException;

public class LabyrinthGenerationManager {

    public static void generate(LabyrinthGenerator labyrinthGenerator, Integer maxNumberOfOptimizations, int numRows, int numCols) throws IOException, InterruptedException {

        Labyrinth labyrinth = generateLabyrinth(labyrinthGenerator, maxNumberOfOptimizations, numRows, numCols);
        Labyrinth labyrinthSolved = LabyrinthSolver.solve(labyrinth);

        showAndSaveLabyrinth(labyrinth, "labyrinth");
        showAndSaveLabyrinth(labyrinthSolved, "labyrinthSolved");
    }

    private static Labyrinth generateLabyrinth(LabyrinthGenerator labyrinthGenerator, Integer maxNumberOfOptimizations, int numRows, int numCols) {

        Labyrinth labyrinth = labyrinthGenerator.generate(numRows, numCols);
        return LabyrinthOptimizer.optimize(labyrinth, maxNumberOfOptimizations);
    }

    private static void showAndSaveLabyrinth(Labyrinth labyrinth, String name) throws InterruptedException, IOException {

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);

        Thread.sleep(1000);

        labyrinthGraphicRepresentation.saveScreenShot(name);
    }
}
