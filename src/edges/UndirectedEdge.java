package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public interface UndirectedEdge extends Edge {
    int either();
    int other(int vertex);
}
