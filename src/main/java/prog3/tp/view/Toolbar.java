package prog3.tp.view;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

class Toolbar extends JToolBar {
    private ToolbarButton _localityButton;
    private ToolbarButton _connectionsButton;
    private ToolbarButton _deleteAllLocalities;
    private ToolbarButton _helpButton;
    private ToolbarButton _costButton;
    private ToolbarListener _listener;
    private double _costMessage;

    public Toolbar(ToolbarListener listener) {
        _listener = listener;
        this.setFloatable(false);
        initButtons();
    }

    private void initButtons() {
        initLocalityButton();
        initConnectionsButton();
        initDeleteAllLocalities();
        initHelpButton();
        initStartButton();

        this.add(_localityButton);
        this.add(_connectionsButton);
        this.addSeparator();
        this.add(_deleteAllLocalities);
        this.addSeparator();
        this.add(_helpButton);
        this.add(Box.createHorizontalGlue());
        this.add(_costButton);
    }

    void setFontForButtons(Font font) {
        _localityButton.setFont(font);
        _connectionsButton.setFont(font);
        _deleteAllLocalities.setFont(font);
        _helpButton.setFont(font);
        _costButton.setFont(font);
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

    private void initDeleteAllLocalities() {
        _deleteAllLocalities = new ToolbarButton("󰆴");
        _deleteAllLocalities.setToolTipText("Delete all marks in the map.");
        _deleteAllLocalities.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showDeleteAllConfirmation();
                    }
                });
    }

    private void showDeleteAllConfirmation() {
        int res = JOptionPane.showOptionDialog(null,
                "Do you want to delete all marks in the map?", "Delete all",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                null, null);
        if (res == JOptionPane.OK_OPTION) _listener.onAllLocalitiesDeleted();
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
    
    private void initStartButton() { 
    	 _costButton = new ToolbarButton("$");
         _costButton.setToolTipText("Muestra el precio total del costo");
         _costButton.addActionListener(
                 new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         showCost();
                     }
                 });
	}
    
    private void showCost() {
		DecimalFormat df = new DecimalFormat("$#,##0.000");
		String formattedCost = df.format(_costMessage);
        JOptionPane.showOptionDialog(
                null,
                "El precio total del costo es:\n"
                        + formattedCost,
                "Cost window",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                null);
   }
    
    public void setCostMessage(double cost) {
    	this._costMessage = cost;
    }

}
