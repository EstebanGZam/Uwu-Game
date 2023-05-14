package com.example.ti3.Control;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class ObjetoJuego {

    private String image;


    private int Worldx;
    private int Worldy;

    Image up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    private int speed;

    private int spriteCounter= 0;

    private  int spriteNum=1;

    private String direction;
    private Rectangle are;

    private boolean collisionOn=false;

    public abstract void print(GraphicsContext grafics);

    public abstract void mover();

    public ObjetoJuego(String image, int x, int y, int speed) {
        this.image = image;
        this.Worldx = x;
        this.Worldy = y;
        this.speed = speed;
        direction="down";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWorldx() {
        return Worldx;
    }

    public void setWorldx(int worldx) {
        Worldx = worldx;
    }

    public int getWorldy() {
        return Worldy;
    }

    public void setWorldy(int worldy) {
        Worldy = worldy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public Image getUp1() {
        return up1;
    }

    public void setUp1(Image up1) {
        this.up1 = up1;
    }

    public Image getUp2() {
        return up2;
    }

    public void setUp2(Image up2) {
        this.up2 = up2;
    }

    public Image getUp3() {
        return up3;
    }

    public void setUp3(Image up3) {
        this.up3 = up3;
    }

    public Image getDown1() {
        return down1;
    }

    public void setDown1(Image down1) {
        this.down1 = down1;
    }

    public Image getDown2() {
        return down2;
    }

    public void setDown2(Image down2) {
        this.down2 = down2;
    }

    public Image getDown3() {
        return down3;
    }

    public void setDown3(Image down3) {
        this.down3 = down3;
    }

    public Image getLeft1() {
        return left1;
    }

    public void setLeft1(Image left1) {
        this.left1 = left1;
    }

    public Image getLeft2() {
        return left2;
    }

    public void setLeft2(Image left2) {
        this.left2 = left2;
    }

    public Image getLeft3() {
        return left3;
    }

    public void setLeft3(Image left3) {
        this.left3 = left3;
    }

    public Image getRight1() {
        return right1;
    }

    public void setRight1(Image right1) {
        this.right1 = right1;
    }

    public Image getRight2() {
        return right2;
    }

    public void setRight2(Image right2) {
        this.right2 = right2;
    }

    public Image getRight3() {
        return right3;
    }

    public void setRight3(Image right3) {
        this.right3 = right3;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
}
