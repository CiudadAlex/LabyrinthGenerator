package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.tshape.TShapedLabyrinthGenerator;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;
import org.leviatanplatform.labyrinth.viewer.LabyrinthGraphicRepresentation;

public class Main {

    public static void main(String[] args) {

        LabyrinthGenerator labyrinthGenerator = new TShapedLabyrinthGenerator();
        Labyrinth labyrinth = labyrinthGenerator.generate(70, 150);

        labyrinth = LabyrinthSolver.solve(labyrinth);

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);
    }



}
