package com.example.integrativeTask.screens;

import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.controller.CollisionChecker;
import com.example.integrativeTask.controller.KeyHandler;
import com.example.integrativeTask.controller.MainController;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Level extends BaseScreen {
	private final int maxWorldCol, maxWorldRow, worldWidth, worldHeight;
	private final TileManager tile;
	private final Player player;

	public Level(int maxWorldColumns, int maxWorldRow, Canvas canvas, String levelRelativePath, Player player) {
		super(canvas);
		this.maxWorldCol = maxWorldColumns;
		this.maxWorldRow = maxWorldRow;
		this.worldWidth = Screen.TILES_SIZE * maxWorldColumns; // 1536
		this.worldHeight = Screen.TILES_SIZE * maxWorldRow; // 816
		this.tile = new TileManager(maxWorldRow, maxWorldColumns, MainController.COLLISIONS_PATH + levelRelativePath);
		this.player = player;
	}

	public void update() {
		player.move(CollisionChecker.getInstance(), tile);
	}

	@Override
	public void paint() {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);
		tile.draw(graphicsContext, player.getWorldX(), player.getWorldY(), player.ScreenX, player.ScreenY);
		player.print(graphicsContext);
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		KeyHandler.getInstance().keyPressed(event);
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		KeyHandler.getInstance().keyReleased(event);
	}
}
