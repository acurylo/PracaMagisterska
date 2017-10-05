package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class DirectedUnweigtedEdge implements DirectedEdge, UnweightedEdge {
    private final int v;
    private final int w;

    public DirectedUnweigtedEdge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public DirectedUnweigtedEdge(DirectedUnweigtedEdge e) {
        this.v = e.v;
        this.w = e.w;
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
    public DirectedUnweigtedEdge reverse() {
        return new DirectedUnweigtedEdge(to(), from());
    }

    @Override
    public String between() {
        return null;
    }

    @Override
    public boolean checkVertex(int v, int w) {
        return false;
    }
}
