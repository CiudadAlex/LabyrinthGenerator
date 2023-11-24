package org.leviatanplatform.labyrinth.solver;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Position;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.util.NodePathFind;
import org.leviatanplatform.labyrinth.util.PositionSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabyrinthSolver {

    public static Labyrinth solve(Labyrinth labyrinthOriginal) {

        Labyrinth labyrinth = labyrinthOriginal.makeAClone();

        NodePathFind nodePathFind = findFastestWayNodePathFromStartToTarget(labyrinth);

        if (nodePathFind == null) {
            return labyrinth;
        }

        NodePathFind nodeIndex = nodePathFind.getPrevious();

        while(nodeIndex != null) {
            labyrinth.setSquareOnlyIfBlank(nodeIndex.getRowDest(), nodeIndex.getColDest(), Square.PATH);
            nodeIndex = nodeIndex.getPrevious();
        }

        return labyrinth;
    }

    public static List<Position> getSolutionPositions(Labyrinth labyrinth) {

        List<Position> listPosition = new ArrayList<>();

        NodePathFind nodePathFind = findFastestWayNodePathFromStartToTarget(labyrinth);

        if (nodePathFind == null) {
            return null;
        }

        NodePathFind nodeIndex = nodePathFind.getPrevious();

        while(nodeIndex != null) {
            Position position = new Position(nodeIndex.getRowDest(), nodeIndex.getColDest());
            listPosition.add(position);
            nodeIndex = nodeIndex.getPrevious();
        }

        return listPosition;
    }

    public static NodePathFind findFastestWayNodePathFromStartToTarget(Labyrinth labyrinth) {

        final int numRows = labyrinth.getNumRows();
        final int numCols = labyrinth.getNumCols();

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {

                Square square = labyrinth.getSquare(r, c);

                if (Square.START.equals(square)) {
                    return findFastestWayNodePathToTarget(labyrinth, r, c);
                }
            }
        }

        throw new RuntimeException("There is no square START in the labyrinth");
    }

    public static Direction findFastestWayDirectionToTarget(Labyrinth labyrinth, int row, int col) {
        NodePathFind nodePathFind = findFastestWayNodePathToTarget(labyrinth, row, col);
        return nodePathFind != null ? getRootDirection(nodePathFind) : null;
    }

    private static NodePathFind findFastestWayNodePathToTarget(Labyrinth labyrinth, int row, int col) {

        PositionSet positionSet = new PositionSet();
        List<NodePathFind> listNodeIter = getNextSteps(labyrinth, row, col, null, positionSet);

        while (!listNodeIter.isEmpty()) {

            List<NodePathFind> listAllNextSteps = new ArrayList<>();

            for (NodePathFind nodePathFind : listNodeIter) {

                int rowIter = nodePathFind.getRowDest();
                int colIter = nodePathFind.getColDest();

                if (labyrinth.getSquare(rowIter, colIter) == Square.TARGET) {
                    return nodePathFind;
                }

                List<NodePathFind> listNextSteps = getNextSteps(labyrinth, rowIter, colIter, nodePathFind, positionSet);
                listAllNextSteps.addAll(listNextSteps);
            }

            listNodeIter = listAllNextSteps;
        }

        return null;
    }

    private static List<NodePathFind> getNextSteps(Labyrinth labyrinth, int row, int col, NodePathFind nodeCurrent, PositionSet positionSet) {

        List<NodePathFind> listNodeNext = new ArrayList<>();

        for (Direction direction : Direction.values()) {

            boolean isDirectionPossible = isDirectionPossible(direction, labyrinth, row, col);

            int newR = row+direction.getDeltaR();
            int newC = col+direction.getDeltaC();

            if (isDirectionPossible && !positionSet.contains(newR, newC)) {

                positionSet.add(newR, newC);

                NodePathFind nodeNext = new NodePathFind(direction, nodeCurrent, newR, newC);
                listNodeNext.add(nodeNext);
            }
        }

        return listNodeNext;
    }

    private static Direction getRootDirection(NodePathFind nodePathFind) {
        return getSequenceOfDirections(nodePathFind).get(0);
    }

    private static List<Direction> getSequenceOfDirections(NodePathFind nodePathFind) {

        NodePathFind nodeIndex = nodePathFind;

        List<Direction> listDirections = new ArrayList<>();

        while(nodeIndex != null) {
            listDirections.add(nodeIndex.getDirection());
            nodeIndex = nodeIndex.getPrevious();
        }

        Collections.reverse(listDirections);

        return listDirections;
    }

    private static boolean isDirectionPossible(Direction direction, Labyrinth labyrinth, int row, int col) {

        int numRows = labyrinth.getNumRows();
        int numCols = labyrinth.getNumCols();

        int newR = row + direction.getDeltaR();
        int newC = col + direction.getDeltaC();

        if (newR < 0 || newC < 0 || newR >= numRows || newC >= numCols) {
            return false;
        }

        return labyrinth.getSquare(newR, newC) != Square.WALL;
    }
}
