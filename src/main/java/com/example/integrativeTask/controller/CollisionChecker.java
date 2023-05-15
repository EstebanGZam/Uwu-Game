package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.GameObject;
import com.example.integrativeTask.control.TileManager;

public class CollisionChecker {

	public void checkTitle(GameObject ob, TileManager tile) {

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
			case "up" :
				topRow = (TopWorldY - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][leftCol];
				tileNum2 = tile.getMapTileNum()[topRow][rightCol];
				if (tile.getTile()[tileNum1].collision || tile.getTile()[tileNum2].collision) {
					ob.setCollisionOn(true);
				}
				break;
			case "down" :
				bottomRow = (BottomWorldY + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[bottomRow][leftCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].collision == true|| tile.getTile()[tileNum2].collision == true) {
					ob.setCollisionOn(true);
				}
				break;
			case "left" :
				leftCol = (leftWorldX - ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][leftCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][leftCol];
				if (tile.getTile()[tileNum1].collision  == true|| tile.getTile()[tileNum2].collision == true) {
					ob.setCollisionOn(true);
				}
				break;
			case "right" :
				rightCol = (rightWorldX + ob.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][rightCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].collision  == true|| tile.getTile()[tileNum2].collision == true) {
					ob.setCollisionOn(true);
				}
				break;
		}
	}


}
