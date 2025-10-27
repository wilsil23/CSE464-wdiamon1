import java.util.*;
import java.io.*;

public class Graph {
    private Set<String> nodes = new HashSet<>();
    private List<String[]> edges = new ArrayList<>();

    public void addNode(String label) {
        nodes.add(label);
    }

    public void addEdge(String src, String dst) {
        edges.add(new String[]{src, dst});
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public List<String[]> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes: ").append(nodes.size()).append("\n");
        sb.append("Labels: ").append(nodes).append("\n");
        sb.append("Edges: ").append(edges.size()).append("\n");
        for (String[] e : edges) {
            sb.append(e[0]).append(" -> ").append(e[1]).append("\n");
        }
        return sb.toString();
    }
    public void outputGraph(String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph G {\n");
            // Write edges
            for (String[] e : edges) {
                writer.write("  " + e[0] + " -> " + e[1] + ";\n");
            }
            writer.write("}\n");
            System.out.println("Graph written to " + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
