package algorithms;

import graphs.Digraph;

/**
 * Created by Amanda Curyło on 29.09.2017.
 */
public class TopologicalSort {
    private Iterable<Integer> order;

    public TopologicalSort(Digraph G) {
        if (!G.executeCycle().hasCycle())
            order = G.executeDepthFirstOrder().reversePost();
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;   //nie powinno byc order!=null????
    }

}
