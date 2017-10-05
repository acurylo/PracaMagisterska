package algorithms;

import edges.DirectedEdge;
import graphs.Digraph;

/**
 * Created by Amanda Curyło on 29.09.2017.
 */
public class StronglyConnectedComponents {                  //nie dziala
    private boolean[] marked;
    private int[] id;
    private int count;

    public StronglyConnectedComponents(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s : G.reverse().executeDepthFirstOrder().reversePost())
            if (!marked[s])
            { dfs(G, s); count++; }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public boolean isStronglyConnected() {
        return count == 1;
    }

}
