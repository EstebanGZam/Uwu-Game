package com.example.integrativeTask.Control;

import com.example.integrativeTask.Controller.MainController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;

public class TileManager {
	public static final String SPRITE_DIR = MainController.MAIN_PATH + "\\RPGW_Caves_v2.1\\";
	private final int TILES_AMOUNT = 10;

	private GraphicsContext gr;

	private Tile[] tile;

	private int[][] mapTileNum;

	public int maxWorldCol;
	public int maxWorldRow;
	private String t;


	public TileManager(GraphicsContext gr, int row, int col, String t) {
		this.t = t;
		this.gr = gr;
		this.maxWorldCol = col;
		this.maxWorldRow = row;
		tile = new Tile[TILES_AMOUNT];
		mapTileNum = new int[row][col];
		getTitleImage();
		loadGame();
	}

	public void getTitleImage() {
		for (int i = 0; i < TILES_AMOUNT; i++) {
			tile[i] = new Tile();
		}

		tile[0].image = new Image(SPRITE_DIR + "grass.png");
		tile[1].image = new Image(SPRITE_DIR + "esquinaDeSu.png");
		tile[2].image = new Image(SPRITE_DIR + "esquinaIzSu.png");
		tile[3].image = new Image(SPRITE_DIR + "parteDEINF.png");
		tile[4].image = new Image(SPRITE_DIR + "parteIZINF.png");
		tile[5].image = new Image(SPRITE_DIR + "parteINF.png");
		tile[6].image = new Image(SPRITE_DIR + "parteSu.png");
		tile[7].image = new Image(SPRITE_DIR + "muro.png");
		tile[8].image = new Image(SPRITE_DIR + "muroDE.png");
		tile[9].image = new Image(SPRITE_DIR + "muroINTERIOR.png");
		tile[9].collision = true;

	}

	public void loadGame() {
		try {
			File File = new File("src\\main\\java\\com\\example\\integrativeTask\\Level-1.txt");
			FileInputStream i = new FileInputStream(File);
			BufferedReader reader = new BufferedReader(new InputStreamReader(i));

			int col = 0;
			int row = 0;

			while (col < maxWorldCol && row < maxWorldRow) {

				String line = reader.readLine();
				while (col < maxWorldCol) {
					String nume[] = line.split(" ");

					int num = Integer.parseInt(nume[col]);

					mapTileNum[row][col] = num;
					col++;
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

	public void draw(GraphicsContext g2, int playerX, int playerY, int screenX, int screenY) {
		int col = 0;
		int row = 0;

		while (col < maxWorldCol && row < maxWorldRow) {
			int titleNum = mapTileNum[row][col];

			int worldX = col * 48;
			int worldY = row * 48;
			int screenInX = worldX - playerX + screenX;
			int screenInY = worldY - playerY + screenY;

			if (worldX + 48 > playerX - screenX && worldX - 48 < playerX + screenX
					&& worldY + 48 > playerY - screenY && worldY - 48 < playerY + screenY) {

				g2.drawImage(tile[titleNum].image, screenInX, screenInY, 48, 48);
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
}
