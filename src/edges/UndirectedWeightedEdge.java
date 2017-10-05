package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class UndirectedWeightedEdge implements UndirectedEdge, WeightedEdge, Comparable<UndirectedWeightedEdge> {
    private final int v;
    private final int w;
    private final double weight;

    public UndirectedWeightedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public UndirectedWeightedEdge(UndirectedWeightedEdge e) {
        this.v = e.v;
        this.w = e.w;
        this.weight = e.weight;
    }

    @Override
    public int either() {
        return v;
    }

    @Override
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public double weight() {
        return weight;
    }

    public boolean checkVertex(UndirectedWeightedEdge e) {
        int v = e.either();
        int w = e.other(v);
        return checkVertex(v, w);
    }

    @Override
    public boolean checkVertex(int v, int w) {
        return (this.v == v && this.w == w) || (this.v == w && this.w == v);
    }

    @Override
    public String between() {
        String between = v + "-" + w + "," + weight;
        return between;
    }

    @Override
    public int compareTo(UndirectedWeightedEdge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else return 0;
    }
}


