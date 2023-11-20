package org.leviatanplatform.labyrinth;

import org.leviatanplatform.labyrinth.model.Direction;
import org.leviatanplatform.labyrinth.solver.LabyrinthSolver;
import org.leviatanplatform.labyrinth.solver.SearchStatus;
import org.leviatanplatform.labyrinth.util.MapObjectToInt;
import org.leviatanplatform.labyrinth.util.NodePathFind;

import java.util.*;

// FIXME Make the matrix with enum
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
                direction = LabyrinthSolver.findFastestWayToX(map, KR, KC, 'C');

            } else if (SearchStatus.FIND_T.equals(searchStatus)){
                direction = LabyrinthSolver.findFastestWayToX(map, KR, KC, 'T');

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

            if(LabyrinthSolver.isDirectionPossible(direction, map, KR, KC, 'C') && !mapKeyPosInSearch.containsKey(positionId)){
                mapKeyPosInSearch.add(positionId);
                return direction;
            }
        }

        Map<String, Direction> mapPosicionDirection = new HashMap<String, Direction>();

        for (Direction direction : listDirection){

            int newR = KR+direction.getDeltaR();
            int newC = KC+direction.getDeltaC();
            String positionId = newR + "|" + newC;

            if(LabyrinthSolver.isDirectionPossible(direction, map, KR, KC, 'C')){
                mapPosicionDirection.put(positionId, direction);
            }
        }

        String posMinArg = mapKeyPosInSearch.getMinArg(mapPosicionDirection.keySet());
        mapKeyPosInSearch.add(posMinArg);

        return mapPosicionDirection.get(posMinArg);
    }

}
