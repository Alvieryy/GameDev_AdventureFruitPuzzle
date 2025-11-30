package gamedev;

import object.Key;
import object.OBJ_Apple;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Orange;

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
		gp.obj[1].worldX = 8 * gp.tileSize;
		gp.obj[1].worldY = 33 * gp.tileSize;
		
		gp.obj[2] = new Key();
		gp.obj[2].worldX = 10 * gp.tileSize;
		gp.obj[2].worldY = 45 * gp.tileSize;
		
		// gp.obj[3] = new Key();
		// gp.obj[3].worldX = 43 * gp.tileSize;
		// gp.obj[3].worldY = 23 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 38 * gp.tileSize;
		gp.obj[4].worldY = 7 * gp.tileSize;
		
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
		gp.obj[8].worldX = 11 * gp.tileSize;
		gp.obj[8].worldY = 34 * gp.tileSize;
		
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
		
		//fruits
		
		gp.obj[15] = new OBJ_Apple();
		gp.obj[15].worldX = 41 * gp.tileSize;
		gp.obj[15].worldY = 11 * gp.tileSize;
		
		gp.obj[16] = new OBJ_Apple();
		gp.obj[16].worldX = 37 * gp.tileSize;
		gp.obj[16].worldY = 43 * gp.tileSize;
		
		gp.obj[17] = new OBJ_Apple();
		gp.obj[17].worldX = 14 * gp.tileSize;
		gp.obj[17].worldY = 14 * gp.tileSize;
		
		gp.obj[18] = new OBJ_Orange();
		gp.obj[18].worldX = 22 * gp.tileSize;
		gp.obj[18].worldY = 42 * gp.tileSize;
		
		gp.obj[19] = new OBJ_Orange();
		gp.obj[19].worldX = 36 * gp.tileSize;
		gp.obj[19].worldY = 19 * gp.tileSize;
		
		gp.obj[20] = new OBJ_Orange();
		gp.obj[20].worldX = 30 * gp.tileSize;
		gp.obj[20].worldY = 13 * gp.tileSize;
		
		gp.obj[21] = new OBJ_Door();
		gp.obj[21].worldX = 30 * gp.tileSize;
		gp.obj[21].worldY = 40 * gp.tileSize;
		
		gp.obj[22] = new OBJ_Door();
		gp.obj[22].worldX = 8 * gp.tileSize;
		gp.obj[22].worldY = 28 * gp.tileSize;
		
		gp.obj[23] = new OBJ_Door();
		gp.obj[23].worldX = 13 * gp.tileSize;
		gp.obj[23].worldY = 21 * gp.tileSize;
		
		gp.obj[24] = new OBJ_Door();
		gp.obj[24].worldX = 36 * gp.tileSize;
		gp.obj[24].worldY = 30 * gp.tileSize;
		
		gp.obj[25] = new OBJ_Door();
		gp.obj[25].worldX = 36 * gp.tileSize;
		gp.obj[25].worldY = 18 * gp.tileSize;
		
		gp.obj[26] = new Key();
		gp.obj[26].worldX = 31 * gp.tileSize;
		gp.obj[26].worldY = 21 * gp.tileSize;
	
		gp.obj[27] = new Key();
		gp.obj[27].worldX = 20 * gp.tileSize;
		gp.obj[27].worldY = 41 * gp.tileSize;
		
		gp.obj[28] = new Key();
		gp.obj[28].worldX = 35 * gp.tileSize;
		gp.obj[28].worldY = 43 * gp.tileSize;
		
		gp.obj[29] = new Key();
		gp.obj[29].worldX = 17 * gp.tileSize;
		gp.obj[29].worldY = 16 * gp.tileSize;
		
	}
	
	

}
