package prog3.tp.view;

import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import prog3.tp.presenter.Presenter;

public interface View {
    public void updateView(String name, double latitude, double longitude);

    public void setPresenter(Presenter p);

	public void drawEdges(List<Coordinate[]> edges);
}
