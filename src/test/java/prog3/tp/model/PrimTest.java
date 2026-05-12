package prog3.tp.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrimTest {

    private WeightedGraph _graph;

    @Before
    public void setUp() {
        _graph = new WeightedGraph(4);

        _graph.addEdge(0, 1, 10);
        _graph.addEdge(0, 2, 6);
        _graph.addEdge(0, 3, 5);
        _graph.addEdge(1, 3, 15);
        _graph.addEdge(2, 3, 4);
    }

    @Test
    public void createMinimumSpanningTreeTest() {

        WeightedGraph mst = Prim.mst(_graph, _graph.size());

        assertNotNull(mst);
    }

    @Test
    public void minimumSpanningTreeContainsCorrectEdgesTest() {

        WeightedGraph mst = Prim.mst(_graph, _graph.size());

        assertTrue(mst.edgeExists(2, 3));
        assertTrue(mst.edgeExists(0, 3));
        assertTrue(mst.edgeExists(0, 1));
    }

    @Test
    public void minimumSpanningTreeDoesNotContainIncorrectEdgesTest() {

        WeightedGraph mst = Prim.mst(_graph, _graph.size());

        assertFalse(mst.edgeExists(0, 2));
        assertFalse(mst.edgeExists(1, 3));
    }

    @Test
    public void minimumSpanningTreeHasCorrectWeightsTest() {

        WeightedGraph mst = Prim.mst(_graph, _graph.size());

        assertEquals(4, mst.getWeight(2, 3), 0.001);
        assertEquals(5, mst.getWeight(0, 3), 0.001);
        assertEquals(10, mst.getWeight(0, 1), 0.001);
    }

    @Test
    public void minimumSpanningTreeTotalCostTest() {

        WeightedGraph mst = Prim.mst(_graph, _graph.size());

        double total = mst.getWeight(2, 3) + mst.getWeight(0, 3) + mst.getWeight(0, 1);

        assertEquals(19, total, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void disconnectedGraphTest() {

        WeightedGraph disconnected = new WeightedGraph(4);

        disconnected.addEdge(0, 1, 10);

        Prim.mst(disconnected, disconnected.size());
    }

    @Test
    public void visualMinimumSpanningTreeTest() {

        WeightedGraph graph = new WeightedGraph(5);

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 10);
        graph.addEdge(1, 4, 10);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 10);

        System.out.println("===== ORIGINAL GRAPH =====");

        printGraph(graph);

        WeightedGraph mst = Prim.mst(graph, graph.size());

        System.out.println("\n===== MINIMUM SPANNING TREE =====");

        printGraph(mst);
    }

    private void printGraph(WeightedGraph graph) {

        for (int i = 0; i < graph.size(); i++) {

            for (int j = i + 1; j < graph.size(); j++) {

                if (graph.edgeExists(i, j)) {

                    System.out.println(i + " -- " + j + " | weight: " + graph.getWeight(i, j));
                }
            }
        }
    }
}
