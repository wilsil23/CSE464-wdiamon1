import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

public class GraphTest {

    @Test
    public void testFeature1_ParseGraph() throws IOException {
        InputStream is = getClass().getResourceAsStream("/input.dot");
        Graph g = GraphParser.parseGraph(is);
        assertEquals(3, g.getNodes().size());
        assertTrue(g.getNodes().contains("A"));
        assertTrue(g.getNodes().contains("B"));
        assertTrue(g.getNodes().contains("C"));
        assertEquals(2, g.getEdges().size());
    }

    @Test
    public void testFeature2_AddNodes() {
        Graph g = new Graph();
        g.addNodeUnique("X");
        g.addNodes(new String[]{"Y", "Z", "X"});

        assertEquals(3, g.getNodes().size());
        assertTrue(g.getNodes().contains("X"));
        assertTrue(g.getNodes().contains("Y"));
        assertTrue(g.getNodes().contains("Z"));
    }

    @Test
    public void testFeature3_AddEdges() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("A", "B");
        g.addEdges(new String[][]{{"B", "A"}, {"A", "B"}});

        assertEquals(2, g.getEdges().size());
    }

    @Test
    public void testFeature4_OutputDOTGraph() throws IOException {
        Graph g = new Graph();
        g.addNode("N1");
        g.addNode("N2");
        g.addEdgeUnique("N1", "N2");

        String path = "src/test/resources/test_output.dot";

        g.outputDOTGraph(path);
        String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
        assertTrue(content.contains("N1 -> N2"));
        assertTrue(content.contains("N1;"));
        assertTrue(content.contains("N2;"));
    }
}
