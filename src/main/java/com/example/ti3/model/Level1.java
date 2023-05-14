package com.example.ti3.model;

import com.example.ti3.Control.Player;
import com.example.ti3.Control.Screen;
import com.example.ti3.Control.TileManager;
import com.example.ti3.Main;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Level1 extends Thread  implements Initializable   {



    public final int maxWorldCol =44;
    public final int maxWorldRow =30;
    public final int worldwith = Screen.tilesSize *maxWorldCol;//1536
    public final int worldhight =Screen.tilesSize*maxWorldRow;//816

    public Canvas canvas;

    int fps=60;

    private GraphicsContext g;
    Thread game;

    TileManager tile=new TileManager(g,maxWorldRow,maxWorldCol,"src/main/java/com/example/ti3/level1.txt");


    CollisionChecker collisonChecker = new CollisionChecker();

    KeyHandler keyHandler= new KeyHandler();

    Player player = new Player("",100,100,4,3,g,keyHandler);

    @Override
    public void run() {
        double drawInterval =1000000000/fps;
        double nextDraw = System.nanoTime()+drawInterval;
        while (game!=null){

           // long currentTime= System.nanoTime();

            update();

            paintComponent(g);

            try {
                double remaingingTime = nextDraw-System.nanoTime();
                remaingingTime = remaingingTime/1000000;
                if (remaingingTime<0){
                    remaingingTime =0;
                }
                Thread.sleep((long) remaingingTime);
                nextDraw+=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){
      player.mover(collisonChecker,tile);
    }

    public void paintComponent(GraphicsContext g){
        g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setFill(Color.BLACK);
        g.fillRect(0,0,768,576);
        tile.draw(g,player.getWorldx(),player.getWorldy(),player.ScreenX,player.ScreenY);
        player.print(g);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       game= new Thread(this);
       game.start();
       actualizarEstado();
    }

    public void actualizarEstado() {
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(event -> {
            KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
            keyHandler.keyPressed(keyEvent);
        });

        canvas.setOnKeyReleased(event -> {
            KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
            keyHandler.keyReleased(keyEvent);
        });
    }

}
