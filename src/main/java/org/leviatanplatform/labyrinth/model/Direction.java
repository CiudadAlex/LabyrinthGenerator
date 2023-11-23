package org.leviatanplatform.labyrinth.model;

public enum Direction {

    UP(-1,0),

    DOWN(1,0),

    LEFT(0,-1),

    RIGHT(0,1);

    private final int deltaR;
    private final int deltaC;

    Direction(int deltaR, int deltaC) {
        this.deltaR = deltaR;
        this.deltaC = deltaC;
    }

    public int getDeltaR() {
        return deltaR;
    }

    public int getDeltaC() {
        return deltaC;
    }

    public Tuple<Direction> getPerpendicular() {

        return switch (this) {
            case UP, DOWN -> new Tuple<>(LEFT, RIGHT);
            case LEFT, RIGHT -> new Tuple<>(UP, DOWN);
        };
    }

}
