package world;

import Element.Element;
import Player.Controls;
import Player.Movement;
import Player.Player;
import Player.Resolve;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLoop extends JPanel implements ActionListener {

    public long lastTime = System.nanoTime();
    GamePanel gamePanel;

    Controls controls;
    Movement movement;
    Resolve resolve;
    Player player;

    public GameLoop(GamePanel panel){
        new Timer(16, this).start();
        this.gamePanel = panel;
        this.controls = new Controls(this.gamePanel);
        this.controls.createControls();
        this.player = new Player(this.gamePanel.viewPort,(new ArrayList<GameObject>()), (new ArrayList<GameOverlay>()));

        this.resolve = new Resolve(this.gamePanel, player.objectList);

        this.movement = new Movement(this.controls, resolve, player,500, 1_250,0.987, -987);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.nanoTime();
        double dt = (now - lastTime) / 1_000_000_000.0; // 1 000 000 000 or jus ONE BILLION
        lastTime = now;
        if (dt > 0.05){
            dt = 0.05;
        }

        this.movement.tick(dt);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GameObject gameObject : gamePanel.components) {
            gameObject.render(g);
        }

//        for (Element element : gamePanel.elements){
//            for(GameObject trigger : element.objectList){
//                trigger.render(g);
//            }
//            for(GameOverlay sprite : element.overlayList){
//                sprite.render(g);
//            }
//        }

        for(GameObject obj : player.objectList){
            obj.render(g);
        }
        for(GameOverlay sprite : player.overlayList){
            sprite.render(g);
        }


        for (GameOverlay gameOverlay : gamePanel.decorations) {
            gameOverlay.render(g);
        }

    }

}
