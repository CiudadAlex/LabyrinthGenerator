package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.TShapedLabyrinthGenerator;
import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;
import org.leviatanplatform.labyrinth.util.NodePathFind;
import org.leviatanplatform.labyrinth.viewer.LabyrinthGraphicRepresentation;

public class Main {

    public static void main(String[] args) {

        LabyrinthGenerator labyrinthGenerator = new TShapedLabyrinthGenerator();
        Labyrinth labyrinth = labyrinthGenerator.generate(70, 150);

        // FIXME refactor solve
        NodePathFind nodePathFind = LabyrinthSolver.findFastestWayNodePathFromStartToTarget(labyrinth);

        NodePathFind nodeIndex = nodePathFind.getPrevious();

        while(nodeIndex != null) {
            labyrinth.setSquare(nodeIndex.getRowDest(), nodeIndex.getColDest(), Square.PATH);
            nodeIndex = nodeIndex.getPrevious();
        }

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);

    }



}
