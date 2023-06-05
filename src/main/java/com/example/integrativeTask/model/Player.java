package com.example.integrativeTask.model;

import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.util.CollisionChecker;
import com.example.integrativeTask.util.KeyHandler;
import com.example.integrativeTask.controller.GameController;
import com.example.integrativeTask.screens.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player extends Character {

	public static final String PLAYER_IMAGES_PATH = GameController.MAIN_RESOURCES_PATH + "\\16x16-RPG-characters\\sprites\\new-style";

	public int bullets = 60; // Maximum number of bullets in a player's possession

	private final int ScreenX = 768 / 2 - (48 / 2);
	private final int ScreenY = 560 / 2 - (48 / 2);

	private final ArrayList<EntityGame> inventory = new ArrayList<>();

	int typeGun = 0;

	public Player(int speed, int lives) {
		super(100, 100, speed, lives); // The player is created in the 100x100 position by default.
		area = new Rectangle();
		area.x = 7;
		area.y = 14;
		area.width = 32;
		area.height = 32;
		setSolidAreaDefaultX(area.x);
		setSolidAreaDefaultY(area.y);
		setDefaultPlayerProperties();
		getPlayerImage();
	}


	public void setDefaultPlayerProperties() {
		super.setWorldX(100 * 4);  // Player's starting position in X
		super.setWorldY(100 * 4);  // Player's starting position in Y
		super.lives = 3; // Number of initial lives
		super.setDirection("down");
		bullets = 60;
	}


	public void getPlayerImage() {
		for (int i = 1; i <= 3; i++) {
			images.add(i - 1, new Image(PLAYER_IMAGES_PATH + "\\up" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			images.add(new Image(PLAYER_IMAGES_PATH + "\\down" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			images.add(new Image(PLAYER_IMAGES_PATH + "\\left" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			images.add(new Image(PLAYER_IMAGES_PATH + "\\right" + i + ".png"));
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

	// Player class update, that is moving and updating states.
	public void move(CollisionChecker collisionChecker, TileManager tile, Player player, EntityGame[] objects, ArrayList<Enemy> enemies, Level level) {
		KeyHandler keyHandler = KeyHandler.getInstance();
		if (keyHandler.isKeyMovementPressed()) {
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
			int objIndex = collisionChecker.checkObject(this, true, objects);
			pickObject(objIndex, objects);

			int monsterIndex = collisionChecker.checkEntity(this, enemies.stream()
					.map(child -> (EntityGame) child)
					.collect(Collectors.toCollection(ArrayList::new)));
			contactEnemy(monsterIndex);

			super.move();

			if (getSpriteCounter() > 12) {
				if (getSpriteNum() == 1 || getSpriteNum() == 2) {
					setSpriteNum(getSpriteNum() + 1);
				} else {
					setSpriteNum(1);
				}
				setSpriteCounter(0);
			}
		}
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}

	}

	public void pickObject(int i, EntityGame[] objects) {
		if (i != 999) {
			switch (objects[i].getName()) {
				case "AK-47", "pistol" -> {
					if (inventory.size() < 2) {
						inventory.add(objects[i]);
					}
					objects[i] = null;
				}
				case "portal" -> {
					objects[i] = null;
					GameController.LEVEL++;
					setDefaultPlayerProperties();
				}
			}

		}

	}

	public int gunActual(int i) {
		if (i != 999) {
			switch (inventory.get(i - 1).getName()) {
				case "AK-47" -> {
					return 8;
				}
				case "pistol" -> {
					return 5;
				}
			}

		}
		return 10;
	}

	public void contactEnemy(int i) {
		if (i != 999) {
			if (!invincible) {
				super.setLives(getLives() - 1);
				this.invincible = true;
			}

		}

	}

	public void damageMonster(int i, int attack, Level level) {
		if (i != 999) {
			if (!level.getEnemies().get(i).isInvincible()) {
				int damage = level.getEnemies().get(i).getLives() - attack;
				if (damage < 0) {
					damage = 0;
				}
				System.out.println("Attacked him");
				level.getEnemies().get(i).setLives(damage);
				level.getEnemies().get(i).setInvincible(true);
				if (level.getEnemies().get(i).getLives() <= 0) {
					level.getEnemies().remove(i);
				}
			}
		}
	}


	public int getScreenX() {
		return ScreenX;
	}

	public int getScreenY() {
		return ScreenY;
	}

	public ArrayList<EntityGame> getInventory() {
		return inventory;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getTypeGun() {
		return typeGun;
	}

	public void setTypeGun(int typeGun) {
		this.typeGun = typeGun;
	}

	public int getBullets() {
		return bullets;
	}

	public void setBullets(int bullets) {
		this.bullets = bullets;
	}
}
