package com.example.integrativeTask.screens;

import com.example.integrativeTask.control.*;
import com.example.integrativeTask.controller.*;
import com.example.integrativeTask.model.Bullet;
import com.example.integrativeTask.model.Enemy;
import com.example.integrativeTask.model.EntityGame;
import com.example.integrativeTask.model.Player;
import com.example.integrativeTask.util.AssetSetter;
import com.example.integrativeTask.util.CollisionChecker;
import com.example.integrativeTask.util.KeyHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;

public class Level extends BaseScreen {
	private final int maxWorldCol, maxWorldRow;
	private TileManager tile;
	private final Player player;
	private final ArrayList<EntityGame> entityList;
	private final ArrayList<Bullet> bullets = new ArrayList<>();
	private final EntityGame[] objects = new EntityGame[4];
	private final ArrayList<Enemy> enemies = new ArrayList<>();
	private final AssetSetter assetSetter;
	private Ui ui;
	private final Rectangle mouseRect;
	private double mouseRectX;
	private double mouseRectY;
	public int gameState;
	public final int playState = 1, gameOverState = 2, gameWinState = 3;
	private final String levelRelativePath;

	public Level(int maxWorldColumns, int maxWorldRow, Canvas canvas, String levelRelativePath, Player player, int map) {
		super(canvas);
		this.maxWorldCol = maxWorldColumns;
		this.maxWorldRow = maxWorldRow;
		this.tile = new TileManager(maxWorldRow, maxWorldColumns, GameController.COLLISIONS_PATH + levelRelativePath);
		this.levelRelativePath = levelRelativePath;
		this.player = player;
		this.entityList = new ArrayList<>();
		this.ui = new Ui();
		this.assetSetter = new AssetSetter(map);
		assetSetter.setObject(objects, enemies, tile.getValidPositions());
		mouseRect = new Rectangle(mouseRectX, mouseRectY, 20, 20);
		gameState = playState;

	}

	public void update() {

		if (gameState == playState) {
			portalUpdate();
			player.move(CollisionChecker.getInstance(), tile, player, objects, enemies, this);

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).move(CollisionChecker.getInstance(), tile, player, objects, enemies, this);
			}
			for (int i = 0; i < bullets.size(); i++) {
				if (!bullets.get(i).isLive) {
					bullets.remove(i);
				} else {
					bullets.get(i).update(this);
				}
			}
			if (player.getLives() <= 0) {
				gameState = gameOverState;
			}
		}
	}

	public void retry() {
		player.setDefaultPlayerProperties();
		enemies.clear();
		bullets.clear();
		tile = new TileManager(maxWorldRow, maxWorldCol, GameController.COLLISIONS_PATH + levelRelativePath);
		assetSetter.setObject(objects, enemies, tile.getValidPositions());
		gameState = playState;
		ui = new Ui();
	}

	@Override
	public void paint() {
		// Debug
		long drawStart = 0;
		if (KeyHandler.getInstance().isDrawTime()) {
			drawStart = System.nanoTime();
		}
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);

		// map
		tile.draw(graphicsContext, player.getWorldX(), player.getWorldY(), player.getScreenX(), player.getScreenY());

		// entityList
		addEntity();

		// Draw Entity
		for (EntityGame entityGame : entityList) {
			entityGame.print(graphicsContext, player);
		}

		//Draw pointer
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(mouseRectX, mouseRectY, mouseRect.getWidth(), mouseRect.getHeight());

		entityList.clear();

		//ui
		ui.Draw(graphicsContext, player, gameState, gameOverState, playState, gameWinState);

		//shot
		for (Bullet bullet : bullets) {
			bullet.paint(player);
		}

		//debug
		if (KeyHandler.getInstance().isDrawTime()) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			System.out.println(passed);
		}

	}


	public void addEntity() {
		entityList.add(player);
		for (EntityGame enemy : enemies) {
			if (enemy != null) {
				entityList.add(enemy);
			}
		}
		for (EntityGame object : objects) {
			if (object != null) {
				entityList.add(object);
			}
		}
		entityList.sort(Comparator.comparingInt(EntityGame::getWorldY));
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		KeyHandler.getInstance().keyPressed(event, this);
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		KeyHandler.getInstance().keyReleased(event);
	}

	@Override
	public void onMousePressed(MouseEvent event) {

		if (player.getTypeGun() != 0 && player.getBullets() != 0) {
			double playerScreenX = player.getScreenX();
			double playerScreenY = player.getScreenY();
			double diffX = event.getX() - playerScreenX;
			double diffY = event.getY() - playerScreenY;
			Vector diff = new Vector(diffX, diffY);
			int speed = player.gunActual(player.getTypeGun());
			diff.normalize();
			diff.setSpeed(speed);
			Vector diffPlayer = new Vector(player.getWorldX(), player.getWorldY());
			int num = player.bullets - 1;
			player.setBullets(num);
			bullets.add(new Bullet(player.getWorldX(), player.getWorldY(), speed, canvas, diffPlayer, diff, player));
		}
	}


	public void mouseMoved(MouseEvent mouseEvent) {
		mouseRectX = mouseEvent.getX();
		mouseRectY = mouseEvent.getY();
	}

	public void portalUpdate() {
		if (enemies.size() == 0) {
			if (GameController.LEVEL == 2) {
				gameState = gameWinState;
			} else {
				objects[1].setCollisionOn(true);
			}
		}
	}

	public Ui getUi() {
		return ui;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public TileManager getTile() {
		return tile;
	}

}
