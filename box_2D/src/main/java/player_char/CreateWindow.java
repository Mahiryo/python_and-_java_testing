package player_char;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateWindow {
    static boolean wDown, sDown, aDown, dDown;
    public static PlayerPhysics player;
    public static JFrame frame;
    private static Thread gameThread;
    private static boolean running = true;
    public static double SPEED = 960;


    public static void main(String[] args){SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            CreatePanel();
        }
    });}
    public static void CreatePanel(){
        frame = new JFrame("DEMO");
        frame.setFocusable(true);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setSize(800,600);
        player = new PlayerPhysics(400,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(player);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> wDown = true;
                    case KeyEvent.VK_S -> sDown = true;
                    case KeyEvent.VK_A -> aDown = true;
                    case KeyEvent.VK_D -> dDown = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> wDown = false;
                    case KeyEvent.VK_S -> sDown = false;
                    case KeyEvent.VK_A -> aDown = false;
                    case KeyEvent.VK_D -> dDown = false;
                }
            }
        });
        gameThread = new Thread(() -> {
            while (running) {
                updateGame();
                player.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }
    public static void updateGame() {
        if (wDown) {
            player.applyForce(0, -SPEED);
            System.out.println("WWWW");
        }
        if (sDown) {
            player.applyForce(0, SPEED);
            System.out.println("SSSS");
        }
        if (aDown) {
            player.applyForce(-SPEED, 0);
            System.out.println("AAAA");
        }
        if (dDown) {
            player.applyForce(SPEED, 0);
            System.out.println("DDDD");
        }
    }
}
