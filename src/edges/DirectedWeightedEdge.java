package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class DirectedWeightedEdge implements DirectedEdge, WeightedEdge, Comparable<DirectedWeightedEdge> {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedWeightedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public DirectedWeightedEdge(DirectedWeightedEdge e) {
        this.v = e.v;
        this.w = e.w;
        this.weight = e.weight;
    }

    @Override
    public int from() {
        return v;
    }

    @Override
    public int to() {
        return w;
    }

    @Override
    public DirectedWeightedEdge reverse() {
        return new DirectedWeightedEdge(to(),from(),weight);
    }

    @Override
    public double weight() {
        return weight;
    }

    public boolean checkVertex(DirectedWeightedEdge e) {
        int v = e.from();
        int w = e.to();
        return checkVertex(v, w);
    }

    @Override
    public boolean checkVertex(int v, int w) {
        return (this.v == v && this.w == w);
    }

    @Override
    public String between() {
        String between = v + "->" + w + "," + weight;
        return between;
    }

    @Override
    public int compareTo(DirectedWeightedEdge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else return 0;
    }
}
