package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage spiderman_up1, spiderman_up2, spiderman_down1, spiderman_down2,
	spiderman_right1, spiderman_right2, spiderman_left1, spiderman_left2;
	public BufferedImage specialskin_up1, specialskin_up2, specialskin_down1, specialskin_down2,
	specialskin_left1, specialskin_left2, specialskin_right1, specialskin_right2;
	
	public BufferedImage naruto_up1, naruto_up2, naruto_down1, naruto_down2,
	naruto_left1, naruto_left2, naruto_right1, naruto_right2;
	
	public BufferedImage jansen_up1, jansen_up2, jansen_down1, jansen_down2,
	jansen_left1, jansen_left2, jansen_right1, jansen_right2;
	
	public BufferedImage setup(String path) {
	    BufferedImage image = null;
	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/" + path + ".png"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return image;
	}
	
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;

}
