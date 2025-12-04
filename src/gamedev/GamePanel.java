package gamedev;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTilesize = 16; //16x16 tiles 
	final int scale = 3;
	
	public final int tileSize = originalTilesize * scale; //48x48 tile 
	public final int maxScreenCol = 16; //16 tiles horizontal
	public final int maxScreenRow = 12; //12 tiles vertical
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	
	//WORLD
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	//FPS
	int fps = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this); 
	Sound music = new Sound();
	Sound se = new Sound();
	
	public Collisions cChecker = new Collisions(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[50];
	Thread gameThread;
	
	
	//PLAYER SKINS
	public int playerSkin = 1;
	
	
	//GAME STATE
	public final int titleState = 0;
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int characterState = 3;
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		loadSkin();
	}
	
	public void setupGame(){
		 aSetter.setObj();
		playMusic(0);
		stopMusic();
		gameState = titleState;
	}


	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override

	public void run() {
		
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
//				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}

		}
		
	}
	
	public void update() {
		
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			//nothing
		}
		
	
	}
	   
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//TITLE SCREEN
		
		if(gameState == titleState) {
			ui.draw(g2);
		}
		else {
			
			//tile
			
			tileM.draw(g2);
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			player.draw(g2);
			//UI draw
			ui.draw(g2);
			
		}
		
		g2.dispose();
	}

	public void playMusic(int i){
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic(){
		music.stop();
	}

	public void playSE(int i){
		System.out.println("Loading sound: " + se.soundURL[i]);
	    se.setFile(i);
	    se.play();
	}
	
	public void saveSkin() {
	    try {
	        FileWriter fw = new FileWriter("skin.dat");
	        fw.write(String.valueOf(playerSkin));
	        fw.close();
	    } catch (Exception e) {}
	}

	public void loadSkin() {
	    try {
	        File file = new File("skin.dat");
	        if(file.exists()) {
	            Scanner sc = new Scanner(file);
	            playerSkin = Integer.parseInt(sc.nextLine());
	            sc.close();
	        }
	    } catch (Exception e) {}
	}
	
}