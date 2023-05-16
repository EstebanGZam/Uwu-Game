package com.example.integrativeTask.screens;

import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Player;
import com.example.integrativeTask.control.TileManager;
import com.example.integrativeTask.controller.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Level extends BaseScreen {
	private final int maxWorldCol, maxWorldRow, worldWidth, worldHeight;
	private final TileManager tile;
	private final Player player;


	private ArrayList <EntityGame> entityList;

	private EntityGame []  objects = new EntityGame[4];;

	private EntityGame []  enemies= new EntityGame[3];

	private AssetSetter assetSetter;

	private Ui ui;


	public Level(int maxWorldColumns, int maxWorldRow, Canvas canvas, String levelRelativePath, Player player) {
		super(canvas);
		this.maxWorldCol = maxWorldColumns;
		this.maxWorldRow = maxWorldRow;
		this.worldWidth = Screen.TILES_SIZE * maxWorldColumns; // 1536
		this.worldHeight = Screen.TILES_SIZE * maxWorldRow; // 816
		this.tile = new TileManager(maxWorldRow, maxWorldColumns, MainController.COLLISIONS_PATH + levelRelativePath);
		this.player = player;
		this.entityList=new ArrayList<>();
		this.ui=new Ui();
		this.assetSetter = new AssetSetter();
		assetSetter.setObject(objects);

	}

	public void update() {
		player.move(CollisionChecker.getInstance(), tile,player);
	}

	@Override
	public void paint() {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);

		// map

		tile.draw(graphicsContext, player.getWorldX(), player.getWorldY(), player.getScreenX(), player.getScreenY());

		//entityLis
		addEntity();


		//Draw Entity
		for(int i=0;i<entityList.size(); i++){
			entityList.get(i).print(graphicsContext,player);
		}

		for(int i=0;i<entityList.size(); i++){
			entityList.remove(i);
		}

		//ui
		ui.Draw(graphicsContext,player);
	}

	public void addEntity(){
		entityList.add(player);
		for(int i=0;i<enemies.length;i++){
			if(enemies[i]!=null){
				entityList.add(enemies[i]);
			}
		}
		for(int i=0;i<objects.length;i++){
			if(objects[i]!=null){
				entityList.add(objects[i]);
			}
		}

		Collections.sort(entityList, new Comparator<EntityGame>() {
			@Override
			public int compare(EntityGame o1, EntityGame o2) {
				int result =Integer.compare(o1.getWorldY(),o1.getWorldY());
				return result;
			}
		});
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		KeyHandler.getInstance().keyPressed(event);
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		KeyHandler.getInstance().keyReleased(event);
	}
}
