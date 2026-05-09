package prog3.tp.presenter;

import prog3.tp.model.Observer;
import prog3.tp.model.Model;
import prog3.tp.view.View;

public class Presenter implements Observer {
    private final Model _model;
    private final View _view;

    public Presenter(Model model, View view) {
        _model = model;
        _view = view;

        _view.setPresenter(this);
        _model.addObserver(this);
    }

    @Override
    public void update() {}
}
