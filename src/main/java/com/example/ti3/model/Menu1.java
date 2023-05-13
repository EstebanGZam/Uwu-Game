package com.example.ti3.model;

import com.example.ti3.Control.Music;
import com.example.ti3.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu1 implements Initializable {


    @FXML
    private Canvas canvas;

    private GraphicsContext graficos;

    private Music music;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciar();
        print();
        actualizarEstado();

    }

    private void iniciar() {
        graficos = canvas.getGraphicsContext2D();
        soundtrack();
    }


    public void soundtrack(){
        music= new Music("src\\main\\java\\com\\example\\ti3\\image\\u.mpeg",true);
        music.reproducir();

    }

    public void print(){

        File menu2File = new File("src/main/java/com/example/ti3/image/menu2.jpg");
        Image menu2Image = new Image(menu2File.toURI().toString());
        graficos.drawImage(menu2Image, 0, 0, 700, 500);

        File logo2File = new File("src/main/java/com/example/ti3/image/logo2.png");
        Image logo2Image = new Image(logo2File.toURI().toString());
        graficos.drawImage(logo2Image, -100, -100, 900, 700);

        File enterFile = new File("src/main/java/com/example/ti3/image/enter.png");
        Image enterImage = new Image(enterFile.toURI().toString());
        graficos.drawImage(enterImage, 0, 130, 700, 500);

    }

    public void actualizarEstado() {
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().toString() == "ENTER") {
                    music.detener();
                    Main.hideWindow((Stage) canvas.getScene().getWindow());
                    Main.showWindow("Level1");
                }
            }
        });
    }








}