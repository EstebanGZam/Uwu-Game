package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Player extends GameObject {

	private int lives;

	GraphicsContext gr;

	public final int ScreenX = 768 / 2 - (48 / 2);
	public final int ScreenY = 560 / 2 - (48 / 2);

	public Player(int x, int y, int speed, int lives, GraphicsContext gr) {
		super(x, y, speed);
		this.gr = gr;
		this.lives = lives;
		area = new Rectangle();
		area.x = 7;
		area.y = 14;
		area.width = 32;
		area.height = 32;
		setDefault();
		getPlayerImage();
	}


	public void setDefault() {
		setWorldX(100 * 4);  // posicion de inicio del jugagor
		setWorldY(100 * 4);  // posicion de inicio del jugador
		setLives(3);
		setSpeed(4);
		setDirection("down");
	}


	public void getPlayerImage() {

		File spriteDir = new File("src\\main\\resources\\16x16-RPG-characters\\sprites\\new-style\\");
		File up1 = new File(spriteDir, "up1.png");
		File up2 = new File(spriteDir, "up2.png");
		File up3 = new File(spriteDir, "up3.png");
		File down1 = new File(spriteDir, "down1.png");
		File down2 = new File(spriteDir, "down2.png");
		File down3 = new File(spriteDir, "down3.png");
		File left1 = new File(spriteDir, "left1.png");
		File left2 = new File(spriteDir, "left2.png");
		File left3 = new File(spriteDir, "left3.png");
		File right1 = new File(spriteDir, "right1.png");
		File right2 = new File(spriteDir, "right2.png");
		File right3 = new File(spriteDir, "right3.png");

		setUp1(new Image(up1.toURI().toString()));
		setUp2(new Image(up2.toURI().toString()));
		setUp3(new Image(up3.toURI().toString()));
		setDown1(new Image(down1.toURI().toString()));
		setDown2(new Image(down2.toURI().toString()));
		setDown3(new Image(down3.toURI().toString()));
		setLeft1(new Image(left1.toURI().toString()));
		setLeft2(new Image(left2.toURI().toString()));
		setLeft3(new Image(left3.toURI().toString()));
		setRight1(new Image(right1.toURI().toString()));
		setRight2(new Image(right2.toURI().toString()));
		setRight3(new Image(right3.toURI().toString()));
	}


	@Override
	public void print(GraphicsContext graphicsContext) {

		Image image = null;

		switch (getDirection()) {
			case "up" -> {
				if (getSpriteNum() == 1) {
					image = getUp1();
				}
				if (getSpriteNum() == 2) {
					image = getUp2();
				}
				if (getSpriteNum() == 3) {
					image = getUp3();
				}
			}
			case "down" -> {
				if (getSpriteNum() == 1) {
					image = getDown1();
				}
				if (getSpriteNum() == 2) {
					image = getDown2();
				}
				if (getSpriteNum() == 3) {
					image = getDown3();
				}
			}
			case "left" -> {
				if (getSpriteNum() == 1) {
					image = getLeft1();
				}
				if (getSpriteNum() == 2) {
					image = getLeft2();
				}
				if (getSpriteNum() == 3) {
					image = getLeft3();
				}
			}
			case "right" -> {
				if (getSpriteNum() == 1) {
					image = getRight1();
				}
				if (getSpriteNum() == 2) {
					image = getRight2();
				}
				if (getSpriteNum() == 3) {
					image = getRight3();
				}
			}
		}
		graphicsContext.drawImage(image, ScreenX, ScreenY, 48, 48);
	}

	@Override
	public void move(CollisionChecker collisionChecker, TileManager tile) {
		KeyHandler keyHandler = KeyHandler.getInstance();
		if (keyHandler.isUpPressed() || keyHandler.isDownPressed() ||
				keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
			if (keyHandler.isUpPressed()) {
				setDirection("up");
			} else if (keyHandler.isDownPressed()) {
				setDirection("down");
			} else if (keyHandler.isLeftPressed()) {
				setDirection("left");
			} else {
				setDirection("right");
			}

			setCollisionOn(false);
			collisionChecker.checkTile(this, tile);

			if (!isCollisionOn()) {
				switch (getDirection()) {
					case "up" -> setWorldY(getWorldY() - getSpeed());
					case "down" -> setWorldY(getWorldY() + getSpeed());
					case "left" -> setWorldX(getWorldX() - getSpeed());
					case "right" -> setWorldX(getWorldX() + getSpeed());
				}
			}

			setSpriteCounter(getSpriteCounter() + 1);

			if (getSpriteCounter() > 10) {
				if (getSpriteNum() == 1 || getSpriteNum() == 2) {
					setSpriteNum(getSpriteNum() + 1);
				} else {
					setSpriteNum(1);
				}
				setSpriteCounter(0);
			}
		}

	}

	public void setLives(int lives) {
		this.lives = lives;
	}


}
