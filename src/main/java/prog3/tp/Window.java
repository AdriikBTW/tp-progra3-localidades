package prog3.tp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

public class Window implements View, JMapViewerEventListener {
    private JMapViewer _map;
    private JFrame _frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        try {
                            Window window = new Window();
                            window._frame.setVisible(true);
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
        _map = new JMapViewer();
        _map.setDisplayPosition(new Coordinate(-34.603889, -58.381389), 10);

        setUpFrame();
        _frame.add(_map, BorderLayout.CENTER);
    }

    private void setUpFrame() {
        _frame = new JFrame("Amazing Map Viewer");
        _frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLayout(new BorderLayout());
    }

    @Override
    public void updateView() {}

    @Override
    public void processCommand(JMVCommandEvent command) {}
}
