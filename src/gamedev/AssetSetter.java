package gamedev;

import object.Key;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void setObj() {
		gp.obj[0] = new Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new Key();
		gp.obj[1].worldX = 4 * gp.tileSize;
		gp.obj[1].worldY = 33 * gp.tileSize;
		
		gp.obj[2] = new Key();
		gp.obj[2].worldX = 10 * gp.tileSize;
		gp.obj[2].worldY = 45 * gp.tileSize;
		
		// gp.obj[3] = new Key();
		// gp.obj[3].worldX = 43 * gp.tileSize;
		// gp.obj[3].worldY = 23 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 19 * gp.tileSize;
		gp.obj[4].worldY = 38 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Chest();
		gp.obj[5].worldX = 17 * gp.tileSize;
		gp.obj[5].worldY = 14 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 30 * gp.tileSize;
		gp.obj[6].worldY = 16 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Chest();
		gp.obj[7].worldX = 31 * gp.tileSize;
		gp.obj[7].worldY = 42 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Chest();
		gp.obj[8].worldX = 30 * gp.tileSize;
		gp.obj[8].worldY = 28 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Boots();
		gp.obj[9].worldX = 8 * gp.tileSize;
		gp.obj[9].worldY = 19 * gp.tileSize;
		
		 gp.obj[10] = new OBJ_Boots();
		 gp.obj[10].worldX = 16 * gp.tileSize;
		 gp.obj[10].worldY = 44 * gp.tileSize;
		
		 gp.obj[11] = new OBJ_Boots();
		 gp.obj[11].worldX = 23 * gp.tileSize;
		 gp.obj[11].worldY = 31 * gp.tileSize;

		gp.obj[12] = new OBJ_Chest();
		gp.obj[12].worldX = 10 * gp.tileSize;
		gp.obj[12].worldY = 7 * gp.tileSize;

		gp.obj[13] = new OBJ_Door();
		gp.obj[13].worldX = 10 * gp.tileSize;
		gp.obj[13].worldY = 11 * gp.tileSize;

		gp.obj[14] = new OBJ_Door();
		gp.obj[14].worldX = 13 * gp.tileSize;
		gp.obj[14].worldY = 45 * gp.tileSize;

	}
	
	

}
