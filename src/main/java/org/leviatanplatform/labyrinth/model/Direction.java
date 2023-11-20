package org.leviatanplatform.labyrinth.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    UP(-1,0, 1),

    DOWN(1,0, 3),

    LEFT(0,-1, 2),

    RIGHT(0,1, 0);

    private final int deltaR;
    private final int deltaC;
    private final int seq;

    private Direction(int deltaR, int deltaC, int seq) {
        this.deltaR = deltaR;
        this.deltaC = deltaC;
        this.seq = seq;
    }

    public int getDeltaR() {
        return deltaR;
    }

    public int getDeltaC() {
        return deltaC;
    }

    public Direction getReverse() {

        if (this.equals(UP)) {
            return DOWN;
        } else if(this.equals(DOWN)) {
            return UP;
        } else if(this.equals(LEFT)) {
            return RIGHT;
        } else if(this.equals(RIGHT)) {
            return LEFT;
        }

        return null;
    }

    public List<Direction> getSequence() {

        int numDir = values().length;
        int seqInit = seq + numDir -1;

        List<Direction> listDir = new ArrayList<>();

        for(int i=0; i<numDir; i++){
            int desiredSeq = (seqInit + i) % numDir;
            listDir.add(getBySeq(desiredSeq));
        }

        return listDir;
    }

    private static Direction getBySeq(int seq) {

        for (Direction direction : values()) {

            if(direction.seq == seq) {
                return direction;
            }
        }

        return null;
    }

    public static List<Direction> getDefaultSequence() {
        return Arrays.asList(RIGHT, UP, LEFT, DOWN);
    }
}
