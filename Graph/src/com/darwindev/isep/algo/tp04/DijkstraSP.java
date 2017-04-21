package com.darwindev.isep.algo.tp04;

import java.util.*;

/**
 * Created by Zheng on 21/04/2017.
 *
 */
public class DijkstraSP {

    private int sourceNode;
    private boolean[] marked;
    private int[] previous;
    private double[] distance;

    private boolean verifyNonNegative(WDGraph G) {
        for (LinkedList<WDGraph.DirectedEdge> edges: G.adj) {
            for (WDGraph.DirectedEdge n : edges) {
                if (n.weight() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Integer> DijkstraSP(WDGraph G, int s) {
        // To ensure all weights of edges are positive.
        if (!verifyNonNegative(G)) {
            return null;
        }
        sourceNode = s;
        int v = G.V + 1;
        marked = new boolean[v];
        previous = new int[v];
        distance = new double[v];
        // Use an array to store unvisited nodes
        HashSet<Integer> openedNodes = new HashSet<>();
        // Open all nodes
        for (int i = 0; i < v; i++) {
            previous[i] = -1; // UNDEFINED
            distance[i] = Double.MAX_VALUE; // +INFINITY
            openedNodes.add(i);
        }
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Distance from source to source
        distance[s] = 0; // distance
        marked[s] = true; // mark
        visitOrder.add(s); // visit
        while (!openedNodes.isEmpty()) {
            // Choose the smallest distance.
            double smallestDistance = Double.MAX_VALUE;
            int smallestNode = -1;
            for (Integer thisNode : openedNodes) {
                if (distance[thisNode] < smallestDistance) {
                    smallestDistance = distance[thisNode];
                    smallestNode = thisNode;
                }
            }
            // Go to the smallest one.
            openedNodes.remove(smallestNode);
            visitOrder.add(smallestNode);
            // If remained nodes are not available, it is not a connected graph, terminate the progress.
            if (smallestNode == -1) {
                break;
            }
            // Check all neighbours and update distances
            for (WDGraph.DirectedEdge directedEdge : G.adj[smallestNode]) {
                int childNode = directedEdge.to();
                double alt = distance[smallestNode] + directedEdge.weight();
                if (alt < distance[childNode]) {
                    marked[childNode] = true;
                    previous[childNode] = smallestNode;
                    distance[childNode] = alt;
                }
            }
        }
        return visitOrder;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public double distTo(int v) {
        return distance[v];
    }

    public void printSP(int v) {
        ArrayList<Integer> shortestPath = new ArrayList<>();
        int thisNode = v;
        while (thisNode > -1) {
            shortestPath.add(thisNode);
            thisNode = previous[thisNode];
            if (thisNode == sourceNode) {
                shortestPath.add(sourceNode);
                break;
            }
        }
        Collections.reverse(shortestPath);
        System.out.println(shortestPath);
    }

}
