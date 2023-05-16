import com.zubayear.customds.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphTest {

    Graph<Integer> graph;

    @BeforeEach
    public void init() {
        graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
    }

    @Test
    public void shouldPrintGraph() {
        graph.printGraph();
    }

    @Test
    public void shouldReturnBFSResult() {
        List<Integer> result = graph.bfs(1);
        System.out.println(result);
    }

    @Test
    public void shouldReturnDFSResult() {
        List<Integer> result = graph.dfs(1);
        System.out.println(result);
    }
}
