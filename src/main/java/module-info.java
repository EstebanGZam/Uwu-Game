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

}