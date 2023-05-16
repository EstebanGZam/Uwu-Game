package com.example.integrativeTask.control;

import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.KeyHandler;
import com.example.integrativeTask.controller.MainController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Player extends EntityGame {

	public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\16x16-RPG-characters\\sprites\\new-style";

	private int lives;


	GraphicsContext gr;

	private final int ScreenX = 768 / 2 - (48 / 2);
	private final int ScreenY = 560 / 2 - (48 / 2);

	public Player(int x, int y, int speed, int lives, GraphicsContext gr) {
		super(x, y, speed);
		this.gr = gr;
		this.lives = lives;
		area = new Rectangle();
		area.x = 7;
		area.y = 14;
		area.width = 32;
		area.height = 32;
		setSolidAreaDefaultX(area.x);
		setSolidAreaDefaultY(area.y);
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

		for(int i=1;i<=3;i++){
			getImages().add(new Image(TILES_PATH+"\\up"+i+".png"));
		}
		for(int i=1;i<=3;i++){
			getImages().add(new Image(TILES_PATH+"\\down"+i+".png"));
		}
		for(int i=1;i<=3;i++){
			getImages().add(new Image(TILES_PATH+"\\left"+i+".png"));
		}
		for(int i=1;i<=3;i++){
			getImages().add(new Image(TILES_PATH+"\\right"+i+".png"));
		}
	}

@Override
	public void print(GraphicsContext graphicsContext, Player player) {

		Image image = null;

		switch (getDirection()) {
			case "up" -> {
				if (getSpriteNum() == 1) {
					image = getImages().get(0);
				}
				if (getSpriteNum() == 2) {
					image = getImages().get(1);
				}
				if (getSpriteNum() == 3) {
					image = getImages().get(2);
				}
			}
			case "down" -> {
				if (getSpriteNum() == 1) {
					image = getImages().get(3);
				}
				if (getSpriteNum() == 2) {
					image = getImages().get(4);
				}
				if (getSpriteNum() == 3) {
					image = getImages().get(5);
				}
			}
			case "left" -> {
				if (getSpriteNum() == 1) {
					image = getImages().get(6);
				}
				if (getSpriteNum() == 2) {
					image = getImages().get(7);
				}
				if (getSpriteNum() == 3) {
					image = getImages().get(8);
				}
			}
			case "right" -> {
				if (getSpriteNum() == 1) {
					image = getImages().get(9);
				}
				if (getSpriteNum() == 2) {
					image = getImages().get(10);
				}
				if (getSpriteNum() == 3) {
					image = getImages().get(11);
				}
			}
		}
		graphicsContext.drawImage(image, ScreenX, ScreenY, 48, 48);
	}

	@Override
	public void move(CollisionChecker collisionChecker, TileManager tile,Player player,EntityGame[] objects) {
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
			int objIndex=collisionChecker.checkObject(this,true,objects);
			pickObject(objIndex,objects);

			if (!isCollisionOn()) {
				switch (getDirection()) {
					case "up" -> setWorldY(getWorldY() - getSpeed());
					case "down" -> setWorldY(getWorldY() + getSpeed());
					case "left" -> setWorldX(getWorldX() - getSpeed());
					case "right" -> setWorldX(getWorldX() + getSpeed());
				}
			}

			setSpriteCounter(getSpriteCounter() + 1);

			if (getSpriteCounter() > 12) {
				if (getSpriteNum() == 1 || getSpriteNum() == 2) {
					setSpriteNum(getSpriteNum() + 1);
				} else {
					setSpriteNum(1);
				}
				setSpriteCounter(0);
			}
		}

	}

	public void pickObject(int i,EntityGame [] objects){
		if(i!=999){
          objects[i]=null;
		}
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public int getScreenX() {
		return ScreenX;
	}

	public int getScreenY() {
		return ScreenY;
	}
}
