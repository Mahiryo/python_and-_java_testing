import Player.Player;
import world.GameLoop;
import world.GameObject;
import world.GamePanel;

import javax.swing.*;
import java.awt.Dimension;

public class Window {
    JFrame frame;
    GamePanel panel;
    GameLoop loop;

    public Window(){
        frame = new JFrame("Arc");
        frame.setPreferredSize(new Dimension(1280,720));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new GamePanel(frame);
        frame.add(panel);

        loop = new GameLoop(panel);
        frame.add(loop);

        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
