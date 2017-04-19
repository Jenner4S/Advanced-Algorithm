package com.darwindev.isep.algo.tp03;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Bonus3 {
    public static void main(String[] args) throws Exception {
        FileReader jsonFile = new FileReader("reseau.json");
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonFile);

        JSONObject stations = (JSONObject) obj.get("stations");
        ArrayList<JSONObject> stationArray = new ArrayList<JSONObject>(stations.values());
        HashMap<String, String> stationMap = new HashMap<>();
        for (int i = 0; i < stationArray.size(); i++) {
            JSONObject stationDict = stationArray.get(i);
            stationMap.put((String) stationDict.get("num"), (String) stationDict.get("nom"));
        }

        HashSet<String> stationNameSet = new HashSet<>();
        for (int i = 0; i < stationArray.size(); i++) {
            JSONObject stationDict = stationArray.get(i);
            stationNameSet.add((String) stationDict.get("nom"));
        }

        ArrayList<String> stationNames = new ArrayList<>(stationNameSet);
        UndirectedGraph graph = new UndirectedGraph(stationNames.size());
        for (int i = 0; i < stationNames.size(); i++) {
            graph.adj[i].id = stationNames.get(i);
        }

        JSONObject lines = (JSONObject) obj.get("lignes");
        ArrayList<JSONObject> lineArray = new ArrayList<JSONObject>(lines.values());
        for (int i = 0; i < lineArray.size(); i++) {
            JSONArray arretArray = (JSONArray) lineArray.get(i).get("arrets");
            if (arretArray != null) {
                for (int j = 0; j < arretArray.size(); j++) {
                    JSONArray arretSubArray = (JSONArray) arretArray.get(j);
                    for (int k = 0; k < arretSubArray.size() - 1; k++) {
                        String arretName1 = stationMap.get((String) arretSubArray.get(k));
                        String arretName2 = stationMap.get((String) arretSubArray.get(k + 1));
                        graph.addEdgeBetweenNode(arretName1, arretName2);
                    }
                }
            }
        }

        graph.print();

        // Generate UndirectedGraph JSON
        HashMap out = new HashMap();
        ArrayList<HashMap> outNodeArr = new ArrayList<>(graph.adj.length);
        for (UndirectedGraph.Node node1: graph.adj) {
            HashMap newDict = new HashMap();
            newDict.put("id", node1.id);
            newDict.put("group", 0);
            outNodeArr.add(newDict);
        }
        out.put("nodes", outNodeArr);

        ArrayList<HashMap> outLinkArr = new ArrayList<>();
        for (UndirectedGraph.Node node1: graph.adj) {
            UndirectedGraph.Edge edge1 = node1.firstEdge;
            while (edge1 != null) {
                HashMap newDict = new HashMap();
                newDict.put("source", node1.id);
                newDict.put("target", graph.adj[edge1.ivex].id);
                outLinkArr.add(newDict);
                edge1 = edge1.nextEdge;
            }
        }
        out.put("links", outLinkArr);

        JSONObject outObj = new JSONObject(out);
        FileWriter writer = new FileWriter("metro.json");
        outObj.writeJSONString(writer);
        writer.close();
    }
}
