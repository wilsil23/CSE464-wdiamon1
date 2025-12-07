import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getResourceAsStream("/input.dot");
            Graph g = GraphParser.parseGraph(is);

            System.out.println("====================================");
            System.out.println("Loaded Graph:");
            System.out.println(g);
            System.out.println("====================================\n");

            String start = "a";
            String target = "h";

            System.out.println("===== BFS Search (Scheme A) =====");
            BFSSearch bfs = new BFSSearch(g);
            Path bfsPath = bfs.search(start, target);
            if (bfsPath != null) {
                System.out.println("BFS Final Path: " + bfsPath + "\n");
            } else {
                System.out.println("BFS could not find a path.\n");
            }

            System.out.println("===== DFS Search (Scheme A) =====");
            DFSSearch dfs = new DFSSearch(g);
            Path dfsPath = dfs.search(start, target);
            if (dfsPath != null) {
                System.out.println("DFS Final Path: " + dfsPath + "\n");
            } else {
                System.out.println("DFS could not find a path.\n");
            }

            System.out.println("===== Random Walk Search (5 Attempts) =====");

            RandomWalkSearch rws = new RandomWalkSearch(g);

            for (int attempt = 1; attempt <= 5; attempt++) {
                Path p = rws.search(start, target);

                if (p == null) {
                    System.out.println("Attempt " + attempt + ": dead end");
                } else {
                    boolean success =
                            p.getNodes().get(p.getNodes().size() - 1).equals(target);

                    if (success) {
                        System.out.println("Attempt " + attempt + ": " + p + " (target!)");
                    } else {
                        System.out.println("Attempt " + attempt + ": " + p + " (dead end)");
                    }
                }
            }

            System.out.println("\n===== Random Walk Demo Complete =====");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
