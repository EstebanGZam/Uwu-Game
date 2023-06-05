package com.example.integrativeTask.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class BaseScreen {
	public static Canvas canvas;
	protected GraphicsContext graphicsContext;

	public BaseScreen(Canvas canvas) {
		BaseScreen.canvas = canvas;
		this.graphicsContext = canvas.getGraphicsContext2D();
	}

	public abstract void paint();

	public abstract void onKeyPressed(KeyEvent event);

	public abstract void onKeyReleased(KeyEvent event);

	public abstract  void onMousePressed(MouseEvent event);

}
