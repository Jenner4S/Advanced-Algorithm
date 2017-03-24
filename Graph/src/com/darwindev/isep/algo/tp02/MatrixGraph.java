package com.darwindev.isep.algo.tp02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Zheng on 24/03/2017.
 *
 */
public class MatrixGraph {
    private int N;
    private int M;
    private boolean[][] adj;

    public MatrixGraph(int capacity) {
        adj = new boolean[capacity][capacity];
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                adj[i][j] = false;
            }
        }
    }

    public MatrixGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        int capacity = lines.size();
        adj = new boolean[capacity][capacity];
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                adj[i][j] = false;
            }
        }
        for (String line : lines) {
            String[] nodesId = line.split(" ");
            adj[Integer.parseInt(nodesId[0])][Integer.parseInt(nodesId[1])] = true;
        }
    }
}
