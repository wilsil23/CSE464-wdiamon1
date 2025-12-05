import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphStrategyAPITest {

    @Test
    public void testGraphSearchUsesBFS() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");

        Path p = g.GraphSearch("A", "C", Graph.Algorithm.BFS);

        assertNotNull(p);
        assertEquals("A -> B -> C", p.toString());
    }

    @Test
    public void testGraphSearchUsesDFS() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");

        Path p = g.GraphSearch("A", "C", Graph.Algorithm.DFS);

        assertNotNull(p);
    }
}
