package prog3.tp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import prog3.tp.presenter.Presenter;

public class Window implements View, JMapViewerEventListener, ToolbarListener {
    private static final int MAP_ZOOM_LEVEL = 10;
    private JMapViewer _map;
    private JFrame _frame;
    private Toolbar _toolbar;
    private Presenter _presenter;

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

        // NOTE: for testing drawing a line, it should draw this in an update
        // when it receives the msp
        Coordinate joseCPaz = new Coordinate(-34.516667, -58.766667);
        _map.addMapMarker(new MapMarkerDot("Buenos Aires", buenosAires));
        _map.addMapMarker(new MapMarkerDot("José C. Paz", joseCPaz));
        drawLineBetweenCoords(buenosAires, joseCPaz);
    }

    private void setUpToolbar() {
        _toolbar = new Toolbar(this);
    }

    private void drawLineBetweenCoords(Coordinate coord1, Coordinate coord2) {
        _map.addMapPolygon(new MapPolygonImpl(coord1, coord2, coord2));
    }


    @Override
    public void onLocalityAdded(String name, String province, double latitude, double longitude) {
        _presenter.addLocality(name, province, latitude, longitude);
    }

    @Override
    public void onConnectionsGenerated(double kilometerCost, double percentageCost, double provinceCost) {
        // TODO: make all the presenter -> model stuff from here
    	_presenter.configCosts(kilometerCost, percentageCost,provinceCost);
    }

    @Override
    public void updateView(String name, double latitude, double longitude) {
        Coordinate coord = new Coordinate(latitude, longitude);
        _map.addMapMarker(new MapMarkerDot(name, coord));
    }

    @Override
    public void processCommand(JMVCommandEvent command) {}

    @Override
    public void setPresenter(Presenter presenter) {
        _presenter = presenter;
    }
}
