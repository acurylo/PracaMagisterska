package algorithms;

import edges.Edge;

/**
 * Created by Amanda Curyło on 30.09.2017.
 */
public interface Dijkstra {
    double distTo(int v);
    boolean hasPathTo(int v);
    Iterable<? extends Edge> pathTo(int v);
}
