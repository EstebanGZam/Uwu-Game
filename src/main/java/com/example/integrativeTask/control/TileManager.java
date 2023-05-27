package com.example.integrativeTask.control;

import com.example.integrativeTask.controller.MainController;
import com.example.integrativeTask.model.Point;
import com.example.integrativeTask.model.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;

public class TileManager {
	public static final String TILES_PATH = MainController.MAIN_RESOURCES_PATH + "\\tiles-sprites";
	private final int TILES_AMOUNT = 10;

	private Tile[] tile;

	private int[][] mapTileNum;

	private ArrayList<Point> validPositions;

	private int maxWorldCol;
	private int maxWorldRow;
	private final String stagePath;


	public TileManager(int rows, int columns, String stagePath) {
		this.stagePath = stagePath;
		this.maxWorldCol = columns;
		this.maxWorldRow = rows;
		validPositions= new ArrayList<>();
		tile = new Tile[TILES_AMOUNT];
		mapTileNum = new int[rows][columns];
		initializeTilesImages();
		loadGame();
	}

	public void initializeTilesImages() {

		for (int i = 0; i < TILES_AMOUNT; i++) {
			tile[i] = new Tile(getTileImage("\\tile_" + i + ".png"));
		}

		tile[7].setCollision(true);
		tile[8].setCollision(true);
		tile[9].setCollision(true);

	}

	private Image getTileImage(String relativePath) {
		return new Image(TILES_PATH + relativePath);
	}

	public void loadGame() {
		try {
			File File = new File(stagePath);
			FileInputStream fis = new FileInputStream(File);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			int col = 0;
			int row = 0;

			while (col < maxWorldCol && row < maxWorldRow) {

				String line = reader.readLine();
				while (col < maxWorldCol) {

					String[] nume = line.split(" ");
					int num = Integer.parseInt(nume[col]);

					mapTileNum[row][col] = num;
					col++;
					if(!tile[num].isCollision()){
						validPositions.add(new Point(col,row));
					}
				}
				if (col == maxWorldCol) {
					col = 0;
					row++;
				}
			}
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(GraphicsContext graphicsContext, int playerX, int playerY, int screenX, int screenY) {
		int col = 0;
		int row = 0;

		while (col < maxWorldCol && row < maxWorldRow) {
			int titleNum = mapTileNum[row][col];

			int worldX = col * 48;
			int worldY = row * 48;
			int screenInX = worldX - playerX + screenX;
			int screenInY = worldY - playerY + screenY+2;

			if (worldX + 48 > playerX - screenX && worldX - 48 < playerX + screenX
					&& worldY + 48 > playerY - screenY && worldY - 48 < playerY + screenY) {

				graphicsContext.drawImage(tile[titleNum].getImage(), screenInX, screenInY, 48, 48);
			}
			col++;

			if (col == maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public Tile[] getTile() {
		return tile;
	}

	public void setTile(Tile[] tile) {
		this.tile = tile;
	}

	public int[][] getMapTileNum() {
		return mapTileNum;
	}

	public void setMapTileNum(int[][] mapTileNum) {
		this.mapTileNum = mapTileNum;
	}

	public int getMaxWorldCol() {
		return maxWorldCol;
	}

	public void setMaxWorldCol(int maxWorldCol) {
		this.maxWorldCol = maxWorldCol;
	}

	public int getMaxWorldRow() {
		return maxWorldRow;
	}

	public void setMaxWorldRow(int maxWorldRow) {
		this.maxWorldRow = maxWorldRow;
	}

	public ArrayList<Point> getValidPositions() {
		return validPositions;
	}

	public void setValidPositions(ArrayList<Point> validPositions) {
		this.validPositions = validPositions;
	}
}
