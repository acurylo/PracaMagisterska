package graphs;

import algorithms.ConnectedComponents;
import algorithms.Cycle;
import collections.Bag;
import edges.UndirectedEdge;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public interface UndirectedGraph extends Graph {
    @Override
    Iterable<? extends UndirectedEdge> edges();
    @Override
    Iterable<? extends UndirectedEdge> adj(int v);
    @Override
    Bag<? extends UndirectedEdge>[] getAdjCopy();
    @Override
    Iterable<? extends UndirectedEdge> nbsI(int v);
    @Override
    Iterable<? extends UndirectedEdge> nbsO(int v);
    boolean isCompleteGraph();
    boolean isTree();
    ConnectedComponents executeConnectedComponents();
}
