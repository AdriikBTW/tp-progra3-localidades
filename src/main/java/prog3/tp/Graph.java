package prog3.tp;

import java.util.HashSet;
import java.util.Set;

/** Undirected graph implementation using an adjacency matrix. */
public class Graph {
    private static int _DEFAULTSIZE = 100;
    private boolean[][] _matrix;

    /** Constructs a graph with a default capacity of 100 vertices. */
    public Graph() {
        _matrix = new boolean[_DEFAULTSIZE][_DEFAULTSIZE];
    }

    /**
     * Constructs a graph with the specified number of vertices.
     *
     * @param vertices Total positive vertices
     * @throws IllegalArgumentException if vertices are non-positive.
     */
    public Graph(int vertices) throws IllegalArgumentException {
        if (vertices <= 0) throw new IllegalArgumentException();
        _matrix = new boolean[vertices][vertices];
    }

    /**
     * Add an undirecter edge between vertex i and vertex j.
     *
     * @param v1 First vertex
     * @param v2 Second vertex
     * @return true if the edge was added. false if it already exists or vertex are out of bounds.
     */
    public boolean addEdge(int v1, int v2) {
        if (verticesAreEquals(v1, v2) || !vertexIsValid(v1) || !vertexIsValid(v2)) return false;

        _matrix[v1][v2] = true;
        _matrix[v2][v1] = true;

        return true;
    }

    /**
     * Get the neighbors of the vertex.
     *
     * @param vertex The vertex index.
     * @return a set containing all the neighbors of vertex.
     * @throws IllegalArgumentException if the vertex doesn't exist in the graph.
     */
    public Set<Integer> getNeighbors(int vertex) throws IllegalArgumentException {
        if (!vertexIsValid(vertex)) throw new IllegalArgumentException();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < _matrix.length; i++) if (edgeExists(vertex, i)) set.add(i);

        return set;
    }

    /**
     * Removes the edge between vertex i and vertex j.
     *
     * @param v1 First vertex
     * @param v2 Second vertex
     * @return true if the edge was removed. false if indices are out of bounds.
     */
    public boolean deleteEdge(int v1, int v2) {
        if (verticesAreEquals(v1, v2) || !vertexIsValid(v1) || !vertexIsValid(v2)) return false;

        _matrix[v1][v2] = false;
        _matrix[v2][v1] = false;

        return true;
    }

    /**
     * Gets the total number of vertices in the graph.
     *
     * @return Vertex count.
     */
    public int size() {
        return _matrix.length;
    }

    /**
     * Checks if the edge exist in the graph.
     *
     * @param v1 The first vertex index.
     * @param v2 The second vertex index.
     * @return true if the edge exists in the graph. false if doesn't exist or the vertices are
     *     invalid.
     */
    public boolean edgeExists(int v1, int v2) {
        if (verticesAreEquals(v1, v2) || !vertexIsValid(v1) || !vertexIsValid(v2)) return false;

        return _matrix[v1][v2];
    }

    /**
     * Checks if the vertex is a valid vertex for the graph.
     *
     * @param vertex The vertex to check.
     * @return true if vertex is positive and less than the total vertices of the graph. false if
     *     vertex is negative or bigger than the amount of vertices in the graph.
     */
    private boolean vertexIsValid(int vertex) {
        return vertex >= 0 && vertex < this.size();
    }

    /**
     * Checks if two vertex are equals.
     *
     * @param v1 First vertex.
     * @param v2 Second vertex.
     * @return true if both vertex are equals. false if not.
     */
    private boolean verticesAreEquals(int v1, int v2) {
        return v1 == v2;
    }
}
