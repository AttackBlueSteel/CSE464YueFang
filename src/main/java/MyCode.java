import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyCode {
    public static Graph<String, DefaultEdge> graph;

    public String toString() {
        return ("The number of nodes: " + graph.vertexSet().size() + "\n" +
                "The label of the nodes: " + graph.vertexSet() + "\n" +
                "The number of edges: " + graph.edgeSet().size() + "\n" +
                "The Nodes and the edge directions of the edges: " + (graph.edgeSet() + "").replace(":", "->"));
    }
    public static void main(String[] args) throws IOException {
    }
    public static void outputGraph(String filepath) throws IOException {
        String dotFormat = (new MyCode()).toString();
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(dotFormat);
        fileWriter.close();
    }

    public static void addNode(String node) {
        if(!graph.vertexSet().contains(node)) {
            graph.addVertex(node);
        }
    }

    public static void addNodes(String[] nodes) {
        for(int i = 0; i < nodes.length; i++){
            addNode(nodes[i]);
        }
    }

    public static void removeNode(String node) {
        if(graph.vertexSet().contains(node)) {
            Set<DefaultEdge> tempSet = graph.edgesOf(node);
            for(DefaultEdge edge: tempSet){
                graph.removeEdge(edge);
            }
            graph.removeVertex(node);
        }
    }

    public static void removeNodes(String[] nodes) {
        for(int i = 0; i < nodes.length; i++) {
            removeNode(nodes[i]);
        }
    }

    public static void addEdge(String node1, String node2) {
        if(!graph.containsEdge(node1, node2)) {
            graph.addEdge(node1, node2);
        }
    }

    public static void removeEdge(String node1, String node2) {
        if(graph.containsEdge(node1, node2)) {
            graph.removeEdge(node1, node2);
        }
    }

    public static void outputDOTGraph(String filepath) throws IOException {
        DOTExporter<String, DefaultEdge> dotExporter = new DOTExporter<>();
        Writer stringWriter = new StringWriter();
        dotExporter.exportGraph(graph, stringWriter);
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(stringWriter.toString());
        fileWriter.close();
    }

    public static void outputGraphics(String path, String format) throws IOException {
        JGraphXAdapter jgxAdapter = new JGraphXAdapter(graph);
        mxIGraphLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        BufferedImage image = mxCellRenderer.createBufferedImage(jgxAdapter, null, 1, Color.WHITE, true, null);
        ImageIO.write(image, format, new File(path));
    }
    public static void parseGraph(String filepath) throws IOException {
        String dotFormat = new String(Files.readAllBytes(Paths.get(filepath)));
        graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        DOTImporter<String, DefaultEdge> dotImporter = new DOTImporter<>();
        dotImporter.setVertexFactory(id->id);
        dotImporter.importGraph(graph, new StringReader(dotFormat));
    }
    public static Path GraphSearch(String src, String dst, Algorithm algorithm) throws IOException {
   
        switch(algorithm) {

            case DFS:
            Path tempPath = new Path();
            tempPath.append(src);
            while (tempPath.size() != 0) {
                String tempNode = tempPath.get(tempPath.size() - 1);
                if (tempNode.equals(dst)) {
                    return tempPath;
                }
                for (Object o : graph.vertexSet()) {
                    if (!((String) o).equals(tempNode) && graph.containsEdge(tempNode, (String) o)) {
                        tempPath.append((String) o);
                        break;
                    }
                }
            }
            Path emptyPath = new Path();
            return emptyPath;
            
            case BFS:
            List queue = new ArrayList();
            Path initPath = new Path();
            initPath.append(src);
            queue.add(initPath);
            while (!queue.isEmpty()) {
                Path tempPath2 = (Path) queue.get(0);
                queue.remove(0);
                String tempNode = tempPath2.get(tempPath2.size() - 1);
                if (tempNode.equals(dst)) {
                    return tempPath2;
                }
                for (Object o : graph.vertexSet()) {
                    if (!((String) o).equals(tempNode) && graph.containsEdge(tempNode, (String) o)) {
                        Path newPath = Path.copyPath(tempPath2);
                        newPath.append((String) o);
                        queue.add(newPath);
                    }
                }
            }
            Path emptyPath2 = new Path();
            return emptyPath2;
            
        }
        return null;
    }
}
