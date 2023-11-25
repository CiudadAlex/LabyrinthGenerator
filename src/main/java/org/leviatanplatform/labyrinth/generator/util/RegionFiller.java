package org.leviatanplatform.labyrinth.generator.util;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Position;
import org.leviatanplatform.labyrinth.model.Square;

import java.util.ArrayList;
import java.util.List;

public class RegionFiller {

    public static Labyrinth fillReachableFromPoint(Labyrinth labyrinthOriginal, int row, int col) {

        Labyrinth labyrinth = labyrinthOriginal.makeAClone();

        List<Position> listPosition = new ArrayList<>();
        listPosition.add(new Position(row, col));

        List<Position> listPositionNext = new ArrayList<>();

        while (!listPosition.isEmpty()) {

            for (Position position : listPosition) {
                List<Position> listPositionIter = fillReachableFromPointAndReturnTheirPositions(labyrinth, position);
                listPositionNext.addAll(listPositionIter);
            }

            listPosition = listPositionNext;
            listPositionNext = new ArrayList<>();
        }

        return labyrinth;
    }

    private static List<Position> fillReachableFromPointAndReturnTheirPositions(Labyrinth labyrinth, Position position) {

        List<Position> listPosition = new ArrayList<>();

        for (Direction direction : Direction.values()) {

            int row = position.getRow() + direction.getDeltaR();
            int col = position.getCol() + direction.getDeltaC();
            Square square = labyrinth.getSquare(row, col);

            if (square == null) {
                labyrinth.setSquareOnlyIfBlank(row, col, Square.PATH);
                listPosition.add(new Position(row, col));
            }
        }

        return listPosition;
    }
}
