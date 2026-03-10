package Element;

import world.GameObject;
import world.GamePanel;
import world.ViewPort;

import java.awt.*;

public class Resolve {
    GamePanel gamePanel;
    ViewPort viewPort;
    Rectangle local_rect;
    public Resolve(GamePanel panel) {
        this.gamePanel = panel;
        this.viewPort = this.gamePanel.viewPort;
//        this.local_rect = this.gamePanel.viewPort.rect;
    }

    public boolean solveX(int dx) {
        Rectangle local_test_rectangle_x = new Rectangle(this.local_rect);
        local_test_rectangle_x.x -= (dx);

        for (GameObject obj : gamePanel.components){
            if(local_test_rectangle_x.intersects(obj.rect)) return false;
        }

        return true;
    }

    public boolean solveY(int dy) {
        Rectangle local_test_rectangle_y = new Rectangle(this.local_rect);
        local_test_rectangle_y.y -= dy;
        for (GameObject obj : gamePanel.components){
            if(local_test_rectangle_y.intersects(obj.rect)) return false;
        }
        return true;
    }
}
