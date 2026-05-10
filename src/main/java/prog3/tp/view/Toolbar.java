package prog3.tp.view;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Toolbar extends JToolBar {
    private ToolbarButton _localityButton;
    private ToolbarButton _connectionsButton;
    private ToolbarButton _helpButton;
    private ToolbarListener _listener;

    public Toolbar(ToolbarListener listener) {
        _listener = listener;
        this.setFloatable(false);
        initButtons();
    }

    private void initButtons() {
        initLocalityButton();
        initConnectionsButton();
        initHelpButton();

        this.add(_localityButton);
        this.add(_connectionsButton);
        this.addSeparator();
        this.add(_helpButton);
    }

    private void initLocalityButton() {
        _localityButton = new ToolbarButton("");
        _localityButton.setToolTipText("Add new locality.");
        _localityButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addNewLocality();
                    }
        });
    }

    private void addNewLocality() {
        LocalityDialogPane dialog = new LocalityDialogPane();

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            _listener.onLocalityAdded(dialog.getName(), dialog.getProvince(),
                    dialog.getLatitude(), dialog.getLongitude());
        }
    }

    private void initConnectionsButton() {
        _connectionsButton = new ToolbarButton("󱕆");
        _connectionsButton.setToolTipText("Generate connections for localities in the map.");
        _connectionsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        genConnections();
                    }
                });
    }

    private void genConnections() {
        GenConnectionsPane dialog = new GenConnectionsPane();

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            _listener.onConnectionsGenerated(dialog.getKilometerCost(), dialog.getPercentageCost(), dialog.getProvincesCost());
        }

    }

    private void initHelpButton() {
        _helpButton = new ToolbarButton("󰋖");
        _helpButton.setToolTipText("Show usage help.");
        _helpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showHelp();
                    }
                });
    }

    private void showHelp() {
        JOptionPane.showOptionDialog(
                null,
                "Use the right mouse to move the map,\n"
                        + "use mouse wheel to zoom.",
                "Help window",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                null);
   }
}
