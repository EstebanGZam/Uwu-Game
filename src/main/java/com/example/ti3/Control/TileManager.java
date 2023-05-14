package com.example.ti3.Control;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.*;

public class TileManager {

    GraphicsContext gr;

    Tile [] tile;

    int mapTileNum [][];


    public TileManager(GraphicsContext gr){

        this.gr=gr;

        tile = new Tile[10];
        mapTileNum = new int[16][12];
        getTitleImage();
        loadGame();
    }

    public void getTitleImage(){

        File spriteDir = new File("src/main/java/com/example/ti3/RPGW_Caves_v2.1/");
        File x1 = new File(spriteDir, "esquinaDeSu.png");
        File x2 = new File(spriteDir, "esquinaIzSu.png");
        File x3 = new File(spriteDir, "grass.png");
        File x4 = new File(spriteDir, "muro.png");
        File x5 = new File(spriteDir, "muroDE.png");
        File x6 = new File(spriteDir, "muroINF.png");
        File x7 = new File(spriteDir, "muroINTERIOR.png");
        File x8 = new File(spriteDir, "muroIz.png");
        File x9 = new File(spriteDir, "parteDEINF.png");
        File x10 = new File(spriteDir, "parteINF.png");
        File x11 = new File(spriteDir, "parteIZINF.png");
        File x12 = new File(spriteDir, "parteSu.png");


        tile[0]= new Tile();

        tile[0].image =new Image(x3.toURI().toString());

        tile[1]= new Tile();
        tile[1].image =new Image(x1.toURI().toString());

        tile[2]= new Tile();
        tile[2].image =new Image(x2.toURI().toString());

        tile[3]= new Tile();
        tile[3].image =new Image(x9.toURI().toString());
        tile[4]= new Tile();
        tile[4].image =new Image(x11.toURI().toString());

        tile[5]= new Tile();
        tile[5].image =new Image(x10.toURI().toString());

        tile[6]= new Tile();
        tile[6].image =new Image(x12.toURI().toString());

        tile[7]= new Tile();
        tile[7].image =new Image(x4.toURI().toString());

        tile[8]= new Tile();
        tile[8].image =new Image(x5.toURI().toString());

        tile[9]= new Tile();
        tile[9].image =new Image(x7.toURI().toString());



    }

    public void loadGame(){
        try {
            File File= new File("src/main/java/com/example/ti3/level1.txt");
            FileInputStream i= new FileInputStream(File);
            BufferedReader reader = new BufferedReader(new InputStreamReader(i));

            int col=0;
            int row=0;

            while(col<16&& row<12){

                String line =reader.readLine();
                while (col< 16){
                    String nume[]=line.split(" ");

                    int num = Integer.parseInt(nume[col]);

                    mapTileNum[col][row] =num;
                    col++;
                }
                if(col==16){
                    col=0;
                    row++;
                }
            }
            reader.close();

        }catch (Exception e){

        }
    }

    public void draw(GraphicsContext g2){
        int col=0;
        int row=0;
        int x=0;
        int y=0;

        while(col<16 && row <12){
            int titlenum= mapTileNum[col][row];

            g2.drawImage(tile[titlenum].image,x,y,48,48);
            col++;
            x+=48;

            if(col== 16){
                col=0;
                x=0;
                row++;
                y+=48;
            }
        }
    }

}
