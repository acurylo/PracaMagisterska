package algorithms;

import collections.Bag;
import collections.IndexMinPQ;
import edges.UndirectedWeightedEdge;
import graphs.EdgeWeightedUndirectedGraph;

/**
 * Created by Amanda Curyło on 29.09.2017.
 */
public class Prim implements MST {
    private UndirectedWeightedEdge[] edgeTo;
    private boolean marked[];
    private IndexMinPQ<Double> pq;
    private double[] distTo;

    public Prim(EdgeWeightedUndirectedGraph G) {
        if (!G.executeConnectedComponents().isConnected()) throw new IllegalArgumentException("Graph is not connected");
        edgeTo = new UndirectedWeightedEdge[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedUndirectedGraph G, int v) {
        marked[v] = true;
        for (UndirectedWeightedEdge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    @Override
    public Iterable<UndirectedWeightedEdge> edges() {
        Bag<UndirectedWeightedEdge> mst = new Bag<>();
        for (int v = 1; v < edgeTo.length; v++) {
            mst.add(edgeTo[v]);
        }
        return mst;
    }

}
