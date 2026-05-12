package prog3.tp.model;

 class WeightedGraph extends Graph {
	private double[][] weights;
	
	
	  public WeightedGraph() {
	        super();
	        this.weights = new double[_DEFAULTSIZE][_DEFAULTSIZE];
	  }
	
	public WeightedGraph(int vertices) {
		super(vertices);
		this.weights = new double[vertices][vertices];
		
	}

	public boolean addEdge(int v1, int v2, double weight) {
		boolean alreadyExists = edgeExists(v1, v2);

		if (!alreadyExists) {
			boolean added = super.addEdge(v1, v2);
			if (!added) return false;
		}

	    weights[v1][v2] = weight;
	    weights[v2][v1] = weight;

	    return true;
	}
	
	public double getWeight(int v1, int v2) {
	    return weights[v1][v2];
	}
	
	@Override
	public boolean edgeExists(int v1, int v2) {
	    return super.edgeExists(v1, v2);
	}

	public void clear() {
		super.clear();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				weights[i][j] = 0.0;
			}
		}
	}

}
