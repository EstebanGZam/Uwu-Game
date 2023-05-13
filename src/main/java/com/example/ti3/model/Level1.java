package com.example.ti3.model;

import com.example.ti3.Control.Player;
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
    public Canvas canvas;

    int fps=60;

    private GraphicsContext g;
    Thread game;

    private KeyCode keyPressed;

    KeyHandler keyHandler= new KeyHandler();

    Player player = new Player("",100,100,4,3,g,keyHandler);

    int playerX=100;
    int playerY=100;

    int playerSpeed=4;
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
      player.mover();
    }

    public void paintComponent(GraphicsContext g){
        g = canvas.getGraphicsContext2D();

        player.print(g);
        g.closePath();

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
