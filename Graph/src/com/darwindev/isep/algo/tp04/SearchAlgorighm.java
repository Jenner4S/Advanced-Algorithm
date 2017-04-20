package com.darwindev.isep.algo.tp04;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Zheng on 19/04/2017.
 *
 */
public class SearchAlgorighm {

    interface SearchCallbackInterface {
        ArrayList<Integer> search(DirectedGraph G, int startNode);
    }

    // The function to do DFS traversal.
    private static ArrayList<Integer> dfs(DirectedGraph G, int startNode) {
        // Mark all the vertices as not visited (set as false by default in java).
        boolean[] visited = new boolean[G.V + 1];
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Perform search algorithm without recursive calls.
        // DFS uses Stack data structure.
        Stack<Integer> stack = new Stack<>();
        // Choose the smallest one as root.
        stack.push(startNode);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            visitOrder.add(node);
            // In case of choice, the vertex with the smallest identifier will be chosen.
            ListIterator<Integer[]> iter = G.adj[node].listIterator(G.adj[node].size());
            while (iter.hasPrevious()) {
                Integer[] childNode = iter.previous();
                if (!visited[childNode[0]]) {
                    stack.push(childNode[0]);
                }
            }
        }
        return visitOrder;
    }

    // The function to do BFS traversal.
    private static ArrayList<Integer> bfs(DirectedGraph G, int startNode) {
        // Mark all the vertices as not visited (set as false by default in java)
        boolean[] visited = new boolean[G.V + 1];
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Perform search algorithm without recursive calls.
        // BFS uses Queue data structure.
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            int node = queue.remove();
            visited[node] = true;
            visitOrder.add(node);
            Iterator<Integer[]> iter = G.adj[node].listIterator();
            while (iter.hasNext()) {
                Integer[] childNode = iter.next();
                if (!visited[childNode[0]]) {
                    queue.add(childNode[0]);
                }
            }
        }
        return visitOrder;
    }

    // The function to determine the number of connected vertex.
    private static int cc(DirectedGraph G, int startNode, SearchCallbackInterface callback) {
        return callback.search(G, startNode).size();
    }

    // To determine whether the graph is a connected graph.
    private static boolean isConnected(DirectedGraph G, int startNode, SearchCallbackInterface callback) {
        return cc(G, startNode, callback) == G.V;
    }

    public static void main(String[] args) {
        try {
            DirectedGraph g = new DirectedGraph("graph-DFS-BFS.txt");
            g.print();

            // Test DFS
            SearchCallbackInterface dfsCallback = new SearchCallbackInterface() {
                @Override
                public ArrayList<Integer> search(DirectedGraph G, int startNode) {
                    return dfs(G, startNode);
                }
            };
            System.out.println(dfs(g, 1));
            System.out.println(cc(g, 1, dfsCallback));
            System.out.println(isConnected(g, 1, dfsCallback));
            System.out.println(dfs(g, 5));
            System.out.println(cc(g, 5, dfsCallback));
            System.out.println(isConnected(g, 5, dfsCallback));

            // Test BFS
            SearchCallbackInterface bfsCallback = new SearchCallbackInterface() {
                @Override
                public ArrayList<Integer> search(DirectedGraph G, int startNode) {
                    return bfs(G, startNode);
                }
            };
            System.out.println(bfs(g, 1));
            System.out.println(cc(g, 1, bfsCallback));
            System.out.println(isConnected(g, 1, bfsCallback));
            System.out.println(bfs(g, 5));
            System.out.println(cc(g, 5, bfsCallback));
            System.out.println(isConnected(g, 5, bfsCallback));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
