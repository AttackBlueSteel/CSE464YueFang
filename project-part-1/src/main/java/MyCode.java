import com.mxgraph.layout.*;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.dot.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MyCode {
    static Graph<String, DefaultEdge> graph;
    @Override
    //print the information of the graph
    public String toString() {
        return ("The number of nodes: " + graph.vertexSet().size() + "\n" +
                "The label of the nodes: " + graph.vertexSet() + "\n" +
                "The number of edges: " + graph.edgeSet().size() + "\n" +
                "The Nodes and the edge directions of the edges: " + (graph.edgeSet() + "").replace(":", "->"));
    }

    //for testing
    public static void main(String[] args) throws IOException {
        //feature 1
        MyCode myGraph = new MyCode();
        parseGraph("src/testFiles/dotGraph.dot");
        System.out.println(myGraph);
        outputGraph("src/testFiles/output.txt");
        //feature 2
        addNode("test_node");
        System.out.println("after adding a node:\n" + myGraph);
        removeNode("test_node");
        System.out.println("after removing a node:\n" + myGraph);
        addNodes(new String[]{"test_node1", "test_node2", "test_node3"});
        System.out.println("after adding three nodes:\n" + myGraph);
        removeNodes(new String[]{"test_node1", "test_node2", "test_node3"});
        System.out.println("after removing three nodes:\n" + myGraph);
        //feature 3,for displaying, we first add two new nodes
        addNodes(new String[]{"test_node1", "test_node2"});
        addEdge("test_node1","test_node2");
        System.out.println("after adding an edge:\n" + myGraph);
        removeEdge("test_node1","test_node2");
        System.out.println("after removing an edge:\n" + myGraph);
        removeNodes(new String[]{"test_node1", "test_node2"});
        //feature 4
        outputDOTGraph("src/testFiles/output.dot");
        outputGraphics("src/testFiles/output.jpg", "JPG");
        outputGraphics("src/testFiles/output.png", "PNG");
    }

    public static void parseGraph(String filepath) throws IOException {
        String dot_format = new String(Files.readAllBytes(Paths.get(filepath)));
        graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        DOTImporter<String, DefaultEdge> dot_importer = new DOTImporter<>();
        dot_importer.setVertexFactory(id->id);
        dot_importer.importGraph(graph, new StringReader(dot_format));
    }

    public static void outputGraph(String filepath) throws IOException {
        String dot_format = (new MyCode()).toString();
        FileWriter file_writer = new FileWriter(filepath);
        file_writer.write(dot_format);
        file_writer.close();
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
        DOTExporter<String, DefaultEdge> dot_exporter = new DOTExporter<>();
        Writer string_writer = new StringWriter();
        dot_exporter.exportGraph(graph, string_writer);
        FileWriter file_writer = new FileWriter(filepath);
        file_writer.write(string_writer.toString());
        file_writer.close();
    }

    public static void outputGraphics(String path, String format) throws IOException {
        JGraphXAdapter jgxAdapter = new JGraphXAdapter(graph);
        mxIGraphLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        BufferedImage image = mxCellRenderer.createBufferedImage(jgxAdapter, null, 1, Color.WHITE, true, null);
        ImageIO.write(image, format, new File(path));
    }
}
