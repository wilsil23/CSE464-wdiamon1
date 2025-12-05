import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RandomWalkSearchTest {

    @Test
    public void testRandomWalkFindsAPathEventually() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("B", "C");
        g.addEdgeUnique("A", "D");
        g.addEdgeUnique("D", "C");

        RandomWalkSearch rw = new RandomWalkSearch(g);

        Path result = null;

        for (int i = 0; i < 500 && result == null; i++) {
            result = rw.search("A", "C");
        }

        assertNotNull(result,
                "RandomWalk should eventually find a path from A to C");

        assertEquals("A", result.getNodes().get(0),
                "Path must start at A");

        assertEquals("C", result.getNodes().get(result.getNodes().size() - 1),
                "Path must end at C");
    }

    @Test
    public void testRandomWalkNoPath() {
        Graph g = new Graph();
        g.addEdgeUnique("A", "B");
        g.addNode("Z");

        RandomWalkSearch rw = new RandomWalkSearch(g);

        Path p = rw.search("A", "Z");

        assertNull(p);
    }
}
