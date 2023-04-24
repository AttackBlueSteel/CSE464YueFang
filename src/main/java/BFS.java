import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import java.util.Deque;
import java.util.Set;

public class BFS extends GraphSearchAlgorithm implements GraphSearch{

    public BFS(Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected void addNodeToQueue(Deque<Path> queue,String node) {
        Path path = new Path();
        path.append(node);
        queue.add(path);
    }

    @Override
    protected void addNeighborsToQueue(Path currPath, Deque<Path> queue, Set<String> visitedNodes) {
        String currVertex = currPath.getLastNode();
        for(String neighbor: Graphs.neighborListOf(graph,currVertex)) {
            if (!visitedNodes.contains(neighbor)) {
                Path path = Path.copyPath(currPath);
                path.append(neighbor);
                queue.add(path);
            }
        }
    }
}
