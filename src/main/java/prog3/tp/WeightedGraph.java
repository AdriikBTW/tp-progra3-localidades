package prog3.tp;

public class WeightedGraph extends Graph {
	private double[][] weights;
	
	public WeightedGraph(int vertices) {
		super(vertices);
		this.weights = new double[vertices][vertices];
		
	}

	public boolean addEdge(int v1, int v2, double weight) {
		boolean added = super.addEdge(v1, v2); // Validations

	    if (!added) return false;

	    weights[v1][v2] = weight;
	    weights[v2][v1] = weight;

	    return true;
	}
	
	public double getWeight(int v1, int v2) {
	    return weights[v1][v2];
	}
	
	@Override
	public boolean edgeExists(int v1, int v2) {
	    return weights[v1][v2] > 0;
	}
	
}
