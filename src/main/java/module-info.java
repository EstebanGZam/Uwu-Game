module com.example.integrativeTask {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;

	opens com.example.integrativeTask to javafx.fxml;
	exports com.example.integrativeTask.model;
	opens com.example.integrativeTask.model to javafx.fxml;
	exports com.example.integrativeTask;
	exports com.example.integrativeTask.Control;
	opens com.example.integrativeTask.Control to javafx.fxml;
	exports com.example.integrativeTask.Controller;
	opens com.example.integrativeTask.Controller to javafx.fxml;

}