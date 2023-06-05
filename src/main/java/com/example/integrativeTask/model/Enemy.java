package com.example.integrativeTask.model;

import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.control.Vector;
import com.example.integrativeTask.util.CollisionChecker;
import com.example.integrativeTask.controller.GameController;
import com.example.integrativeTask.screens.BaseScreen;
import com.example.integrativeTask.screens.Level;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Enemy extends Character {

	int fireCounter;
	int fireDelay = 60;

	int actionCounter;
	Vector position;

	public static final String TILES_PATH = GameController.MAIN_RESOURCES_PATH + "\\enemy";

	public Enemy(int x, int y, int speed, int lives) {
		super(x, y, speed, lives);
		actionCounter = 0;
		area = new Rectangle();
		area.x = 7;
		area.y = 14;
		area.width = 32;
		area.height = 32;
		setSolidAreaDefaultX(area.x);
		setSolidAreaDefaultY(area.y);
		getPlayerEnemy();
		position = new Vector(x, y);
	}

	public void getPlayerEnemy() {
		for (int i = 1; i <= 2; i++) {
			getImages().add(new javafx.scene.image.Image(TILES_PATH + "\\up" + i + ".png"));
		}
		for (int i = 1; i <= 2; i++) {
			getImages().add(new javafx.scene.image.Image(TILES_PATH + "\\down" + i + ".png"));
		}
		for (int i = 1; i <= 2; i++) {
			getImages().add(new javafx.scene.image.Image(TILES_PATH + "\\left" + i + ".png"));
		}
		for (int i = 1; i <= 2; i++) {
			getImages().add(new Image(TILES_PATH + "\\right" + i + ".png"));
		}
	}

	public void setAction(Level level) {
		actionCounter++;
		fireCounter--;
		if (actionCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				setDirection("up");
			}
			if (i > 25 && i <= 50) {
				setDirection("down");
			}
			if (i > 50 && i <= 75) {
				setDirection("left");
			}
			if (i > 75) {
				setDirection("right");
			}
			actionCounter = 0;
		}

		if (fireCounter <= 0) {
			Player player = level.getPlayer();
			double playerScreenX = player.getWorldX() - player.getScreenX();
			double playerScreenY = player.getWorldY() - player.getScreenY();
			double enemyScreenX = getWorldX() - player.getScreenX();
			double enemyScreenY = getWorldY() - player.getScreenY();

			double distanceX = playerScreenX - enemyScreenX;
			double distanceY = playerScreenY - enemyScreenY;
			double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

			if (distance <= 250) {
				Vector diff = new Vector(distanceX, distanceY);
				diff.normalize();
				diff.setSpeed(4);
				Vector diffEnemy = new Vector(getWorldX(), getWorldY());

				level.getBullets().add(new Bullet(getWorldX(), getWorldY(), 4, BaseScreen.canvas, diffEnemy, diff, this));
				fireCounter = fireDelay;
			}
		}
	}

	public void move(CollisionChecker collisionChecker, TileManager tile, Player player, EntityGame[] objects, ArrayList<Enemy> enemies, Level level) {
		setAction(level);
		setCollisionOn(false);
		collisionChecker.checkTile(this, tile);
		boolean collisionPlayer = collisionChecker.checkPlayer(this, player);
		collisionChecker.checkEntity(this, enemies.stream()
				.map(child -> (EntityGame) child)
				.collect(Collectors.toCollection(ArrayList::new)));

		if (collisionPlayer) {
			if (!player.isInvincible()) {
				int x = player.getLives() - 1;
				player.setLives(x);
				player.setInvincible(true);
			}
		}
		super.move();

		if (getSpriteCounter() > 12) {
			if (getSpriteNum() == 1) {
				setSpriteNum(getSpriteNum() + 1);
			} else {
				setSpriteNum(1);
			}
			setSpriteCounter(0);
		}
		if (isInvincible()) {
			setInvincibleCounter(getInvincibleCounter() + 1);
			if (getInvincibleCounter() > 20) {
				setInvincible(false);
				setInvincibleCounter(0);
			}
		}
	}


	@Override
	public void print(GraphicsContext graphicsContext, Player player) {

		Image image = null;
		int screenX = getWorldX() - player.getWorldX() + player.getScreenX();
		int screenY = getWorldY() - player.getWorldY() + player.getScreenY();
		if (getWorldX() + Screen.TILES_SIZE > player.getWorldX() - player.getScreenX() &&
				getWorldX() - Screen.TILES_SIZE < player.getWorldX() + player.getScreenX() &&
				getWorldY() + Screen.TILES_SIZE > player.getWorldY() - player.getScreenY() &&
				getWorldY() - Screen.TILES_SIZE < player.getWorldY() + player.getScreenY()) {

			switch (getDirection()) {
				case "up" -> {
					if (getSpriteNum() == 1) {
						image = getImages().get(0);
					}
					if (getSpriteNum() == 2) {
						image = getImages().get(1);
					}
				}
				case "down" -> {
					if (getSpriteNum() == 1) {
						image = getImages().get(2);
					}
					if (getSpriteNum() == 2) {
						image = getImages().get(3);
					}

				}
				case "left" -> {
					if (getSpriteNum() == 1) {
						image = getImages().get(4);
					}
					if (getSpriteNum() == 2) {
						image = getImages().get(5);
					}
				}
				case "right" -> {
					if (getSpriteNum() == 1) {
						image = getImages().get(6);
					}
					if (getSpriteNum() == 2) {
						image = getImages().get(7);
					}

				}
			}
			graphicsContext.drawImage(image, screenX, screenY, 48, 48);
		}
	}

}
