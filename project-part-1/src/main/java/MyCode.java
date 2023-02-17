import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public static void addNode(String label) {
        if(!graph.vertexSet().contains(label)) {
            graph.addVertex(label);
        }
    }

    public static void addNodes(String[] label) {
        for(int i = 0; i < label.length; i++){
            addNode(label[i]);
        }
    }

    public static void removeNode(String label) {
        if(graph.vertexSet().contains(label)) {
            graph.removeVertex(label);
        }
    }

    public static void removeNodes(String[] label) {
        for(int i = 0; i < label.length; i++) {
            removeNode(label[i]);
        }
    }



}
