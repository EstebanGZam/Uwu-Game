package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.Music;
import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.screens.Level;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	public static final String MAIN_RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
	public static final String IMAGES_PATH = MAIN_RESOURCES_PATH + "\\images";
	public static final String AUDIO_PATH = MAIN_RESOURCES_PATH + "\\audio";
	public static final String COLLISIONS_PATH = MAIN_RESOURCES_PATH + "\\collisions";
	public static int LEVEL = 0;
	private ArrayList<Level> levels;
	private Player player;

	@FXML
	private Canvas canvas;
	@FXML
	private GraphicsContext graphics;
	@FXML
	private Music music;
	private boolean isRunning;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		start();
	}

	private void start() {
		graphics = canvas.getGraphicsContext2D();
		isRunning = true;
		levels = new ArrayList<>();
		player = new Player(100, 100, 4, 3, canvas.getGraphicsContext2D());
		canvas.setFocusTraversable(true);
		soundtrack();
		printInCanvas();
		levels.add(new Level(44, 30, canvas, "\\Level-1.txt", player,1));
		levels.add(new Level(44, 30, canvas, "\\Level-3.txt", player,3));
		initEvents();
	}

	public void soundtrack() {
		music = new Music(AUDIO_PATH + "\\u.mpeg", true);
		music.play();
	}

	public void printInCanvas() {
		graphics.drawImage(getImage("\\menu2.jpg"), 0, 0, 768.0, 576.0);
		graphics.drawImage(getImage("\\logo.png"), -100, -100, 960, 776);
		graphics.drawImage(getImage("\\enter.png"), 0, 130, 768, 576);
	}

	private Image getImage(String relativePath) {
		return new Image(IMAGES_PATH + relativePath);
	}


	public void run() {
		new Thread(() -> {
			long targetFPS = 60; // El objetivo es ejecutar el juego a 60 fotogramas por segundo
			long targetFrameTime = 1000 / targetFPS; // Tiempo objetivo por fotograma en milisegundos
			long lastDrawTime = System.currentTimeMillis();
			while (isRunning) {
				long currentTime, elapsedTime;

				levels.get(LEVEL).update();

				currentTime = System.currentTimeMillis();
				elapsedTime = currentTime - lastDrawTime;
				if (elapsedTime >= targetFrameTime) {
					lastDrawTime = currentTime;
					levels.get(LEVEL).paint();
				}

				// Calcular el tiempo de espera antes del siguiente fotograma
				long remainingTime = targetFrameTime - (System.currentTimeMillis() - lastDrawTime);
				if (remainingTime > 0) {
					pause(remainingTime);
				}
			}
		}).start();
	}

	public void initEvents() {
		canvas.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) run();
			levels.get(LEVEL).onKeyPressed(event);
		});
		canvas.setOnKeyReleased(event -> {
			levels.get(LEVEL).onKeyReleased(event);
		});

	}

	private void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}

}