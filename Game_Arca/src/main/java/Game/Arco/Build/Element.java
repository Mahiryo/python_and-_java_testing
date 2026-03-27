package Game.Arco.Build;

import Game.Arco.GameFrame;
import Game.Arco.ViewPort;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Element {

    List<Overlay> overlays = new ArrayList<>();
    ViewPort viewPort;

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public Element() {
    }

    public List<Overlay> getOverlays() {
        return overlays;
    }

    public void addOverlay(Overlay overlay) {
        this.overlays.add(overlay);
    }

    public ViewPort getViewPort() {
        return viewPort;
    }
}
