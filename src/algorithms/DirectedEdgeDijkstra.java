package algorithms;

import collections.IndexMinPQ;
import collections.LIFOqueue;
import edges.DirectedWeightedEdge;
import graphs.EdgeWeightedDigraph;

/**
 * Created by Amanda Curyło on 30.09.2017.
 */
public class DirectedEdgeDijkstra implements Dijkstra{
    private DirectedWeightedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public  DirectedEdgeDijkstra(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedWeightedEdge[G.V()];
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

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedWeightedEdge e : G.adj(v))
        {
            int w = e.to();
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
    public Iterable<DirectedWeightedEdge> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        LIFOqueue<DirectedWeightedEdge> path = new LIFOqueue<>();
        for (DirectedWeightedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
        {
            path.push(e);
        }
        return path;
    }

}
