package prog3.tp;

import java.awt.EventQueue;
import prog3.tp.model.LocalityRedServices;
import prog3.tp.presenter.Presenter;
import prog3.tp.view.Window;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        try {
                            Window window = new Window();
                            window.setVisible(true);
                            new Presenter(new LocalityRedServices(),
                                    window);
                        } catch (Exception e) {
                            System.out.println(
                                    "Error initializing the system: " + e);
                        }
                    }
                });
    }
}
