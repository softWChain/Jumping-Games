import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SplashScreen;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game implements Runnable {
	

	private Display display;
	private Thread thread;
	private BufferStrategy bs;
	private Image image;
	private Graphics g;
	
	
	static int[] blockX = new int[5];
	static int[] blockDenemeX = new int[3];
	
	static boolean collosion = false;
	static int SCORE = 0;
	
	static boolean SCREEN = true; 
	
	static BufferedImage blockImage = LoaderBuffer.loadImage("block.png");
	
	static BufferedImage cloudBufferedImage = LoaderBuffer.loadImage("cloud1.png");
	static BufferedImage cloud3BufferedImage = LoaderBuffer.loadImage("cloud1.png");
	static BufferedImage cloud2BufferedImage = LoaderBuffer.loadImage("cloud2.png");
	static BufferedImage playerImage = LoaderBuffer.loadImage("run_anim1.png");
	static BufferedImage playerImage1 = LoaderBuffer.loadImage("jump.png");
	static BufferedImage playerImage2 = LoaderBuffer.loadImage("duck.png");
    static BufferedImage image1 = LoaderBuffer.loadImage("run_anim1.png");
	static BufferedImage image2 = LoaderBuffer.loadImage("run_anim2.png");
    static BufferedImage image3 = LoaderBuffer.loadImage("run_anim3.png");
    static BufferedImage image4 = LoaderBuffer.loadImage("run_anim4.png");
    static BufferedImage image5 = LoaderBuffer.loadImage("run_anim5.png");
    static BufferedImage playerImages[] = {image1, image2, image3, image4, image5, image4, image3, image2};
	

	
	private boolean running = false;
	static boolean devam = false;
	static boolean UP,LEFT,RIGHT,DOWN;
	
	static int xPos = 0,yPos = Display.HEIGHT -235;
	
	static int playerVelocityY = 0;
	static int playerAccY = 0;
	static int cloudX1 = 50, cloudY1 = 2;
	static int cloudX,cloudY = 2;
	static int cloudX2,cloudY2 = 2;
	static int blockY = 310;
	static int blockY1 = 215;
	
	Random random = new Random();
	
	
	static int FPSVALUE;
	
	int width,height;
	String title ;
	
	
	public Game(int width, int height , String title){
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		image = Loader.imageLoader("res/grass.png");
		

		

	}

	
	
	public void init(){
		
		display = new Display(width, height, title);
		
		blockX[0] = (int) (Math.random()*10+900);
		blockX[1] = (int) (Math.random()*10+1300);
		blockX[2] = (int) (Math.random()*1+1700);
		blockX[3] = (int) (Math.random()*1+2100);
		blockX[4] = (int) (Math.random()*1+2500);
		
		
		blockDenemeX[0] = (int) (Math.random()*1+1200);
		blockDenemeX[1] = (int) (Math.random()*10+1900);
		blockDenemeX[2] = (int) (Math.random()*10+2300);

		
		

	}
	
	public void render(){
		
		
		bs  = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//BACKGROUND
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		
		//FPS
		g.setColor(Color.CYAN);
		g.setFont(new Font("Arial" , Font.BOLD ,15));
		g.drawString("FPS : " + FPSVALUE, 720, 30);


		
		//IMAGE DRAWİNGG
		g.drawImage(image, 0,height-45, null);
		
		
		if(DOWN == true &&  yPos + 1 >  265){
			
			g.drawImage(playerImage2, xPos, yPos, null);
	
		}	
		
		else if(UP  == true && yPos < 265){
			
			g.drawImage(playerImage1, xPos, yPos, null);
		}

		else{
	    	g.drawImage(playerImage, xPos, yPos, null);
	    }
		
		g.drawImage(cloudBufferedImage, cloudX1, cloudY1, null);
		g.drawImage(cloud2BufferedImage, cloudX, cloudY, null);
		g.drawImage(cloud2BufferedImage, cloudX2, cloudY2, null);
		

		for(int i=0;i<5;i++){
			g.drawImage(blockImage, blockX[i], blockY, null);
		}
		
		for(int i=0;i<3;i++){
			g.drawImage(blockImage, blockDenemeX[i], blockY1, null);
		}
		
		if(collosion == true){
			SCORE  = SCORE - 400;
			
			
			
		}
		
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.drawString("SCORE : " + SCORE, 20, 20);

		
		
		
		if(collosion == true){
			g.setColor(new Color(253,0,0,100));
			g.fillRect(0, 0, width, height);
			g.setColor(new Color(253,0,0,100));
			g.fillRect(0, 0, width, height);
			g.setColor(new Color(253,0,0,100));
			g.fillRect(0, 0, width, height);
			collosion = false;
		}
		

		bs.show();
		g.dispose();
		
	}
	public void tick(){
		display.update();
		if(cloudX1 > -135){
			cloudX1 -=2;
			
		}
		if(cloudX1 < -130 ){
			cloudX1 = (int) (Math.random()*100+700);
			
		}
		
		if(cloudX > -135){
			cloudX -=2.2;
		}
		if(cloudX < -130 ){
			cloudX = (int) (Math.random()*500+850);
			
		}
		
		if(cloudX2 > -135){
			cloudX2 -=2.5;
		}
		if(cloudX2 < -130 ){
			cloudX2 = (int) (Math.random()*700+1000);
			
		}
		
		
		
		
		//WHEN THE BLOCKS ON THE GROUND PASS TO LEFT AND TO COME AGAİN
		
		for(int i=0;i<5;i++){
		
			if(blockX[i] > -20){
				blockX[i] -= 3;
				
			}

		}

		

		
		for(int i=0;i<5;i++){
			
			if(blockX[0] < -15){
				blockX[0] = (int) (Math.random()*10+900);

			}
			
			if(blockX[1] < -15){
				blockX[1] = (int) (Math.random()*10+1300);

			}
			
			if(blockX[2] < -15){
				blockX[2] = (int) (Math.random()*10+1700);

			}
			if(blockX[3] < -15){
				blockX[3] = (int) (Math.random()*10+2100);

			}
			if(blockX[4] < -15){
				blockX[4] = (int) (Math.random()*10+2500);

			}

			
		}
		
		
		//WHEN THE BLOCKS ON THE SPACE PASS TO LEFT AND TO COME AGAİN
		for(int i=0;i<3;i++){
			
			if(blockDenemeX[i] > -20){
				blockDenemeX[i] -= 3;
				
			}

		}
		for(int i=0;i<3;i++){
			
			if(blockDenemeX[0] < -15){
				blockDenemeX[0] = (int) (Math.random()*1+1200);

			}
			
			if(blockDenemeX[1] < -15){
				blockDenemeX[1] = (int) (Math.random()*11+1900);

			}
			
			if(blockDenemeX[2] < -15){
				blockDenemeX[2] = (int) (Math.random()*10+2300);

				}
			}
		
		//COLLOSİON FOR BLOCK ON THE GROUND
		for(int i=0;i<5;i++){
			if(Game.xPos - 40 < Game.blockX[i] && Game.xPos + 40 >  blockX[i] && Game.blockY  <=  50 + Game.yPos){
				
				if(Game.xPos - 40 < Game.blockX[0] && Game.xPos + 40 >  blockX[0] && Game.blockY  <=  50 + Game.yPos){
					blockX[0] = (int) (Math.random()*10+900);
				}
				if(Game.xPos - 40 < Game.blockX[1] && Game.xPos + 40 >  blockX[1] && Game.blockY  <=  50 + Game.yPos){
					blockX[1] = (int) (Math.random()*10+1300);
				}
				if(Game.xPos - 40 < Game.blockX[2] && Game.xPos + 40 >  blockX[2] && Game.blockY  <=  50 + Game.yPos){
					blockX[2] = (int) (Math.random()*10+1700);
				}
				if(Game.xPos - 40 < Game.blockX[3] && Game.xPos + 40 >  blockX[3] && Game.blockY  <=  50 + Game.yPos){
					blockX[3] = (int) (Math.random()*10+2100);
				}
				if(Game.xPos - 40 < Game.blockX[4] && Game.xPos + 40 >  blockX[4] && Game.blockY  <=  50 + Game.yPos){
					blockX[4] = (int) (Math.random()*10+2500);
				}

				collosion = true;
			}
		}
		
		//COLLOSİON FOR BLOCK ON THE SPACE
		for(int i=0;i<3;i++){
			if(Game.xPos - 40 < Game.blockDenemeX[i] && Game.xPos + 40 >  blockDenemeX[i] && Game.blockY1 + 40 >=  Game.yPos ){

				
				if(Game.xPos - 40 < Game.blockDenemeX[0] && Game.xPos + 40 >  blockDenemeX[0] && Game.blockY1 + 40 >=  Game.yPos){
					blockDenemeX[0] = (int) (Math.random()*1+1200);
				}
				if(Game.xPos - 40 < Game.blockDenemeX[1] && Game.xPos + 40 >  blockDenemeX[1] && Game.blockY1 + 40 >=  Game.yPos){
					blockDenemeX[1] = (int) (Math.random()*11+1900);
				}
				if(Game.xPos - 40 < Game.blockDenemeX[2] && Game.xPos + 40 >  blockDenemeX[2] && Game.blockY1 + 40 >=  Game.yPos){
					blockDenemeX[2] = (int) (Math.random()*10+2300);
				}
				
				
				
				
				collosion = true;
			}
		}
		
		SCORE = SCORE + 10;
			
		}
		

	
	public void run(){
		

		init();
		int fps = 60;
		long lastTime = System.nanoTime();
		double timePerSecond = 1000000000/fps;
		double delta = 0;
		long now;
		int ticks = 0;
		long timer=0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerSecond;
			timer += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1){
				render();
				tick();
				

				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS : " + ticks);
				FPSVALUE = ticks;
				ticks = 0;
				timer = 0;
			}
			
			
		}
		
		
		stop();
		
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop(){
		
		if(!running)
			return;
		running =false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
