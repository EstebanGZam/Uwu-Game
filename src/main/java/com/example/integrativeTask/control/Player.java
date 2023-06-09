package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.KeyHandler;
import com.example.integrativeTask.controller.MainController;
import com.example.integrativeTask.screens.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class Player extends EntityGame {

	public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\16x16-RPG-characters\\sprites\\new-style";

	GraphicsContext gr;

	public int bullets=60;

	public int attack;

	private final int ScreenX = 768 / 2 - (48 / 2);
	private final int ScreenY = 560 / 2 - (48 / 2);

	private ArrayList<EntityGame> inventory = new ArrayList<>();

	int typeGun = 0;

	public Player(int x, int y, int speed, int lives, GraphicsContext gr) {
		super(x, y, speed, lives);
		this.gr = gr;
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
		setLifes(3);
		setSpeed(4);
		setDirection("down");
		bullets=60;

	}



	public void getPlayerImage() {

		for (int i = 1; i <= 3; i++) {
			getImages().add(new Image(TILES_PATH + "\\up" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			getImages().add(new Image(TILES_PATH + "\\down" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			getImages().add(new Image(TILES_PATH + "\\left" + i + ".png"));
		}
		for (int i = 1; i <= 3; i++) {
			getImages().add(new Image(TILES_PATH + "\\right" + i + ".png"));
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

	//actualizacion de la clase player, esto es mover y actualizacion de estados.
	 @Override
	public void move(CollisionChecker collisionChecker, TileManager tile, Player player, EntityGame[] objects, ArrayList<EntityGame> Enemies, Level level) {
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
			int objIndex = collisionChecker.checkObject(this, true, objects);
			pickObject(objIndex, objects);

			int monsterIndex = collisionChecker.checkEntity(this, Enemies);
			contactEnemy(monsterIndex, Enemies);

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
				case "AK-47", "pistol":
					if (inventory.size() < 2) {
						inventory.add(objects[i]);
					}
					objects[i] = null;
					break;
				case "portal":
						objects[i] = null;
						MainController.LEVEL++;
						setDefault();
					break;
			}

		}

	}

	public int gunActual(int i) {
		if(inventory != null){
			if (i != 999) {
				switch (inventory.get(i-1).getName()) {
					case "AK-47":
						return 8;
					case "pistol":
						return  5;
				}

			}
			return 10;
		}
		return 10;
	}

	public void contactEnemy(int i, ArrayList<EntityGame> enemies) {
		if (i != 999) {
			if (!invincible) {
				setLifes(getLifes() - 1);
				invincible = true;
			}

		}

	}

	public void damageMonster(int i , int attack, Level level){
		if(i!=999){
			if(!level.getEnemies().get(i).isInvincible()){
				int damage= level.getEnemies().get(i).getLifes()-attack;
				if(damage<0){
					damage=0;
				}
				System.out.println("lo ataco");
				level.getEnemies().get(i).setLifes(damage);
				level.getEnemies().get(i).setInvincible(true);
				if(level.getEnemies().get(i).getLifes()<=0){
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

	public void setInventory(ArrayList<EntityGame> inventory) {
		this.inventory = inventory;
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}
