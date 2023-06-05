module com.example.integrativeTask {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;

	opens com.example.integrativeTask to javafx.fxml;
	exports com.example.integrativeTask;
	exports com.example.integrativeTask.control;
	opens com.example.integrativeTask.control to javafx.fxml;
	exports com.example.integrativeTask.controller;
	opens com.example.integrativeTask.controller to javafx.fxml;
	exports com.example.integrativeTask.model;
	opens com.example.integrativeTask.model to javafx.fxml;
	exports com.example.integrativeTask.screens;
	opens com.example.integrativeTask.screens to javafx.fxml;
	exports com.example.integrativeTask.util;
	opens com.example.integrativeTask.util to javafx.fxml;

}