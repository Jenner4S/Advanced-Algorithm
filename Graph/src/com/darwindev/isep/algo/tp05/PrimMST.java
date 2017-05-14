package com.darwindev.isep.algo.tp05;

import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by Zheng on 14/05/2017.
 *
 */
public class PrimMST {

    private EdgeWeightedGraph MST;
    private double weight;
    private PriorityQueue<EdgeWeightedGraph.UndirectedEdge> priorityQueue;

    public void prim(EdgeWeightedGraph G, int s) {
        MST = new EdgeWeightedGraph(G.getCapacity());
        priorityQueue = new PriorityQueue<>((o1, o2) -> (o1.weight() > o2.weight()) ? 1 : -1);
        weight = 0.0;
        scan(G, s);
        while (!priorityQueue.isEmpty()) {
            EdgeWeightedGraph.UndirectedEdge edge = priorityQueue.poll();
            int v = edge.either(), w = edge.other(v);
            assert MST.getNode(v) != null || MST.getNode(w) != null;
            if (MST.getNode(v) != null && MST.getNode(w) != null) continue;
            if (MST.getNode(v) == null) scan(G, v);
            if (MST.getNode(w) == null) scan(G, w);
            MST.addEdge(v, w, edge.weight());
            weight += edge.weight();
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
        assert MST.getNode(v) == null;
        MST.addVertex(v);
        for (EdgeWeightedGraph.UndirectedEdge e : G.getNode(v))
            if (MST.getNode(e.other(v)) == null) priorityQueue.add(e);
    }

    public void edges() {

    }

    public double getWeight() {
        return weight;
    }

    public boolean connected() {
        return MST.getEdgeCount() == MST.getNodeCount() - 1;
    }

    public void print() {
        MST.print();
    }

    static public void main(String[] args) {

        try {
            System.out.println("Graph: ");

            EdgeWeightedGraph graph = new EdgeWeightedGraph("WG-MST.txt");
            graph.print();

            System.out.println();
            System.out.println("PrimMST: ");

            PrimMST primMST = new PrimMST();
            primMST.prim(graph, 0);
            primMST.print();

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
