package com.darwindev.isep.algo.tp04;

import java.io.IOException;
import java.util.*;

/**
 * Created by Zheng on 19/04/2017.
 *
 */
public class SearchAlgorithm {

    interface SearchCallbackInterface {
        ArrayList<Integer> search(WDGraph G, int startNode);
    }

    // The function to determine the number of connected vertex.
    private static int cc(WDGraph G, int startNode, SearchCallbackInterface callback) {
        return callback.search(G, startNode).size();
    }

    // To determine whether the graph is a connected graph.
    private static boolean isConnected(WDGraph G, int startNode, SearchCallbackInterface callback) {
        return cc(G, startNode, callback) == G.V;
    }

    public static void main(String[] args) {
        try {

            // We assume that the vertex id should be consecutive from 0 to max(nodeIds).
            // If some vertex ids are missing, we consider these vertex are isolated.

            // Test DFS and BFS
            WDGraph g = new WDGraph("graph-DFS-BFS.txt");
            g.print();

            testDFS(g);
            testBFS(g);

            // Test BFS shortest path
            WDGraph g2 = new WDGraph("graph-BFS-SP.txt");
            g2.print();

            testBFSP(g2);

            // Test weighted graphs
            WDGraph g3 = new WDGraph("graph-WDG.txt");
            g3.print();

            testDijkstra(g3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testDijkstra(WDGraph g) {
        DijkstraSP dijkstraSP = new DijkstraSP();
        dijkstraSP.DijkstraSP(g, 1);
        System.out.println(dijkstraSP.hasPathTo(8));
        System.out.println(dijkstraSP.distTo(8));
        dijkstraSP.printSP(8);
    }

    private static void testBFSP(WDGraph g) {
        BFSShortestPaths bfsShortestPaths = new BFSShortestPaths();
        bfsShortestPaths.bfs(g, 0);
        System.out.println(bfsShortestPaths.hasPathTo(7));
        System.out.println(bfsShortestPaths.distTo(7));
        bfsShortestPaths.printSP(7);
    }

    private static void testDFS(WDGraph g) {
        // Test DFS
        SearchCallbackInterface dfsCallback = (G, startNode) -> {
            DFSSearch dfsSearch = new DFSSearch();
            return dfsSearch.dfs(G, startNode);
        };
        System.out.println(new DFSSearch().dfs(g, 1));
        System.out.println(cc(g, 1, dfsCallback));
        System.out.println(isConnected(g, 1, dfsCallback));
        System.out.println(new DFSSearch().dfs(g, 5));
        System.out.println(cc(g, 5, dfsCallback));
        System.out.println(isConnected(g, 5, dfsCallback));
    }

    private static void testBFS(WDGraph g) {
        // Test BFS
        SearchCallbackInterface bfsCallback = (G, startNode) -> {
            BFSShortestPaths bfsSearch = new BFSShortestPaths();
            return bfsSearch.bfs(G, startNode);
        };
        System.out.println(new BFSShortestPaths().bfs(g, 1));
        System.out.println(cc(g, 1, bfsCallback));
        System.out.println(isConnected(g, 1, bfsCallback));
        System.out.println(new BFSShortestPaths().bfs(g, 5));
        System.out.println(cc(g, 5, bfsCallback));
        System.out.println(isConnected(g, 5, bfsCallback));
    }
}
