import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestCode {
    static MyCode demo;
    @Before
    public void setup() throws Exception {
        demo = new MyCode();
        demo.parseGraph("src/testFiles/dotGraph.dot");
    }
    @Test
    public void testDFSSearch() throws IOException {
        Path result = demo.GraphSearch("v1","v6",Algorithm.DFS);
        Assert.assertEquals(result.toString(),"v1->v2->v5->v6");
    }
    @Test
    public void testBFSSearch() throws IOException {
        Path result = demo.GraphSearch("v1","v6",Algorithm.BFS);
        Assert.assertEquals(result.toString(),"v1->v3->v6");
    }
}
