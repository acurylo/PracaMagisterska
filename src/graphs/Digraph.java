package graphs;

import algorithms.DepthFirstOrder;
import algorithms.DirectedEdgeDijkstra;
import algorithms.StronglyConnectedComponents;
import algorithms.TopologicalSort;
import collections.Bag;
import edges.DirectedEdge;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public interface Digraph extends Graph {
    @Override
    Iterable<? extends DirectedEdge> edges();
    @Override
    Iterable<? extends DirectedEdge> adj(int v);
    @Override
    Bag<? extends DirectedEdge>[] getAdjCopy();
    @Override
    Iterable<? extends DirectedEdge> nbsI(int v);
    @Override
    Iterable<? extends DirectedEdge> nbsO(int v);
    Digraph reverse();
    StronglyConnectedComponents executeStronglyConnectedComponents();
    DepthFirstOrder executeDepthFirstOrder();
    TopologicalSort executeTopologicalSort();

}
