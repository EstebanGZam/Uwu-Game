package com.example.ti3.Control;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileManager {

    GraphicsContext gr;

    Tile [] tile;

    public TileManager(GraphicsContext gr){

        this.gr=gr;

        tile = new Tile[10];
    }

    public void getTitleImage(){

        tile[0]= new Tile();

        tile[1]= new Tile();

        tile[2]= new Tile();


    }

    public void draw(GraphicsContext g2){
        //g2.drawImage(tile[0],0,0,);
    }

}
