package Game.Arco;

import Game.Arco.Build.Elements.Player;
import Game.Arco.Build.Elements.Tile;
import Game.Arco.Build.GameLoop;
import Game.Arco.Build.Overlay;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    GameLoop gameLoop;
    JFrame frame;
    Tile tile_1;
    Tile tile_2;
    Tile tile_3;
    Tile tile_4;
    Tile tile_5;
    Tile tile_6;
    Tile tile_7;
    Tile tile_8;
    Tile tile_9;
    Tile tile_0;
    Overlay tileOverlay;
    Overlay tileOverlay_2;
    Overlay tileOverlay_3;
    Overlay tileOverlay_4;
    Overlay tileOverlay_5;
    Overlay tileOverlay_6;
    Overlay tileOverlay_7;
    Overlay tileOverlay_8;
    Overlay tileOverlay_9;
    Overlay tileOverlay_0;
    ViewPort viewPort;
    Player player;
    Overlay Frame_01 = new Overlay();
    Overlay Frame_02 = new Overlay();
    Overlay Frame_03 = new Overlay();
    Overlay Frame_04 = new Overlay();


    public GameFrame(){
        this.frame = new JFrame("Arc");
        this.frame.setPreferredSize(new Dimension(1280,720));
        this.frame.pack();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.gameLoop = new GameLoop();

        this.viewPort = new ViewPort(frame.getWidth(), frame.getHeight());

        this.gameLoop.setViewPort(viewPort);

        this.player = new Player();

        this.player.setViewPort(viewPort);
        this.Frame_01.setH(50);
        this.Frame_01.setW(26);
        this.Frame_01.image_width = 26;
        this.Frame_01.setName("frame_01");
        this.Frame_01.setViewPort(viewPort);
        this.Frame_01.setImage(new ImageIcon("/home/marci/IdeaProjects/Game_Arca/src/main/resources/Imagens/prot/update/protag_0001.png").getImage());

        this.Frame_02.setH(50);
        this.Frame_02.setW(26);
        this.Frame_02.image_width = 26;
        this.Frame_02.setName("frame_02");
        this.Frame_02.setViewPort(viewPort);
        this.Frame_02.setImage(new ImageIcon("/home/marci/IdeaProjects/Game_Arca/src/main/resources/Imagens/prot/update/protag_0002.png").getImage());

        this.Frame_03.setH(50);
        this.Frame_03.setW(26);
        this.Frame_03.image_width = 26;
        this.Frame_03.setName("frame_03");
        this.Frame_03.setViewPort(viewPort);
        this.Frame_03.setImage(new ImageIcon("/home/marci/IdeaProjects/Game_Arca/src/main/resources/Imagens/prot/update/protag_0003.png").getImage());

        this.Frame_04.setH(50);
        this.Frame_04.setW(26);
        this.Frame_04.image_width = 26;
        this.Frame_04.setName("frame_04");
        this.Frame_04.setViewPort(viewPort);
        this.Frame_04.setImage(new ImageIcon("/home/marci/IdeaProjects/Game_Arca/src/main/resources/Imagens/prot/update/protag_0004.png").getImage());

        this.player.addOverlay(Frame_01);

        this.player.setViewPort(viewPort);

        ArrayList<Overlay> sprites = new ArrayList<>();

        sprites.add(Frame_01);
        sprites.add(Frame_02);
        sprites.add(Frame_03);
        sprites.add(Frame_04);

        player.setFrameList(sprites);

        player.createControls(frame);


        tile_1 = new Tile();
        tile_1.setjPanel(gameLoop);

        tileOverlay = new Overlay();
        tileOverlay.setH(64);
        tileOverlay.setW(128);
        tileOverlay.image_width = 128;
//        tileOverlay.setX(0);
//        tileOverlay.setY(100);
        tileOverlay.setName("ccccc");
        tileOverlay.setViewPort(viewPort);
        tileOverlay.setImage(new ImageIcon("src/main/resources/Imagens/Sprite_Tile/middle_center_til.png").getImage());
        tile_1.addOverlay(tileOverlay);
        tile_1.getOverlays().getFirst().setY(100);
        tile_1.getOverlays().getFirst().setX(0);



        tile_2 = new Tile();
        tile_2.setjPanel(gameLoop);

        tileOverlay_2 = new Overlay();
        tileOverlay_2.setH(64);
        tileOverlay_2.setW(128);
        tileOverlay_2.image_width = 128;
        tileOverlay_2.setName("bbbb");
        tileOverlay_2.setViewPort(viewPort);
        tileOverlay_2.setImage(new ImageIcon("src/main/resources/Imagens/Sprite_Tile/middle_center_til.png").getImage());
        tile_2.addOverlay(tileOverlay_2);
        tile_2.getOverlays().getFirst().setY(100);
        tile_2.getOverlays().getFirst().setX(128);



        tile_3 = new Tile();
        tile_3.setjPanel(gameLoop);

        tileOverlay_3 = new Overlay();
        tileOverlay_3.setH(64);
        tileOverlay_3.setW(128);
        tileOverlay_3.image_width = 128;
        tileOverlay_3.setName("aaaa");
        tileOverlay_3.setViewPort(viewPort);
        tileOverlay_3.setImage(new ImageIcon("src/main/resources/Imagens/Sprite_Tile/middle_center_til.png").getImage());
        tile_3.addOverlay(tileOverlay_3);
        tile_3.getOverlays().getFirst().setY(100);
        tile_3.getOverlays().getFirst().setX(-128);

        tile_4 = new Tile();
        tile_4.setjPanel(gameLoop);

        tileOverlay_4 = new Overlay();
        tileOverlay_4.setH(64);
        tileOverlay_4.setW(128);
        tileOverlay_4.image_width = 128;
        tileOverlay_4.setName("aaaa");
        tileOverlay_4.setViewPort(viewPort);
        tileOverlay_4.setImage(new ImageIcon("src/main/resources/Imagens/Sprite_Tile/left_end_tile.png").getImage());
        tile_4.addOverlay(tileOverlay_4);
        tile_4.getOverlays().getFirst().setY(100);
        tile_4.getOverlays().getFirst().setX(256);

        tile_5 = new Tile();
        tile_5.setjPanel(gameLoop);

        tileOverlay_5 = new Overlay();
        tileOverlay_5.setH(64);
        tileOverlay_5.setW(128);
        tileOverlay_5.image_width = 128;
        tileOverlay_5.setName("aaaa");
        tileOverlay_5.setViewPort(viewPort);
        tileOverlay_5.setImage(new ImageIcon("src/main/resources/Imagens/Sprite_Tile/right_end_tile.png").getImage());
        tile_5.addOverlay(tileOverlay_5);
        tile_5.getOverlays().getFirst().setY(100);
        tile_5.getOverlays().getFirst().setX(-256);

        gameLoop.addElement(tile_1);

        gameLoop.addElement(tile_2);

        gameLoop.addElement(tile_3);

        gameLoop.addElement(tile_4);

        gameLoop.addElement(tile_5);

        gameLoop.addElement(player);

        player.setElementArrayList(gameLoop.getElementArrayList());

        frame.getContentPane().setBackground(Color.BLACK);
        gameLoop.setBackground(Color.BLACK);

        frame.add(gameLoop);

        gameLoop.start_this();

        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);



    }



}
