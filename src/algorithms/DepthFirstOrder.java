package algorithms;

import collections.FIFOqueue;
import collections.LIFOqueue;
import edges.DirectedEdge;
import graphs.Digraph;

/**
 * Created by Amanda Curyło on 28.09.2017.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private FIFOqueue<Integer> pre;
    private FIFOqueue<Integer> post;
    private LIFOqueue<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new FIFOqueue<Integer>();
        post = new FIFOqueue<Integer>();
        reversePost = new LIFOqueue<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (DirectedEdge e : G.adj(v))
        {
            int w = e.to();
            if (!marked[w])
                dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
