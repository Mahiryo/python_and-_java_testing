package Game.Arco.Build;

import Game.Arco.ViewPort;

import java.awt.*;

public class Overlay{
    private int x, y, w, h;
    public int image_width;
    private String name;
    private Image image;
    private Rectangle rect;
    private ViewPort viewPort;
    public Overlay(){

    }

    public void render(Graphics g){
        this.rect = new Rectangle(
        ((x - w/2) + (viewPort.getViewport_width()/2) + viewPort.getPosition_x()),
        (((y - h/2) + (viewPort.getViewport_height()/2) + viewPort.getPosition_y())) ,
        w,
        h);

        g.drawImage(this.image,
        ((x - image_width/2) + (viewPort.getViewport_width()/2) + viewPort.getPosition_x()),
        (((y - h/2) + (viewPort.getViewport_height()/2) + viewPort.getPosition_y())) ,
        image_width,
        h,
        null);
    }


    public void setImage(Image image) {
        this.image = image;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return rect;
    }


}
