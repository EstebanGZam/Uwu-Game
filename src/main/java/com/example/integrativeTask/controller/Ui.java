package com.example.integrativeTask.controller;

import com.example.integrativeTask.model.Character;
import com.example.integrativeTask.model.EntityGame;
import com.example.integrativeTask.model.Object;
import com.example.integrativeTask.model.Player;
import com.example.integrativeTask.screens.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Ui {

	private final Font arial_14;

	private final EntityGame heart;

	public int commandNum = 0;

	public Ui() {
		heart = new Object("\\heart_blank.png", 0, 0, 0, "AK-47", 1.5);
		heart.getImages().add(new Image(Object.TILES_PATH + "\\heart_full.png"));
		arial_14 = new Font("Arial", 14);
	}

	public void Draw(GraphicsContext graphicsContext, Player player, int gameState, int gameOverState, int gamePlayState, int gameWinState) {

		if (gameState == gamePlayState) {
			drawPlayerLife(graphicsContext, player);
			drawPlayerInventory(graphicsContext, player);
			drawPlayerBullets(graphicsContext, player);
			drawUseAmor(graphicsContext, player);
		}
		if (gameState == gameOverState) {
			drawGameOverScreen(graphicsContext);
		}
		if (gameState == gameWinState) {
			drawWinScreen(graphicsContext);
		}


	}

	// Method responsible for drawing the "GAME OVER" screen.
	public void drawGameOverScreen(GraphicsContext graphicsContext) {
		drawScreen(graphicsContext, "GAME OVER.");
	}

	// Method responsible for drawing the "WIN" screen.
	public void drawWinScreen(GraphicsContext graphicsContext) {
		drawScreen(graphicsContext, "Congratulations!");
	}

	public void drawScreen(GraphicsContext graphicsContext, String message) {
		int x;
		int y;
		String text;
		graphicsContext.setFill(new Color(0, 0, 0, 0.5));
		graphicsContext.fillRect(0, 0, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);

		graphicsContext.setFont(new Font("Arial", 110));
		text = message;

		graphicsContext.setFill(Color.BLACK);
		x = getXForCenteredText(graphicsContext, text);
		y = Screen.TILES_SIZE * 4;
		graphicsContext.fillText(text, x, y);

		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillText(text, x - 4, y - 4);

		// Try again.

		graphicsContext.setFont(new Font("Arial", 50));
		text = "Space to Play Again";
		x = getXForCenteredText(graphicsContext, text);
		y += Screen.TILES_SIZE * 4;
		graphicsContext.fillText(text, x, y);

		if (commandNum == 0) {
			graphicsContext.fillText(">", x - 40, y);

		}

	}

	public int getXForCenteredText(GraphicsContext graphicsContext, String text) {
		Text tempText = new Text(text);
		tempText.setFont(graphicsContext.getFont());

		double textWidth = tempText.getBoundsInLocal().getWidth();
		double x = graphicsContext.getCanvas().getWidth() / 2 - textWidth / 2;

		return (int) x;
	}

	public void drawPlayerLife(GraphicsContext g, Player player) {
		int x = Screen.TILES_SIZE / 2;
		int y = Screen.TILES_SIZE / 2;
		int i = 0;

		while (i < Character.MAX_LIVES / 2) {
			g.drawImage(heart.getImages().get(0), x, y, 24, 24);
			i++;
			x += Screen.TILES_SIZE;
		}

		x = Screen.TILES_SIZE / 2;
		i = 0;

		while (i < player.getLives()) {
			g.drawImage(heart.getImages().get(1), x, y, 24, 24);
			i++;
			x += Screen.TILES_SIZE;
		}
	}

	public void drawPlayerInventory(GraphicsContext g, Player player) {
		int x = Screen.TILES_SIZE / 2;
		int y = (Screen.TILES_SIZE / 2) * 2;
		int i = 0;

		while (i < player.getInventory().size()) {
			g.drawImage(player.getInventory().get(i).getImages().get(0), x, y, 48, 48);
			i++;
			x += Screen.TILES_SIZE;
		}

	}

	public void drawPlayerBullets(GraphicsContext graphicsContext, Player player) {
		int x = Screen.TILES_SIZE / 2;
		int y = (Screen.TILES_SIZE / 2) * 2 + 60;

		graphicsContext.setFont(arial_14);
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillText("Bullets := " + player.getBullets(), x, y);

	}

	public void drawUseAmor(GraphicsContext graphicsContext, Player player) {
		int x = Screen.TILES_SIZE / 2;
		int y = (Screen.TILES_SIZE / 2) * 2 + 40;

		if (player.getInventory() != null) {
			if (player.getTypeGun() == 1) {
				graphicsContext.setFill(Color.YELLOW);
				graphicsContext.fillRect(x, y, 48, 3);
			} else if (player.getTypeGun() == 2) {
				graphicsContext.setFill(Color.YELLOW);
				graphicsContext.fillRect(x + 48, y, 48, 3);
			}
		}
	}
}
