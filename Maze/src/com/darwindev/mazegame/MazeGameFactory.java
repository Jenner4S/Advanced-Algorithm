package com.darwindev.mazegame;

public class MazeGameFactory extends Object {
    public MazeGame create(String type) throws Exception {
        switch (type) {
            case "BombedMazeGame":
                return new BombedMazeGame();
            case "EnchantedMazeGame":
                return new EnchantedMazeGame();
            default:
                throw new Exception("MazeGame " + type + " is unknown.");
        }
    }

    public static void main(String[] args) {
        MazeGameFactory mazeGameFactory = new MazeGameFactory();
        try {
            MazeGame mazeGame = mazeGameFactory.create("BombedMazeGame");
            mazeGame.makeRoom(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(mazeGameFactory);
    }
}
