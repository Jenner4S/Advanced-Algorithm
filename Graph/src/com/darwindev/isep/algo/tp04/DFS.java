package com.darwindev.isep.algo.tp04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by Zheng on 19/04/2017.
 *
 */
public class DFS {

    // The function to do DFS traversal.
    private static ArrayList<Integer> dfs(DirectedGraph G) {
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Perform DFS algorithm without recursive calls.
        Stack<Integer> stack = new Stack<>();
        // Choose the smallest one as root.
        stack.push(1);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            visitOrder.add(node);
            // In case of choice, the vertex with the smallest identifier will be chosen.
            ListIterator<Integer> iter = G.adj[node].listIterator(G.adj[node].size());
            while (iter.hasPrevious()) {
                int childNode = iter.previous();
                stack.push(childNode);
            }
        }
        return visitOrder;
    }

    // The function to determine the number of connected vertex.
    private static int cc(DirectedGraph G) {
        return dfs(G).size();
    }

    // To determine whether the graph is a connected graph.
    private static boolean isConnected(DirectedGraph G) {
        return cc(G) == G.V;
    }

    public static void main(String[] args) {
        try {
            DirectedGraph g = new DirectedGraph("graph-DFS-BFS.txt");
            g.print();
            System.out.println(dfs(g));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
