package com.example.integrativeTask.Controller;

import com.example.integrativeTask.Control.Player;
import com.example.integrativeTask.Control.Screen;
import com.example.integrativeTask.Control.TileManager;
import com.example.integrativeTask.model.CollisionChecker;
import com.example.integrativeTask.model.KeyHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Level1Controller extends Thread implements Initializable {

	public final int maxWorldCol = 44;
	public final int maxWorldRow = 30;
	public final int worldWidth = Screen.tilesSize * maxWorldCol;//1536
	public final int worldHeight = Screen.tilesSize * maxWorldRow;//816

	public Canvas canvas;

	int fps = 60;

	private GraphicsContext graphicsContext;
	Thread game;

	TileManager tile = new TileManager(graphicsContext, maxWorldRow, maxWorldCol, "src\\main\\java\\com\\example\\integrativeTask\\level-1.txt");

	CollisionChecker collisionChecker = new CollisionChecker();

	KeyHandler keyHandler = new KeyHandler();

	Player player = new Player("", 100, 100, 4, 3, graphicsContext, keyHandler);

	@Override
	public void run() {
		double drawInterval = 1000000000 / fps;
		double nextDraw = System.nanoTime() + drawInterval;
		while (game != null) {

			update();

			paintComponent(graphicsContext);

			try {
				double remaingingTime = nextDraw - System.nanoTime();
				remaingingTime = remaingingTime / 1000000;
				if (remaingingTime < 0) {
					remaingingTime = 0;
				}
				Thread.sleep((long) remaingingTime);
				nextDraw += drawInterval;
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public void update() {
		player.mover(collisionChecker, tile);
	}

	public void paintComponent(GraphicsContext g) {
		g = canvas.getGraphicsContext2D();
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, 768, 576);
		tile.draw(g, player.getWorldX(), player.getWorldY(), player.ScreenX, player.ScreenY);
		player.print(g);

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		game = new Thread(this);
		game.start();
		updateStatus();
	}

	public void updateStatus() {
		canvas.setFocusTraversable(true);

		canvas.setOnKeyPressed(event -> {
			KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
			keyHandler.keyPressed(keyEvent);
		});

		canvas.setOnKeyReleased(event -> {
			KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
			keyHandler.keyReleased(keyEvent);
		});
	}

}
