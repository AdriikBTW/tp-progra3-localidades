package prog3.tp.view;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LocalityDialogPane extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private JTextField _nameField;
    private JTextField _latitudeField;
    private JTextField _longitudeField;
    private JComboBox<String> _provinceField;
    private String[] _provinces = {"Buenos Aires", "Catamarca", "Chaco",
        "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy",
        "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén",
        "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz",
        "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán"};

    public LocalityDialogPane() {
        super(new GridLayout(ROW, COL, HGAP, VGAP));

        _nameField = new JTextField();
        _latitudeField = new JTextField();
        _longitudeField = new JTextField();
        _provinceField = new JComboBox<>(_provinces);

        this.add(new JLabel("Name: "));
        this.add(_nameField);
        this.add(new JLabel("Province: "));
        this.add(_provinceField);
        this.add(new JLabel("Latitude: "));
        this.add(_latitudeField);
        this.add(new JLabel("Longitude: "));
        this.add(_longitudeField);
    }

    public int showDialog() {
        return JOptionPane.showConfirmDialog(
                null,
                this,
                "New locality",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    // TODO: handle limits for latitude and longitude

    public String getName() {
        return _nameField.getText();
    }

    public String getProvince() {
        return (String) _provinceField.getSelectedItem();
    }

    public double getLatitude() {
        return Double.parseDouble(_latitudeField.getText());
    }

    public double getLongitude() {
        return Double.parseDouble(_longitudeField.getText());
    }
}
