package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public abstract class EntityGame {
    private ArrayList <Image> images;
	private int worldX, worldY, speed, spriteCounter = 0, spriteNum = 1;
	private String direction;
	protected Rectangle area;

	private int solidAreaDefaultX;
	private int solidAreaDefaultY;
	private boolean collisionOn;

	private String name;


	public EntityGame(int x, int y, int speed) {
		this.worldX = x;
		this.worldY = y;
		this.speed = speed;
		direction = "down";
		images= new ArrayList<>();
	}

	public void print(GraphicsContext graphicsContext, Player player){

	}

	public void move(CollisionChecker collisionChecker, TileManager tile,Player player,EntityGame[] object) {
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

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}

	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}

	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
