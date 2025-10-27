import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getResourceAsStream("/input.dot");
            Graph g = GraphParser.parseGraph(is);
            System.out.println(g.toString());
            g.outputGraph("src/main/resources/output.dot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
