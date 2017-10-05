package algorithms;

import collections.Bag;
import edges.UndirectedEdge;
import graphs.UndirectedGraph;

/**
 * Created by Amanda Curyło on 27.09.2017.
 */
public class ConnectedComponents {
    private boolean marked[];
    private int id[];
    private int count;

    public ConnectedComponents(UndirectedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
        {
            if(!marked[v])
            {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(UndirectedGraph G, int v) {
        id[v] = count;
        marked[v] = true;
        for(UndirectedEdge e : G.adj(v))
        {
            int w = e.other(v);
            if(!marked[w])
            {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v]==id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public boolean isConnected() {
        return count == 1;
    }
}
