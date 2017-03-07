package com.darwindev.mazegame;

import com.darwindev.mazegame.elements.*;

/**
 * Created by Zheng on 01/03/2017.
 *
 */
class MazeGame {
    public Maze makeMaze() {
        return new Maze();
    }

    public Room makeRoom(int n) {
        return new Room(n);
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }

    public Maze CreateMaze() {
        Maze aMaze = makeMaze();
        Room r1 = makeRoom(1);
        Room r2 = makeRoom(2);
        Door theDoor = makeDoor(r1, r2);
        aMaze.addRoom(r1);
        aMaze.addRoom(r2);
        r1.setSide(Side.North, makeWall());
        r1.setSide(Side.East, theDoor);
        r1.setSide(Side.South, makeWall());
        r1.setSide(Side.West, makeWall());
        r2.setSide(Side.North, makeWall());
        r2.setSide(Side.East, makeWall());
        r2.setSide(Side.South, makeWall());
        r2.setSide(Side.West, theDoor);
        return aMaze;
    }
}
