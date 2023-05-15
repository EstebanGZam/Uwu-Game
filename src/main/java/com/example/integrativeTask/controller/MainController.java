package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.Music;
import com.example.integrativeTask.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	public static String MAIN_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\integrativeTask";
	public static String IMAGES_PATH = MAIN_PATH + "\\image";
	public static String AUDIO_PATH = MAIN_PATH + "\\audio";

	@FXML
	private Canvas canvas;
	@FXML
	private GraphicsContext graphics;
	@FXML
	private Music music;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		start();
	}

	private void start() {
		graphics = canvas.getGraphicsContext2D();
		soundtrack();
		printInCanvas();
		updateState();
	}


	public void soundtrack() {
		music = new Music(AUDIO_PATH + "\\u.mpeg", true);
		music.play();
	}

	public void printInCanvas() {
		graphics.drawImage(getImage("\\menu2.jpg"), 0, 0, 768.0, 576.0);
		graphics.drawImage(getImage("\\logo2.png"), -100, -100, 960, 776);
		graphics.drawImage(getImage("\\enter.png"), 0, 130, 768, 576);
	}

	private Image getImage(String relativePath) {
		return new Image(IMAGES_PATH + relativePath);
	}

	public void updateState() {
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode().equals(KeyCode.ENTER)) {
				music.stop();
				Main.closeWindow((Stage) canvas.getScene().getWindow());
				Main.renderView("level-1-view.fxml", "Level 1");
			}
		});
	}

}