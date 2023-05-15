package com.example.integrativeTask.control;

import javafx.scene.image.Image;

public class Tile {

	private final Image image;

	private boolean collision = false;

    public Tile(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
