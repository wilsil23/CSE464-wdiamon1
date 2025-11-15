import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphDFSTest {
    @Test
    public void testDFSFindsAPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");
        g.addEdgeUnique("A", "D");
        Path p = g.GraphSearchDFS("A", "C");
        assertNotNull(p);
    }
    @Test
    public void testDFSNoPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addNode("Z");
        assertNull(g.GraphSearchDFS("A", "Z"));
    }
}
