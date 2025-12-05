import java.util.*;

public class DFSSearch extends GraphSearch {

    public DFSSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected Path doSearch(String src, String dst) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        boolean found = dfs(src, dst, visited, path);
        if (found) {
            System.out.println("Path{nodes=" + path + "}");
            return new Path(path);
        }
        return null;
    }

    private boolean dfs(String current, String dst,
                        Set<String> visited, List<String> path) {

        visited.add(current);
        path.add(current);

        System.out.println("visiting Path{nodes=" + path + "}");

        if (current.equals(dst)) {
            return true;
        }

        for (Edge e : graph.getEdges()) {
            if (e.getSrc().equals(current)) {
                String neighbor = e.getDst();
                if (!visited.contains(neighbor)) {
                    if (dfs(neighbor, dst, visited, path)) {
                        return true;
                    }
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
