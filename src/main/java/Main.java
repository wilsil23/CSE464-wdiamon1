public class Main {
    public static void main(String[] args) {
        try {
            Graph g = GraphParser.parseGraph("src/main/resources/input.dot");
            System.out.println(g.toString());
            g.outputGraph("src/main/resources/output.dot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
