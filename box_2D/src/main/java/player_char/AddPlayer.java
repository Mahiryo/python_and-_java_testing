package player_char;
//import player_char.PlayerPhysics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Dimension;

public class AddPlayer extends JPanel implements Runnable {

    private boolean wDown, sDown, aDown, dDown;
    private PlayerPhysics player;
    private Thread gameThread;

    public AddPlayer() {

        addKeyListener(new KeyAdapter() {
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


        // Start the game loop thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Simple game loop
        while (true) {
            updateGame(); // Update game logic based on key flags
            repaint();    // Request a repaint of the panel
            try {
                Thread.sleep(16); // Sleep for ~16ms for roughly 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        if (wDown) {
            player.applyForce(0, -10);
            System.out.println("WWWW");
        }
        if (sDown) {
            player.applyForce(0, 10);
            System.out.println("SSSS");
        }
        if (aDown) {
            player.applyForce(-10, 0);
            System.out.println("AAAA");
        }
        if (dDown) {
            player.applyForce(10, 0);
            System.out.println("DDDD");
        }
    }
}
