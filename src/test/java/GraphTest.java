import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphTest {

    @Test
    public void testFeature1_ParseGraph() throws IOException {
        InputStream is = getClass().getResourceAsStream("/input.dot");
        Graph g = GraphParser.parseGraph(is);
        String expected = Files.readString(Paths.get("src/test/resources/expected_feature1.txt"));
        assertEquals(expected.trim().replace("\r\n", "\n"), g.toString().trim().replace("\r\n", "\n"));
    }

    @Test
    public void testFeature2_AddNodes() throws IOException {
        Graph g = new Graph();
        g.addNodeUnique("X");
        g.addNodes(new String[]{"Y", "Z", "X"});
        String expected = Files.readString(Paths.get("src/test/resources/expected_feature2.txt"));
        assertEquals(expected.trim().replace("\r\n", "\n"), g.toString().trim());
    }

    @Test
    public void testFeature3_AddEdges() throws IOException {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");
        g.addEdgeUnique("A", "B");
        g.addEdgeUnique("A", "B");
        g.addEdges(new String[][]{{"B", "A"}, {"A", "B"}});
        String expected = Files.readString(Paths.get("src/test/resources/expected_feature3.txt"));
        String actual = g.toString();
        assertEquals(expected.trim().replace("\r\n", "\n"), actual.trim().replace("\r\n", "\n"));
    }

    @Test
    public void testFeature4_OutputDOTGraph() throws IOException {
        Graph g = new Graph();
        g.addNode("N1");
        g.addNode("N2");
        g.addEdgeUnique("N1", "N2");
        String path = "src/test/resources/test_output.dot";
        g.outputDOTGraph(path);
        String expected = Files.readString(Paths.get("src/test/resources/expected_feature4.txt"));
        String actual = Files.readString(Paths.get(path));
        assertEquals(expected.trim().replace("\r\n", "\n"), actual.trim().replace("\r\n", "\n"));
    }
}
