package com.example.integrativeTask.model;

import com.example.integrativeTask.Control.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class Menu2 implements Initializable {

	public TextField up;
	public TextField down;
	public TextField right;
	public TextField left;
	public TextField shoot;
	public TextField change;
	public TextField leave;
	public Button playButton;
	@FXML
	private Canvas canvas;

	private GraphicsContext graphicsContext;

	private Music music;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		start();
		print();
	}

	private void start() {
		graphicsContext = canvas.getGraphicsContext2D();
		soundtrack();
	}


	public void soundtrack() {

		//File file = new File("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\u.mpeg");
		music = new Music("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\u.mpeg", true);
		music.play();

	}

	public void print() {
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\menu2.jpg"), 0, 0, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\up.png"), -250, -190, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\down.png"), -250, -65, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\right.png"), -230, 30, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\left.png"), -230, 130, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\shoot.png"), 130, -135, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\change.png"), 0, -30, 700, 500);
		graphicsContext.drawImage(new Image("src\\main\\java\\com\\example\\integrativeTask\\com.example.integrativeTask.image\\leave.png"), 80, 100, 700, 500);
	}


	public void play(ActionEvent actionEvent) {

	}
}
