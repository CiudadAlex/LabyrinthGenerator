package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.solver.SearchStatus;
import org.leviatanplatform.labyrinth.util.MapObjectToInt;
import org.leviatanplatform.labyrinth.util.NodePathFind;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Main {

    private static SearchStatus searchStatus = SearchStatus.SEARCH;
    private static MapObjectToInt<String> mapKeyPosInSearch = new MapObjectToInt<>();

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.

        int searchIterations = 500;
        int iterations = 0;
        Direction lastDirection = null;

        // game loop
        while (true) {
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.

            char[][] map = buildMap(in, R, C);


            if (iterations == searchIterations){
                searchStatus = SearchStatus.FIND_C;
            }

            if (map[KR][KC]=='C'){
                searchStatus = SearchStatus.FIND_T;
            }

            System.err.println(searchStatus);

            Direction direction;

            if (SearchStatus.FIND_C.equals(searchStatus)) {
                direction = findFastestWayToX(map, KR, KC, 'C');

            } else if (SearchStatus.FIND_T.equals(searchStatus)){
                direction = findFastestWayToX(map, KR, KC, 'T');

            } else {
                direction = getDirectionSearch(map, KR, KC, lastDirection);
            }


            System.out.println(direction.name()); // Kirk's next move (UP DOWN LEFT or RIGHT).

            lastDirection = direction;
            iterations++;
        }
    }

    private static char[][] buildMap(Scanner in, int R, int C){

        char[][] map = new char[R][C];

        for (int r = 0; r < R; r++) {
            String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
            System.err.println(ROW);

            for (int c = 0; c < C; c++) {
                map[r][c] = ROW.charAt(c);
            }
        }

        return map;
    }

    private static Direction getDirectionSearch(char[][] map, int KR, int KC, Direction lastDirection){

        List<Direction> listDirectionDefault = Direction.getDefaultSequence();

        List<Direction> listDirection = lastDirection!=null ? lastDirection.getSequence() : listDirectionDefault;

        //System.err.println(listDirection);

        for (Direction direction : listDirection){

            int newR = KR+direction.getDeltaR();
            int newC = KC+direction.getDeltaC();
            String positionId = newR + "|" + newC;

            if(isDirectionPossible(direction, map, KR, KC, 'C') && !mapKeyPosInSearch.containsKey(positionId)){
                mapKeyPosInSearch.add(positionId);
                return direction;
            }
        }

        Map<String, Direction> mapPosicionDirection = new HashMap<String, Direction>();

        for (Direction direction : listDirection){

            int newR = KR+direction.getDeltaR();
            int newC = KC+direction.getDeltaC();
            String positionId = newR + "|" + newC;

            if(isDirectionPossible(direction, map, KR, KC, 'C')){
                mapPosicionDirection.put(positionId, direction);
            }
        }

        String posMinArg = mapKeyPosInSearch.getMinArg(mapPosicionDirection.keySet());
        mapKeyPosInSearch.add(posMinArg);

        return mapPosicionDirection.get(posMinArg);
    }

    private static boolean isDirectionPossible(Direction direction, char[][] map, int KR, int KC, Character chBarrierExtra){

        int newR = KR+direction.getDeltaR();
        int newC = KC+direction.getDeltaC();

        boolean result = map[newR][newC] != '#' && map[newR][newC] != '?';

        if (chBarrierExtra != null){
            result = result && map[newR][newC] != chBarrierExtra;
        }

        return result;
    }

    private static Direction findFastestWayToX(char[][] map, int KR, int KC, char X){

        Set<String> setKeyPos = new HashSet<>();
        List<NodePathFind> listNodePathFindInit = new ArrayList<>();
        addSearchStep(map, KR, KC, null, listNodePathFindInit, setKeyPos);

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
