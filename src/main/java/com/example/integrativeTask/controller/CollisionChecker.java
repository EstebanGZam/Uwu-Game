package com.example.integrativeTask.controller;

import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;
import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.control.TileManager;

import java.util.ArrayList;

public class CollisionChecker {
	private static CollisionChecker instance;

	public static CollisionChecker getInstance() {
		if (instance == null)
			instance = new CollisionChecker();
		return instance;
	}

	public void checkTile(EntityGame ob, TileManager tile) {

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


	public int checkObject(EntityGame entity, boolean player, EntityGame [] objects){

		int index=999;

		for(int i=0; i<objects.length;i++){

			if(objects[i]!=null){
				//conseguimos la ubicacion del entity
				entity.getArea().x= entity.getWorldX()+entity.getArea().x;
				entity.getArea().y= entity.getWorldY()+entity.getArea().y;
				//conseguimo la ubicacion del objeto
				objects[i].getArea().x=objects[i].getWorldX()+objects[i].getArea().x;
				objects[i].getArea().y=objects[i].getWorldY()+objects[i].getArea().y;

				switch (entity.getDirection()){
					case "up" -> {
                       entity.getArea().y-= entity.getSpeed();
					   if(entity.getArea().intersects(objects[i].getArea())){
                           if(objects[i].isCollisionOn()){
							   entity.setCollisionOn(true);
						   }
						   if(player&&objects[i].isCollisionOn()){
							   index=i;
						   }
					   }
					}
					case "down" -> {
						entity.getArea().y+= entity.getSpeed();
						if(entity.getArea().intersects(objects[i].getArea())){
							System.out.println("Si esta colisionando");
							if(objects[i].isCollisionOn()){
								entity.setCollisionOn(true);
							}
							if(player&&objects[i].isCollisionOn()){
								index=i;
							}
						}
					}
					case "left" -> {
						entity.getArea().x-= entity.getSpeed();
						if(entity.getArea().intersects(objects[i].getArea())){
							System.out.println("Si esta colisionando");
							if(objects[i].isCollisionOn()){
								entity.setCollisionOn(true);
							}
							if(player&&objects[i].isCollisionOn()){
								index=i;
							}
						}
					}
					case "right" -> {
						entity.getArea().x+= entity.getSpeed();
						if(entity.getArea().intersects(objects[i].getArea())){
							System.out.println("Si esta colisionando");
							if(objects[i].isCollisionOn()){
								entity.setCollisionOn(true);
							}
							if(player&&objects[i].isCollisionOn()){
								index=i;
							}
						}
					}

				}
				entity.getArea().x=entity.getSolidAreaDefaultX();
				entity.getArea().y=entity.getSolidAreaDefaultY();
				objects[i].getArea().x= objects[i].getSolidAreaDefaultX();
				objects[i].getArea().y= objects[i].getSolidAreaDefaultY();
			}
		}



      return index;

	}

	public int checkEntity(EntityGame entity, ArrayList<EntityGame> target){

		int index=999;

		for(int i=0; i<target.size();i++){

			if(target.get(i)!=null){
				//conseguimos la ubicacion del entity
				entity.getArea().x= entity.getWorldX()+entity.getArea().x;
				entity.getArea().y= entity.getWorldY()+entity.getArea().y;
				//conseguimo la ubicacion del objeto
				target.get(i).getArea().x=target.get(i).getWorldX()+target.get(i).getArea().x;
				target.get(i).getArea().y=target.get(i).getWorldY()+target.get(i).getArea().y;

				switch (entity.getDirection()){
					case "up" -> {
						entity.getArea().y-= entity.getSpeed();
					}
					case "down" -> {
						entity.getArea().y+= entity.getSpeed();
					}
					case "left" -> {
						entity.getArea().x-= entity.getSpeed();
					}
					case "right" -> {
						entity.getArea().x+= entity.getSpeed();
					}

				}
				if(entity.getArea().intersects(target.get(i).getArea())){
					if(target.get(i)!=entity){
						entity.setCollisionOn(true);
						index=i;
					}
				}
				entity.getArea().x=entity.getSolidAreaDefaultX();
				entity.getArea().y=entity.getSolidAreaDefaultY();
				target.get(i).getArea().x= target.get(i).getSolidAreaDefaultX();
				target.get(i).getArea().y= target.get(i).getSolidAreaDefaultY();
			}
		}



		return index;

	}

	public boolean checkPlayer(EntityGame entity, Player player){
          boolean collisionPlayer=false;
		//conseguimos la ubicacion del entity
		entity.getArea().x= entity.getWorldX()+entity.getArea().x;
		entity.getArea().y= entity.getWorldY()+entity.getArea().y;
		//conseguimo la ubicacion del objeto
		player.getArea().x=player.getWorldX()+player.getArea().x;
		player.getArea().y=player.getWorldY()+player.getArea().y;

		switch (entity.getDirection()){
			case "up" -> {
				entity.getArea().y-= entity.getSpeed();
			}
			case "down" -> {
				entity.getArea().y+= entity.getSpeed();
			}
			case "left" -> {
				entity.getArea().x-= entity.getSpeed();
			}
			case "right" -> {
				entity.getArea().x+= entity.getSpeed();
			}

		}
		if(entity.getArea().intersects(player.getArea())){
				entity.setCollisionOn(true);
				 collisionPlayer=true;
		}
		entity.getArea().x=entity.getSolidAreaDefaultX();
		entity.getArea().y=entity.getSolidAreaDefaultY();
		player.getArea().x= player.getSolidAreaDefaultX();
		player.getArea().y= player.getSolidAreaDefaultY();
		return  collisionPlayer;

	}


}
