package prog3.tp.model;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class LocalityRedServicesTest {

    private LocalityRedServices service;

    private Locality a;
    private Locality b;
    private Locality c;

    @Before
    public void setUp() {

        service = new LocalityRedServices();
        service.addObserver(locality -> {});

        service.setCostConfig(10, 20, 500);

        a = new Locality("A", "Buenos Aires", 0, 0);
        b = new Locality("B", "Buenos Aires", 0, 1);
        c = new Locality("C", "Cordoba", 1, 50);

        service.addLocality(a);
        service.addLocality(b);
        service.addLocality(c);

        service.generateAllEdges();
    }

    @Test
    public void addLocalityTest() {

        assertEquals(3, service.localityCount());
    }

    @Test
    public void generateAllEdgesTest() {

        service.generateAllEdges();

        assertTrue(service.getCost(a, b) > 0);
        assertTrue(service.getCost(a, c) > 0);
        assertTrue(service.getCost(b, c) > 0);
    }

    @Test
    public void neighborsTest() {

        service.generateAllEdges();

        Set<Locality> neighborsA = service.getNeighbors(a);

        assertTrue(neighborsA.contains(b));
        assertTrue(neighborsA.contains(c));
    }

    @Test
    public void costConsistencyTest() {

        service.generateAllEdges();

        double costAB = service.getCost(a, b);
        double costAC = service.getCost(a, c);

        assertTrue(costAB >= 0);
        assertTrue(costAC >= 0);
    }

    @Test
    public void minimumSpanningTreeTest() {

        service.generateAllEdges();

        WeightedGraph mst = service.minimumSpanningTree();

        assertNotNull(mst);

        // En un grafo de 3 nodos, MST debe tener 2 aristas
        int edges = 0;

        for (int i = 0; i < service.localityCount(); i++) {
            for (int j = i + 1; j < service.localityCount(); j++) {
                if (mst.edgeExists(i, j)) {
                    edges++;
                }
            }
        }

        assertEquals(2, edges);
    }

    @Test
    public void duplicateLocalityTest() {

        service.addLocality(a);

        assertEquals(3, service.localityCount());
    }

    @Test
    public void showGraphBeforeAndAfterPrim() {

        System.out.println("\n=====================");
        System.out.println("   ORIGINAL GRAPH");
        System.out.println("=====================");

        printOriginalGraph();

        System.out.println("\n=====================");
        System.out.println("   MINIMUM SPANNING TREE (PRIM)");
        System.out.println("=====================");

        WeightedGraph mst = service.minimumSpanningTree();

        printMST(mst);
    }

    private void printOriginalGraph() {

        System.out.println("A, B, C (complete graph):\n");

        printEdge(a, b);
        printEdge(a, c);
        printEdge(b, c);
    }

    private void printEdge(Locality x, Locality y) {

        System.out.println(
                x.getName() + " -- " + y.getName() + " | cost: " + service.getCost(x, y));
    }

    private void printMST(WeightedGraph mst) {

        int n = service.localityCount();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (mst.edgeExists(i, j)) {

                    System.out.println(
                            get(i).getName()
                                    + " -- "
                                    + get(j).getName()
                                    + " | cost: "
                                    + mst.getWeight(i, j));
                }
            }
        }
    }

    private Locality get(int i) {

        switch (i) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            default:
                return null;
        }
    }
}
