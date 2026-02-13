package player_char;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPhysics extends JPanel implements ActionListener {
    int p_x, p_y; // position X/Y in 2d Plane
    double s_x, s_y; // current speed inside the X/Y axis in the 2D Plane
    double a_x, a_y; // current acceleration inside the X/Y axis in the 2D Plane
    double friction = 0.985; // base resistance in all directions
    int height, width;
    Image img;

    public PlayerPhysics(int initialX, int initialY) {
        Timer timer = new Timer(16, this);
        timer.start();
        this.width = 50;
        this.height = 50;
        this.p_x = initialX;
        this.p_y = initialY;
//        setPreferredSize(new Dimension(800, 600));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        update(0.016);
        repaint();
    }

    public void applyForce(double forceX, double forceY){
        a_x += forceX;
        a_y += forceY;
    }
    public void update(double dt){
        s_x += a_x * dt;
        s_y += a_y * dt;

        p_x += (int) (s_x * dt);
        p_y += (int) (s_y * dt);

        s_x *= friction;
        s_y *= friction;

        a_x = 0;
        a_y = 0;

//        s_x *= (Math.pow(friction, 2));
//        s_y *= (Math.pow(friction, 2));
//        a_x *= (Math.pow(friction, 2));
//        a_y *= (Math.pow(friction, 2));

//        a_x *= friction;
//        a_y *= friction;

        if (((s_y * dt) + p_x) < 0 || ((s_x * dt) + p_x) >= (800 - width)) {
            s_x = (-s_x);
            a_x = (-a_x);
//            System.out.println("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK");
        }
        if (((s_y * dt) + p_y) < 0 || ((s_y * dt) + p_y) >= (600 - height)) {
            s_y = (-s_y);
            a_y = (-a_y);
//            System.out.println("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK");
        }

//        repaint(p_x, p_y, height, width);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        img = new ImageIcon("/home/marci/Área de trabalho/game_images/slaughter.png").getImage();
        g.drawImage(img,p_x,p_y,width, height, this);

    }
}

