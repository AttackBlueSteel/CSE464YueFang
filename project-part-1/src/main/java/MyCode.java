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
    public String toString() {
        return ("The number of nodes: " + graph.vertexSet().size() + "\n" +
                "The label of the nodes: " + graph.vertexSet() + "\n" +
                "The number of edges: " + graph.edgeSet().size() + "\n" +
                "The Nodes and the edge directions of the edges: " + (graph.edgeSet() + "").replace(":", "->"));
    }

    public static void main(String[] args) throws IOException {
        MyCode myGraph = new MyCode();
        parseGraph("src/testFiles/dotGraph.dot");
        System.out.println(myGraph);
        outputGraph("src/testFiles/output.txt");
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
}
