package prog3.tp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocalityRedServices {
    private Locality[] _localities; // Vertex, add in view.
    private double[][] _matrix;
    private Map<Locality, Integer> _index;

    public LocalityRedServices(Locality[] localities) {
        int n = localities.length;

        this._localities = localities;
        this._matrix = new double[n][n];
        this._index = new HashMap<>();

        for (int i = 0; i < n; i++) {
            _index.put(localities[i], i);
        }
    }

    public void addEdge(
            Locality a,
            Locality b,
            double costPerKm,
            int percentIncreaseCostPer300km,
            double costPerTwoStates) {

        int i = getIndex(a);
        int j = getIndex(b);

        double km = kmBetween2Localities(a, b);
        double cost = calculateCost(costPerKm, percentIncreaseCostPer300km, costPerTwoStates, km);

        _matrix[i][j] = cost;
        _matrix[j][i] = cost;
    }

    private double kmBetween2Localities(Locality a, Locality b) {
        // ADD THE ALGORITH TO CALCULATE KM
        return 0;
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

    public double calculateCost(
            double costPerKm, int percentIncreaseCostPer300km, double costPerTwoStates, double km) {
        // ADD THE ALGORITH TO CALCULATE KM
        return 0;
    }
}
