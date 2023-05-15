package com.example.integrativeTask.Control;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;

public class TileManager {

	GraphicsContext gr;

	Tile[] tile;

	int mapTileNum[][];

	public int maxWorldCol;
	public int maxWorldRow;
	private String t;


	public TileManager(GraphicsContext gr, int row, int col, String t) {
		this.t = t;
		this.gr = gr;
		this.maxWorldCol = col;
		this.maxWorldRow = row;
		tile = new Tile[10];
		mapTileNum = new int[row][col];
		getTitleImage();
		loadGame();
	}

	public void getTitleImage() {

		File spriteDir = new File("src\\main\\java\\com\\example\\integrativeTask\\RPGW_Caves_v2.1");
		File x1 = new File(spriteDir, "esquinaDeSu.png");
		File x2 = new File(spriteDir, "esquinaIzSu.png");
		File x3 = new File(spriteDir, "grass.png");
		File x4 = new File(spriteDir, "muro.png");
		File x5 = new File(spriteDir, "muroDE.png");
		File x6 = new File(spriteDir, "muroINF.png");
		File x7 = new File(spriteDir, "muroINTERIOR.png");
		File x8 = new File(spriteDir, "muroIz.png");
		File x9 = new File(spriteDir, "parteDEINF.png");
		File x10 = new File(spriteDir, "parteINF.png");
		File x11 = new File(spriteDir, "parteIZINF.png");
		File x12 = new File(spriteDir, "parteSu.png");


		tile[0] = new Tile();

		tile[0].image = new Image(x3.toURI().toString());

		tile[1] = new Tile();
		tile[1].image = new Image(x1.toURI().toString());

		tile[2] = new Tile();
		tile[2].image = new Image(x2.toURI().toString());

		tile[3] = new Tile();
		tile[3].image = new Image(x9.toURI().toString());
		tile[4] = new Tile();
		tile[4].image = new Image(x11.toURI().toString());

		tile[5] = new Tile();
		tile[5].image = new Image(x10.toURI().toString());

		tile[6] = new Tile();
		tile[6].image = new Image(x12.toURI().toString());

		tile[7] = new Tile();
		tile[7].image = new Image(x4.toURI().toString());
		tile[7].collision = true;

		tile[8] = new Tile();
		tile[8].image = new Image(x5.toURI().toString());
		tile[8].collision = true;

		tile[9] = new Tile();
		tile[9].image = new Image(x7.toURI().toString());
		tile[9].collision = true;


	}

	public void loadGame() {
		try {
			File File = new File(t);
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
