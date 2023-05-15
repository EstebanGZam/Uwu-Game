package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.control.Screen;
import com.example.integrativeTask.control.TileManager;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Level3 extends Thread implements Initializable {
    public final int maxWorldCol = 44;
    public final int maxWorldRow = 30;
    public final int worldWidth = Screen.tilesSize * maxWorldCol;//1536
    public final int worldHeight = Screen.tilesSize * maxWorldRow;//816

    public Canvas canvas;

    int fps = 60;

    private GraphicsContext graphicsContext;
    Thread game;

    TileManager tile = new TileManager(graphicsContext, maxWorldRow, maxWorldCol, "src\\main\\java\\com\\example\\integrativeTask\\Level-3.txt");

    CollisionChecker collisionChecker = new CollisionChecker();

    KeyHandler keyHandler = new KeyHandler();

    Player player = new Player("", 100, 100, 4, 3, graphicsContext, keyHandler);

    @Override
    public void run() {
        long targetFPS = 60; // El objetivo es ejecutar el juego a 60 fotogramas por segundo
        long targetFrameTime = 1000 / targetFPS; // Tiempo objetivo por fotograma en milisegundos
        long lastUpdateTime = System.currentTimeMillis();
        long lastDrawTime = System.currentTimeMillis();

        while (game != null) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            update();

            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastDrawTime;
            if (elapsedTime >= targetFrameTime) {
                lastDrawTime = currentTime;
                paintComponent(graphicsContext);
            }

            // Calcular el tiempo de espera antes del siguiente fotograma
            long remainingTime = targetFrameTime - (System.currentTimeMillis() - lastDrawTime);
            if (remainingTime > 0) {
                try {
                    Thread.sleep(remainingTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void update() {
        player.move(collisionChecker, tile);
    }

    public void paintComponent(GraphicsContext g) {
        g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 768, 576);
        tile.draw(g, player.getWorldX(), player.getWorldY(), player.ScreenX, player.ScreenY);
        player.print(g);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Thread(this);
        game.start();
        updateStatus();
    }

    public void updateStatus() {
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(event -> {
            KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
            keyHandler.keyPressed(keyEvent);
        });

        canvas.setOnKeyReleased(event -> {
            KeyEvent keyEvent = new KeyEvent(null, null, null, event.getCode(), false, false, false, false);
            keyHandler.keyReleased(keyEvent);
        });
    }

}
