package prog3.tp.view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

class LocalityDialogPane extends ToolbarDialogPane {
    private JTextField _nameField;
    private JSpinner _latitudeField;
    private JSpinner _longitudeField;
    private JComboBox<String> _provinceField;
    private String[] _provinces = {"Buenos Aires", "Catamarca", "Chaco",
        "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy",
        "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén",
        "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz",
        "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán"};

    public LocalityDialogPane() {
        super("New locality");

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        _nameField = new JTextField();
        _latitudeField = SpinnerCreation.createSpinner(0.0, -90.0, 90.0, 5.0);
        _longitudeField = SpinnerCreation.createSpinner(0.0, -180.0, 180.0, 5.0);
        _provinceField = new JComboBox<>(_provinces);
    }

    @Override
    void addComponents() {
        this.add(new JLabel("Nombre: "));
        this.add(_nameField);
        this.add(new JLabel("Provincia: "));
        this.add(_provinceField);
        this.add(new JLabel("Latitud: "));
        this.add(_latitudeField);
        this.add(new JLabel("Longitud: "));
        this.add(_longitudeField);
    }

    public String getName() {
        return _nameField.getText();
    }

    public String getProvince() {
        return (String) _provinceField.getSelectedItem();
    }

    public double getLatitude() {
        return (double) _latitudeField.getValue();
    }

    public double getLongitude() {
        return (double) _longitudeField.getValue();
    }
}
