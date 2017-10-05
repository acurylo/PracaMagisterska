package graphs;

import algorithms.*;
import collections.Bag;
import edges.DirectedWeightedEdge;
import edges.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Amanda Curyło on 28.09.2017.
 */
public class EdgeWeightedDigraph implements Digraph, EdgeWeightedGraph {
    private final int V;
    private final int E;
    private final Bag<DirectedWeightedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(String filename, String delim) throws FileNotFoundException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        this.V = Integer.parseInt(in.nextLine());
        boolean[][] adjMatrix = new boolean[V][V];
        Bag<DirectedWeightedEdge>[] adjContainer = (Bag<DirectedWeightedEdge>[]) new Bag[V];
        int e = 0;
        for (int v = 0; v < V; v++) {
            adjContainer[v] = new Bag<DirectedWeightedEdge>();
        }
        while (in.hasNext()) {
            String edges[] = in.nextLine().split(delim);
            int from = Integer.parseInt(edges[0]);
            int to = Integer.parseInt(edges[1]);
            double weight = Double.parseDouble(edges[2]);
            if(!adjMatrix[from][to]) {
                DirectedWeightedEdge edge = new DirectedWeightedEdge(from, to, weight);
                adjContainer[from].add(edge);
                adjMatrix[from][to] = true;
                e++;
            }
        }
        in.close();
        this.E = e;
        adj = adjContainer;
    }

    private EdgeWeightedDigraph(int V, int E, Bag<DirectedWeightedEdge>[] adj) {
        this.V = V;
        this.E = E;
        this.adj = adj;
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public Graph addVertex() {
        return createGraphWithNewVertex();
    }

    private EdgeWeightedDigraph createGraphWithNewVertex() {
        return createGraphWithNewVertexes(1);
    }

    @Override
    public Graph addVertex(int vertexCount) {
        return createGraphWithNewVertexes(vertexCount);
    }

    @SuppressWarnings("unchecked")
    private  EdgeWeightedDigraph createGraphWithNewVertexes(int vertexCount) {
        Bag<DirectedWeightedEdge>[] adjCopy = (Bag<DirectedWeightedEdge>[]) new Bag[V+vertexCount];
        for (int v = 0; v < V+vertexCount; v++)
            adjCopy[v] = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedWeightedEdge e : adj[v]) {
                DirectedWeightedEdge eCopy = new DirectedWeightedEdge(e);
                adjCopy[v].add(eCopy);
            }
        }
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V + vertexCount, E, adjCopy);
        return G;
    }

    @Override
    public Graph removeVertex(int v) {
        return null;
    }

    @Override
    public EdgeWeightedGraph addEdge(int v, int w, double weight) {
        return null;
    }

    @Override
    public Graph removeEdge(int v, int w) {
        return null;
    }

    @Override
    public Iterable<DirectedWeightedEdge> edges() {
        Bag<DirectedWeightedEdge> edges = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedWeightedEdge e : adj[v]) {
                DirectedWeightedEdge edge = new DirectedWeightedEdge(e);
                edges.add(edge);
            }
        return edges;
    }

    @Override
    public Iterable<DirectedWeightedEdge> adj(int v) {
        Bag<DirectedWeightedEdge> adjCopy = new Bag<>();
        for(DirectedWeightedEdge e : adj[v]) {
            DirectedWeightedEdge edge = new DirectedWeightedEdge(e);
            adjCopy.add(edge);
        }
        return adjCopy;
    }

    @Override
    public Bag<DirectedWeightedEdge>[] getAdjCopy() {
        Bag<DirectedWeightedEdge>[] adjCopy = (Bag<DirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adjCopy[v] = new Bag<>();
            for(DirectedWeightedEdge e : adj[v]) {
                DirectedWeightedEdge eCopy = new DirectedWeightedEdge(e);
                adjCopy[v].add(eCopy);
            }
        }
        return adjCopy;
    }

    @Override
    public Iterable<DirectedWeightedEdge> nbsI(int v) {
        return null;
    }

    @Override
    public Iterable<DirectedWeightedEdge> nbsO(int v) {
        return adj(v);
    }

    @Override
    public EdgeWeightedDigraph reverse() {
        Bag<DirectedWeightedEdge>[] adjNew =  (Bag<DirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adjNew[v] = new Bag<>();
        for(int v = 0; v < V; v++)
            for (DirectedWeightedEdge e : adj[v])
                adjNew[v].add(e.reverse());
        return new EdgeWeightedDigraph(V, E, adjNew);
    }

    @Override
    public boolean containsEdge(int v, int w) {
        for (DirectedWeightedEdge e : adj[v]) {
            if (e.checkVertex(v, w))
                return true;
        }
        return false;
    }

    public boolean containsEdge(DirectedWeightedEdge edge) {
        int v = edge.from();
        for (DirectedWeightedEdge e : adj[v])
            if (e.checkVertex(edge))
                return true;
        return false;
    }

    public int inDegree(int v) {
        return 0;
    }

    public int outDegree(int v) {
        return adj[v].size();
    }

    @Override
    public DepthFirstOrder executeDepthFirstOrder() {
        return new DepthFirstOrder(new EdgeWeightedDigraph(V, E, adj));
    }

    @Override
    public TopologicalSort executeTopologicalSort() {
        return new TopologicalSort(new EdgeWeightedDigraph(V, E, adj));
    }

    @Override
    public Dijkstra executeDijkstra(int s) {
        return new DirectedEdgeDijkstra(new EdgeWeightedDigraph(V, E, adj), s);
    }

    @Override
    public DepthFirstSearch executeDepthFirstSearch(int s) {
        return new DepthFirstSearch(new EdgeWeightedDigraph(V, E, adj), s);
    }

    @Override
    public BreadthFirstSearch executeBreadthFirstSearch(int s) {
        return new BreadthFirstSearch(new EdgeWeightedDigraph(V, E, adj), s);
    }

    @Override
    public Cycle executeCycle() {
        return new Cycle(new EdgeWeightedDigraph(V, E, adj));
    }

    @Override
    public StronglyConnectedComponents executeStronglyConnectedComponents() {
        return new StronglyConnectedComponents(new EdgeWeightedDigraph(V, E, adj));
    }


}
