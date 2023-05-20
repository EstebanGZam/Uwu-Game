package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.KeyHandler;
import com.example.integrativeTask.controller.MainController;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends EntityGame{

    int actionCounter;

    public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\enemy";

    public Enemy(int x, int y, int speed, int lifes) {
        super(x, y, speed, lifes);
        actionCounter=0;
        area = new Rectangle();
        area.x = 7;
        area.y = 14;
        area.width = 32;
        area.height = 32;
        setSolidAreaDefaultX(area.x);
        setSolidAreaDefaultY(area.y);
        getPlayerEnmy();
    }

    public void getPlayerEnmy() {

        for(int i=1;i<=2;i++){
            getImages().add(new javafx.scene.image.Image(TILES_PATH+"\\up"+i+".png"));
        }
        for(int i=1;i<=2;i++){
            getImages().add(new javafx.scene.image.Image(TILES_PATH+"\\down"+i+".png"));
        }
        for(int i=1;i<=2;i++){
            getImages().add(new javafx.scene.image.Image(TILES_PATH+"\\left"+i+".png"));
        }
        for(int i=1;i<=2;i++){
            getImages().add(new Image(TILES_PATH+"\\right"+i+".png"));
        }
    }

    public void setAction(){
        actionCounter++;
        if(actionCounter==120){
            Random random = new Random();
            int i =random.nextInt(100)+1;

            if(i<=25){
                setDirection("up");
            }
            if(i>25 && i<=50){
                setDirection("down");
            }
            if(i>50 && i<=75){
                setDirection("left");
            }
            if(i>75 && i<=100){
                setDirection("right");
            }
            actionCounter=0;
        }
    }

    @Override
    public void move(CollisionChecker collisionChecker, TileManager tile, Player player, EntityGame[] objects, ArrayList<EntityGame> enemies) {
        setAction();
            setCollisionOn(false);
            collisionChecker.checkTile(this, tile);
            boolean collisionPlayer=collisionChecker.checkPlayer(this,player);
            collisionChecker.checkEntity(this,enemies);

            if (collisionPlayer){
                System.out.println(player.isInvincible());
                if(!player.isInvincible()){
                     player.setLifes(getLifes()-1);
                     player.setInvincible(true);
                }
            }
            if (!isCollisionOn()) {
                switch (getDirection()) {
                    case "up" -> setWorldY(getWorldY() - getSpeed());
                    case "down" -> setWorldY(getWorldY() + getSpeed());
                    case "left" -> setWorldX(getWorldX() - getSpeed());
                    case "right" -> setWorldX(getWorldX() + getSpeed());
                }
            }

            setSpriteCounter(getSpriteCounter() + 1);

            if (getSpriteCounter() > 12) {
                if (getSpriteNum() == 1 ) {
                    setSpriteNum(getSpriteNum() + 1);
                } else {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }


    @Override
    public void print(GraphicsContext graphicsContext, Player player) {

        Image image = null;
        int screenX = getWorldX()- player.getWorldX()+player.getScreenX();
        int screenY = getWorldY()- player.getWorldY()+player.getScreenY();
        if(getWorldX()+ Screen.TILES_SIZE>player.getWorldX()-player.getScreenX()&&
                getWorldX()- Screen.TILES_SIZE<player.getWorldX()+player.getScreenX()&&
                getWorldY()+ Screen.TILES_SIZE>player.getWorldY()-player.getScreenY()&&
                getWorldY()- Screen.TILES_SIZE<player.getWorldY()+player.getScreenY()){

            switch (getDirection()) {
                case "up" -> {
                    if (getSpriteNum() == 1) {
                        image = getImages().get(0);
                    }
                    if (getSpriteNum() == 2) {
                        image = getImages().get(1);
                    }

                }
                case "down" -> {
                    if (getSpriteNum() == 1) {
                        image = getImages().get(2);
                    }
                    if (getSpriteNum() == 2) {
                        image = getImages().get(3);
                    }

                }
                case "left" -> {
                    if (getSpriteNum() == 1) {
                        image = getImages().get(4);
                    }
                    if (getSpriteNum() == 2) {
                        image = getImages().get(5);
                    }
                }
                case "right" -> {
                    if (getSpriteNum() == 1) {
                        image = getImages().get(6);
                    }
                    if (getSpriteNum() == 2) {
                        image = getImages().get(7);
                    }

                }
            }
            graphicsContext.drawImage(image, screenX, screenY, 48, 48);
        }
    }


}
