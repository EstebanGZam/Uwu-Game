package com.example.integrativeTask.util;

import com.example.integrativeTask.controller.GameController;
import com.example.integrativeTask.screens.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
	private static KeyHandler instance;

	public static KeyHandler getInstance() {
		if (instance == null)
			instance = new KeyHandler();
		return instance;
	}

	private boolean upPressed, downPressed, leftPressed, rightPressed, drawTime;

	public void keyPressed(KeyEvent event, Level level) {

		if (level.gameState == level.playState) {
			switch (event.getCode()) {
				case W -> upPressed = true;
				case S -> downPressed = true;
				case A -> leftPressed = true;
				case D -> rightPressed = true;
				case C -> {
					if (level.getPlayer().getTypeGun() >= level.getPlayer().getInventory().size()) {
						level.getPlayer().setTypeGun(0);
						System.out.println("The gun was removed, now you have no gun.");
					} else {
						level.getPlayer().setTypeGun(level.getPlayer().getTypeGun() + 1);
						System.out.println("The weapon was changed");
					}
				}
				case T -> drawTime = !drawTime;
				case M -> level.getEnemies().clear();
				case R -> level.getPlayer().setBullets(60);
			}
		} else if (level.gameState == level.gameOverState) {
			if (event.getCode() == KeyCode.SPACE) {
				if (level.getUi().commandNum == 0) {
					level.gameState = level.playState;
					level.retry();
				}
			}
		} else if (level.gameState == level.gameWinState) {
			if (event.getCode() == KeyCode.SPACE) {
				if (level.getUi().commandNum == 0) {
					GameController.reset = true;
				}
			}
		}

	}

	public void keyReleased(KeyEvent event) {
		switch (event.getCode()) {
			case W -> upPressed = false;
			case S -> downPressed = false;
			case A -> leftPressed = false;
			case D -> rightPressed = false;
		}
	}

	public boolean isKeyMovementPressed() {
		return isUpPressed() || isDownPressed() || isLeftPressed() || isRightPressed();
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isDrawTime() {
		return drawTime;
	}

}
