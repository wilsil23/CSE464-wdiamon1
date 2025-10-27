import java.util.*;
import java.io.*;

public class Graph {
    private Set<String> nodes = new HashSet<>();
    private List<String[]> edges = new ArrayList<>();

    public void addNode(String label) {
        nodes.add(label);
    }

    public boolean addNodeUnique(String label) {
        if (nodes.contains(label)) return false;
        nodes.add(label);
        return true;
    }

    public List<String> addNodes(String[] labels) {
        List<String> added = new ArrayList<>();
        for (String label : labels) {
            if (!nodes.contains(label)) {
                nodes.add(label);
                added.add(label);
            }
        }
        return added;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public List<String[]> getEdges() {
        return edges;
    }

    public boolean addEdgeUnique(String src, String dst) {
        for (String[] edge : edges) {
            if (edge[0].equals(src) && edge[1].equals(dst)) {
                return false;
            }
        }
        nodes.add(src);
        nodes.add(dst);

        edges.add(new String[]{src, dst});
        return true;
    }

    public int addEdges(String[][] edgeList) {
        int addedCount = 0;
        for (String[] edge : edgeList) {
            if (edge.length != 2) continue;
            if (addEdgeUnique(edge[0], edge[1])) {
                addedCount++;
            }
        }
        return addedCount;
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

    public void outputDOTGraph(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph G {\n");
            for (String node : nodes) {
                writer.write("  " + node + ";\n");
            }
            for (String[] edge : edges) {
                writer.write("  " + edge[0] + " -> " + edge[1] + ";\n");
            }
            writer.write("}\n");
        }
    }

    public void outputGraphics(String path, String format) throws IOException, InterruptedException {
        String tempDot = "src/main/resources/tempGraph.dot";
        outputDOTGraph(tempDot);
        String cmd = String.format("dot -T%s %s -o %s", format, tempDot, path);
        Process process = Runtime.getRuntime().exec(cmd);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
            }
            throw new IOException("Graphviz command failed. Make sure Graphviz is installed and 'dot' is in your PATH.");
        }
        new File(tempDot).delete();
    }
}

