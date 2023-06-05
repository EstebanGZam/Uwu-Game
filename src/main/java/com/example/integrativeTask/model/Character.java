package com.example.integrativeTask.model;

public abstract class Character extends EntityGame {
	protected int lives;
	public static final int MAX_LIVES = 3;

	public Character(int x, int y, int speed, int lives) {
		super(x, y, speed);
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	protected void move() {
		if (!isCollisionOn()) {
			switch (getDirection()) {
				case "up" -> setWorldY(getWorldY() - getSpeed());
				case "down" -> setWorldY(getWorldY() + getSpeed());
				case "left" -> setWorldX(getWorldX() - getSpeed());
				case "right" -> setWorldX(getWorldX() + getSpeed());
			}
		}

		setSpriteCounter(getSpriteCounter() + 1);
	}
}
