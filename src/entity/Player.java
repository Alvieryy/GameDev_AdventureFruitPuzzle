package entity;

import gamedev.GamePanel;
import gamedev.KeyHandler;
import tile.TileManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY; 
	public int haskey, hasApple, hasOrange, hasBoots = 0;
	int speedUp = 0;
	public TileManager world2;
	boolean moving = false;
	int pixelCounter = 0;
	int standCounter = 0;
	public String skin = "normal";
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize / 2);
		screenY = gp.screenHeight/2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
		
			
			spiderman_down1 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_down1.png"));
			spiderman_down2 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_down2.png"));
			spiderman_left1 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_left1.png"));
			spiderman_left2 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_left2.png"));
			spiderman_right1 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_right1.png"));
			spiderman_right2 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_right2.png"));
			spiderman_up1 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_up1.png"));
			spiderman_up2 = ImageIO.read(getClass().getResourceAsStream("/player/Spiderman_up2.png"));

			
			specialskin_up1 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin B1.png"));
			specialskin_up2 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin B2.png"));
			specialskin_down1 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin F1[.png"));
			specialskin_down2 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin F2.png"));
			specialskin_right1 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin R1.png"));
			specialskin_right2 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin R2.png"));
			specialskin_left1 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin L1.png"));
			specialskin_left2 = ImageIO.read(getClass().getResourceAsStream("/player/Special_Skin L2.png"));
			
			naruto_up1 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto B1.png"));
			naruto_up2 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto B2.png"));
			naruto_down1 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto F1.png"));
			naruto_down2 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto F2.png"));
			naruto_right1 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto R1.png"));
			naruto_right2 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto R2.png"));
			naruto_left1 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto L1.png"));
			naruto_left2 = ImageIO.read(getClass().getResourceAsStream("/player/Naruto L2.png"));
			
			jansen_up1 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen B1.png"));
			jansen_up2 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen B2.png"));
			jansen_down1 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen f1.png"));
			jansen_down2 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen f2.png"));
			jansen_right1 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen R1.png"));
			jansen_right2 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen R2.png"));
			jansen_left1 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen L1.png"));
			jansen_left2 = ImageIO.read(getClass().getResourceAsStream("/player/Jansen L2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setSkin() {
        if(gp.playerSkin == 1){
            up1 = setup("player/up1");
            up2 = setup("player/up2");
            down1 = setup("player/down1");
            down2 = setup("player/down2");
            left1 = setup("player/left1");
            left2 = setup("player/left2");
            right1 = setup("player/right1");
            right2 = setup("player/right2");
        }

        if(gp.playerSkin == 2){
            up1 = setup("skin2/up1");
            up2 = setup("skin2/up2");
            down1 = setup("skin2/down1");
            down2 = setup("skin2/down2");
            left1 = setup("skin2/left1");
            left2 = setup("skin2/left2");
            right1 = setup("skin2/right1");
            right2 = setup("skin2/right2");
        }
    }
	
	public void update() {
		if(moving == false) {
			if(keyH.upPressed == true || keyH.downPressed == true 
					|| keyH.leftPressed == true || keyH.rightPressed) {
				if(keyH.upPressed == true) {
					direction = "up";
				}
				else if(keyH.downPressed == true) {
					direction = "down";
				}
				else if(keyH.leftPressed == true) {
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					direction = "right";
				}
				
				moving = true;

				collisionOn = false;
				gp.cChecker.checkTile(this);

				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObj(objIndex);
			}
			else {
				standCounter++;
				if(standCounter == 20) {
					spriteNum = 1;
					standCounter = 0;
				}
			}
		}

		if(moving == true) {
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}

			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

			pixelCounter += speed;

			if(pixelCounter == 48) {
				moving = false;
				pixelCounter = 0;
			}
		}
	}

	public void pickUpObj(int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				haskey++;
				gp.obj[i] = null;
				gp.ui.showMessage("Key picked-up!");
				break;
			case "Door":
			    gp.playSE(3);

			    if(i == 4) {
			        if(haskey > 0) {
			        	gp.ui.gameFinished = true;
			        	gp.stopMusic();
			        	gp.playSE(4);
			            haskey--;
			            gp.ui.showMessage("Map Loaded!");
			            break;
			        }
			    }
			    else {
			        if(haskey > 0) {
			            haskey--;
			            gp.obj[i] = null;
			        } else {
			            gp.ui.showMessage("You need a key to open a door!");
			        }
			    }
			    break;

			case "Boots":
				gp.playSE(2);
				speed += 2;
				if(speed > 8) {
					speed = 8;;
				}
				gp.obj[i] = null;
				gp.ui.showMessage("Speedup!");
//				if(speed == gp.tileSize * 3) {
//					
//					speed -= 2;
//				}
				break;

			case "Apple":
				hasApple++;
				gp.obj[i] = null;
				gp.ui.showMessage("Apple picked up!");
				break;
				
			case "Orange":
				hasOrange++;
				gp.obj[i] = null;	
				gp.ui.showMessage("Orange picked up!");
				break;
			case "Chest":
				gp.ui.showMessage("");
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		if(skin.equals("normal")) {
			switch(direction) {
			case "up": image = (spriteNum == 1 ? up1 : up2); break;
			case "down": image = (spriteNum == 1 ? down1 : down2); break;
			case "left": image = (spriteNum == 1 ? left1 : left2); break;
			case "right": image = (spriteNum == 1 ? right1 : right2); break;
			}
		}
		else if(skin.equals("spiderman")) {
			switch(direction) {
			case "up": image = (spriteNum == 1 ? spiderman_up1 : spiderman_up2); break;
			case "down": image = (spriteNum == 1 ? spiderman_down1 : spiderman_down2); break;
			case "left": image = (spriteNum == 1 ? spiderman_left1 : spiderman_left2); break;
			case "right": image = (spriteNum == 1 ? spiderman_right1 : spiderman_right2); break;
			}
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); 
		g2.setColor(Color.RED);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
