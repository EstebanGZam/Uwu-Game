package com.example.integrativeTask.model;

import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.screens.Level;
import com.example.integrativeTask.util.CollisionChecker;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public abstract class EntityGame {
	protected final ArrayList<Image> images;
	private int worldX;
	private int worldY;
	private final int speed;
	private int spriteCounter = 0;
	private int spriteNum = 1;
	private String direction;
	protected Rectangle area;
	private int solidAreaDefaultX, solidAreaDefaultY;
	private boolean collisionOn;
	private String name;
	public boolean invincible;
	public int invincibleCounter;

	public EntityGame(int x, int y, int speed) {
		this.worldX = x;
		this.worldY = y;
		this.speed = speed;
		direction = "down";
		images = new ArrayList<>();
	}

	public void print(GraphicsContext graphicsContext, Player player) {
	}

	public void move(CollisionChecker collisionChecker, TileManager tile, Player player, EntityGame[] objects, ArrayList<Enemy> enemies, Level level) {
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

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	public void setInvincibleCounter(int invincibleCounter) {
		this.invincibleCounter = invincibleCounter;
	}
}
