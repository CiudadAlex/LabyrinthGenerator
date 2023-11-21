package org.leviatanplatform.labyrinth;

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

        // FIXME generate
        // FIXME text with all the path (list of Directions)

        Labyrinth labyrinth = new Labyrinth(MAP);

        LabyrinthGraphicRepresentation labyrinthGraphicRepresentation = new LabyrinthGraphicRepresentation();
        labyrinthGraphicRepresentation.show(labyrinth);
    }



}
