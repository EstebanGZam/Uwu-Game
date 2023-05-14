package com.example.integrativeTask.Control;

import com.example.integrativeTask.model.CollisionChecker;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;


public abstract class GameObject {

	private String image;

	private int worldX;
	private int worldY;

	private Image up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	private int speed;

	private int spriteCounter = 0;

	private int spriteNum = 1;

	private String direction;
	private Rectangle area;

	private boolean collisionOn = false;

	public abstract void print(GraphicsContext graphicsContext);

	public abstract void mover(CollisionChecker collisionChecker, TileManager tile);

	public GameObject(String image, int x, int y, int speed) {
		area = new Rectangle();
		this.image = image;
		this.worldX = x;
		this.worldY = y;
		this.speed = speed;
		direction = "down";
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
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


	public Rectangle getArea() {
		return area;
	}

	public void setArea(Rectangle area) {
		this.area = area;
	}

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
}
