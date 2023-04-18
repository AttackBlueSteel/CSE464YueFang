import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Set;

public class DFS extends GraphSearchAlgorithm {
    public DFS(Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected void addNodeToQueue(Deque<Path> queue,String node) {
        Path path = new Path();
        path.append(node);
        queue.push(path);
    }

    @Override
    protected void addNeighborsToQueue(Path currPath, Deque<Path> queue, Set<String> visitedNodes) {
        String currVertex = currPath.getLastNode();

        List<String> neighbors = Graphs.neighborListOf(graph,currVertex);
        Collections.reverse(neighbors);
        for(String neighbor: neighbors) {
            if (!visitedNodes.contains(neighbor)) {
                Path path = Path.copyPath(currPath);
                path.append(neighbor);
                queue.push(path);
            }
        }
    }
}
