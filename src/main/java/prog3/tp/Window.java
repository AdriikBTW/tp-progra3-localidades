package prog3.tp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

public class Window implements View, JMapViewerEventListener {
    private JMapViewer _map;
    private JFrame _frame;
    private JToolBar _toolbar;

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
        _map = new JMapViewer();
        _map.setDisplayPosition(new Coordinate(-34.603889, -58.381389), 10);
    }

    private void setUpToolbar() {
        // NOTE: this is increasing in size, maybe it would be better to move it to a class?
        _toolbar = new JToolBar();
        _toolbar.setFloatable(false);

        JButton newLocalityButton = new JButton("");
        newLocalityButton.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
        newLocalityButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showOptionDialog(null, "Hello",
                                "Hello message", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, null,
                                null);
                    }
                });

        JButton helpButton = new JButton("󰋖");
        helpButton.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
        helpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showOptionDialog(null,
                                "Use the right mouse to move the map,\n"
                                        + "use mouse wheel to zoom.",
                                "Help window", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, null,
                                null);
                    }
                });

        _toolbar.add(newLocalityButton);
        _toolbar.addSeparator();
        _toolbar.add(helpButton);
    }

    @Override
    public void updateView() {}

    @Override
    public void processCommand(JMVCommandEvent command) {}

	@Override
	public void setPresenter(Presenter p) {
	}
}
