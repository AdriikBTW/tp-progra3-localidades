package prog3.tp.model;

import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public interface Model {
    public void addObserver(Observer observer);
	public void addLocality(Locality locality);
	public void setCostConfig(double kilometerCost, double percentageCost, double provinceCost);
	public void generateAllEdges();
	public void generateMST();
	public int lenghtMST();
	public List<Coordinate[]> getMSTCoordinates();
	public double getCostMST();
}