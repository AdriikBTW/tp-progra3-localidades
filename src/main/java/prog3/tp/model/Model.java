package prog3.tp.model;

public interface Model {
    public void addObserver(Observer observer);
	public void addLocality(Locality locality);
	public void setCostConfig(double kilometerCost, double percentageCost, double provinceCost);
}