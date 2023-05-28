package com.example.integrativeTask.control;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Gun extends  Bullet{

    Gun(int x, int y, int speed , int lifes, Canvas canvas, int xDirection, int yDirection){
        super(x,y,speed,lifes,canvas,xDirection,yDirection);
        this.setSpeed(8);
    }
}
