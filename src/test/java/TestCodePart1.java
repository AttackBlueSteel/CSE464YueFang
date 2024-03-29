import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestCodePart1 {
    MyCode myGraph;

    @Before
    public void setup() throws Exception {
        myGraph = new MyCode();
        myGraph.parseGraph("src/testFiles/dotGraphPart1.dot");
    }
    @Test
    public void testFeature1() throws IOException {
        Assert.assertEquals(13,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
        Assert.assertTrue(myGraph.graph.containsEdge("0", "2"));
        myGraph.outputGraph("src/testFiles/outputPart1.txt");
    }

    @Test
    public void testFeature2() throws IOException{
        myGraph.addNode("test_node");
        Assert.assertEquals(14,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
        myGraph.removeNode("test_node");
        Assert.assertEquals(13,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
        myGraph.addNodes(new String[]{"test_node1", "test_node2", "test_node3"});
        Assert.assertEquals(16,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
        myGraph.removeNodes(new String[]{"test_node1", "test_node2", "test_node3"});
        Assert.assertEquals(13,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
    }

    @Test
    public void testFeature3() throws IOException{
        myGraph.addNodes(new String[]{"test_node1", "test_node2"});
        myGraph.addEdge("test_node1","test_node2");
        Assert.assertEquals(15,myGraph.graph.vertexSet().size());
        Assert.assertEquals(27,myGraph.graph.edgeSet().size());
        Assert.assertTrue(myGraph.graph.containsEdge("test_node1", "test_node2"));
        myGraph.removeEdge("test_node1","test_node2");
        myGraph.removeNodes(new String[]{"test_node1", "test_node2"});
        Assert.assertEquals(13,myGraph.graph.vertexSet().size());
        Assert.assertEquals(26,myGraph.graph.edgeSet().size());
        Assert.assertFalse(myGraph.graph.containsEdge("test_node1", "test_node2"));
    }

    @Test
    public void testFeature4() throws IOException{
        myGraph.outputDOTGraph("src/testFiles/outputPart1.dot");
        myGraph.outputGraphics("src/testFiles/outputPart1.jpg", "JPG");
        myGraph.outputGraphics("src/testFiles/outputPart1.png", "PNG");
        String output = Files.readString(Paths.get("src/testFiles/outputPart1.dot"));
        String expected = Files.readString(Paths.get("src/testFiles/expectedPart1.txt"));
        Assert.assertEquals(expected, output);
    }
}
