package Player;

import world.GamePanel;
import world.GameObject;
import world.ViewPort;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Resolve {
    GamePanel gamePanel;

    ArrayList<GameObject> triggerList;
    public Resolve(GamePanel panel, ArrayList<GameObject> colliderList) {
        this.gamePanel = panel;
        this.triggerList = colliderList;

    }

    public boolean solveX(int dx) {
        for(GameObject trigger : this.triggerList){

            Rectangle local_test_rectangle_x = new Rectangle(trigger.rect);
            local_test_rectangle_x.x -= (dx);

            for (GameObject obj : gamePanel.components){
                if(local_test_rectangle_x.intersects(obj.rect)) return false;
            }

        }
        return true;
    }

    public boolean solveY(int dy) {

        for (GameObject trigger : this.triggerList){

            Rectangle local_test_rectangle_y = new Rectangle(trigger.rect);
            local_test_rectangle_y.y -= dy;
            for (GameObject obj : gamePanel.components){
                if(local_test_rectangle_y.intersects(obj.rect)) return false;
            }
        }
        return true;
    }
}
