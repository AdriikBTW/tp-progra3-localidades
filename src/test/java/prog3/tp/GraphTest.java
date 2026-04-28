package prog3.tp;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {
    private Graph _graph;
    private int _graph_size = 10;

    @Before
    public void setUp() {
        _graph = new Graph(_graph_size);
    }

    @Test
    public void createGraphTest() {
        assertNotNull(new Graph());
        assertNotNull(_graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createGraphNegativeIndicesTest() {
        new Graph(-1);
    }

    @Test
    public void sizeGraphTest() {
        assertEquals(_graph_size, _graph.size());
    }

    @Test
    public void addEdgeTest() {
        assertTrue(_graph.addEdge(5, 4));
    }

    @Test
    public void addOutOfBoundsEdgeTest() {
        assertFalse(_graph.addEdge(_graph_size * 10, _graph_size * 20));
        assertFalse(_graph.addEdge(-5, -10));
    }

    @Test
    public void addEdgeForSameVertexTest() {
        assertFalse(_graph.addEdge(4, 4));
    }

    @Test
    public void deleteEdgeTest() {
        _graph.addEdge(4, 3);
        assertTrue(_graph.deleteEdge(4, 3));
    }

    @Test
    public void deleteOutOfBoundsEdgeTest() {
        assertFalse(_graph.deleteEdge(_graph_size * 10, _graph_size * 20));
        assertFalse(_graph.deleteEdge(-5, -4));
    }

    @Test
    public void edgeExistTest() {
        _graph.addEdge(5, 4);
        assertTrue(_graph.edgeExists(5, 4));
    }

    @Test
    public void edgeNotExistTest() {
        assertFalse(_graph.edgeExists(5, 4));
    }

    @Test
    public void checkEdgeOutOfBounds() {
        assertFalse(_graph.edgeExists(5, 4));
        assertFalse(_graph.edgeExists(-5, -4));
    }

    @Test
    public void getNeighborsOfVertexTest() {
        _graph.addEdge(5, 1);
        _graph.addEdge(5, 2);
        _graph.addEdge(5, 3);
        _graph.addEdge(5, 4);

        Set<Integer> testSet = new HashSet<>();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        testSet.add(4);

        Set<Integer> graphSet = _graph.getNeighbors(5);

        for (Integer i : graphSet) assertTrue(testSet.contains(i));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNeighborsOfNotExistingVertexTest() {
        _graph.getNeighbors(_graph_size * 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNeighborsOfNegativeVertexTest() {
        _graph.getNeighbors(-5);
    }
}
