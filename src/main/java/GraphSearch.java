import java.util.List;

public abstract class GraphSearch {

    protected Graph graph;

    public GraphSearch(Graph graph) {
        this.graph = graph;
    }

    public final Path search(String src, String dst) {
        if (!graph.hasNode(src) || !graph.hasNode(dst)) {
            return null;
        }
        return doSearch(src, dst);
    }

    protected abstract Path doSearch(String src, String dst);
}
