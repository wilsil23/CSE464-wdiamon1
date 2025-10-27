import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getResourceAsStream("/input.dot");
            Graph g = GraphParser.parseGraph(is);

            System.out.println("Original graph:");
            System.out.println(g.toString());
            g.addNodeUnique("D");
            g.addNodes(new String[]{"E", "F"});
            g.addEdgeUnique("C", "D");
            g.addEdgeUnique("D", "E");
            g.addEdges(new String[][] {{"A", "C"}, {"E", "F"}, {"B", "C"}});

            System.out.println("After adding nodes and edges:");
            System.out.println(g.toString());

            g.outputGraph("src/main/resources/output.dot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

