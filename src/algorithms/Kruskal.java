package algorithms;

import collections.FIFOqueue;
import collections.MinPQ;
import edges.UndirectedWeightedEdge;
import graphs.EdgeWeightedUndirectedGraph;

/**
 * Created by Amanda Curyło on 29.09.2017.
 */
public class Kruskal implements MST {
    private FIFOqueue<UndirectedWeightedEdge> mst;

    public Kruskal(EdgeWeightedUndirectedGraph G) {
        if (!G.executeConnectedComponents().isConnected()) throw new IllegalArgumentException("Graph is not connected");
        mst = new FIFOqueue<>();
        MinPQ<UndirectedWeightedEdge> pq = new MinPQ<>(G.edges(), G.E());
        UnionFind uf = new UnionFind(G.V());
        while (!pq.isEmpty() && mst.size() < G.V()-1)
        {
            UndirectedWeightedEdge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    @Override
    public Iterable<UndirectedWeightedEdge> edges() {
        return mst;
    }
}
