package algorithms;

import collections.LIFOqueue;
import edges.DirectedEdge;
import edges.Edge;
import edges.UndirectedEdge;
import graphs.Digraph;
import graphs.UndirectedGraph;

/**
 * Created by Amanda Curyło on 28.09.2017.
 */
public class Cycle<T extends Edge> {
    private boolean[] marked;
    private int[] edgeTo;
    private LIFOqueue<Integer> cycle;
    private boolean[] onStack;

    public Cycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    public Cycle(UndirectedGraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v, v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new LIFOqueue<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private void dfs(UndirectedGraph G, int v, int u) {
        marked[v] = true;
        onStack[v] = true;
        for (UndirectedEdge e : G.adj(v))
        {
            int w = e.other(v);
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, v);
            }
            else if (w != u) {
                cycle = new LIFOqueue<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


}
