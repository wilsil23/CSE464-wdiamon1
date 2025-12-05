import java.util.*;

public class RandomWalkSearch extends GraphSearch {

    public RandomWalkSearch(Graph graph) {
        super(graph);
    }

    @Override
    protected Path doSearch(String src, String dst) {

        Random rand = new Random();
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        String current = src;
        path.add(current);

        for (int steps = 0; steps < 1000; steps++) {

            if (current.equals(dst)) {
                return new Path(path);
            }

            List<String> neighbors = new ArrayList<>();
            for (Edge e : graph.getEdges()) {
                if (e.getSrc().equals(current)) {
                    neighbors.add(e.getDst());
                }
            }

            if (neighbors.isEmpty()) return null;

            current = neighbors.get(rand.nextInt(neighbors.size()));

            if (visited.contains(current)) return null;

            visited.add(current);
            path.add(current);
        }

        return null;
    }
}
