package prog3.tp.view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GenConnectionsPane extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private JTextField _kilometerCost;
    private JTextField _percentageCost;
    private JTextField _differentProvincesCost;

    public GenConnectionsPane() {
        super(new GridLayout(ROW, COL, HGAP, VGAP));

        _kilometerCost = new JTextField();
        _percentageCost = new JTextField();
        _differentProvincesCost = new JTextField();

        this.add(new JLabel("Costo en ARS$ por kilómetro: "));
        this.add(_kilometerCost);
        this.add(new JLabel("Incremento en porcentaje para 300 km o más: "));
        this.add(_percentageCost);
        this.add(new JLabel("Costo fijo por conexión entre dos provincias: "));
        this.add(_differentProvincesCost);
    }

    public int showDialog() {
        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    this,
                    "Generate connections",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return result;
            }

            String kmCostText = _kilometerCost.getText().trim();
            String percentCostText = _percentageCost.getText().trim();
            String provinceCostText = _differentProvincesCost.getText().trim();

            if (kmCostText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El costo por kilómetro no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (percentCostText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El incremento porcentual no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (provinceCostText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El costo entre provincias no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                double kmCost = Double.parseDouble(kmCostText);
                if (kmCost < 0) {
                    JOptionPane.showMessageDialog(null, "El costo por kilómetro no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                double percentCost = Double.parseDouble(percentCostText);
                if (percentCost < 0) {
                    JOptionPane.showMessageDialog(null, "El incremento porcentual no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                double provinceCost = Double.parseDouble(provinceCostText);
                if (provinceCost < 0) {
                    JOptionPane.showMessageDialog(null, "El costo entre provincias no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                return result;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Los valores deben ser numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public double getKilometerCost() {
        return Double.parseDouble(_kilometerCost.getText());
    }

    public double getPercentageCost() {
        return Double.parseDouble(_percentageCost.getText());
    }

    public double getProvincesCost() {
        return Double.parseDouble(_differentProvincesCost.getText());
    }
}
