package prog3.tp.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeightedGraphTest {

    private WeightedGraph _graph;
    private int _graph_size = 10;

    @Before
    public void setUp() {
        _graph = new WeightedGraph(_graph_size);
    }

    @Test
    public void createWeightedGraphTest() {
        assertNotNull(new WeightedGraph());
        assertNotNull(_graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWeightedGraphNegativeIndicesTest() {
        new WeightedGraph(-1);
    }

    @Test
    public void sizeWeightedGraphTest() {
        assertEquals(_graph_size, _graph.size());
    }

    @Test
    public void addWeightedEdgeTest() {
        assertTrue(_graph.addEdge(2, 5, 10.5));
    }

    @Test
    public void addOutOfBoundsWeightedEdgeTest() {
        assertFalse(_graph.addEdge(_graph_size * 10, _graph_size * 20, 15));
        assertFalse(_graph.addEdge(-5, -10, 15));
    }

    @Test
    public void addWeightedEdgeForSameVertexTest() {
        assertFalse(_graph.addEdge(4, 4, 20));
    }

    @Test
    public void weightedEdgeExistTest() {
        _graph.addEdge(1, 2, 30);

        assertTrue(_graph.edgeExists(1, 2));
        assertTrue(_graph.edgeExists(2, 1));
    }

    @Test
    public void weightedEdgeNotExistTest() {
        assertFalse(_graph.edgeExists(1, 2));
    }

    @Test
    public void getWeightTest() {
        _graph.addEdge(3, 7, 55.7);

        assertEquals(55.7, _graph.getWeight(3, 7), 0.001);
    }

    @Test
    public void getSymmetricWeightTest() {
        _graph.addEdge(3, 7, 55.7);

        assertEquals(55.7, _graph.getWeight(7, 3), 0.001);
    }

    @Test
    public void getWeightOfNonExistingEdgeTest() {
        assertEquals(0.0, _graph.getWeight(1, 4), 0.001);
    }

    @Test
    public void deleteWeightedEdgeTest() {
        _graph.addEdge(2, 8, 12);

        assertTrue(_graph.deleteEdge(2, 8));
        assertFalse(_graph.edgeExists(2, 8));
    }

    @Test
    public void deleteOutOfBoundsWeightedEdgeTest() {
        assertFalse(_graph.deleteEdge(_graph_size * 10, _graph_size * 20));
        assertFalse(_graph.deleteEdge(-5, -4));
    }

    @Test
    public void overwriteWeightTest() {

        _graph.addEdge(1, 2, 10);

        _graph.deleteEdge(1, 2);

        _graph.addEdge(1, 2, 50);

        assertEquals(50, _graph.getWeight(1, 2), 0.001);
    }
}