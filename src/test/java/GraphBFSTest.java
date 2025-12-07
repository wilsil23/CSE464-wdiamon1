import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphBFSTest {

    @Test
    public void testBFSPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");
        g.addEdgeUnique("A", "D");

        BFSSearch bfs = new BFSSearch(g);
        Path p = bfs.search("A", "C");

        assertNotNull(p);
        assertEquals("A -> B -> C", p.toString());
    }

    @Test
    public void testNoPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addNode("Z");

        BFSSearch bfs = new BFSSearch(g);
        Path p = bfs.search("A", "Z");

        assertNull(p);
    }
}
