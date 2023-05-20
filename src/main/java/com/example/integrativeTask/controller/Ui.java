package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;
import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ui {

    Font arial_40;

    EntityGame heart;

    public Ui(){

        heart =new Object("\\heart_blank.png", 0,0,0,"AK-47",1.5);
        heart.getImages().add(new Image(Object.TILES_PATH+"\\heart_full.png"));

    }

    public void Draw(GraphicsContext g, Player player ){


        drawPlayerLife(g,player);
        drawPlayerInventary(g,player);

      //  g.setFont(arial_40);
       // g.setFill(Color.WHITE);
        //g.fillText("lifes="+ player.getLifes(),50,50);

    }

    public void drawPlayerLife(GraphicsContext g,Player player){
        int x= Screen.TILES_SIZE/2;
        int y= Screen.TILES_SIZE/2;
        int i=0;

        while (i<player.getMaxlifes()/2){
            g.drawImage(heart.getImages().get(0),x,y,24,24);
            i++;
            x+=Screen.TILES_SIZE;
        }

        x= Screen.TILES_SIZE/2;
        y= Screen.TILES_SIZE/2;
        i=0;

        while (i<player.getLifes()){
            g.drawImage(heart.getImages().get(1),x,y,24,24);
            i++;
            x+=Screen.TILES_SIZE;
        }
    }

    public void drawPlayerInventary(GraphicsContext g,Player player) {
        int x = Screen.TILES_SIZE / 2;
        int y = (Screen.TILES_SIZE / 2)*2;
        int i = 0;

        while (i < player.getInventory().size()) {
            g.drawImage(player.getInventory().get(i).getImages().get(0), x, y, 48, 48);
            i++;
            x += Screen.TILES_SIZE;
        }

    }
}
