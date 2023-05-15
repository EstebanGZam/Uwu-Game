package com.example.integrativeTask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		FXMLLoader fxmlLoader = renderView("main-view.fxml", "Uwu game");
	}

	public static FXMLLoader renderView(String fxml, String title) {
		FXMLLoader fxmlLoader = null;
		try {
			fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
			Scene scene = new Scene(fxmlLoader.load(), 768, 576);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fxmlLoader;
	}

	public static void hideWindow(Stage stage) {
		stage.close();
	}

	public static void main(String[] args) {
		launch();
	}
}