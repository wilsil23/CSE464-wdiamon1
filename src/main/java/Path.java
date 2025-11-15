import java.util.*;

public class Path {
    private List<String> nodes;
    public Path() {
        this.nodes = new ArrayList<>();
    }
    public Path(List<String> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }
    public List<String> getNodes() {
        return nodes;
    }
    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }
}
