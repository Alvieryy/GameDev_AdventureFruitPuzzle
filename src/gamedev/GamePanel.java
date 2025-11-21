package gamedev;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int fps = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); 
	Sound sound = new Sound();
	Thread gameThread;
	public Collisions cChecker = new Collisions(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[15];
	
	//GAME STATE
	public final int titleState = 0;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame(){
		 aSetter.setObj();
		playMusic(0);
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
		
	player.update();
	}
	   
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//tile
		tileM.draw(g2);
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		player.draw(g2);
		
		g2.dispose();
	}

	public void playMusic(int i){
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic(){
		sound.stop();
	}

	public void playSE(int i){
		sound.setFile(i);
		sound.play();
	}
	
}
