package com.example.integrativeTask.model;

import com.example.integrativeTask.Control.Music;
import com.example.integrativeTask.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Menu1 implements Initializable {

	@FXML
	private Canvas canvas;

	private GraphicsContext graphics;

	private Music music;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		start();
		print();
		updateState();
	}

	private void start() {
		graphics = canvas.getGraphicsContext2D();
		soundtrack();
	}


	public void soundtrack() {
		music = new Music("src\\main\\java\\com\\example\\integrativeTask\\image\\u.mpeg", true);
		music.play();
	}

	public void print() {

		File menu2File = new File("src\\main\\java\\com\\example\\integrativeTask\\image\\menu2.jpg");
		Image menu2Image = new Image(menu2File.toURI().toString());
		graphics.drawImage(menu2Image, 0, 0, 768.0, 576.0);

		File logo2File = new File("src\\main\\java\\com\\example\\integrativeTask\\image\\logo2.png");
		Image logo2Image = new Image(logo2File.toURI().toString());
		graphics.drawImage(logo2Image, -100, -100, 960, 776);

		File enterFile = new File("src\\main\\java\\com\\example\\integrativeTask\\image\\enter.png");
		Image enterImage = new Image(enterFile.toURI().toString());
		graphics.drawImage(enterImage, 0, 130, 768, 576);

	}

	public void updateState() {
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(keyEvent -> {
			if (Objects.equals(keyEvent.getCode().toString(), "ENTER")) {
				music.stop();
				Main.hideWindow((Stage) canvas.getScene().getWindow());
				Main.showWindow("Level-3");
			}
		});
	}

}