import java.util.*;

public class DFSSearch {

    private final Graph graph;

    public DFSSearch(Graph graph) {
        this.graph = graph;
    }

    public Path search(String src, String dst) {
        if (!graph.hasNode(src) || !graph.hasNode(dst)) {
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

        for (Edge e : graph.getEdges()) {
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
}
