package com.example.integrativeTask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu-1.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 768, 576);
		stage.setTitle("Icesi Games");
		stage.setScene(scene);
		stage.show();
	}

	public static void showWindow(String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
		Scene scene;
		try {
			scene = new Scene(fxmlLoader.load(), 768, 576);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void hideWindow(Stage stage) {
		stage.close();
	}

	public static void main(String[] args) {
		launch();
	}
}