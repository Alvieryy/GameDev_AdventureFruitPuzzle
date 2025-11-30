package tile;

import gamedev.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public Tile[] tile2; 
	public int mapTileNum[][];
	public boolean useForestTiles = false;

	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[30];
		tile2 = new Tile[30];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadmap("/maps/WorldMap1.1.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png")); 
			
//			tile2[0] = new Tile();
//			tile2[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Bush Forest.png"));
//			tile2[0].collision = true;
//			
//			tile2[1] = new Tile();
//			tile2[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Grass.png"));
//
//			
//			tile2[2] = new Tile();
//			tile2[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Tall Grass.png"));
//
//			
//			tile2[3] = new Tile();
//			tile2[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail inner 1 (1).png"));
//			
//			tile2[4] = new Tile();
//			tile2[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail inner 1 (2).png"));
//
//			
//			tile2[5] = new Tile();
//			tile2[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail inner 1 (3).png")); 
//			
//			tile2[6] = new Tile();
//			tile2[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail inner 1 (4).png"));
//			
//			tile2[7] = new Tile();
//			tile2[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail inner 1 (5).png"));
//
//			
//			tile2[8] = new Tile();
//			tile2[8].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (1).png"));
//
//			
//			tile2[9] = new Tile();
//			tile2[9].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (2).png"));
//			
//			tile2[10] = new Tile();
//			tile2[10].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (3).png"));
//
//			
//			tile2[11] = new Tile();
//			tile2[11].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (4).png")); 
//			
//			tile2[12] = new Tile();
//			tile2[12].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (5).png"));
//			
//			tile2[13] = new Tile();
//			tile2[13].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (6).png"));
//
//			
//			tile2[14] = new Tile();
//			tile2[14].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 (7).png"));
//
//			
//			tile2[15] = new Tile();
//			tile2[15].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/Trail outer 1 side.png"));
//			
//			tile2[16] = new Tile();
//			tile2[16].image = ImageIO.read(getClass().getResourceAsStream("/tile/forest/tree.png"));
//			tile2[16].collision = true;
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadmap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
//				if(useForestTiles) {
//				    g2.drawImage(tile2[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//				} else {
//				    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//				}
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);				
			}
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
	}
	
	
	
}
