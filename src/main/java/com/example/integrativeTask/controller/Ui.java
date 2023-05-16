package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ui {

    Font arial_40;

    public Ui(){

    }

    public void Draw(GraphicsContext g, Player player ){

        g.setFont(arial_40);
        g.setFill(Color.WHITE);
        g.fillText("lifes="+ player.getLives(),50,50);

    }
}
