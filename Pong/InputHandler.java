import java.awt.event.*;


public class InputHandler implements KeyListener{
	
	public static boolean w = false;
	public static boolean s = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean space = false;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			w = true;
		}else if(key == KeyEvent.VK_S) {
			s = true;
		}else if(key == KeyEvent.VK_UP) {
			up = true;
		}else if(key == KeyEvent.VK_DOWN) {
			down = true;
		}else if(key == KeyEvent.VK_SPACE) {
			space = true;
		}

		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			w = false;
		}else if(key == KeyEvent.VK_S) {
			s = false;
		}else if(key == KeyEvent.VK_UP) {
			up = false;
		}else if(key == KeyEvent.VK_DOWN) {
			down = false;
		}else if(key == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}