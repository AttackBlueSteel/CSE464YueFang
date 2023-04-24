import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class RandomWalk extends GraphSearchAlgorithm implements GraphSearch {

    public RandomWalk(Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected void addNodeToQueue(Deque<Path> queue, String node) {
        Path path = new Path();
        path.append(node);
        queue.add(path);
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
                queue.add(path);
            }
        }

        // shuffle the queue to implement random walk
        List<Path> list = new ArrayList<>(queue);
        Collections.shuffle(list);
        queue.clear();
        queue.addAll(list);
    }
}
