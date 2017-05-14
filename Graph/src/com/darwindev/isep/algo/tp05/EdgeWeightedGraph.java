package com.darwindev.isep.algo.tp05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Zheng on 14/05/2017.
 *
 */
public class EdgeWeightedGraph {

    public class UndirectedEdge {

        private final int v;
        private final int w;
        private final double weight;

        UndirectedEdge(int either, int other, double power) {
            v = either;
            w = other;
            weight = power;
        }

        public int either() {
            return v;
        }

        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            throw new IllegalArgumentException("Illegal endpoint");
        }

        public double weight() {
            return weight;
        }

    }


    private int capacity = 0;   // capacity of graph
    private int nodeCount = 0; // number of vertex
    private int edgeCount = 0; // number of edge

    // Array of lists for Adjacency List Representation
    private LinkedList<UndirectedEdge> adj[];

    // Constructor
    public EdgeWeightedGraph(int v) {
        capacity = v;
        adj = new LinkedList[capacity];
    }

    // Constructor: build graph from file
    public EdgeWeightedGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
        HashSet<Integer> set = new HashSet<Integer>();
        for (String line : lines) {
            String[] nodesId = line.trim().split("\\s+");
            if (nodesId.length >= 2) {
                set.add(Integer.parseInt(nodesId[0]));
                set.add(Integer.parseInt(nodesId[1]));
            }
        }
        ArrayList<Integer> nodeIds = new ArrayList<Integer>(set);
        int size = Collections.max(nodeIds) + 1;
        capacity = size;
        adj = new LinkedList[size];
        for (int i = 0; i < size; ++i)
            addVertex(i);
        for (String line : lines) {
            String[] nodesId = line.split("\\s+");
            if (nodesId.length == 2) {
                addEdge(Integer.parseInt(nodesId[0]), Integer.parseInt(nodesId[1]), 1);
            } else if (nodesId.length == 3) {
                addEdge(Integer.parseInt(nodesId[0]), Integer.parseInt(nodesId[1]), Double.parseDouble(nodesId[2]));
            }
        }
    }

    public void addVertex(int v) {
        adj[v] = new LinkedList<>();
        nodeCount = nodeCount + 1;
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w, double power) {
        adj[v].add(new UndirectedEdge(v, w, power));  // Add w to v's list.
        adj[w].add(new UndirectedEdge(v, w, power)); // Add v to w's list.
        edgeCount = edgeCount + 1;
    }

    public LinkedList<UndirectedEdge> getNode(int v) {
        return adj[v];
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int getCapacity() {
        return capacity;
    }

    // Print the whole graph
    public void print() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");
            if (adj[i] != null) {
                for (UndirectedEdge n : adj[i]) {
                    System.out.print("(" + Integer.toString(i) + ", " + Integer.toString(n.other(i)) + ", " + Double.toString(n.weight()) + "), ");
                }
            }
            System.out.println();
        }
    }

}
