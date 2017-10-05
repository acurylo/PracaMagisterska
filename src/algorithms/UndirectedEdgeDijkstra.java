package algorithms;

import collections.IndexMinPQ;
import collections.LIFOqueue;
import edges.DirectedWeightedEdge;
import edges.UndirectedEdge;
import edges.UndirectedWeightedEdge;
import graphs.EdgeWeightedDigraph;
import graphs.EdgeWeightedUndirectedGraph;

/**
 * Created by Amanda Curyło on 30.09.2017.
 */
public class UndirectedEdgeDijkstra implements Dijkstra {
    private UndirectedWeightedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public  UndirectedEdgeDijkstra(EdgeWeightedUndirectedGraph G, int s) {
        edgeTo = new UndirectedWeightedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++ )
        {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
        {
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedUndirectedGraph G, int v) {
        for (UndirectedWeightedEdge e : G.adj(v))
        {
            int w = e.other(v);
            if(distTo[w] > distTo[v] + e.weight())
            {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }

        }
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<UndirectedWeightedEdge> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        LIFOqueue<UndirectedWeightedEdge> path = new LIFOqueue<>();
        for (UndirectedWeightedEdge e = edgeTo[v]; e != null; e = edgeTo[e.either()])
        {
            path.push(e);
        }
        return path;
    }

}
