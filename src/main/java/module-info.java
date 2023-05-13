module com.example.ti3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.example.ti3 to javafx.fxml;
    exports com.example.ti3.model;
    opens com.example.ti3.model to javafx.fxml;
    exports com.example.ti3;
}