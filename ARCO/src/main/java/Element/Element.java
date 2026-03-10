package Element;

import Player.Movement;
import Player.Resolve;
import world.GameObject;
import world.GameOverlay;

import java.util.ArrayList;

public class Element {

    Movement movement;
    Resolve resolve;

    public ArrayList<GameObject> objectList;
    public ArrayList<GameOverlay> overlayList;

    public Element(Movement mov, Resolve solve, ArrayList<GameObject> triggerList, ArrayList<GameOverlay> spriteList){
        this.movement = mov;
        this.resolve = solve;
        this.objectList = triggerList;
        this.overlayList = spriteList;
    }


    public void update(double dt){
        double totalArea = 0;
        double sumX = 0;
        double sumY = 0;

        for(GameObject element : objectList){
            double area = element.rect.getWidth() * element.rect.getHeight();
            totalArea += area;
            sumX += (element.rect.getCenterX() * area);
            sumY += (element.rect.getCenterY() * area);
        }

        int centerX = (int)(sumX / totalArea);
        int centerY = (int)(sumY / totalArea);





        movement.tick(dt);
    }
}
