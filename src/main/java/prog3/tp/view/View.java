package prog3.tp.view;

import prog3.tp.presenter.Presenter;

public interface View {
    public void updateView(String name, double latitude, double longitude);

    public void setPresenter(Presenter p);
}
