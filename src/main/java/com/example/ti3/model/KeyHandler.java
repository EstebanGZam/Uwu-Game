package com.example.ti3.model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler {

    public boolean upPresed, downPressed,leftPressed, rightPressed;


    public void keyPressed( KeyEvent e){
        if (e.getCode().toString() == "W") {
             upPresed=true;
        }
        if (e.getCode().toString() == "S") {
            downPressed= true;
        }
        if (e.getCode().toString() == "A") {
             leftPressed=true;
        }
        if (e.getCode().toString() == "D") {
            rightPressed=true;
        }

    }

    public void keyReleased(KeyEvent e){
        if (e.getCode().toString() == "W") {
            upPresed=false;
        }
        if (e.getCode().toString() == "S") {
            downPressed= false;
        }
        if (e.getCode().toString() == "A") {
            leftPressed=false;
        }
        if (e.getCode().toString() == "D") {
            rightPressed=false;
        }

    }

}
