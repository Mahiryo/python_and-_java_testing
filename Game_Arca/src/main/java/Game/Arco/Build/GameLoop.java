package Game.Arco.Build;


import Game.Arco.Build.Elements.Player;
import Game.Arco.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLoop extends JPanel implements ActionListener {
    long lastTime = System.nanoTime();
    ViewPort viewPort;
    ArrayList<Element> elementArrayList = new ArrayList<>();
    public GameLoop(){}

    public void start_this(){
        new Timer(16, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.nanoTime();
        double dt = (now - lastTime) / 1_000_000_000.0; // 1 000 000 000 or jus ONE BILLION
        lastTime = now;
        if (dt > 0.05){
            dt = 0.05;
        }
        for(Element element : elementArrayList){
            if(element instanceof Player){
//                System.out.println("AAAA");
                ((Player) element).movement(dt);
            }
        }

        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Element element : elementArrayList){
            for(Overlay overlay : element.getOverlays()){
                overlay.render(g);
            }
        }
    }

    public void addElement(Element element) {
        this.elementArrayList.add(element);
        System.out.println(this.elementArrayList);
    }

    public ArrayList<Element> getElementArrayList() {
        return elementArrayList;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

}

