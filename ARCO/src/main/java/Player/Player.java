package Player;

import Element.Controller;
import world.GameObject;
import world.GameOverlay;
import world.ViewPort;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    public int centerX;
    public int centerY;

    ViewPort viewPort;

    public ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    public ArrayList<GameOverlay> overlayList = new ArrayList<GameOverlay>();

    public Player(ViewPort view, ArrayList<GameObject> triggerList, ArrayList<GameOverlay> spriteList){
        this.viewPort = view;
        this.objectList = triggerList;
        this.overlayList = spriteList;
        this.objectList.add(new GameObject("Player", 0, 0, 100, 100, Color.BLACK, viewPort, true));
        this.objectList.add(new GameObject("Hat", 0, -75, 50, 50, Color.GREEN, viewPort, true));

        double totalArea = 0;
        double sumX = 0;
        double sumY = 0;

        for(GameObject element : objectList){
            double area = element.rect.getWidth() * element.rect.getHeight();
            totalArea += area;
            sumX += (element.rect.getCenterX() * area);
            sumY += (element.rect.getCenterY() * area);
        }
        this.centerX = (int)(sumX / totalArea);
        this.centerY = (int)(sumY / totalArea);
    }
    public void update(){
        double totalArea = 0;
        double sumX = 0;
        double sumY = 0;
        for(GameObject trigger : objectList){
            double area = trigger.rect.getWidth() * trigger.rect.getHeight();
            totalArea += area;
            sumX += (trigger.rect.getCenterX() * area);
            sumY += (trigger.rect.getCenterY() * area);
        }
        this.centerX = (int)(sumX / totalArea);
        this.centerY = (int)(sumY / totalArea);

    }
    public void tick(int delta_x, int delta_y){

        for(GameObject trigger : objectList) {

            trigger.x -= delta_x;
            trigger.y -= delta_y;

        }
        update();

        if(delta_y != 0){
        this.viewPort.position_y -= (this.centerY - viewPort.viewport_height/2);
        }
        if(delta_x != 0){
        this.viewPort.position_x -= (this.centerX - viewPort.viewport_width/2);
        }
    }
}
