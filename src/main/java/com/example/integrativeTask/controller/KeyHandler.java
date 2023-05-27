package com.example.integrativeTask.controller;

import com.example.integrativeTask.screens.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyHandler {
	private static KeyHandler instance;
	public static KeyHandler getInstance() {
		if (instance == null)
			instance = new KeyHandler();
		return instance;
	}

	private boolean upPressed, downPressed, leftPressed, rightPressed,drawTime, enterPressed;

	public void keyPressed(KeyEvent event) {

		switch (event.getCode()) {
			case W -> upPressed = true;
			case S -> downPressed = true;
			case A -> leftPressed = true;
			case D -> rightPressed = true;
			case T -> {
				if(drawTime==false){
					drawTime=true;
				}else if(drawTime==true){
					drawTime=false;
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

	public void gameOverState(KeyEvent e, Level level) {
		KeyCode code = e.getCode();

		if (code == KeyCode.ENTER) {
			level.getUi().commandNum--;
			if (level.getUi().commandNum < 0) {
				level.getUi().commandNum = 1;
			}
		}

		if (code == KeyCode.ENTER) {
			if (level.getUi().commandNum == 0) {
				level.gameState = level.playState;
				level.retry();
			}
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

	public boolean isDrawTime() {
		return drawTime;
	}

	public boolean  isEnterPressed(){
		return  enterPressed;
	}

	public void setDrawTime(boolean drawTime) {
		this.drawTime = drawTime;
	}
}
