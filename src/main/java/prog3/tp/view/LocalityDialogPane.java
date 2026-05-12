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

        this.add(new JLabel("Nombre: "));
        this.add(_nameField);
        this.add(new JLabel("Provincia: "));
        this.add(_provinceField);
        this.add(new JLabel("Latitud: "));
        this.add(_latitudeField);
        this.add(new JLabel("Longitud: "));
        this.add(_longitudeField);
    }

    public int showDialog() {
        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    this,
                    "New locality",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return result;
            }

            String latitudeText = _latitudeField.getText().trim();
            String longitudeText = _longitudeField.getText().trim();

            if (latitudeText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La latitud no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                Double.parseDouble(latitudeText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La latitud debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (longitudeText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La longitud no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                Double.parseDouble(longitudeText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La longitud debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            return result;
        }
    }

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
