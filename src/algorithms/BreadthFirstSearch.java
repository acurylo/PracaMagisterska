package algorithms;

import collections.FIFOqueue;
import collections.LIFOqueue;
import edges.DirectedEdge;
import edges.UndirectedEdge;
import graphs.Digraph;
import graphs.UndirectedGraph;

/**
 * Created by Amanda Curyło on 28.09.2017.
 */
public class BreadthFirstSearch {
    private boolean marked[];
    private int edgeTo[];
    private final int s;

    public BreadthFirstSearch(UndirectedGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    public BreadthFirstSearch(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(UndirectedGraph G, int s) {
        FIFOqueue<Integer> queue = new FIFOqueue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty())
        {
            int v = queue.dequeue();
            for (UndirectedEdge e : G.adj(v))
            {
                int w = e.other(v);
                if (!marked[w])
                {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    private void bfs(Digraph G, int s) {
        FIFOqueue<Integer> queue = new FIFOqueue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty())
        {
            int v = queue.dequeue();
            for (DirectedEdge e : G.adj(v))
            {
                int w = e.to();
                if (!marked[w])
                {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LIFOqueue<Integer> path = new LIFOqueue<>();
        for ( int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}
