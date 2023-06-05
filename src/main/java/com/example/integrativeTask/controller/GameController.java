package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.Music;
import com.example.integrativeTask.model.Player;
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

public class GameController implements Initializable {
	public static final String MAIN_RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
	public static final String IMAGES_PATH = MAIN_RESOURCES_PATH + "\\images";
	public static final String AUDIO_PATH = MAIN_RESOURCES_PATH + "\\audio";
	public static final String COLLISIONS_PATH = MAIN_RESOURCES_PATH + "\\collisions";
	public static int LEVEL = 0; // Variable defining the current level
	public static final int MAXIMUM_NUMBER_OF_LEVELS = 3; // Constant defining the maximum number of levels
	private ArrayList<Level> levels;
	private Player player;
	@FXML
	private Canvas canvas;
	@FXML
	private GraphicsContext graphics;
	@FXML
	private Music music;
	private boolean isRunning;
	public static boolean reset;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		start();
	}

	private void start() {
		graphics = canvas.getGraphicsContext2D();
		reset = false;
		isRunning = true;
		player = new Player(4, 3);
		canvas.setFocusTraversable(true);
		soundtrack();
		printInCanvas();

		this.levels = initLevels();
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
			long targetFPS = 60; // The goal is to run the game at 60 frames per second.
			long targetFrameTime = 1000 / targetFPS; // target time per frame in milliseconds.
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
				if (reset) {
					resetGame();
				}
				// Calculate the time to wait before the next frame
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
			setRunning(true);
		});
		canvas.setOnKeyReleased(event -> levels.get(LEVEL).onKeyReleased(event));
		canvas.setOnMouseMoved(mouseEvent -> levels.get(LEVEL).mouseMoved(mouseEvent));
		canvas.setOnMousePressed(mouseEvent -> levels.get(LEVEL).onMousePressed(mouseEvent));
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

	public void resetGame() {
		setRunning(false); // Stop game thread
		LEVEL = 0;
		reset = false;
		player = new Player(4, 3);

		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphics = canvas.getGraphicsContext2D();
		canvas.setFocusTraversable(true);

		printInCanvas();
		this.levels = initLevels();
		initEvents();
	}

	private ArrayList<Level> initLevels() {
		ArrayList<Level> levels = new ArrayList<>();
		for (int i = 1; i <= MAXIMUM_NUMBER_OF_LEVELS; i++)
			levels.add(new Level(44, 30, canvas, "\\Level-" + i + ".txt", player, i));
		return levels;
	}

}