package Game.Arco.Build.Elements;



import Game.Arco.Build.Element;
import Game.Arco.Build.Overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Element {
    double s_x = 0;
    double s_y = 0;
    double a = 450;
    double max_speed = 750;
    double drag = 0.956;
    double grav = -987;
//      this.move = new Move(gamePanel,player,solve,element,85,85,0.987, -987);
    boolean on_ground = false;
    boolean wDown = false;
    boolean sDown = false;
    boolean aDown = false;
    boolean dDown = false;
    boolean spaceDown = false;
    boolean shiftDown = false;
    double cool_down = 0.0;
    double counter_aux = 0.0;

    int current_frame;
    double frame_timer;

    double totalArea = 0;
    int centerX;
    int centerY;
    double sumX = 0;
    double sumY = 0;
    ArrayList<Overlay> frameList = new ArrayList<>();
    ArrayList<Element> elementArrayList = new ArrayList<>();


    public Player(){
    }

    public void createControls(JFrame frame) {

        frame.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        wDown = true;
                        break;
                    case KeyEvent.VK_S:
                        sDown = true;
                        break;
                    case KeyEvent.VK_A:
                        aDown = true;
                        break;
                    case KeyEvent.VK_D:
                        dDown = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        spaceDown = true;
                        break;
                    case KeyEvent.VK_SHIFT:
                        shiftDown = true;
                        break;
                }
            }

            @Override public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        wDown = false;
                        break;
                    case KeyEvent.VK_S:
                        sDown = false;
                        break;
                    case KeyEvent.VK_A:
                        aDown = false;
                        break;
                    case KeyEvent.VK_D:
                        dDown = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        spaceDown = false;
                        break;
                    case KeyEvent.VK_SHIFT:
                        shiftDown = false;
                        break;
                }
            }
        });
    }



    public void update(){

        double totalArea = 0;
        double sumX = 0;
        double sumY = 0;
        for(Overlay overlay : getOverlays()){
            Rectangle rect = overlay.getRect();
            double area = rect.getWidth() * rect.getHeight();
            totalArea += area;
            sumX += (rect.getCenterX() * area);
            sumY += (rect.getCenterY() * area);


        }
        this.centerX = (int)(sumX / totalArea);
        this.centerY = (int)(sumY / totalArea);

    }



    public void tick(int delta_x, int delta_y, double dt){

        if(delta_x != 0){
            frame_timer += dt;
            if(frame_timer >= 0.12){
                frame_timer = 0;
                if(current_frame > 3){
                    current_frame = 1;
                }
                getOverlays().getFirst().setImage(new ImageIcon(frameList.get(current_frame++).getImage()).getImage());
            }
        }
        if(delta_x == 0 && delta_y == 0 && dt == 0){
            current_frame = 2;
            getOverlays().getFirst().setImage(new ImageIcon(frameList.get(current_frame).getImage()).getImage());
        }

        for(Overlay sprite : getOverlays()) {
            sprite.setX(sprite.getX() - delta_x);
            sprite.setY(sprite.getY() - delta_y);

//            sprite.getRect().x -= delta_x;
//            sprite.getRect().y -= delta_y;
            System.out.println(delta_x);
            if(delta_x < 0){
                sprite.image_width = Math.abs(sprite.image_width);
            }
            else if(delta_x > 0){
                sprite.image_width = -(Math.abs(sprite.image_width));
            }



        }

        this.update();

        if(delta_y != 0){
            this.getViewPort().setPosition_y(this.getViewPort().getPosition_y() - ((this.centerY) - (this.getViewPort().getViewport_height() / 2)));
        }
        if(delta_x != 0){
            this.getViewPort().setPosition_x(this.getViewPort().getPosition_x() - ((this.centerX) - (this.getViewPort().getViewport_width() / 2)));
        }
    }


    public void movement(double dt) {

        if (dt > 0.05) dt = 0.05;
        if (dt <= 0) return;
        double target_X = 0.0;
        if (aDown && !dDown) {
            target_X = max_speed;
        }
        if (dDown && !aDown) {
            target_X = -max_speed;
        }

        if(!dDown && !aDown){

            tick(0,0,0);

        }
        double maxChange = a * dt;
        double diff_X = target_X - s_x;

        if (diff_X >  maxChange) diff_X =  maxChange;
        if (diff_X < -maxChange) diff_X = -maxChange;

        int prevX = getCenterX();
        int prevY = getCenterY();

        s_x += diff_X;
        int d_x = ((int)(Math.round(s_x * dt)));
        boolean can_move_x = solveX(d_x);
        if(can_move_x) {
            tick((int) (Math.round(s_x * dt)), 0,dt);
        }
        else {
            setCenterX(prevX);
            s_x = 0;
        }

        boolean can_move_y = false;

        if(on_ground){
            s_y = 0;
        } else {
            s_y += (grav * dt);
        }

        double d_y = (s_y * dt);

        if(d_y != 0){
            can_move_y = solveY((int)(Math.round(d_y)));
        }

        if(can_move_y)
        {
            tick(0, (int)(Math.round(d_y)), dt);
            on_ground = false;
        } else {
            setCenterY(prevY);
            on_ground = s_y < 0;
            s_y = 0;
        }
        if(s_y == 0 && on_ground ){
            jump();
        }
        if(shiftDown && cool_down >= 1){
            dash();
            shiftDown = false;
        }
        counter_aux -= dt;
        cool_down += dt;
        s_y *= drag;
        s_x *= drag;

    }

    void jump(){
        if(spaceDown){
            s_y = 550;
            on_ground = false;
        }
    }
    void dash(){
        if (dDown && !aDown) {
            s_x = -650;
            cool_down = 0;
            counter_aux = 0.5;
            return;
        }
        if (aDown && !dDown) {
            s_x = 650;
            cool_down = 0;
            counter_aux = 0.5;
            return;
        }
    }

    public boolean solveX(int dx) {
        Rectangle local_test_rectangle_x = new Rectangle(this.getOverlays().getFirst().getRect());
        local_test_rectangle_x.x -= (dx);
        for(Element element : elementArrayList){
            if(!(element instanceof Player)) {
                for (Overlay overlay : element.getOverlays()) {
                    if (local_test_rectangle_x.intersects((overlay.getRect()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean solveY(int dy) {
        Rectangle local_test_rectangle_y= new Rectangle(this.getOverlays().getFirst().getRect());
        local_test_rectangle_y.y -= (dy);
        for (Element element : elementArrayList) {
            if (!(element instanceof Player)) {
                for (Overlay overlay : element.getOverlays()) {
                    if (local_test_rectangle_y.intersects((overlay.getRect()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public int getCenterX() {
        return centerX;
    }
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    public int getCenterY() {
        return centerY;
    }
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    public void setFrameList(ArrayList<Overlay> frameList) {
        this.frameList = frameList;
    }
    public void setElementArrayList(ArrayList<Element> elementArrayList) {
        this.elementArrayList = elementArrayList;
    }

}