package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Orange extends SuperObject{
	
	public OBJ_Orange(){

        name = "Orange";

        try {                                                                           
        	
        	image = ImageIO.read(getClass().getResourceAsStream("/objects/Orange.png"));
            
        } catch (IOException e) {
            e.printStackTrace();

        }
        
    }

}
