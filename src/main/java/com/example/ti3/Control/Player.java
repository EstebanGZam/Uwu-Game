package com.example.ti3.Control;

import com.example.ti3.model.KeyHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;

public class Player extends ObjetoJuego {

    private int lifes;


    GraphicsContext gr;

    KeyHandler keyHandler;



    public Player(String image, int x, int y, int speed, int lifes,GraphicsContext gr,KeyHandler keyHandler) {
        super(image, x, y, speed);
        this.gr=gr;
        this.keyHandler=keyHandler;
        this.lifes = lifes;
        getPlayerImage();
    }


    public void setDefault(){
        setX(100);
        setY(100);
        setLifes(3);
        setSpeed(4);
        setDirection("down");
    }


    public void getPlayerImage(){

        File spriteDir = new File("src/main/resources/16x16-RPG-characters/sprites/new-style/");
        File up1 = new File(spriteDir, "up1.png");
        File up2 = new File(spriteDir, "up2.png");
        File up3 = new File(spriteDir, "up3.png");
        File down1 = new File(spriteDir, "down1.png");
        File down2 = new File(spriteDir, "down2.png");
        File down3 = new File(spriteDir, "down3.png");
        File left1 = new File(spriteDir, "left1.png");
        File left2 = new File(spriteDir, "left2.png");
        File left3 = new File(spriteDir, "left3.png");
        File right1 = new File(spriteDir, "right1.png");
        File right2 = new File(spriteDir, "right2.png");
        File right3 = new File(spriteDir, "right3.png");

        setUp1(new Image(up1.toURI().toString()));
        setUp2(new Image(up2.toURI().toString()));
        setUp3(new Image(up3.toURI().toString()));
        setDown1(new Image(down1.toURI().toString()));
        setDown2(new Image(down2.toURI().toString()));
        setDown3(new Image(down3.toURI().toString()));
        setLeft1(new Image(left1.toURI().toString()));
        setLeft2(new Image(left2.toURI().toString()));
        setLeft3(new Image(left3.toURI().toString()));
        setRight1(new Image(right1.toURI().toString()));
        setRight2(new Image(right2.toURI().toString()));
        setRight3(new Image(right3.toURI().toString()));
    }


    @Override
    public void print(GraphicsContext g) {
       // g.setFill(Color.RED);
       // g.fillRect(getX(),getY(),48,48);

        Image image =null;

        switch (getDirection()){
            case "up":
                if(getSpriteNum()==1){
                    image = getUp1();
                }
                if(getSpriteNum()==2){
                    image = getUp2();
                }
                if(getSpriteNum()==3){
                    image = getUp3();
                }

                break;
            case "down":
                if(getSpriteNum()==1){
                    image = getDown1();
                }
                if(getSpriteNum()==2){
                    image = getDown2();
                }
                if(getSpriteNum()==3){
                    image = getDown3();
                }
                break;
            case "left":
                if(getSpriteNum()==1){
                    image = getLeft1();
                }
                if(getSpriteNum()==2){
                    image = getLeft2();
                }
                if(getSpriteNum()==3){
                    image = getLeft3();
                }
                break;
            case "right":
                if(getSpriteNum()==1){
                    image = getRight1();
                }
                if(getSpriteNum()==2){
                    image = getRight2();
                }
                if(getSpriteNum()==3){
                    image = getRight3();
                }
                break;
        }
        g.drawImage(image,getX(),getY(),48,48);
    }

    @Override
    public void mover() {
        if(keyHandler.upPresed ||keyHandler.downPressed||keyHandler.leftPressed
                ||keyHandler.rightPressed){
            if(keyHandler.upPresed){
                setDirection("up");
                setY(getY()-getSpeed());
            }else if(keyHandler.downPressed){
                setDirection("down");
                setY(getY()+getSpeed());
            }else if(keyHandler.leftPressed){
                setDirection("left");
                setX(getX()-getSpeed());
            }else if(keyHandler.rightPressed){
                setDirection("right");
                setX(getX()+getSpeed());
            }
            setSpriteCounter(getSpriteCounter()+1);

            if(getSpriteCounter()>10){
                if(getSpriteNum()==1||getSpriteNum()==2){
                    setSpriteNum(getSpriteNum()+1);
                }
                else{
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }

    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }


}
