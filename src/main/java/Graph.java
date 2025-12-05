import java.util.*;
import java.io.*;

public class Graph {

    private Set<String> nodes = new HashSet<>();
    private List<Edge> edges = new ArrayList<>();

    public enum Algorithm {
        BFS,
        DFS
    }

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

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean addEdgeUnique(String src, String dst) {
        for (Edge edge : edges) {
            if (edge.getSrc().equals(src) && edge.getDst().equals(dst)) {
                return false;
            }
        }

        nodes.add(src);
        nodes.add(dst);

        edges.add(new Edge(src, dst));
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

    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new IllegalArgumentException("Node does not exist: " + label);
        }

        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.getSrc().equals(label) || e.getDst().equals(label)) {
                it.remove();
            }
        }

        nodes.remove(label);
    }

    public void removeNodes(String[] labels) {
        for (String label : labels) {
            if (!nodes.contains(label)) {
                throw new IllegalArgumentException("Node does not exist: " + label);
            }
        }
        for (String label : labels) {
            removeNode(label);
        }
    }

    public void removeEdge(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            throw new IllegalArgumentException("One or both nodes do not exist: " + src + ", " + dst);
        }
        boolean removed = false;

        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.getSrc().equals(src) && e.getDst().equals(dst)) {
                it.remove();
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new IllegalArgumentException("Edge does not exist: " + src + " -> " + dst);
        }
    }

    public boolean hasNode(String label) {
        return nodes.contains(label);
    }

    public boolean hasEdge(String src, String dst) {
        for (Edge e : edges) {
            if (e.getSrc().equals(src) && e.getDst().equals(dst))
                return true;
        }
        return false;
    }

    public Path GraphSearch(String src, String dst, Algorithm initAlgorithm) {
        if (initAlgorithm == Algorithm.BFS) {
            return bfsSearch(src, dst);
        } else {
            return dfsSearch(src, dst);
        }
    }

    public Path bfsSearch(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        queue.add(src);
        parent.put(src, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(dst)) {
                List<String> pathList = new ArrayList<>();
                String node = dst;
                while (node != null) {
                    pathList.add(node);
                    node = parent.get(node);
                }
                Collections.reverse(pathList);
                return new Path(pathList);
            }

            for (Edge e : edges) {
                if (e.getSrc().equals(current)) {
                    String neighbor = e.getDst();
                    if (!parent.containsKey(neighbor)) {
                        parent.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    public Path dfsSearch(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }

        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        boolean found = dfsHelper(src, dst, visited, path);
        if (found) {
            return new Path(path);
        }
        return null;
    }

    private boolean dfsHelper(String current, String dst,
                              Set<String> visited,
                              List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(dst)) {
            return true;
        }

        for (Edge e : edges) {
            if (e.getSrc().equals(current)) {
                String neighbor = e.getDst();
                if (!visited.contains(neighbor)) {
                    if (dfsHelper(neighbor, dst, visited, path)) {
                        return true;
                    }
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes: ").append(nodes.size()).append("\n");
        sb.append("Labels: ").append(nodes).append("\n");
        sb.append("Edges: ").append(edges.size()).append("\n");

        for (Edge e : edges) {
            sb.append(e.getSrc()).append(" -> ").append(e.getDst()).append("\n");
        }

        return sb.toString();
    }

    public void outputDOTGraph(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph G {\n");
            for (String node : nodes) {
                writer.write("  " + node + ";\n");
            }
            for (Edge edge : edges) {
                writer.write("  " + edge.getSrc() + " -> " + edge.getDst() + ";\n");
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
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
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
