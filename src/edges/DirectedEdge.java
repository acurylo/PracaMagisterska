package edges;

/**
 * Created by Amanda Curyło on 21.09.2017.
 */
public interface DirectedEdge extends Edge{
    int from();
    int to();
    DirectedEdge reverse();
}
