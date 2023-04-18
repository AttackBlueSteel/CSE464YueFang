import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;


public class TestCodePart3 {
    static MyCode demo;
    @Before
    public void setup() throws Exception {
        demo = new MyCode();
        demo.parseGraph("src/testFiles/dotGraphPart3.dot");
    }
    @Test
    public void testDFSSearch() throws IOException {
        GraphSearchAlgorithm searchAlgorithm = new DFS(MyCode.graph);
        Path result = searchAlgorithm.search("v1","v6");
        Assert.assertEquals(result.toString(),"v1->v2->v5->v6");
    }
    @Test
    public void testBFSSearch() throws IOException {
        GraphSearchAlgorithm searchAlgorithm = new BFS(MyCode.graph);
        Path result = searchAlgorithm.search("v1","v6");
        Assert.assertEquals(result.toString(),"v1->v3->v6");
    }
}
