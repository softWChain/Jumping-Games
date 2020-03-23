import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	
	private boolean[] keys = new boolean[256];
	
	
	public KeyBoard(){
		
		Game.UP 	 = false;
		Game.DOWN	 = false;
		Game.LEFT	 = false;
		Game.RIGHT	 = false;
	}
	
	public void update(){
		
		Game.UP = keys[KeyEvent.VK_SPACE];
		Game.LEFT = keys[KeyEvent.VK_A];
		Game.RIGHT = keys[KeyEvent.VK_D];
		Game.DOWN = keys[KeyEvent.VK_SHIFT];

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
