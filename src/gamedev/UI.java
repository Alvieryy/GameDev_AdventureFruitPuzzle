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
	public int titleScreenState = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
//	public BufferedImage image;
	
	public UI (GamePanel gp) {
		
//		try {
//			image = ImageIO.read(getClass().getResourceAsStream("/player/BG.gif"));
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
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
		
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.playState) {
			
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
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

		else if(gp.gameState != gp.titleState) {

		    g2.setFont(arial_40);
		    g2.setColor(Color.WHITE);

		    int x = gp.tileSize / 2;
		    int y = gp.tileSize / 2;

		    g2.drawImage(keyImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.haskey, x + gp.tileSize + 5, y + 35);

		    x += gp.tileSize + 60;
		    g2.drawImage(appleImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.hasApple, x + gp.tileSize + 5, y + 35);

		    x += gp.tileSize + 60;
		    g2.drawImage(orangeImage, x, y, gp.tileSize, gp.tileSize, null);
		    g2.drawString("x " + gp.player.hasOrange, x + gp.tileSize + 5, y + 35);

		    playTime += (double)1/60;
		    g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);

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
			
			
			
//			g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
			
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

//g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//g2.drawString("x " + gp.player.haskey, 74, 65);
//g2.drawImage(appleImage, gp.tileSize/2 * 2, gp.tileSize/2, gp.tileSize + 5*2, gp.tileSize + 5, null);
//g2.drawString("x " + gp.player.hasApple, 148, 65);
//g2.drawImage(orangeImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//g2.drawString("x " + gp.player.hasOrange, 296, 65);
