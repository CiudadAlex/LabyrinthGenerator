package org.leviatanplatform.labyrinth.solver;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.util.NodePathFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LabyrinthSolver {

    public static boolean isDirectionPossible(Direction direction, char[][] map, int row, int col, Character chBarrierExtra){

        int newR = row + direction.getDeltaR();
        int newC = col + direction.getDeltaC();

        boolean result = map[newR][newC] != '#' && map[newR][newC] != '?';

        if (chBarrierExtra != null){
            result = result && map[newR][newC] != chBarrierExtra;
        }

        return result;
    }

    public static Direction findFastestWayToX(char[][] map, int row, int col, char X) {

        Set<String> setKeyPos = new HashSet<>();
        List<NodePathFind> listNodePathFindInit = new ArrayList<>();
        addSearchStep(map, row, col, null, listNodePathFindInit, setKeyPos);

        List<NodePathFind> listNodeIter = listNodePathFindInit;

        while (true) {

            List<NodePathFind> listNodeNext = new ArrayList<>();

            for (NodePathFind nodePathFind : listNodeIter) {

                int rowIter = nodePathFind.getRowDest();
                int colIter = nodePathFind.getColDest();

                if (map[rowIter][colIter] == X){
                    return getRootDirection(nodePathFind);
                }

                addSearchStep(map, rowIter, colIter, nodePathFind, listNodeNext, setKeyPos);
            }

            listNodeIter = listNodeNext;
        }
    }

    private static void addSearchStep(char[][] map, int row, int col, NodePathFind nodeCurrent, List<NodePathFind> listNode, Set<String> setKeyPos){

        for (Direction direction : Direction.values()) {

            //System.err.println("(" + row + "," + col + "," + direction.name() + ") >> isDirectionPossible?");

            boolean isDirectionPossible = isDirectionPossible(direction, map, row, col, null);

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
}
