package prog3.tp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class LocalityRedServices implements Model, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Observer _observer;
	private final List<Locality> _localities; // Vertex, add in view.
    private final Map<Locality, Integer> _index;
    private final WeightedGraph _graph;
    private WeightedGraph _mst;
    private double _costPerKm;
    private double _percentIncreaseMoreThan300km;
    private double _costPerTwoStates;
    
	public LocalityRedServices () {		
		
	    this._localities = new ArrayList<>();
	    this._index = new HashMap<>();
	    this._graph = new WeightedGraph();

	}
	
	public void addEdge(Locality a, Locality b) {

	    int i = getIndex(a);
	    int j = getIndex(b);

	    double km = kmBetween2Localities(a, b);

	    double cost;

	    if (localitiesInOneState(a, b)) {
	        cost = calculateCost(km,0);
	    } else {
	        cost = calculateCost(km, this._costPerTwoStates);
	    }

	    _graph.addEdge(i, j, cost);
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

	private double calculateCost(double km, double extraStateCost) {
		double cost = 0;
		
		if(km >300) {
			cost =(this._costPerKm * km) * (1 + this._percentIncreaseMoreThan300km/ 100.0);
		} else cost = (this._costPerKm * km);
		
		return cost + extraStateCost;
	}
	
	public double getCost(Locality a, Locality b) {
	     return _graph.getWeight(getIndex(a), getIndex(b));
	}
	
	@Override
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
	    return Prim.mst(_graph, localityCount());
	}
	
	@Override
	public void generateAllEdges() {
		_graph.clear();

		for (int i = 0; i < _localities.size(); i++) {
			for (int j = i + 1; j < _localities.size(); j++) {
				Locality a = _localities.get(i);
				Locality b = _localities.get(j);
				addEdge(a,b);
			}
		}
	}
	
	
	//The default size of graph is 100**2, this method return the real size of the graph 
	public int localityCount() {
	    return _localities.size();
	}
	
	@Override
	public void setCostConfig(double costPerKm, double percentIncreaseMoreThan300km, double costPerTwoStates) {

		this._costPerKm = costPerKm;
		this._percentIncreaseMoreThan300km = percentIncreaseMoreThan300km;
		this._costPerTwoStates = costPerTwoStates;
	}
	
	@Override
	public void generateMST() {
		 this.generateAllEdges();
		 this._mst = this.minimumSpanningTree();
	}
	
	public int lenghtMST() {
		return this._mst.size();
	}
	
	@Override
	public List<Coordinate[]> getMSTCoordinates() {

	    List<Coordinate[]> edges = new ArrayList<>();

	    if (_mst == null) return edges;

	    for (int i = 0; i < this.localityCount(); i++) {
	        Set<Integer> neighbors = _mst.getNeighbors(i);

	        Locality a = _localities.get(i);

	        for (Integer j : neighbors) {
	            if (j > i) {
	                Locality b = _localities.get(j);

	                Coordinate c1 = new Coordinate(a.getLatitude(), a.getLongitude());
	                Coordinate c2 = new Coordinate(b.getLatitude(), b.getLongitude());

	                edges.add(new Coordinate[]{c1, c2});
	            }
	        }
	    }

	    return edges;
	}

	
    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }

	@Override
	public double getCostMST() {
		double totalCost = 0.0;

		if (_mst == null) {
			return totalCost;
		}

		for (int i = 0; i < _mst.size(); i++) {
			Set<Integer> neighbors = _mst.getNeighbors(i);
			for (Integer j : neighbors) {
				if (j > i) {
					totalCost += _mst.getWeight(i, j);
				}
			}
		}

		return totalCost;
	}
}
