import java.util.*;

public class BFSSearch extends GraphSearch {

    public BFSSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected Path doSearch(String src, String dst) {

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        List<String> startPath = new ArrayList<>();
        startPath.add(src);
        queue.add(startPath);
        visited.add(src);

        while (!queue.isEmpty()) {
            List<String> currentPath = queue.poll();
            String current = currentPath.get(currentPath.size() - 1);

            System.out.println("visiting Path{nodes=" + currentPath + "}");

            if (current.equals(dst)) {
                System.out.println("Path{nodes=" + currentPath + "}");
                return new Path(currentPath);
            }

            for (Edge e : graph.getEdges()) {
                if (e.getSrc().equals(current)) {
                    String neighbor = e.getDst();

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        List<String> newPath = new ArrayList<>(currentPath);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
        }
        return null;
    }
}


