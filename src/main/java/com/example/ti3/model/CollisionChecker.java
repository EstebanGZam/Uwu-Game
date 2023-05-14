package com.example.ti3.model;

import com.example.ti3.Control.ObjetoJuego;
import com.example.ti3.Control.TileManager;
import javafx.scene.canvas.GraphicsContext;

public class CollisionChecker {

    public CollisionChecker() {

    }

    public void checkTitle(ObjetoJuego ob, TileManager tile){

        int leftWorldX= ob.getWorldx()+ob.getArea().x;
        int rightWorldY= ob.getWorldx()+ob.getArea().x+ob.getArea().width;
        int TopWorldY= ob.getWorldy()+ob.getArea().y;
        int BottomWorldY= ob.getWorldy()+ob.getArea().y+ob.getArea().height;

        int leftCol=leftWorldX/48;
        int rightCol=rightWorldY/48;
        int topRow=TopWorldY/48;
        int bottomRow=BottomWorldY/48;

        int tileNum1, tileNum2;

        switch (ob.getDirection()){
            case "up":
                topRow=(TopWorldY-ob.getSpeed())/(48);
                tileNum1= tile.getMapTileNum()[leftCol][topRow];
                tileNum2= tile.getMapTileNum()[rightCol][topRow];
                if(tile.getTile()[tileNum1].collision||tile.getTile()[tileNum2].collision){
                    ob.setCollisionOn(true);
                }
                break;
            case "down":
                bottomRow=(BottomWorldY+ob.getSpeed())/(48);
                tileNum1= tile.getMapTileNum()[leftCol][bottomRow];
                tileNum2= tile.getMapTileNum()[rightCol][bottomRow];
                if(tile.getTile()[tileNum1].collision||tile.getTile()[tileNum2].collision){
                    ob.setCollisionOn(true);
                }
                break;
            case "left":
                leftCol=(leftWorldX-ob.getSpeed())/(48);
                tileNum1= tile.getMapTileNum()[leftCol][topRow];
                tileNum2= tile.getMapTileNum()[leftCol][bottomRow];
                if(tile.getTile()[tileNum1].collision||tile.getTile()[tileNum2].collision){
                    ob.setCollisionOn(true);
                }
                break;
            case "right":
                rightCol=(rightWorldY+ob.getSpeed())/(48);
                tileNum1= tile.getMapTileNum()[rightCol][topRow];
                tileNum2= tile.getMapTileNum()[rightCol][bottomRow];
                if(tile.getTile()[tileNum1].collision||tile.getTile()[tileNum2].collision){
                    ob.setCollisionOn(true);
                }
                break;
        }
    }


}
