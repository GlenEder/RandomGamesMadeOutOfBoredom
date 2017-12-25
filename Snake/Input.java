import java.awt.event.*;

public class Input implements KeyListener{

			public static boolean UP = false;
			public static boolean DOWN = false;
			public static boolean RIGHT = false;
			public static boolean LEFT = false;
	
		public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					UP = true;
					DOWN = false;
					RIGHT = false;
					LEFT = false;
				}else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					UP = false;
					DOWN = true;
					RIGHT = false;
					LEFT = false;
				}else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					UP = false;
					DOWN = false;
					RIGHT = true;
					LEFT = false;
				}else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					UP = false;
					DOWN = false;
					RIGHT = false;
					LEFT = true;
				}

				if(key == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}

				if(!Player.isAlive && key == KeyEvent.VK_SPACE) {
					Game.resetGame();
				}
		}

		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}

		public static void resetInput() {
			UP = false;
			DOWN = false;
			RIGHT = false;
			LEFT = false;
		}

}
