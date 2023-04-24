import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphSearchStrategy {
    public static Path GraphSearch(Graph<String, DefaultEdge> graph, String src, String dst, Algorithm algo){
        GraphSearch searchStrategy;
        switch (algo) {
            case BFS:
                searchStrategy = new BFS(graph);
                break;
            case DFS:
                searchStrategy = new DFS(graph);
                break;
            case RANDOM_WALK:
                searchStrategy = new RandomWalk(graph);
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algo);
        }

        return searchStrategy.search(src,dst);
    }
}
