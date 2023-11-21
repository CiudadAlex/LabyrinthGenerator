package org.leviatanplatform.labyrinth.util;

import java.util.HashSet;
import java.util.Set;

public class PositionSet {

    private static final String SEPARATOR = "|";

    private final Set<String> setKeyPos = new HashSet<>();

    public void add(int row, int col) {
        String key = generateKeyPosition(row, col);
        setKeyPos.add(key);
    }

    public boolean contains(int row, int col) {
        String key = generateKeyPosition(row, col);
        return setKeyPos.contains(key);
    }

    public String generateKeyPosition(int row, int col) {
        return row + SEPARATOR + col;
    }


}
