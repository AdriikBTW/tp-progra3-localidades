package prog3.tp;

public class LocalityRedServices {
	private double _costPerKM;
	private int _percentIncreaseCostPer300Km;
	private double _costPerTwoStates;
	private Locality[] localities; // Vertex, add in view.
	
	public LocalityRedServices (double costPerKm, int percentIncreaseCostPer300km, double costPerTwoStates) {
		this._costPerKM = costPerKm;
		this._percentIncreaseCostPer300Km = percentIncreaseCostPer300km;
		this._costPerTwoStates = costPerTwoStates;
	}
}
