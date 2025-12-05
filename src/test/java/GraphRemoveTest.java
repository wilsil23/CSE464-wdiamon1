import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphRemoveTest {

    @Test
    public void testRemoveNodesAndEdges_Successful() {
        Graph g = new Graph();
        g.addNodes(new String[]{"A", "B", "C", "D"});
        g.addEdges(new String[][]{{"A","B"}, {"B","C"}, {"A","C"}, {"C","D"}});

        assertTrue(g.hasNode("A"));
        assertTrue(g.hasNode("D"));
        assertTrue(g.hasEdge("A", "B"));
        assertTrue(g.hasEdge("C", "D"));

        g.removeEdge("A", "B");
        g.removeNode("D");

        assertFalse(g.hasEdge("A", "B"));
        assertFalse(g.hasNode("D"));
        assertTrue(g.hasEdge("B", "C"));
    }

    @Test
    public void testRemoveNode_NotExist_Throws() {
        Graph g = new Graph();
        g.addNode("A");
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("X"));
    }

    @Test
    public void testRemoveNodes_Multiple_NotExist_Throws() {
        Graph g = new Graph();
        g.addNodes(new String[]{"A", "B"});
        assertThrows(IllegalArgumentException.class, () -> g.removeNodes(new String[]{"A", "C"}));
    }

    @Test
    public void testRemoveEdge_NotExist_Throws() {
        Graph g = new Graph();
        g.addNodes(new String[]{"A", "B"});
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge("A", "B"));
    }

    @Test
    public void testRemoveEdge_NodeMissing_Throws() {
        Graph g = new Graph();
        g.addNode("A");
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge("A", "B"));
    }
}
