package prog3.tp.view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

class GenConnectionsPane extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private JSpinner _kilometerCost;
    private JSpinner _percentageCost;
    private JSpinner _provincesCost;

    public GenConnectionsPane() {
        super(new GridLayout(ROW, COL, HGAP, VGAP));

        _kilometerCost = SpinnerCreation.createSpinner(1.0, 1.0, 5.0);
        _percentageCost = SpinnerCreation.createSpinner(0.0, 0.0, 100.0, 5.0);
        _provincesCost = SpinnerCreation.createSpinner(1.0, 1.0, 5.0);

        this.add(new JLabel("Cost in ARS$ per kilometer: "));
        this.add(_kilometerCost);
        this.add(new JLabel("% increase for 300 km or more: "));
        this.add(_percentageCost);
        this.add(new JLabel("Fixed cost per connection between two provinces: "));
        this.add(_provincesCost);
    }

    public int showDialog() {
            return JOptionPane.showConfirmDialog(
                    null,
                    this,
                    "Generate connections",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
    }

    public double getKilometerCost() {
        return (double) _kilometerCost.getValue();
    }

    public double getPercentageCost() {
        return (double) _percentageCost.getValue();
    }

    public double getProvincesCost() {
        return (double) _provincesCost.getValue();
    }
}
