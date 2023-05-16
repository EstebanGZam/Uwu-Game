package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.MainController;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Object extends EntityGame {


    String name;
    public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\object";
    public Object(String path,int x, int y, int speed,String name) {
        super(x, y, speed);
        this.name=name;
        getImages().add(new Image(TILES_PATH+path));
        setCollisionOn(true);
    }


    public void print(GraphicsContext graphicsContext,Player player) {
        int screenX = getWorldX()- player.getWorldX()+player.getScreenX();
        int screenY = getWorldY()- player.getWorldY()+player.getScreenY();
        if(getWorldX()+ Screen.TILES_SIZE>player.getWorldX()-player.getScreenX()&&
                getWorldX()- Screen.TILES_SIZE<player.getWorldX()+player.getScreenX()&&
                getWorldY()+ Screen.TILES_SIZE>player.getWorldY()-player.getScreenY()&&
                getWorldY()- Screen.TILES_SIZE<player.getWorldY()+player.getScreenY()){
            graphicsContext.drawImage(getImages().get(0),Screen.TILES_SIZE,Screen.TILES_SIZE);
        }

    }


    public void print(GraphicsContext graphicsContext) {

    }
    public void move(CollisionChecker collisionChecker, TileManager tile) {

    }
}
