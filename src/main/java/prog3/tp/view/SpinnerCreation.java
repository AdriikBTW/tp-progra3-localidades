package prog3.tp.view;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/** Spinner factory class */
class SpinnerCreation {
    /**
     * Create a JSpinner with min and max boundaries.
     *
     * @param initialValue default value
     * @param minValue minimum value that the spinner can accept
     * @param maxValue maximum value that the spinner can accept
     * @param step the jump between values
     * @return a JSpinner object
     */
    static JSpinner createSpinner(
            double initialValue, double minValue, double MaxValue, double step) {
        SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, MaxValue, step);
        return new JSpinner(model);
    }

    /**
     * Create a JSpinner with min and no max boundaries.
     *
     * @param initialValue default value
     * @param minValue minimum value that the spinner can accept
     * @param step the jump between values
     * @return a JSpinner object
     */
    static JSpinner createSpinner(double initialValue, double minValue, double step) {
        SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, null, step);
        return new JSpinner(model);
    }
}
