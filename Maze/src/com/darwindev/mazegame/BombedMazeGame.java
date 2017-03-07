package com.darwindev.mazegame;

/**
 * Created by Zheng on 01/03/2017.
 *
 */
class BombedMazeGame extends MazeGame {
    public Room makeRoom(int n) {
        return new RoomWithABomb(n);
    }

    public Wall makeWall() {
        return new BombedWall();
    }

    public Door makeDoor(Room r1, Room r2) {
        return new IronDoor(r1, r2);
    }
}
