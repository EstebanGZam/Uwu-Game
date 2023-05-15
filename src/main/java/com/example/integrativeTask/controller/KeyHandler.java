package com.example.integrativeTask.controller;

import javafx.scene.input.KeyEvent;

public class KeyHandler {

	public boolean upPressed, downPressed, leftPressed, rightPressed;

	public void keyPressed(KeyEvent e) {
		if (e.getCode().toString() == "W") {
			upPressed = true;
		}
		if (e.getCode().toString() == "S") {
			downPressed = true;
		}
		if (e.getCode().toString() == "A") {
			leftPressed = true;
		}
		if (e.getCode().toString() == "D") {
			rightPressed = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getCode().toString() == "W") {
			upPressed = false;
		}
		if (e.getCode().toString() == "S") {
			downPressed = false;
		}
		if (e.getCode().toString() == "A") {
			leftPressed = false;
		}
		if (e.getCode().toString() == "D") {
			rightPressed = false;
		}

	}

}
