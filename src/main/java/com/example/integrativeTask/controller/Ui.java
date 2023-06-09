package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;
import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Ui {

    Font arial_14;

    EntityGame heart;

     public int commandNum = 0;

    public Ui(){

        heart =new Object("\\heart_blank.png", 0,0,0,"AK-47",1.5);
        heart.getImages().add(new Image(Object.TILES_PATH+"\\heart_full.png"));
        arial_14= new Font("Arial",14);

    }

    public void Draw(GraphicsContext g, Player player, int gameState, int gameOverState, int gamePlaystate, int gameWinState  ){

        if(gameState==gamePlaystate){
            drawPlayerLife(g,player);
            drawPlayerInventary(g,player);
            drawPlayerBullets(g,player);
            drawUseAmor(g,player);
        }
        if(gameState == gameOverState){
            drawGameOverScreen(g);
        }if(gameState==gameWinState ){
            drawWinrScreen(g);
        }



    }

    public void drawGameOverScreen(GraphicsContext g){
        int x;
        int y;
        String text;
        g.setFill(new Color(0,0,0,0.5));
        g.fillRect(0,0,Screen.SCREEN_WIDTH,Screen.SCREEN_HEIGHT);

        g.setFont(new Font("Arial", 110));
        text = "GAME OVER";

        g.setFill(Color.BLACK);
        x = getXForCenteredText(g,text);
        y = Screen.TILES_SIZE*4;

        g.fillText(text,x,y);

        g.setFill(Color.WHITE);
        g.fillText(text,x-4,y-4);

        //intentar otra vez.


        g.setFont(new Font("Arial", 50));
        text = "Space to Play Again";
        x = getXForCenteredText(g,text);
        y += Screen.TILES_SIZE*4;
        g.fillText(text,x,y);

        if(commandNum == 0){
            g.fillText(">",x-40,y);

        }


    }
    public void drawWinrScreen(GraphicsContext g){
        int x;
        int y;
        String text;
        g.setFill(new Color(0,0,0,0.5));
        g.fillRect(0,0,Screen.SCREEN_WIDTH,Screen.SCREEN_HEIGHT);

        g.setFont(new Font("Arial", 110));
        text = "Felcitaciones";

        g.setFill(Color.BLACK);
        x = getXForCenteredText(g,text);
        y = Screen.TILES_SIZE*4;

        g.fillText(text,x,y);

        g.setFill(Color.WHITE);
        g.fillText(text,x-4,y-4);

        //intentar otra vez.


        g.setFont(new Font("Arial", 50));
        text = "Space to Play Again";
        x = getXForCenteredText(g,text);
        y += Screen.TILES_SIZE*4;
        g.fillText(text,x,y);

        if(commandNum == 0){
            g.fillText(">",x-40,y);

        }


    }

    public int getXForCenteredText(GraphicsContext g, String text) {
        Text tempText = new Text(text);
        tempText.setFont(g.getFont());

        double textWidth = tempText.getBoundsInLocal().getWidth();
        double x = g.getCanvas().getWidth() / 2 - textWidth / 2;

        return (int) x;
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
    public void drawPlayerBullets(GraphicsContext g,Player player) {
        int x = Screen.TILES_SIZE / 2;
        int y = (Screen.TILES_SIZE / 2)*2+60;

        g.setFont(arial_14);
        g.setFill(Color.WHITE);
        g.fillText("bullets="+ player.getBullets(),x,y);

    }

    public void drawUseAmor(GraphicsContext g,Player player) {
        int x = Screen.TILES_SIZE / 2;
        int y = (Screen.TILES_SIZE / 2)*2+40;

        if(player.getInventory()!=null){
            if(player.getTypeGun()==0){

            }
            else if(player.getTypeGun()==1){
               g.setFill(Color.YELLOW);
               g.fillRect(x, y, 48, 3);
            }else if(player.getTypeGun()==2){
                g.setFill(Color.YELLOW);
                g.fillRect(x+48, y, 48, 3);
            }
        }

    }
}
