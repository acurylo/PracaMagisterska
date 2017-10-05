package algorithms;

import collections.Bag;
import collections.FIFOqueue;
import collections.MinPQ;
import edges.UndirectedWeightedEdge;
import graphs.EdgeWeightedGraph;
import graphs.EdgeWeightedUndirectedGraph;

/**
 * Created by Amanda Curyło on 27.09.2017.
 */
public class LazyPrim implements MST {
    private boolean[] marked;
    private MinPQ<UndirectedWeightedEdge> pq;
    private FIFOqueue<UndirectedWeightedEdge> mst;

    public  LazyPrim(EdgeWeightedUndirectedGraph G) {
        if (!G.executeConnectedComponents().isConnected()) throw new IllegalArgumentException("Graph is not connected");
        pq = new MinPQ<>(G.E());        // tu trzeba poprawic
        mst = new FIFOqueue<>();
        marked = new boolean[G.V()];
        visit(G, 0);
        while (!pq.isEmpty())
        {
            UndirectedWeightedEdge e = pq.delMin();
            int v = e.either(); int w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedUndirectedGraph G, int v) {
        marked[v] = true;
        for (UndirectedWeightedEdge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);

    }

    @Override
    public Iterable<UndirectedWeightedEdge> edges() {
        return mst;
    }

}
