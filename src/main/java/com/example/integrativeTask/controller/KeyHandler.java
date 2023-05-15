package com.example.integrativeTask.controller;

import javafx.scene.input.KeyEvent;

public class KeyHandler {
	private static KeyHandler instance;

	public static KeyHandler getInstance() {
		if (instance == null)
			instance = new KeyHandler();
		return instance;
	}

	private boolean upPressed, downPressed, leftPressed, rightPressed;

	public void keyPressed(KeyEvent event) {
		switch (event.getCode()) {
			case W -> upPressed = true;
			case S -> downPressed = true;
			case A -> leftPressed = true;
			case D -> rightPressed = true;
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
}
