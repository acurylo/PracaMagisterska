package graphs;

import edges.WeightedEdge;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public interface EdgeWeightedGraph {
    EdgeWeightedGraph addEdge(int v, int w, double weight);

}
