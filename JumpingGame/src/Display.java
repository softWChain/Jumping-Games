import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int WIDTH  = 800;
	static int HEIGHT = 500;
	String TITLE;
	
	private JFrame frame;
	private Canvas canvas;
	private KeyBoard keyboard;
	


	static boolean PLAY = true;
	static boolean devam2 = false;
	
	

	
	private int value = 5;
	private int counter = 0;
	
	public Display(int width , int height , String title){
		
		this.WIDTH  = width;
		this.HEIGHT = height ;
		this.TITLE = title;
	
		createDisplay();
		
	}
	
	
	public void createDisplay(){
		
		
	
		keyboard = new KeyBoard();
		frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		frame.add(canvas);
		canvas.setFocusable(true);
		canvas.addKeyListener(keyboard);
		frame.pack();

		
	}
	
	public  void update(){
	
		
		keyboard.update();
		
		counter++;

		counter = counter % 8;
	
		Game.playerImage = Game.playerImages[counter];
		
		if(Game.LEFT == true){
			if(Game.xPos > 0 ){
				Game.xPos -= value;
			}
		}
		if(Game.RIGHT == true){
			if(Game.xPos + 75 < 800){
				Game.xPos += value;
			}
		}
		


		if(Game.DOWN == true &&  Game.yPos > 265 ){

			Game.playerVelocityY += Game.playerAccY;
	        Game.yPos += Game.playerVelocityY;
			
			Game.playerVelocityY = 20;	
            Game.playerAccY = -1;

	        
	        if(Game.yPos > 265){
	        	Game.yPos = 265;
	        	Game.playerVelocityY = 0;
	            Game.playerAccY = 0;

	
	        	
	        }
			
		}
		
		if(Game.UP && Game.yPos  == 265 ) {
            Game.playerVelocityY = -20;
            Game.playerAccY = 1;
        }
		
		
		Game.playerVelocityY += Game.playerAccY;
        Game.yPos += Game.playerVelocityY;
        
        if(Game.yPos > 265){
        	Game.yPos = 265;
        	Game.playerVelocityY = 0;
            Game.playerAccY = 0;
        	
        }
        
        
	}
	
	public Canvas getCanvas(){
		
		return canvas;
	}
}
