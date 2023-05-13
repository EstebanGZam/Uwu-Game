package com.example.ti3.model;

import com.example.ti3.Control.Music;
import com.example.ti3.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
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
    public Button Bplay;
    @FXML
    private Canvas canvas;

    private GraphicsContext graficos;

    private Music music;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   iniciar();
   print();
    }

    private void iniciar() {
        graficos = canvas.getGraphicsContext2D();
        soundtrack();
    }


    public void soundtrack(){

        //File file = new File("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\u.mpeg");
        music= new Music("src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\u.mpeg",true);
        music.reproducir();

    }

    public void print(){
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\menu2.jpg"),0,0,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\up.png"),-250,-190,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\down.png"),-250,-65,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\right.png"),-230,30,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\left.png"),-230,130,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\shoot.png"),130,-135,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\change.png"),0,-30,700,500);
        graficos.drawImage(new Image("C:\\Users\\shern\\OneDrive\\Documentos\\clases\\Semestre3\\ApoII\\TI3\\src\\main\\java\\com\\example\\ti3\\com.example.ti3.image\\leave.png"),80,100,700,500);
    }



    public void play(ActionEvent actionEvent) {




    }
}
