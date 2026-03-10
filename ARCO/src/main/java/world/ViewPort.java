package world;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ViewPort extends JPanel {
    public final int max_width = 10_000;
    public final int max_height = 10_000;

    public int viewport_width;
    public int viewport_height;

    public int position_x;
    public int position_y;


    public ViewPort(int frameWidth, int frameHeight){
        this.viewport_width = frameWidth;
        this.viewport_height = frameHeight;


    }

}
