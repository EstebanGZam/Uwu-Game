package com.example.integrativeTask.model;

import com.example.integrativeTask.control.Vector;
import com.example.integrativeTask.util.CollisionChecker;
import com.example.integrativeTask.screens.Level;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Bullet extends EntityGame {
	private final GraphicsContext graphicsContext;
	private final int size;
	private final Vector position;
	private final Vector direction;
	private final EntityGame user;

	public boolean isLive = true;

	public Bullet(int x, int y, int speed, Canvas canvas, Vector position, Vector direction, EntityGame user) {
		super(x, y, speed);
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.position = position;
		this.direction = direction;
		this.size = 8;
		this.user = user;

		area = new Rectangle();
		area.x = 8;
		area.y = 8;
		area.width = 8;
		area.height = 8;
		setSolidAreaDefaultX(area.x);
		setSolidAreaDefaultY(area.y);
	}

	public void update(Level level) {
		if (user == level.getPlayer()) {
			setCollisionOn(false);
			CollisionChecker.getInstance().checkTile(this, level.getTile());
			if (isCollisionOn()) {
				isLive = false;
			}

			int monsterIndex = CollisionChecker.getInstance().checkEntity(this, level.getEnemies().stream()
					.map(child -> (EntityGame) child)
					.collect(Collectors.toCollection(ArrayList::new)));
			if (monsterIndex != 999) {
				level.getPlayer().damageMonster(monsterIndex, 1, level);
			}

		} else {
			setCollisionOn(false);
			CollisionChecker.getInstance().checkTile(this, level.getTile());
			if (isCollisionOn()) {
				isLive = false;
			}
			boolean collisionPlayer = CollisionChecker.getInstance().checkPlayer(this, level.getPlayer());
			if (collisionPlayer) {
				if (!level.getPlayer().isInvincible()) {
					int x = level.getPlayer().getLives() - 1;
					level.getPlayer().setLives(x);
					level.getPlayer().setInvincible(true);
					isLive = false;
				}
			}

		}

		position.setX(position.getX() + direction.getX());
		position.setY(position.getY() + direction.getY());
		setWorldX((int) (position.getX() + direction.getX()));
		setWorldY((int) (position.getY() + direction.getY()));
	}

	public void paint(Player player) {
		double screenX = position.getX() - player.getWorldX() + player.getScreenX();
		double screenY = position.getY() - player.getWorldY() + player.getScreenY();

		if (position.getX() + Screen.TILES_SIZE > player.getWorldX() - player.getScreenX() &&
				position.getX() - Screen.TILES_SIZE < player.getWorldX() + player.getScreenX() &&
				position.getY() + Screen.TILES_SIZE > player.getWorldY() - player.getScreenY() &&
				position.getY() - Screen.TILES_SIZE < player.getWorldY() + player.getScreenY()) {
			graphicsContext.setFill(Color.YELLOW);
			graphicsContext.fillOval(screenX, screenY, size, size);
		}
	}

}
