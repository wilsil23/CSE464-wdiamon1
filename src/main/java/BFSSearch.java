import java.util.*;

public class BFSSearch {

    private final Graph graph;

    public BFSSearch(Graph graph) {
        this.graph = graph;
    }

    public Path search(String src, String dst) {
        if (!graph.hasNode(src) || !graph.hasNode(dst)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(src);
        parent.put(src, null);

        for (Edge e : graph.getEdges()) {}

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(dst)) {
                return buildPath(parent, dst);
            }

            for (Edge e : graph.getEdges()) {
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

    private Path buildPath(Map<String, String> parent, String dst) {
        List<String> path = new ArrayList<>();
        String cur = dst;
        while (cur != null) {
            path.add(cur);
            cur = parent.get(cur);
        }
        Collections.reverse(path);
        return new Path(path);
    }
}
