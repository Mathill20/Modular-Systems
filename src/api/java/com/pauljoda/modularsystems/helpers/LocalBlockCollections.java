package com.pauljoda.modularsystems.helpers;

import com.pauljoda.modularsystems.core.util.Coord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalBlockCollections {

    private static final List<Coord> adjacentBlocks = Collections.unmodifiableList(adjacentBlocks(0, 0, 0));

    private LocalBlockCollections() {}

    public static List<Coord> adjacentBlocks(int x, int y, int z) {
        List<Coord> coords = new ArrayList<Coord>();
        coords.add(new Coord(x + 1, y, z));
        coords.add(new Coord(x - 1, y, z));
        coords.add(new Coord(x, y + 1, z));
        coords.add(new Coord(x, y - 1, z));
        coords.add(new Coord(x, y, z + 1));
        coords.add(new Coord(x, y, z - 1));
        return coords;
    }

    public static List<Coord> getAdjacentBlocks() {
        return adjacentBlocks;
    }
}
