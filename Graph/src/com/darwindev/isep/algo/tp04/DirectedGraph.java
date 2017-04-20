package com.darwindev.isep.algo.tp04;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class DirectedGraph {
    public int V;   // No. of vertices

    // Array of lists for Adjacency List Representation
    // arr[0] -> toNode
    // arr[1] -> weight
    public LinkedList<Integer[]> adj[];

    // Constructor
    DirectedGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Constructor: build graph from file
    DirectedGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        HashSet<Integer> set = new HashSet<Integer>();
        for (String line : lines) {
            String[] nodesId = line.split(" ");
            set.add(Integer.parseInt(nodesId[0]));
            set.add(Integer.parseInt(nodesId[1]));
        }
        ArrayList<Integer> nodeIds = new ArrayList<Integer>(set);
        Collections.sort(nodeIds);
        int startId = 0;
        for (Integer nodeId: nodeIds) {
            startId++;
            if (nodeId != startId) {
                throw new IOException("Nodes are not consecutive!");
            }
        }
        int v = nodeIds.size();
        V = v;
        adj = new LinkedList[v + 1];
        for (int i = 1; i <= v; ++i)
            adj[i] = new LinkedList();
        for (String line : lines) {
            String[] nodesId = line.split(" ");
            addEdge(Integer.parseInt(nodesId[0]), Integer.parseInt(nodesId[1]), 1);
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w, int power) {
        adj[v].add(new Integer[]{ w, power });  // Add w to v's list.
    }

    // Print the whole graph
    void print() {
        int v = V;
        for (int i = 1; i <= v; i++) {
            System.out.print(i + ": ");
            Iterator<Integer[]> iter = adj[i].listIterator();
            while (iter.hasNext()) {
                Integer[] n = iter.next();
                System.out.print("(" + Integer.toString(n[0]) + ", " + Integer.toString(n[1]) + ")");
            }
            System.out.println();
        }
    }
}
