package com.darwindev.isep.algo.tp04;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by Zheng on 21/04/2017.
 *
 */
class DFSSearch {

    // The function to do DFS traversal.
    ArrayList<Integer> dfs(WDGraph G, int startNode) {
        // Mark all the vertices as not visited (set as false by default in java).
        boolean[] visited = new boolean[G.V + 1];
        // Use an array list to record visit orders.
        ArrayList<Integer> visitOrder = new ArrayList<>();
        // Perform search algorithm without recursive calls.
        // DFS uses Stack data structure.
        Stack<Integer> stack = new Stack<>();
        // Put root node into stack.
        stack.push(startNode);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            visitOrder.add(node);
            // In case of choice, the vertex with the smallest identifier will be chosen.
            ListIterator<WDGraph.DirectedEdge> iter = G.adj[node].listIterator(G.adj[node].size());
            while (iter.hasPrevious()) {
                WDGraph.DirectedEdge childNode = iter.previous();
                if (!visited[childNode.to()]) {
                    stack.push(childNode.to());
                }
            }
        }
        return visitOrder;
    }

}
