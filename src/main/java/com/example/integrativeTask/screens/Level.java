package com.example.integrativeTask.screens;

import com.example.integrativeTask.control.*;
import com.example.integrativeTask.control.Object;
import com.example.integrativeTask.controller.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Level extends BaseScreen {
	private final int maxWorldCol, maxWorldRow, worldWidth, worldHeight;
	private  TileManager tile;
	private final Player player;


	private ArrayList <EntityGame> entityList;

	private ArrayList<Bullet> bullets = new ArrayList<>();
	private EntityGame []  objects = new EntityGame[4];;

	private ArrayList<EntityGame>  enemies= new ArrayList<>();

	private AssetSetter assetSetter;

	private Ui ui;

	private Rectangle mouseRect;
	private  double mouseRectX;
	private double mouseRectY;
	public int gameState;
	public final int playState = 1;

	public final int gameOverState = 2;

	private String levelRelativePath;


	public Level(int maxWorldColumns, int maxWorldRow, Canvas canvas, String levelRelativePath, Player player,int map) {
		super(canvas);
		this.maxWorldCol = maxWorldColumns;
		this.maxWorldRow = maxWorldRow;
		this.worldWidth = Screen.TILES_SIZE * maxWorldColumns; // 1536
		this.worldHeight = Screen.TILES_SIZE * maxWorldRow; // 816
		this.tile = new TileManager(maxWorldRow, maxWorldColumns, MainController.COLLISIONS_PATH + levelRelativePath);
		this.levelRelativePath=levelRelativePath;
		this.player = player;
		this.entityList=new ArrayList<>();
		this.ui=new Ui();
		this.assetSetter = new AssetSetter(map);
		assetSetter.setObject(objects,enemies,tile.getValidPositions());
		mouseRect = new Rectangle(mouseRectX, mouseRectY, 20,20);
		gameState = playState;

	}

	public void update() {

		if(gameState==playState){
			player.move(CollisionChecker.getInstance(), tile,player, objects,enemies,this);

			for(int i=0;i<enemies.size();i++){
				enemies.get(i).move(CollisionChecker.getInstance(), tile,player, objects,enemies,this);
			}
			for (int i = 0; i< bullets.size(); i++) {
				if(!bullets.get(i).isLive){
					bullets.remove(i);
				}else{
					bullets.get(i).update(this);
				}
			}
			if(player.getLifes() <= 0){
				gameState = gameOverState;

			}
		}
		if(gameState==gameOverState){

		}


	}

	public  void retry(){
		player.setDefault();
		enemies.clear();
		objects = new EntityGame[4];
		tile = new TileManager(maxWorldRow, maxWorldCol, MainController.COLLISIONS_PATH + levelRelativePath);
		assetSetter.setObject(objects,enemies,tile.getValidPositions());
	}

	@Override
	public void paint() {

		// Debug
		long drawStart=0;
		if(KeyHandler.getInstance().isDrawTime()){
			drawStart= System.nanoTime();
		}


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

		//Draw pointer
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(mouseRectX, mouseRectY,mouseRect.getWidth(),mouseRect.getHeight());

		entityList.clear();

		//ui
		ui.Draw(graphicsContext,player, gameState, gameOverState,playState);

		//shot
		for (int i = 0; i< bullets.size(); i++){
			bullets.get(i).paint(player);


		}


		//debug
		if(KeyHandler.getInstance().isDrawTime()){
			long drawEnd =System.nanoTime();
			long passed= drawEnd-drawStart;
			System.out.println(passed);
		}

	}




	public void addEntity(){
		entityList.add(player);
		for(int i=0;i<enemies.size();i++){
			if(enemies.get(i)!=null){
				entityList.add(enemies.get(i));
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
		KeyHandler.getInstance().keyPressed(event,this);
	}
/*
	public  void onGameOverEvent(KeyEvent event){
		KeyHandler.getInstance().gameOverState(event,this);
	}
*/
	@Override
	public void onKeyReleased(KeyEvent event) {
		KeyHandler.getInstance().keyReleased(event,this);
	}
	@Override
	public void onMousePressed(MouseEvent event) {

		if (player.getTypeGun() != 0) {
			double playerScreenX = player.getScreenX();
			double playerScreenY = player.getScreenY();
			double diffX = event.getX() - playerScreenX;
			double diffY = event.getY() - playerScreenY;
			Vector diff = new Vector(diffX, diffY);
			int speed = player.gunActual(player.getTypeGun());
			diff.normalize();
            diff.setSpeed(speed);
			Vector diffPlayer = new Vector(player.getWorldX(), player.getWorldY());

			bullets.add(new Bullet(player.getWorldX(), player.getWorldY(), speed, 1, canvas, diffPlayer, diff, player));
		}
	}


	public  void mouseMoved(MouseEvent mouseEvent ){

			mouseRectX = mouseEvent.getX();
			mouseRectY = mouseEvent.getY();


	}

	public EntityGame[] getObjects() {
		return objects;
	}

	public void setObjects(EntityGame[] objects) {
		this.objects = objects;
	}

	public Ui getUi() {
		return ui;
	}

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	public Player getPlayer() {
		return player;
	}


	public ArrayList<EntityGame> getEntityList() {
		return entityList;
	}

	public void setEntityList(ArrayList<EntityGame> entityList) {
		this.entityList = entityList;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public ArrayList<EntityGame> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<EntityGame> enemies) {
		this.enemies = enemies;
	}

	public AssetSetter getAssetSetter() {
		return assetSetter;
	}

	public void setAssetSetter(AssetSetter assetSetter) {
		this.assetSetter = assetSetter;
	}

	public TileManager getTile() {
		return tile;
	}

	public void setTile(TileManager tile) {
		this.tile = tile;
	}
}
