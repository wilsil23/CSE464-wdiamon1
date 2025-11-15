import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphBFSTest {
    @Test
    public void testBFSPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");
        g.addEdgeUnique("A", "D");
        Path p = g.bfsSearch("A", "C");
        assertNotNull(p);
        assertEquals("A -> B -> C", p.toString());
    }

    @Test
    public void testNoPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addNode("Z");
        assertNull(g.bfsSearch("A", "Z"));
    }
}
