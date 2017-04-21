package com.darwindev.isep.algo.tp04;

import java.util.*;

/**
 * Created by Zheng on 21/04/2017.
 *
 */
public class BFSShortestPaths {

    private int sourceNode;
    private boolean[] marked;
    private int[] previous;
    private int[] distance;

    // The function to do BFS traversal.
    public ArrayList<Integer> bfs(WDGraph G, int s) {
        sourceNode = s;
        int v = G.V + 1;
        marked = new boolean[v];
        previous = new int[v];
        distance = new int[v];
        for (int i = 0; i < v; i++) {
            previous[i] = -1; // UNDEFINED
            distance[i] = Integer.MAX_VALUE; // +INFINITY
        }
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Perform search algorithm without recursive calls.
        // BFS uses Queue data structure.
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();
        // Put root node into queue
        // Put distance into queue (correspond to node)
        queue.add(s);
        marked[s] = true;
        previous[s] = -1;
        distanceQueue.add(0);
        distance[s] = 0;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            int nodeDistance = distanceQueue.remove();
            visitOrder.add(node);
            // In case of choice, the vertex with the smallest identifier will be chosen.
            for (WDGraph.DirectedEdge childEdge : G.adj[node]) {
                int thisNode = childEdge.to();
                if (!marked[thisNode]) {
                    queue.add(thisNode);
                    // Mark child node
                    marked[thisNode] = true;
                    previous[thisNode] = node;
                    // Update distance
                    distanceQueue.add(nodeDistance + 1);
                    distance[thisNode] = nodeDistance + 1;
                }
            }
        }
        return visitOrder;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
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
