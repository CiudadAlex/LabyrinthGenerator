package org.leviatanplatform.labyrinth.model;

public class Labyrinth {

    private final int numRows;
    private final int numCols;

    private final Square[][] map;

    public Labyrinth(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.map = new Square[numRows][numCols];
    }
}
