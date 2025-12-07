import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphDFSTest {

    @Test
    public void testDFSFindsAPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");
        g.addEdgeUnique("A", "D");

        DFSSearch dfs = new DFSSearch(g);
        Path p = dfs.search("A", "C");

        assertNotNull(p);
    }

    @Test
    public void testDFSNoPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addNode("Z");

        DFSSearch dfs = new DFSSearch(g);
        Path p = dfs.search("A", "Z");

        assertNull(p);
    }
}
