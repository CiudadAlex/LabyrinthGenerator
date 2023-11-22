package org.leviatanplatform.labyrinth.solver;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.util.NodePathFind;
import org.leviatanplatform.labyrinth.util.PositionSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabyrinthSolver {

    public static Direction findFastestWayFromStartToTarget(Labyrinth labyrinth) {

        final int numRows = labyrinth.getNumRows();
        final int numCols = labyrinth.getNumCols();

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {

                Square square = labyrinth.getSquare(r, c);

                if (Square.START.equals(square)) {
                    return findFastestWayToTarget(labyrinth, r, c);
                }
            }
        }

        throw new RuntimeException("There is no square START in the labyrinth");
    }

    public static Direction findFastestWayToTarget(Labyrinth labyrinth, int row, int col) {

        PositionSet positionSet = new PositionSet();
        List<NodePathFind> listNodePathFindInit = new ArrayList<>();
        addSearchStep(labyrinth, row, col, null, listNodePathFindInit, positionSet);

        List<NodePathFind> listNodeIter = listNodePathFindInit;

        while (true) {

            List<NodePathFind> listNodeNext = new ArrayList<>();

            for (NodePathFind nodePathFind : listNodeIter) {

                int rowIter = nodePathFind.getRowDest();
                int colIter = nodePathFind.getColDest();

                if (labyrinth.getSquare(rowIter, colIter) == Square.TARGET) {
                    return getRootDirection(nodePathFind);
                }

                addSearchStep(labyrinth, rowIter, colIter, nodePathFind, listNodeNext, positionSet);
            }

            listNodeIter = listNodeNext;
        }
    }

    private static void addSearchStep(Labyrinth labyrinth, int row, int col, NodePathFind nodeCurrent, List<NodePathFind> listNode, PositionSet positionSet) {

        for (Direction direction : Direction.values()) {

            //System.err.println("(" + row + "," + col + "," + direction.name() + ") >> isDirectionPossible?");

            boolean isDirectionPossible = isDirectionPossible(direction, labyrinth, row, col);

            int newR = row+direction.getDeltaR();
            int newC = col+direction.getDeltaC();

            //System.err.println("(" + row + "," + col + "," + direction.name() + ") >> isDirectionPossible = " + isDirectionPossible);

            if(isDirectionPossible && !positionSet.contains(newR, newC)){

                positionSet.add(newR, newC);

                NodePathFind nodeNext = new NodePathFind(direction, nodeCurrent, newR, newC);
                listNode.add(nodeNext);

                if (nodeCurrent!=null) {
                    nodeCurrent.getListNext().add(nodeNext);
                }
            }
        }
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
