package algorithms;


import edges.DirectedEdge;
import edges.UndirectedEdge;
import graphs.Digraph;
import graphs.UndirectedGraph;

/**
 * Created by Amanda Curyło on 28.09.2017.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(UndirectedGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DepthFirstSearch(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(UndirectedGraph G, int v) {
        marked[v] = true;
        count++;
        for (UndirectedEdge e : G.adj(v)){
            int w = e.other(v);
            if (!marked[w]) dfs(G, w);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        count++;
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
