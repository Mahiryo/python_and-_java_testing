package Game.Arco.Build.Elements;



import Game.Arco.Build.Element;
import Game.Arco.Build.Overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tile extends Element {

    int current_frame;
    double frame_timer;

    double totalArea = 0;
    int centerX;
    int centerY;
    double sumX = 0;
    double sumY = 0;
    ArrayList<Overlay> frameList = new ArrayList<>();
    JPanel jPanel;

    public Tile(){
//        for(Overlay overlay : this.getOverlays()){
//            double area = overlay.getRect().getWidth() * overlay.getRect().getHeight();
//            totalArea += area;
//            sumX += (overlay.getRect().getCenterX() * area);
//            sumY += (overlay.getRect().getCenterY() * area);
//        }
//        this.centerX = (int)(sumX / totalArea);
//        this.centerY = (int)(sumY / totalArea);
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

}
