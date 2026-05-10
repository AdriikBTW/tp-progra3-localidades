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
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import prog3.tp.model.LocalityRedServices;
import prog3.tp.presenter.Presenter;

class Window implements View, JMapViewerEventListener, ToolbarListener {
    private static final int MAP_ZOOM_LEVEL = 10;
    private JMapViewer _map;
    private JFrame _frame;
    private Toolbar _toolbar;

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        try {
                            Window window = new Window();
                            window._frame.setVisible(true);
                            new Presenter(new LocalityRedServices(), window);
                        } catch (Exception e) {
                            System.out.println("Error displaying the theme: " + e);
                        }
                    }
                });
    }

    public Window() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception e) {
            System.out.println("Error setting native look: " + e);
        }
        initialize();
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

    @Override
    public void onLocalityAdded(String name, double latitude, double longitude) {
        Coordinate coord = new Coordinate(latitude, longitude);
        _map.addMapMarker(new MapMarkerDot(name, coord));
    }

    @Override
    public void onConnectionsGenerated(double kilometerCost, double percentageCost, double provinceCost) {
        // TODO: make all the presenter -> model stuff from here
    }

    @Override
    public void updateView() {}

    @Override
    public void processCommand(JMVCommandEvent command) {}

    @Override
    public void setPresenter(Presenter p) {}
}
