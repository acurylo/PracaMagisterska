package graphs;

import algorithms.*;
import collections.Bag;
import edges.UndirectedWeightedEdge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Amanda Curyło on 25.09.2017.
 */
public final class EdgeWeightedUndirectedGraph implements UndirectedGraph, EdgeWeightedGraph {
    private final int V;
    private final int E;
    private final Bag<UndirectedWeightedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedUndirectedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    @SuppressWarnings("unchecked")
    public EdgeWeightedUndirectedGraph(String filename, String delim) throws FileNotFoundException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        this.V = Integer.parseInt(in.nextLine());
        boolean[][] adjMatrix = new boolean[V][V];
        Bag<UndirectedWeightedEdge>[] adjContainer = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        int e = 0;
        for (int v = 0; v < V; v++) {
            adjContainer[v] = new Bag<UndirectedWeightedEdge>();
        }
        while (in.hasNext()) {
            String edges[] = in.nextLine().split(delim);
            int v = Integer.parseInt(edges[0]);
            int w = Integer.parseInt(edges[1]);
            double weight = Double.parseDouble(edges[2]);
            if(!adjMatrix[v][w]) {
                UndirectedWeightedEdge edge = new UndirectedWeightedEdge(v, w, weight);
                adjContainer[v].add(edge);
                adjContainer[w].add(edge);
                adjMatrix[v][w] = true;
                adjMatrix[w][v] = true;
                e++;
            }
        }
        in.close();
        this.E = e;
        adj = adjContainer;
    }

    private EdgeWeightedUndirectedGraph(int V, int E, Bag<UndirectedWeightedEdge>[] adj) {
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
    public EdgeWeightedUndirectedGraph addVertex() {
        return createGraphWithNewVertex();
    }

    private EdgeWeightedUndirectedGraph createGraphWithNewVertex() {
        return createGraphWithNewVertexes(1);
    }

    @Override
    public EdgeWeightedUndirectedGraph addVertex(int vertexCount) {
        return createGraphWithNewVertexes(vertexCount);
    }

    private EdgeWeightedUndirectedGraph createGraphWithNewVertexes(int vertexCount) {
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V+vertexCount];
        for (int v = 0; v < V+vertexCount; v++)
            adjCopy[v] = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (UndirectedWeightedEdge e : adj[v]) {
                UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
                adjCopy[v].add(eCopy);
            }
        }
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(V + vertexCount, E, adjCopy);
        return G;
    }

    @Override
    public EdgeWeightedUndirectedGraph removeVertex(int v) {
        if(V == 0) throw new IndexOutOfBoundsException("");
        return createGraphWithoutVertex(v);
    }

    private EdgeWeightedUndirectedGraph createGraphWithoutVertex(int v) {
        int newE = 0;
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V-1];
        for (int u = 0; u < V - 1; u++)
            adjCopy[u] = new Bag<>();
        Iterable<UndirectedWeightedEdge> edges = edges();
        for (UndirectedWeightedEdge e : edges) {
            int either = e.either();
            int other = e.other(either);
            if(either != v && other != v) {
                if(either > v) either--;
                if(other > v) other--;
                UndirectedWeightedEdge edge = new UndirectedWeightedEdge(either, other, e.weight());
                adjCopy[either].add(edge);
                adjCopy[other].add(edge);
                newE++;
            }
        }
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(V - 1, newE, adjCopy);
        return G;
    }

    @Override
    public EdgeWeightedUndirectedGraph addEdge(int v, int w, double weight) {
        if (containsEdge(v, w)) throw new IllegalArgumentException("Graph already contains this edge.");
        UndirectedWeightedEdge edge = new UndirectedWeightedEdge(v, w, weight);
        EdgeWeightedUndirectedGraph G = createGraphWithNewEdge(edge);
        return G;
    }

    public EdgeWeightedUndirectedGraph addEdge(UndirectedWeightedEdge edge) {
        if (containsEdge(edge)) throw new IllegalArgumentException("Graph already contains this edge.");
        EdgeWeightedUndirectedGraph G = createGraphWithNewEdge(edge);
        return G;
    }

    @SuppressWarnings("unchecked")
    private EdgeWeightedUndirectedGraph createGraphWithNewEdge(UndirectedWeightedEdge e) {
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adjCopy[v] = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (UndirectedWeightedEdge edge : adj[v]) {
                UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(edge);
                adjCopy[v].add(eCopy);
            }
        }
        UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
        int v = eCopy.either();
        int w = eCopy.other(v);
        adjCopy[v].add(eCopy);
        adjCopy[w].add(eCopy);
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(V, E + 1, adjCopy);
        return G;
    }

    public EdgeWeightedUndirectedGraph addEdges(Iterable<UndirectedWeightedEdge> edges) {
        EdgeWeightedUndirectedGraph G = createGraphWithNewEdges(edges);
        return G;
    }

    @SuppressWarnings("unchecked")
    private EdgeWeightedUndirectedGraph createGraphWithNewEdges(Iterable<UndirectedWeightedEdge> edges) {
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adjCopy[v] = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (UndirectedWeightedEdge e : adj[v]) {
                UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
                adjCopy[v].add(eCopy);
            }
        }
        int newE = 0;
        Bag<UndirectedWeightedEdge> existingEdges = new Bag<>();
        for (UndirectedWeightedEdge e : edges) {
            if (!containsEdge(e)) {
                UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
                int v = eCopy.either();
                int w = eCopy.other(v);
                adjCopy[v].add(eCopy);
                adjCopy[w].add(eCopy);
                newE++;
            }
            else existingEdges.add(new UndirectedWeightedEdge(e));
        }
        if (newE == 0) throw new IllegalArgumentException("Graph contains all of this edges");
        else {
            if (!existingEdges.isEmpty()) {
                System.out.println("Edges thaa graph already contained:");
                existingEdges.forEach(e -> System.out.println(e.between()));
            }
        }
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(V, E + newE, adjCopy);
        return G;
    }

    @Override
    public EdgeWeightedUndirectedGraph removeEdge(int v, int w) {
        if(!containsEdge(v, w)) throw  new IllegalArgumentException("Graph does not contain this edge.");
        EdgeWeightedUndirectedGraph G = createGraphWithoutEdge(v, w);
        return G;
    }

    @SuppressWarnings("unchecked")
    private EdgeWeightedUndirectedGraph createGraphWithoutEdge(int u, int w) {
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adjCopy[v] = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (UndirectedWeightedEdge e : adj[v]) {
                if (!e.checkVertex(u, w)) {
                    UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
                    adjCopy[v].add(eCopy);
                }
            }
        }
        EdgeWeightedUndirectedGraph G = new EdgeWeightedUndirectedGraph(V, E - 1, adjCopy);
        return G;
    }

    @Override
    public Iterable<UndirectedWeightedEdge> edges() {
        Bag<UndirectedWeightedEdge> edges = new Bag<>();
        for (int v = 0; v < V; v++)
            for (UndirectedWeightedEdge e : adj[v])
                if (e.other(v) > v) {
                    UndirectedWeightedEdge edge = new UndirectedWeightedEdge(e);
                    edges.add(edge);
                }
        return edges;
    }

    @Override
    public Iterable<UndirectedWeightedEdge> adj(int v) {
        Bag<UndirectedWeightedEdge> adjCopy = new Bag<UndirectedWeightedEdge>();
        for(UndirectedWeightedEdge e : adj[v]) {
            UndirectedWeightedEdge edge = new UndirectedWeightedEdge(e);
            adjCopy.add(edge);
        }
        return adjCopy;
    }

    @Override
    public Bag<UndirectedWeightedEdge>[] getAdjCopy() {
        Bag<UndirectedWeightedEdge>[] adjCopy = (Bag<UndirectedWeightedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adjCopy[v] = new Bag<>();
            for(UndirectedWeightedEdge e : adj[v]) {
                UndirectedWeightedEdge eCopy = new UndirectedWeightedEdge(e);
                adjCopy[v].add(eCopy);
            }
        }
        return adjCopy;
    }

    @Override
    public Iterable<UndirectedWeightedEdge> nbsI(int v) {
        return adj(v);
    }

    @Override
    public Iterable<UndirectedWeightedEdge> nbsO(int v) {
        return adj(v);
    }

    @Override
    public boolean isCompleteGraph() {  //jesli self loopy mozna to trzeba to uwzglednic
        return E == V*(V-1)/2;
    }

    @Override
    public boolean isTree() {
        if (E == V - 1 && executeConnectedComponents().isConnected())
            return true;
        else
            return false;
    }

    @Override
    public boolean containsEdge(int v, int w) {
        for (UndirectedWeightedEdge e : adj[v]) {
            if (e.checkVertex(v, w))
                return true;
        }
        return false;
    }

    public boolean containsEdge(UndirectedWeightedEdge edge) {
        int v = edge.either();
        for (UndirectedWeightedEdge e : adj[v])
            if (e.checkVertex(edge))
                return true;
        return false;
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (int v = 0; v < V; v++)
            if(degree(v) > max)
                max = degree(v);
        return max;
    }

    public int avgDegree() {
        return 2*E/V;
    }

    public LazyPrim executeLazyPrim() {
        return new LazyPrim(new EdgeWeightedUndirectedGraph(V, E, adj));
    }

    public Prim executePrim() {
        return new Prim(new EdgeWeightedUndirectedGraph(V, E, adj));
    }

    public Kruskal executeKruskal() {
        return new Kruskal(new EdgeWeightedUndirectedGraph(V, E, adj));
    }

    @Override
    public DepthFirstSearch executeDepthFirstSearch(int s) {
        return new DepthFirstSearch(new EdgeWeightedUndirectedGraph(V, E, adj), s);
    }

    @Override
    public BreadthFirstSearch executeBreadthFirstSearch(int s) {
        return new BreadthFirstSearch(new EdgeWeightedUndirectedGraph(V, E, adj), s);
    }

    @Override
    public Cycle executeCycle() {
        return new Cycle(new EdgeWeightedUndirectedGraph(V, E, adj));
    }

    @Override
    public Dijkstra executeDijkstra(int s) {
        return new UndirectedEdgeDijkstra(new EdgeWeightedUndirectedGraph(V, E, adj), s);
    }

    @Override
    public ConnectedComponents executeConnectedComponents() {
        return new ConnectedComponents(new EdgeWeightedUndirectedGraph(V, E, adj));
    }

}
