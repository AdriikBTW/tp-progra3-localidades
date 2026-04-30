package prog3.tp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocalityRedServices {
	private double _costPerKM;
	private int _percentIncreaseCostPer300Km;
	private double _costPerTwoStates;
	private Locality[] _localities; // Vertex, add in view.
    private double[][] _matrix;
    private Map<Locality, Integer> _index;
	
	public LocalityRedServices (double costPerKm, int percentIncreaseCostPer300km, double costPerTwoStates, Locality[] localities) {
		this._costPerKM = costPerKm;
		this._percentIncreaseCostPer300Km = percentIncreaseCostPer300km;
		this._costPerTwoStates = costPerTwoStates;
		
	    int n = localities.length;

	    this._localities = localities;
	    this._matrix = new double[n][n];
	    this._index = new HashMap<>();

	    for (int i = 0; i < n; i++) {
	        _index.put(localities[i], i);
	    }
	}
	
	public void addEdge(Locality a, Locality b, double weight) {
		
	    int i = getIndex(a);
	    int j =	getIndex(b);

	    _matrix[i][j] = weight;
	    _matrix[j][i] = weight;
	}
	
	private int getIndex(Locality l) {
	    Integer i = _index.get(l);

	    if (i == null) {
	        throw new IllegalArgumentException("Locality not found: " + l);
	    }

	    return i;
	}
	
	public Set<Locality> getNeighbors(Locality l) {

	    int i = getIndex(l);

	    Set<Locality> result = new HashSet<>();

	    for (int j = 0; j < _matrix.length; j++) {
	        if (_matrix[i][j] > 0) {
	            result.add(_localities[j]);
	        }
	    }

	    return result;
	}
}
