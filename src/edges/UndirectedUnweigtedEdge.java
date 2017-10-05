package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public class UndirectedUnweigtedEdge implements UndirectedEdge, UnweightedEdge {
    private final int v;
    private final int w;

    UndirectedUnweigtedEdge(int v, int w) {
        this.v = v;
        this.w = w;
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
    public String between() {
        return null;
    }

    @Override
    public boolean checkVertex(int v, int w) {
        return false;
    }
}
