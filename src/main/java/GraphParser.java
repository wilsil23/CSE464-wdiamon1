import java.io.*;
import java.util.regex.*;

public class GraphParser {

    public static Graph parseGraph(InputStream stream) throws IOException {
        Graph graph = new Graph();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        Pattern edgePattern = Pattern.compile("(\\w+)\\s*->\\s*(\\w+)");
        while ((line = reader.readLine()) != null) {
            Matcher m = edgePattern.matcher(line);
            if (m.find()) {
                String src = m.group(1);
                String dst = m.group(2);
                graph.addNode(src);
                graph.addNode(dst);
                graph.addEdgeUnique(src, dst);
            }
        }
        reader.close();
        return graph;
    }

}
