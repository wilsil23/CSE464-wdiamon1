public class Edge {
    private final String src;
    private final String dst;

    public Edge(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }
}
