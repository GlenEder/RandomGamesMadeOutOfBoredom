import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {

	private Thread thread;
	private Random random = new Random();
	private boolean running = false;
	private int maxUpdates = 10;
	private Window window;
	private static Input input;
	private static Player player;
	private static PickUp pickUp;

	public static int TICKS = 0;
	public static int boardSize = 16;
	public static int tileSize = 48;
	public static int width = tileSize * boardSize;
	public static int height = width;

	public void init() {
			window = new Window();
			window.add(this);
			input = new Input();
			window.addKeyListener(input);
			player = new Player(12, 12);
			int x = random.nextInt(boardSize);
			int y = random.nextInt(boardSize);
			if(x == 12) {
				x = 0;
			}

			if(y == 12) {
				y = 0;
			}

			pickUp = new PickUp(x, y); 

	}


	public void run() {

		init();

		long now = System.nanoTime();
		long lastTime = now;
		long longTime = now;
		long second = 1000000000;
		long shortTimer = second / maxUpdates;

		int ups = 0;

		while(running) {

			if(now - lastTime >= shortTimer) {
				if(Player.isAlive) {
					tick();
				}
				repaint();
				ups++;
				lastTime = now;
			}

			if(now - longTime >= second) {
				TICKS = ups;
				System.out.println("Ups: " + TICKS);
				ups = 0;
				longTime = now;
			}

			now = System.nanoTime();

		}

	}

	public void tick() {
		player.tick();
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, width, height);
		
		//draw board
		g.setColor(Color.gray);
		g.fillRect(0, 0, boardSize * tileSize, boardSize * tileSize);

		g.setColor(Color.white);
		//debugging 
		g.drawString("xPos: " + Player.x, 10, 10);
		g.drawString("yPos: " + Player.y, 10, 20);
		g.drawString("size: " + Player.size, 10, 30);


		//draw pickup 
		pickUp.render(g);

		//draw player
		player.render(g);

		//draw lines 
		g.setColor(Color.black);
		for(int i = 1; i < boardSize; i++) {
				g.drawLine(i * tileSize, 0, i * tileSize, height);
				g.drawLine(0, i * tileSize, width, i * tileSize);
		}

		//game over
		if(Player.isAlive == false) {
			g.setFont(new Font("IMPACT", Font.BOLD, 44));
			g.drawString("Game Over!", 150, 150);
		}



	}

	public static void resetGame() {
		input.resetInput();
		player.resetPlayer();
		pickUp.move();
	}
	
	public synchronized void start() {
		if(!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
}