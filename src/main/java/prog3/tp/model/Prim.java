package prog3.tp.model;

public class Prim {
	 public static WeightedGraph mst(WeightedGraph graph) {

	        int n = graph.size();

	        WeightedGraph mst = new WeightedGraph(n);

	        boolean[] visited = new boolean[n];

	        visited[0] = true;

	        int edgesAdded = 0;

	        while (edgesAdded < n - 1) {

	            double minWeight = Double.MAX_VALUE;

	            int from = -1;
	            int to = -1;

	            for (int i = 0; i < n; i++) {

	                if (!visited[i]) continue;

	                for (int j = 0; j < n; j++) {

	                    if (visited[j]) continue;

	                    if (!graph.edgeExists(i, j)) continue;

	                    double weight = graph.getWeight(i, j);

	                    if (weight < minWeight) {
	                        minWeight = weight;
	                        from = i;
	                        to = j;
	                    }
	                }
	            }

	            if (from == -1 || to == -1) {
	                throw new IllegalStateException("Graph is disconnected");
	            }

	            mst.addEdge(from, to, minWeight);

	            visited[to] = true;

	            edgesAdded++;
	        }

	        return mst;
	    }
}
