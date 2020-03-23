import java.awt.Image;

import javax.swing.ImageIcon;

public class Loader {
	
	public static Image imageLoader(String path){
		
		return new ImageIcon(path).getImage();
		
	}

}
