package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.GameObject;
import com.example.integrativeTask.control.TileManager;

public class CollisionChecker {
	private static CollisionChecker instance;

	public static CollisionChecker getInstance() {
		if (instance == null)
			instance = new CollisionChecker();
		return instance;
	}

	public void checkTile(GameObject ob, TileManager tile) {

		int leftWorldX = ob.getWorldX() + ob.getArea().x;
		int rightWorldX = ob.getWorldX() + ob.getArea().x + ob.getArea().width;
		int TopWorldY = ob.getWorldY() + ob.getArea().y;
		int BottomWorldY = ob.getWorldY() + ob.getArea().y + ob.getArea().height;

		int leftCol = leftWorldX / 48;
		int rightCol = rightWorldX / 48;
		int topRow = TopWorldY / 48;
		int bottomRow = BottomWorldY / 48;

		int tileNum1, tileNum2;

		switch (ob.getDirection()) {
			case "up" -> {
				topRow = (TopWorldY - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][leftCol];
				tileNum2 = tile.getMapTileNum()[topRow][rightCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					ob.setCollisionOn(true);
				}
			}
			case "down" -> {
				bottomRow = (BottomWorldY + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[bottomRow][leftCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					ob.setCollisionOn(true);
				}
			}
			case "left" -> {
				leftCol = (leftWorldX - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][leftCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][leftCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					ob.setCollisionOn(true);
				}
			}
			case "right" -> {
				rightCol = (rightWorldX + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][rightCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					ob.setCollisionOn(true);
				}
			}
		}
	}


}
