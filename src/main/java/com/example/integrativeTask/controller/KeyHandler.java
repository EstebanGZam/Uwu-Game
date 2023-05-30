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

	private boolean upPressed, downPressed, leftPressed, rightPressed,drawTime;

	public void keyPressed(KeyEvent event,Level level) {

       if(level.gameState==level.playState){
		   switch (event.getCode()) {
			   case W -> upPressed = true;
			   case S -> downPressed = true;
			   case A -> leftPressed = true;
			   case D -> rightPressed = true;
			   case C -> {
				   if(level.getPlayer().getInventory().size() == 0){
					   
				   } else if (level.getPlayer().getTypeGun() >= level.getPlayer().getInventory().size() ) {
					   level.getPlayer().setTypeGun(0);
					   System.out.println("se quito el arma, ahora no tienes arma");
					   
				   } else {
					   level.getPlayer().setTypeGun(level.getPlayer().getTypeGun()+1);
					   System.out.println("se cambio el arma");
				   }

			   }
			   case T -> {
				   if(drawTime==false){
					   drawTime=true;
				   }else if(drawTime==true){
					   drawTime=false;
				   }
			   }
			   case M -> {
	             level.getEnemies().clear();
			   }
			   case R -> {
				   level.getPlayer().setBullets(60);
			   }
		   }
	   }else if(level.gameState==level.gameOverState){
		   if(event.getCode()==KeyCode.SPACE){
			   if (level.getUi().commandNum == 0) {
				   level.gameState = level.playState;
				   level.retry();
			   }
		   }
	   }
	   else if(level.gameState==level.gameWinState){
		   if(event.getCode()==KeyCode.SPACE){
			   if (level.getUi().commandNum == 0) {
                 MainController.reset=true;
			   }
		   }
	   }

	}

	public void keyReleased(KeyEvent event,Level level) {
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

	public boolean isDrawTime() {
		return drawTime;
	}


	public void setDrawTime(boolean drawTime) {
		this.drawTime = drawTime;
	}
}
