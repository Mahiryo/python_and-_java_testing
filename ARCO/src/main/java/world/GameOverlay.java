package world;

import javax.swing.*;
import java.awt.*;

public class GameOverlay extends JPanel {
    int x, y, w, h;
    ViewPort viewPort;
    String name;
    Image image;
    boolean isImage;
    Rectangle rect;

    public GameOverlay(String nickname, int pos_x, int pos_y, int width, int height, ViewPort view, Image sprite, boolean imageCheck){
        this.viewPort = view;
        this.image = sprite;
        this.name = nickname;

        this.x = pos_x;
        this.y = pos_y;

        this.w = width;
        this.h = height;
        this.isImage = imageCheck;
    }

    public void render(Graphics g){
        if(isImage){
            g.drawImage(this.image,
                    (x - w/2 + this.viewPort.viewport_width/2 +  this.viewPort.position_x),
                    (y - h/2 + this.viewPort.viewport_height/2 + this.viewPort.position_y),
                    (x - w/2 + this.viewPort.viewport_width/2 + w),
                    (y - h/2 + this.viewPort.viewport_height/2 + h),
                    null);

        } else {
            g.setColor(new Color(0, 0, 0, 120));
            g.drawRect(
                    ((x - w/2) + (this.viewPort.viewport_width/2) + this.viewPort.position_x),
                    ((y - h/2) + (this.viewPort.viewport_height/2) + this.viewPort.position_y),
                    w,
                    h);

        }
        this.rect = new Rectangle(
                (x - w/2 + this.viewPort.viewport_width/2 +     this.viewPort.position_x),
                (y - h/2 + this.viewPort.viewport_height/2 +    this.viewPort.position_y),
                (x - w/2 + this.viewPort.viewport_width/2 + w),
                (y - h/2 + this.viewPort.viewport_height/2 + h));
    }
}