package prog3.tp.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import prog3.tp.presenter.Presenter;

public class Window implements View, ToolbarListener {
    private static final int MAP_ZOOM_LEVEL = 10;
    private final List<MapPolygonImpl> _edges = new ArrayList<>();
    private JMapViewer _map;
    private JFrame _frame;
    private Toolbar _toolbar;
    private Presenter _presenter;
    private boolean _localityWasAdded = false;

    public Window() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception e) {
            System.out.println("Error setting native look: " + e);
        }
        initialize();
    }

    public void setVisible(boolean visibility) {
        _frame.setVisible(visibility);
    }

    private void initialize() {
        setUpFrame();
        setUpMap();
        setUpToolbar();

        _frame.add(_map, BorderLayout.CENTER);
        _frame.add(_toolbar, BorderLayout.PAGE_START);
    }

    private void setUpFrame() {
        _frame = new JFrame("Amazing Map Viewer");
        _frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLayout(new BorderLayout());
    }

    private void setUpMap() {
        Coordinate buenosAires = new Coordinate(-34.603889, -58.381389);
        _map = new JMapViewer();
        _map.setDisplayPosition(buenosAires, MAP_ZOOM_LEVEL);
        
    }

    private void setUpToolbar() {
        _toolbar = new Toolbar(this);
    }

    private void drawLineBetweenCoords(Coordinate coord1, Coordinate coord2) {
    	MapPolygonImpl line = new MapPolygonImpl(coord1, coord2, coord2);
    	_map.addMapPolygon(line);
        _edges.add(line);
    }


    @Override
    public void onLocalityAdded(String name, String province, double latitude, double longitude) {
        _presenter.addLocality(name, province, latitude, longitude);
        _localityWasAdded = true;
    }

    @Override
    public void onConnectionsGenerated(double kilometerCost, double percentageCost, double provinceCost) {
        if (!_localityWasAdded) return;

    	_presenter.connectionGenerate(kilometerCost, percentageCost,provinceCost);
    	double cost = _presenter.getCostMST();
    	this._toolbar.setCostMessage(cost);
    }
    
    private void deleteEdges() {
    	    for (MapPolygonImpl edge : _edges) {
    	        _map.removeMapPolygon(edge);
    	    }
    	    _edges.clear();
	}

	@Override
    public void drawEdges(List<Coordinate[]> edges){	
		deleteEdges();
		
    	for (Coordinate[] edge : edges) {
    	    drawLineBetweenCoords(edge[0], edge[1]);	
    	}
    }
    @Override
    public void updateView(String name, double latitude, double longitude) {
        Coordinate coord = new Coordinate(latitude, longitude);
        _map.addMapMarker(new MapMarkerDot(name, coord));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        _presenter = presenter;
    }
}
