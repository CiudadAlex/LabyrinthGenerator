package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.generator.LabyrinthGenerator;
import org.leviatanplatform.labyrinth.generator.TShapedLabyrinthGenerator;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.viewer.LabyrinthGraphicRepresentation;

import static org.leviatanplatform.labyrinth.model.Square.TARGET;
import static org.leviatanplatform.labyrinth.model.Square.WALL;

public class Main {

    private static Square[][] MAP = new Square[][] {
            { WALL, WALL, WALL, WALL,   WALL, WALL },
            { null, null, null, null,   null, WALL },
            { WALL, WALL, WALL, WALL,   null, WALL },
            { WALL, WALL, WALL, WALL,   null, WALL },
            { WALL, WALL, WALL, WALL, TARGET, WALL },
            { WALL, WALL, WALL, WALL,   WALL, WALL }
    };

    public static void main(String[] args) {

        // FIXME solve

        LabyrinthGenerator labyrinthGenerator = new TShapedLabyrinthGenerator();
        Labyrinth labyrinth = labyrinthGenerator.generate(70, 150);

        //Labyrinth labyrinth = new Labyrinth(MAP);

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);
    }



}
