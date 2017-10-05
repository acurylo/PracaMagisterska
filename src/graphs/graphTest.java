package graphs;

import algorithms.*;


import java.io.FileNotFoundException;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public class graphTest {
    public static void main(String args[]) throws FileNotFoundException {
//        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(5);
//        EdgeWeightedUndirectedGraph G1 = G.addEdge(1,2,0.1);
//
//        Iterable<UndirectedWeightedEdge> edges = G.edges();
//
//        System.out.println(G1.E());
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph("graf.txt", "-");
//        System.out.println(G.E());
//        System.out.println(G.V());
//        G.edges().forEach(e -> System.out.println(e.between()));
//        UndirectedWeightedEdge e = new UndirectedWeightedEdge(0,1,7);
//     EdgeWeightedUndirectedGraph G2 = G.removeEdge(0,1);
//        System.out.println(G2.containsEdge(e));
//        G2.edges().forEach(e2 -> System.out.println(e2.between()));
//        System.out.println(G.maxDegree());
//
//
//        EdgeWeightedUndirectedGraph G3 = G.removeVertex(2);
//        G3.edges().forEach(e2 -> System.out.println(e2.between()));
//        G3 = G3.addVertex(3);
//        System.out.println(G3.V());
//        G3 = G3.removeEdge(0,2);
//        G3 = G3.removeEdge(1,2);G3 = G3.removeEdge(3,2);
//
//        G3.edges().forEach(e2 -> System.out.println(e2.between()));
//        System.out.println(G2.E());
//        G2.edges().forEach(e2 -> System.out.println(e2.between()));
//        G2 = G2.addEdges(G3.edges());
//
//        System.out.println(G2.E());
//        G2.edges().forEach(e2 -> System.out.println(e2.between()));
//        System.out.println();
//        G.executeLazyPrim().edges().forEach(e -> System.out.println(e.between()));
//        System.out.println(G.executeConnectedComponents().connected(0,2));
      EdgeWeightedDigraph D = new EdgeWeightedDigraph("directed.txt", "-");
//        Cycle c = new Cycle(G);
//       // c.cycle().forEach(i -> System.out.println(i));
//        System.out.println(c.hasCycle());
//        DepthFirstSearch f = new DepthFirstSearch(D,0);
//        System.out.println(f.count());

        System.out.println(D.executeBreadthFirstSearch(0).hasPathTo(4));
        System.out.println(G.isTree());
        DepthFirstOrder d = new DepthFirstOrder(D);
        d.post().forEach(p -> System.out.print(p));
        System.out.println();
        d.pre().forEach(p -> System.out.print(p));
        System.out.println();
        d.reversePost().forEach(p -> System.out.print(p));
        System.out.println();
        UnionFind u = new UnionFind(G.V());
        u.union(1,2);
        u.union(2,3);
        System.out.println(u.connected(1,4));
//        System.out.println(G.executeLazyPrim().weight());
//        G.executeLazyPrim().edges().forEach(e -> System.out.println(e.between()));
        System.out.println();
        System.out.println(G.executeKruskal().weight());
        G.executePrim().edges().forEach(e -> System.out.println(e.between()));
      System.out.println(G.executePrim().weight());
      G.executeKruskal().edges().forEach(e -> System.out.println(e.between()));
//        System.out.println();
//        D.edges().forEach(e -> System.out.println(e.between()));
//        System.out.println();
//        D.reverse().edges().forEach(e -> System.out.println(e.between()));
    //   System.out.print(D.executeStronglyConnectedComponents().stronglyConnected(1,2));
      System.out.println();
      System.out.println(D.executeDijkstra(0).hasPathTo(3));
      D.executeDijkstra(0).pathTo(4).forEach(i -> System.out.println(i.between()));
      System.out.println();
      System.out.println(G.executeDijkstra(0).distTo(3));
      G.executeDijkstra(1).pathTo(4).forEach(e -> System.out.println(e.between()));
      G.executeDijkstra(0).pathTo(3).forEach(i -> System.out.println(i.between()));

    }


}
