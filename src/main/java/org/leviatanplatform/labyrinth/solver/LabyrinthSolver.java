package org.leviatanplatform.labyrinth.solver;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.model.Labyrinth;
import org.leviatanplatform.labyrinth.model.Square;
import org.leviatanplatform.labyrinth.util.NodePathFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LabyrinthSolver {

    public static Direction findFastestWayToTarget(Labyrinth labyrinth, int row, int col) {

        Set<String> setKeyPos = new HashSet<>();
        List<NodePathFind> listNodePathFindInit = new ArrayList<>();
        addSearchStep(labyrinth, row, col, null, listNodePathFindInit, setKeyPos);

        List<NodePathFind> listNodeIter = listNodePathFindInit;

        while (true) {

            List<NodePathFind> listNodeNext = new ArrayList<>();

            for (NodePathFind nodePathFind : listNodeIter) {

                int rowIter = nodePathFind.getRowDest();
                int colIter = nodePathFind.getColDest();

                if (labyrinth.getSquare(rowIter, colIter) == Square.TARGET) {
                    return getRootDirection(nodePathFind);
                }

                addSearchStep(labyrinth, rowIter, colIter, nodePathFind, listNodeNext, setKeyPos);
            }

            listNodeIter = listNodeNext;
        }
    }

    private static void addSearchStep(Labyrinth labyrinth, int row, int col, NodePathFind nodeCurrent, List<NodePathFind> listNode, Set<String> setKeyPos) {

        for (Direction direction : Direction.values()) {

            //System.err.println("(" + row + "," + col + "," + direction.name() + ") >> isDirectionPossible?");

            boolean isDirectionPossible = isDirectionPossible(direction, labyrinth, row, col);

            int newR = row+direction.getDeltaR();
            int newC = col+direction.getDeltaC();

            //System.err.println("(" + row + "," + col + "," + direction.name() + ") >> isDirectionPossible = " + isDirectionPossible);

            if(isDirectionPossible && !setKeyPos.contains(newR+"|"+newC)){

                setKeyPos.add(newR+"|"+newC);

                NodePathFind nodeNext = new NodePathFind(direction, nodeCurrent, newR, newC);
                listNode.add(nodeNext);

                if (nodeCurrent!=null) {
                    nodeCurrent.getListNext().add(nodeNext);
                }
            }
        }
    }

    private static Direction getRootDirection(NodePathFind nodePathFind){

        NodePathFind nodeBefore = nodePathFind;
        NodePathFind nodeIndex = nodePathFind;

        while(nodeIndex != null){

            nodeBefore = nodeIndex;
            nodeIndex = nodeIndex.getPrevious();
        }

        return nodeBefore.getDirection();
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
