package prog3.tp.presenter;

import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import prog3.tp.model.Locality;
import prog3.tp.model.Model;
import prog3.tp.model.Observer;
import prog3.tp.view.View;

public class Presenter implements Observer {
    private final Model _model;
    private final View _view;

    public Presenter(Model model, View view) {
        _model = model;
        _view = view;

        _view.setPresenter(this);
        _model.addObserver(this);
    }

    public void addLocality(String name, String province, double latitude, double longitude) {
        _model.addLocality(new Locality(name, province, latitude, longitude));
    }

    @Override
    public void update(Locality locality) {
        _view.updateView(locality.getName(), locality.getLatitude(), locality.getLongitude());
    }

    public void configCosts(double kilometerCost, double percentageCost, double provinceCost) {
        _model.setCostConfig(kilometerCost, percentageCost, provinceCost);
    }

    public void connectLocalities() {
        _model.generateMST();
    }

    public void connectionGenerate(
            double kilometerCost, double percentageCost, double provinceCost) {
        this.configCosts(kilometerCost, percentageCost, provinceCost);
        this.connectLocalities();
        // Should the presenter import jmapviewer.coordinate?
        List<Coordinate[]> edges = _model.getMSTCoordinates();
        _view.drawEdges(edges);
    }

    public double getCostMST() {
        return _model.getCostMST();
    }

    public void clear() {
        _model.clear();
    }
}
