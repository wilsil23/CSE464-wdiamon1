import java.util.*;

public class BFSSearch extends GraphSearch {

    public BFSSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected Path doSearch(String src, String dst) {

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(src);
        parent.put(src, null);

        for (Edge e : graph.getEdges()) {}

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
}
