package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.MainController;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class Object extends EntityGame {

    private double scale;


    public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\object";
    public Object(String path,int x, int y, int speed,String name,double scale) {
        super(x, y, speed);
        setName(name);
        this.scale=scale;
        area = new Rectangle();
        area.x = 7;
        area.y = 14;
        area.width = 32;
        area.height = 32;
        setSolidAreaDefaultX(area.x);
        setSolidAreaDefaultY(area.y);
        getImages().add(new Image(TILES_PATH+path));
    }


    public void print(GraphicsContext graphicsContext,Player player) {
        int screenX = getWorldX()- player.getWorldX()+player.getScreenX();
        int screenY = getWorldY()- player.getWorldY()+player.getScreenY();
        if(getWorldX()+ Screen.TILES_SIZE>player.getWorldX()-player.getScreenX()&&
                getWorldX()- Screen.TILES_SIZE<player.getWorldX()+player.getScreenX()&&
                getWorldY()+ Screen.TILES_SIZE>player.getWorldY()-player.getScreenY()&&
                getWorldY()- Screen.TILES_SIZE<player.getWorldY()+player.getScreenY()){
            graphicsContext.drawImage(getImages().get(0), screenX, screenY, 48*scale, 48*scale);
        }

    }

    public void move(CollisionChecker collisionChecker, TileManager tile) {

    }
}
