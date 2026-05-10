package prog3.tp.view;

import java.awt.Font;
import javax.swing.JButton;

class ToolbarButton extends JButton {
    public ToolbarButton(String text) {
        super(text);
        this.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
    }
}
