package prog3.tp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import prog3.tp.presenter.Presenter;

public class LocalityRedServices implements Model {
    private Observer _observer;
	private final List<Locality> _localities; // Vertex, add in view.
    private final Map<Locality, Integer> _index;
    private final WeightedGraph _graph;	
    
	public LocalityRedServices () {		
		
	    this._localities = new ArrayList<>();
	    this._index = new HashMap<>();
	    this._graph = new WeightedGraph();

	}
	

	public void addEdge(Locality a, 
						Locality b, 
						double costPerKm, 
						int percentIncreaseMoreThan300km, 
						double costPerTwoStates) {
	   
		int i = getIndex(a); // Get locality num index to introduce in the matrix 
	    int j =	getIndex(b);
	    
	    double km = kmBetween2Localities(a, b);
	    double cost = 0;
	    
	    if(localitiesInOneState(a,b)) {
	    	cost =  calculateCost(costPerKm, percentIncreaseMoreThan300km, 0 , km);
	    } else cost = calculateCost(costPerKm, percentIncreaseMoreThan300km,costPerTwoStates,km);
	    
	    
	    _graph.addEdge(i,j,cost);
	}
	
	private boolean localitiesInOneState(Locality a, Locality b) {
		return a.getState().equalsIgnoreCase(b.getState()); 
	}
	

	private double kmBetween2Localities(Locality a, Locality b) {
		return Vincenty.distance(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
	}

	private int getIndex(Locality l) {
	    Integer i = _index.get(l);

	    if (i == null) {
	        throw new IllegalArgumentException("Locality not found: " + l);
	    }
	    return i;
	}

	public Set<Locality> getNeighbors(Locality locality) {
	    int numIndexLocality = getIndex(locality);

	    Set<Integer> neighbors = _graph.getNeighbors(numIndexLocality);
        Set<Locality> result = new HashSet<>();

        for (Integer numIndex : neighbors) {
            result.add(_localities.get(numIndex));
        }

        return result;
    }

	private double calculateCost(double costPerKm, int percentIncreaseMoreThan300km, double costPerTwoStates, double km) {
		double cost = 0;
		
		if(km >300) {
			cost =(costPerKm * km) * (1 + percentIncreaseMoreThan300km/ 100.0);
		} else cost = (costPerKm * km);
		
		return cost + costPerTwoStates;
	}
	
	public double getCost(Locality a, Locality b) {
	     return _graph.getWeight(getIndex(a), getIndex(b));
	}
	
	public void addLocality(Locality locality) {

	    if (_index.containsKey(locality)) {
	        return;
	    }

	    int index = _localities.size();

	    _localities.add(locality);

	    _index.put(locality, index);
        _observer.update(locality);
	}
	
	public WeightedGraph minimumSpanningTree() {
	    return Prim.mst(_graph);
	}

    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }
}
