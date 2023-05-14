package com.example.integrativeTask.model;

import com.example.integrativeTask.Control.GameObject;
import com.example.integrativeTask.Control.TileManager;

public class CollisionChecker {

	public void checkTitle(GameObject ob, TileManager tile) {

		int leftWorldX = ob.getWorldX() + ob.getArea().x;
		int rightWorldY = ob.getWorldX() + ob.getArea().x + ob.getArea().width;
		int TopWorldY = ob.getWorldY() + ob.getArea().y;
		int BottomWorldY = ob.getWorldY() + ob.getArea().y + ob.getArea().height;

		int leftCol = leftWorldX / 48;
		int rightCol = rightWorldY / 48;
		int topRow = TopWorldY / 48;
		int bottomRow = BottomWorldY / 48;

		int tileNum1, tileNum2;

		switch (ob.getDirection()) {
			case "up" -> {
				topRow = (TopWorldY - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[leftCol][topRow];
				tileNum2 = tile.getMapTileNum()[rightCol][topRow];
				if (tile.getTile()[tileNum1].collision || tile.getTile()[tileNum2].collision) {
					ob.setCollisionOn(true);
				}
			}
			case "down" -> {
				bottomRow = (BottomWorldY + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[leftCol][bottomRow];
				tileNum2 = tile.getMapTileNum()[rightCol][bottomRow];
				if (tile.getTile()[tileNum1].collision || tile.getTile()[tileNum2].collision) {
					ob.setCollisionOn(true);
				}
			}
			case "left" -> {
				leftCol = (leftWorldX - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[leftCol][topRow];
				tileNum2 = tile.getMapTileNum()[leftCol][bottomRow];
				if (tile.getTile()[tileNum1].collision || tile.getTile()[tileNum2].collision) {
					ob.setCollisionOn(true);
				}
			}
			case "right" -> {
				rightCol = (rightWorldY + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[rightCol][topRow];
				tileNum2 = tile.getMapTileNum()[rightCol][bottomRow];
				if (tile.getTile()[tileNum1].collision || tile.getTile()[tileNum2].collision) {
					ob.setCollisionOn(true);
				}
			}
		}
	}


}
