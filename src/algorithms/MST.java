package algorithms;

import edges.UndirectedWeightedEdge;

/**
 * Created by Amanda Curyło on 29.09.2017.
 */
public interface MST {          //trzeba spojnosc sprawdzac
    Iterable<UndirectedWeightedEdge> edges();
    default double weight() {                       //jest ok?
        Iterable<UndirectedWeightedEdge> edges = edges();
        double weight = 0;
        for (UndirectedWeightedEdge e : edges)
            weight += e.weight();
        return weight;
    }
}
