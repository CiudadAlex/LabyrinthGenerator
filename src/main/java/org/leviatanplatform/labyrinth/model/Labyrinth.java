package org.leviatanplatform.labyrinth.model;

public class Labyrinth {

    private final int numRows;
    private final int numCols;

    private final Square[][] map;

    public Labyrinth(Square[][] map) {
        this.numRows = map.length;
        this.numCols = map[0].length;
        this.map = map;
    }

    public Labyrinth(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.map = new Square[numRows][numCols];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Square getSquare(int row, int col) {
        return map[row][col];
    }

    public void setSquare(int row, int col, Square square) {

        if (areIndexesOutOfBounds(row, col)) {
            return;
        }

        map[row][col] = square;
    }

    public void setWallOnlyIfBlank(int row, int col) {

        if (areIndexesOutOfBounds(row, col)) {
            return;
        }

        if (getSquare(row, col) == null) {
            setSquare(row, col, Square.WALL);
        }
    }

    private boolean areIndexesOutOfBounds(int row, int col) {
        return row < 0 || col < 0 || row >= numRows || col >= numCols;
    }
}
