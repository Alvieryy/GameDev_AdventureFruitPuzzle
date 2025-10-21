package gamedev;
import javax.swing.*;

public class Main {
	
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.setResizable(false);
		 window.setTitle("ADVENTURE FRUIT PUZZLE");
		 window.setLocationRelativeTo(null);
		 
		 GamePanel panel = new GamePanel();
		 window.add(panel);	 
		 window.pack();
		 
		 window.setVisible(true);
		 
		 panel.startGameThread();
	}

}
