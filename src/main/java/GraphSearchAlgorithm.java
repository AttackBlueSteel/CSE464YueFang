import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public abstract class GraphSearchAlgorithm {
    protected Graph<String, DefaultEdge> graph;

    public GraphSearchAlgorithm(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    protected abstract void addNodeToQueue(Deque<Path> queue,String node);

    protected abstract void addNeighborsToQueue(Path currPath, Deque<Path> queue, Set<String> visitedNodes);

    public Path search(String src, String dst) {
        Deque<Path> queue = new ArrayDeque<>();
        Set<String> visitedNodes = new HashSet<>();

        // add src node to queue
        addNodeToQueue(queue,src);

        while (!queue.isEmpty()) {
            Path currPath = queue.remove();

            if (visitedNodes.contains(currPath.getLastNode())) {
                continue;
            }

            visitedNodes.add(currPath.getLastNode());
            System.out.println("visiting Path: " + currPath);

            if (currPath.getLastNode().equals(dst)) {
                return currPath;
            }

            addNeighborsToQueue(currPath, queue, visitedNodes);
        }

        return null;
    }
}
