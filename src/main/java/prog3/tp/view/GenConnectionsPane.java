package prog3.tp.view;

import javax.swing.JLabel;
import javax.swing.JSpinner;

class GenConnectionsPane extends ToolbarDialogPane {
    private JSpinner _kilometerCost;
    private JSpinner _percentageCost;
    private JSpinner _provincesCost;

    public GenConnectionsPane() {
        super("Generate connections");

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        _kilometerCost = SpinnerCreation.createSpinner(1.0, 1.0, 5.0);
        _percentageCost = SpinnerCreation.createSpinner(0.0, 0.0, 100.0, 5.0);
        _provincesCost = SpinnerCreation.createSpinner(1.0, 1.0, 5.0);
    }

    @Override
    void addComponents() {
        this.add(new JLabel("Cost in ARS$ per kilometer: "));
        this.add(_kilometerCost);
        this.add(new JLabel("% increase for 300 km or more: "));
        this.add(_percentageCost);
        this.add(new JLabel("Fixed cost per connection between two provinces: "));
        this.add(_provincesCost);
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
