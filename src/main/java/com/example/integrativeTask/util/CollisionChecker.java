package com.example.integrativeTask.util;

import com.example.integrativeTask.model.EntityGame;
import com.example.integrativeTask.model.Player;
import com.example.integrativeTask.control.TileManager;

import java.util.ArrayList;

public class CollisionChecker {
	private static CollisionChecker instance;

	public static CollisionChecker getInstance() {
		if (instance == null)
			instance = new CollisionChecker();
		return instance;
	}

	public void checkTile(EntityGame entityGame, TileManager tile) {
		int leftWorldX = entityGame.getWorldX() + entityGame.getArea().x;
		int rightWorldX = entityGame.getWorldX() + entityGame.getArea().x + entityGame.getArea().width;
		int TopWorldY = entityGame.getWorldY() + entityGame.getArea().y;
		int BottomWorldY = entityGame.getWorldY() + entityGame.getArea().y + entityGame.getArea().height;

		int leftCol = leftWorldX / 48;
		int rightCol = rightWorldX / 48;
		int topRow = TopWorldY / 48;
		int bottomRow = BottomWorldY / 48;

		int tileNum1, tileNum2;

		switch (entityGame.getDirection()) {
			case "up" ->
					collisionInHorizontal(entityGame, tile, leftCol, rightCol, tile.getMapTileNum()[topRow], tile.getMapTileNum()[topRow]);
			case "down" -> {
				bottomRow = (BottomWorldY + entityGame.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[bottomRow][leftCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					entityGame.setCollisionOn(true);
				}
			}
			case "left" ->
					collisionInHorizontal(entityGame, tile, leftCol, leftCol, tile.getMapTileNum()[topRow], tile.getMapTileNum()[bottomRow]);
			case "right" -> {
				rightCol = (rightWorldX + entityGame.getSpeed()) / (48);
				tileNum1 = tile.getMapTileNum()[topRow][rightCol];
				tileNum2 = tile.getMapTileNum()[bottomRow][rightCol];
				if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
					entityGame.setCollisionOn(true);
				}
			}
		}
	}

	private void collisionInHorizontal(EntityGame ob, TileManager tile, int leftCol, int rightCol, int[] ints, int[] ints2) {
		int tileNum1;
		int tileNum2;
		tileNum1 = ints[leftCol];
		tileNum2 = ints2[rightCol];
		if (tile.getTile()[tileNum1].isCollision() || tile.getTile()[tileNum2].isCollision()) {
			ob.setCollisionOn(true);
		}
	}


	public int checkObject(EntityGame entity, boolean player, EntityGame[] objects) {

		int index = 999;

		for (int i = 0; i < objects.length; i++) {

			if (objects[i] != null) {
				// Get the location of the entity
				entity.getArea().x = entity.getWorldX() + entity.getArea().x;
				entity.getArea().y = entity.getWorldY() + entity.getArea().y;
				// Get the location of the object
				objects[i].getArea().x = objects[i].getWorldX() + objects[i].getArea().x;
				objects[i].getArea().y = objects[i].getWorldY() + objects[i].getArea().y;

				switch (entity.getDirection()) {
					case "up" -> {
						entity.getArea().y -= entity.getSpeed();
						if (entity.getArea().intersects(objects[i].getArea())) {
							if (objects[i].isCollisionOn()) {
								entity.setCollisionOn(true);
							}
							if (player && objects[i].isCollisionOn()) {
								index = i;
							}
						}
					}
					case "down" -> {
						entity.getArea().y += entity.getSpeed();
						if (entity.getArea().intersects(objects[i].getArea())) {
							System.out.println("If it is colliding");
							if (objects[i].isCollisionOn()) {
								entity.setCollisionOn(true);
							}
							if (player && objects[i].isCollisionOn()) {
								index = i;
							}
						}
					}
					case "left" -> {
						entity.getArea().x -= entity.getSpeed();
						if (entity.getArea().intersects(objects[i].getArea())) {
							System.out.println(objects[i].isCollisionOn());
							if (objects[i].isCollisionOn()) {
								entity.setCollisionOn(true);
							}
							if (player && objects[i].isCollisionOn()) {
								index = i;
							}
						}
					}
					case "right" -> {
						entity.getArea().x += entity.getSpeed();
						if (entity.getArea().intersects(objects[i].getArea())) {

							if (objects[i].isCollisionOn()) {
								entity.setCollisionOn(true);
							}
							if (player && objects[i].isCollisionOn()) {
								index = i;
							}
						}
					}

				}
				entity.getArea().x = entity.getSolidAreaDefaultX();
				entity.getArea().y = entity.getSolidAreaDefaultY();
				objects[i].getArea().x = objects[i].getSolidAreaDefaultX();
				objects[i].getArea().y = objects[i].getSolidAreaDefaultY();
			}
		}


		return index;

	}

	public int checkEntity(EntityGame entity, ArrayList<EntityGame> target) {

		int index = 999;

		for (int i = 0; i < target.size(); i++) {

			if (target.get(i) != null) {
				// Get the location of the entity
				entity.getArea().x = entity.getWorldX() + entity.getArea().x;
				entity.getArea().y = entity.getWorldY() + entity.getArea().y;
				// Get the location of the object
				target.get(i).getArea().x = target.get(i).getWorldX() + target.get(i).getArea().x;
				target.get(i).getArea().y = target.get(i).getWorldY() + target.get(i).getArea().y;

				crash(entity);
				if (entity.getArea().intersects(target.get(i).getArea())) {
					if (target.get(i) != entity) {
						entity.setCollisionOn(true);
						index = i;
					}
				}
				entity.getArea().x = entity.getSolidAreaDefaultX();
				entity.getArea().y = entity.getSolidAreaDefaultY();
				target.get(i).getArea().x = target.get(i).getSolidAreaDefaultX();
				target.get(i).getArea().y = target.get(i).getSolidAreaDefaultY();
			}
		}

		return index;

	}

	private void crash(EntityGame entity) {
		switch (entity.getDirection()) {
			case "up" -> entity.getArea().y -= entity.getSpeed();
			case "down" -> entity.getArea().y += entity.getSpeed();
			case "left" -> entity.getArea().x -= entity.getSpeed();
			case "right" -> entity.getArea().x += entity.getSpeed();
		}
	}

	public boolean checkPlayer(EntityGame entity, Player player) {
		boolean collisionPlayer = false;
		// Get the location of the entity
		entity.getArea().x = entity.getWorldX() + entity.getArea().x;
		entity.getArea().y = entity.getWorldY() + entity.getArea().y;
		// Get the location of the object
		player.getArea().x = player.getWorldX() + player.getArea().x;
		player.getArea().y = player.getWorldY() + player.getArea().y;

		crash(entity);
		if (entity.getArea().intersects(player.getArea())) {
			entity.setCollisionOn(true);
			collisionPlayer = true;
		}
		entity.getArea().x = entity.getSolidAreaDefaultX();
		entity.getArea().y = entity.getSolidAreaDefaultY();
		player.getArea().x = player.getSolidAreaDefaultX();
		player.getArea().y = player.getSolidAreaDefaultY();
		return collisionPlayer;

	}


}
