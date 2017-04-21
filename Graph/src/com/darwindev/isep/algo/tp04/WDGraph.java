package com.darwindev.isep.algo.tp04;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class WDGraph {

    public class DirectedEdge {

        private final int v;
        private final int w;
        private final double weight;

        DirectedEdge(int from, int to, double power) {
            v = from;
            w = to;
            weight = power;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public double weight() {
            return weight;
        }

    }

    public int V;   // No. of vertices

    // Array of lists for Adjacency List Representation
    public LinkedList<DirectedEdge> adj[];

    // Constructor
    WDGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Constructor: build graph from file
    WDGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        HashSet<Integer> set = new HashSet<Integer>();
        for (String line : lines) {
            String[] nodesId = line.split(" ");
            if (nodesId.length >= 2) {
                set.add(Integer.parseInt(nodesId[0]));
                set.add(Integer.parseInt(nodesId[1]));
            }
        }
        ArrayList<Integer> nodeIds = new ArrayList<Integer>(set);
        V = Collections.max(nodeIds) + 1;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList();
        for (String line : lines) {
            String[] nodesId = line.split(" ");
            if (nodesId.length == 2) {
                addEdge(Integer.parseInt(nodesId[0]), Integer.parseInt(nodesId[1]), 1);
            } else if (nodesId.length == 3) {
                addEdge(Integer.parseInt(nodesId[0]), Integer.parseInt(nodesId[1]), Double.parseDouble(nodesId[2]));
            } else {
                continue;
            }
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w, double power) {
        adj[v].add(new DirectedEdge(v, w, power));  // Add w to v's list.
    }

    // Print the whole graph
    void print() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + ": ");
            Iterator<DirectedEdge> iter = adj[i].listIterator();
            while (iter.hasNext()) {
                DirectedEdge n = iter.next();
                System.out.print("(" + Integer.toString(n.to()) + ", " + Double.toString(n.weight()) + "), ");
            }
            System.out.println();
        }
    }

}
