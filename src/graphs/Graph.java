package graphs;

import algorithms.*;
import collections.Bag;
import edges.Edge;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public interface Graph {
    int V();
    int E();
    Graph addVertex();
    Graph addVertex(int vertexCount);
    Graph removeVertex(int v);
    Graph removeEdge(int v, int w);
    Iterable<? extends Edge> edges();
    Iterable<? extends Edge> adj(int v);
    Bag<? extends Edge>[] getAdjCopy();
    Iterable<? extends Edge> nbsI(int v);
    Iterable<? extends Edge> nbsO(int v);
    boolean containsEdge(int v, int w);
    DepthFirstSearch executeDepthFirstSearch(int s);
    BreadthFirstSearch executeBreadthFirstSearch(int s);
    Cycle executeCycle();
    Dijkstra executeDijkstra(int s);
}
