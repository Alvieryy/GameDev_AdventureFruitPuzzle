package gamedev;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.Key;
import object.OBJ_Apple;
import object.OBJ_Orange;

public class UI {
	
	GamePanel gp;
	Font arial_40, arial_80B;
	Graphics2D g2;
	BufferedImage keyImage, appleImage, orangeImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	public int commandCharNum = 0;
	public int titleScreenState = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI (GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("ARIAL", Font.PLAIN, 40);
		arial_80B = new Font("ARIAL", Font.BOLD, 80);
		Key key = new Key();
		OBJ_Apple apl = new OBJ_Apple();
		OBJ_Orange orng = new OBJ_Orange();
		keyImage = key.image;
		appleImage = apl.image; 
		orangeImage = orng.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw (Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			// Game logic happens below in the HUD section
		}
		
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		// GAME OVER STATE
		if(gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			String text;
			int textLenght;
			int x;
			int y;
			
			text = "You finished the current world!";
			textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLenght / 2;
			y = gp.screenHeight/2 - (gp.tileSize * 3);
			g2.drawString(text, x, y);
			
			text = "Your Time is :" + dFormat.format(playTime) + "!";
			textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLenght / 2;
			y = gp.screenHeight/2 + (gp.tileSize * 4);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.YELLOW);
			
			text = "Congratualations!";
			textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLenght / 2;
			y = gp.screenHeight/2 + (gp.tileSize * 2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}	

		// HUD (Heads Up Display) - Drawn whenever we are NOT on the title screen
		else if(gp.gameState != gp.titleState) {

		    g2.setFont(arial_40);
		    g2.setColor(Color.WHITE);

		    int x = gp.tileSize / 2;
		    int y = gp.tileSize / 2;

		    // Draw Key
		    g2.drawImage(keyImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.haskey, x + gp.tileSize + 5, y + 35);

		    // Draw Apple
		    x += gp.tileSize + 60;
		    g2.drawImage(appleImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.hasApple, x + gp.tileSize + 5, y + 35);

		    // Draw Orange
		    x += gp.tileSize + 60;
		    g2.drawImage(orangeImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.hasOrange, x + gp.tileSize + 5, y + 35);

		    // --- TIME LOGIC FIX START ---
		    // Only increase the timer if the game is currently PLAYING
		    if(gp.gameState == gp.playState) {
		    	playTime += (double)1/60;
		    }
		    // --- TIME LOGIC FIX END ---
		    
		    // Draw the time (visible even when paused)
		    g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);

		    // On-screen Messages
		    if(messageOn == true) {
		        g2.setFont(g2.getFont().deriveFont(30F));
		        g2.drawString(message, x, gp.tileSize*5);

		        messageCounter++;

		        if(messageCounter > 120) {
		            messageCounter = 0;
		            messageOn = false;
		        }
		    }
		}
	} 
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			//TITLE NAME
			g2.setColor(new Color(0, 0, 255));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
			String text = "KEY QUEST";
			int x = getXforCenteredString(text);
			int y = gp.screenHeight / 3;
			
			//SHADOW
			g2.setColor(Color.BLACK);
			g2.drawString(text, x + 5, y - 95);
			
			//MAIN COLOR
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y- 100);
			
			//THE LEGENDARY ORANGE IMAGE
			x = gp.screenWidth / 2 - (gp.tileSize*2) / 2;
			y += gp.tileSize* 2;
			g2.drawImage(gp.player.down1, x, y - 100, gp.tileSize * 2, gp.tileSize * 2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
			
			text = "NEW GAME";
			x = getXforCenteredString(text);
			y += gp.tileSize * 4;
			g2.drawString(text, x, y - 150);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y - 150);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredString(text);
			y += gp.tileSize;
			g2.drawString(text, x, y - 150);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y - 150);
			}
			
			text = "CHARACTERS";
			x = getXforCenteredString(text);
			y += gp.tileSize;
			g2.drawString(text, x, y - 150);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y - 150);
			}
			
			text = "QUIT";
			x = getXforCenteredString(text);
			y += gp.tileSize;
			g2.drawString(text, x, y - 150);
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y - 150);
			}
			
		}
		else if(titleScreenState == 1){

		    g2.setColor(new Color(0, 0, 255));
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		    String text = "CHOOSE A CHARACTER";
		    int x = getXforCenteredString(text);
		    int y = gp.screenHeight / 3;

		    // Shadow
		    g2.setColor(Color.BLACK);
		    g2.drawString(text, x + 5, y - 95);

		    // Main text
		    g2.setColor(Color.WHITE);
		    g2.drawString(text, x, y - 100);

		    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

		    // SpiderMan
		    text = "SpiderMan";
		    x = getXforCenteredString(text);
		    y += gp.tileSize * 4;
		    if(commandCharNum == 0) {
		        g2.drawString(">", x - 250 - gp.tileSize, y - 150);
		    }
		    g2.drawString(text, x - 250, y - 150);

		    // Special Skin
		    text = "Special Skin";
		    x = getXforCenteredString(text);
		    y += gp.tileSize * 4;
		    if(commandCharNum == 1) {
		        g2.drawString(">", x - gp.tileSize, y - 345);
		    }
		    g2.drawString(text, x, y - 345);

		    // Naruto
		    text = "Naruto";
		    x = getXforCenteredString(text);
		    y += gp.tileSize * 4;
		    if(commandCharNum == 2) {
		        g2.drawString(">", x + 250 - gp.tileSize, y - 538);
		    }
		    g2.drawString(text, x + 250, y - 538);

		    // Player images
		    g2.drawImage(gp.player.spiderman_down1, x - 265, y - 650, gp.tileSize * 2, gp.tileSize * 2, null);
		    g2.drawImage(gp.player.specialskin_down1, x - 20, y - 650, gp.tileSize * 2, gp.tileSize * 2, null);
		    g2.drawImage(gp.player.naruto_down1, x + 230, y - 650, gp.tileSize * 2, gp.tileSize * 2, null);

		    // Jansen
		    text = "Jansen";
		    x = getXforCenteredString(text);
		    if(commandCharNum == 3) {
		        g2.drawString(">", x - 250 - gp.tileSize, gp.tileSize * 11 - 150);
		    }
		    g2.drawString(text, x - 250, gp.tileSize * 11 - 150);

		    // Legendary Orange
		    text = "Legendary Orange";
		    x = getXforCenteredString(text);
		    if(commandCharNum == 4) {
		        g2.drawString(">", x - gp.tileSize, gp.tileSize * 11 - 150);
		    }
		    g2.drawString(text, x, gp.tileSize * 11 - 150);

		    g2.drawImage(gp.player.down1, x + 35, gp.tileSize * 11 - 253, gp.tileSize * 2, gp.tileSize * 2, null);
		    g2.drawImage(gp.player.jansen_down1, x - 210, gp.tileSize * 11 - 255, gp.tileSize * 2, gp.tileSize * 2, null);
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "GAME PAUSED";
		int x = getXforCenteredString(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredString(String text) {
		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - lenght / 2;
		return x;
	}
}