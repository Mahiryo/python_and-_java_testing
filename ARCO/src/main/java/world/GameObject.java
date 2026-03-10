package world;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class GameObject extends JPanel {
    public int x, y, w, h;
    ViewPort viewPort;
    public String name;
    Color color;
    boolean fill;
    public Rectangle rect;
    public GameObject(String nickname, int pos_x, int pos_y, int width, int height, Color color, ViewPort view,boolean isFill){
        this.viewPort = view;

        this.name = nickname;

        this.color = color;

        this.fill = isFill;

        this.x = (pos_x);
        this.y = (pos_y);

        this.w = width;
        this.h = height;

        this.rect = new Rectangle(
                ((x - w/2) + (viewPort.viewport_width/2) + viewPort.position_x),
                (((y - h/2) + (viewPort.viewport_height/2) + viewPort.position_y)) ,
                w,
                h);
    }

    public void render(Graphics g){
        g.setColor(color);
        this.rect = new Rectangle(
        ((x - w/2) + (viewPort.viewport_width/2) + viewPort.position_x),
        (((y - h/2) + (viewPort.viewport_height/2) + viewPort.position_y)) ,
        w,
        h);

        if(fill){
        g.fillRect(
        ((x - w/2) + (viewPort.viewport_width/2) + viewPort.position_x),
        (((y - h/2) + (viewPort.viewport_height/2) + viewPort.position_y)) ,
        w,
        h);
        }
        else{
        g.drawRect(
        ((x - w/2) + (viewPort.viewport_width/2) + viewPort.position_x),
        (((y - h/2) + (viewPort.viewport_height/2) + viewPort.position_y)) ,
        w,
        h);
        }

    }
}
