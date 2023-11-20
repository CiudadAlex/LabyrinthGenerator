package org.leviatanplatform.labyrinth.util;

import org.leviatanplatform.labyrinth.model.Direction;

import java.util.ArrayList;
import java.util.List;

public class NodePathFind {

    private final Direction direction;
    private final List<NodePathFind> listNext = new ArrayList<>();
    private final NodePathFind previous;
    private final int rowDest;
    private final int colDest;

    public NodePathFind(Direction direction, NodePathFind previous, int rowDest, int colDest) {
        this.direction = direction;
        this.previous = previous;
        this.rowDest = rowDest;
        this.colDest = colDest;
    }

    public String getKeyPos() {
        return rowDest + "|" + colDest;
    }

    public NodePathFind getPrevious() {
        return this.previous;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public List<NodePathFind> getListNext() {
        return this.listNext;
    }

    public int getRowDest() {
        return this.rowDest;
    }

    public int getColDest() {
        return this.colDest;
    }

}