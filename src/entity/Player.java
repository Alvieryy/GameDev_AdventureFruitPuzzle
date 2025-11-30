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
			
		}catch(IOException e) {
			e.printStackTrace();
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
//			case "Door":
//			    gp.playSE(3);
//
//			    if(i == 4) {
//			        if(haskey > 0) {
//			            gp.ui.gameFinished = true;
//			            gp.stopMusic();
//			            gp.playSE(4);
//			        } else {
//			            gp.ui.showMessage("You need a key to unlock this door!");
//			        }
//			    } else {
//			        if(haskey > 0) {
//			            haskey--;
//			            gp.obj[i] = null;
//			        } else {
//			            gp.ui.showMessage("You need a key to open a door!");
//			        }
//			    }
//
//			    break;
			case "Door":
			    gp.playSE(3);

			    if(i == 4) {
			        if(haskey > 0) {
			        	gp.ui.gameFinished = true;
			        	gp.stopMusic();
			        	gp.playSE(4);
			            haskey--;
//			            gp.tileM.useForestTiles = true;
//			            world2.loadmap("/maps/WorldMap2.txt")
			            
			            
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
				if(speedUp > 120) {
					speed-=2;
				}
				gp.obj[i] = null;
				gp.ui.showMessage("Speedup!");
				
				
//				hasBoots++;
//				gp.obj[i] = null;
//				System.out.println("Boots:" + hasBoots);
				
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
		
//		g2.setColor(Color.blue);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
			image = up1;
			}
			if(spriteNum == 2) {
				image = up2; 
			}
			break;
		case "down":
			if(spriteNum == 1) {
			image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
			image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
			image = right1; 
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage( image, screenX, screenY, gp.tileSize, gp.tileSize, null); 
		//collision rect
		g2.setColor(Color.RED);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
