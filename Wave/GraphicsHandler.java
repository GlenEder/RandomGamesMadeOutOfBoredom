
import javax.swing.JPanel;
import java.awt.*;

public class GraphicsHandler extends JPanel implements Runnable{
	
	private int FPS = 0;
	private int fpsCap = 60;
	private Thread thread;
	private boolean rendering = false;

	public static boolean debugging = false;
	public static int width;
	public static int height;

	private String lives = "Lives ";

	public GraphicsHandler() {
		Game.window.add(this);
		Dimension screen = Game.window.getContentPane().getSize();
		width = (int)screen.getWidth();
		height = (int)screen.getHeight();
	}

	public void run() {

		long second = 1000000000;
		long timer = second / fpsCap;
		long now = System.nanoTime();
		long lastTime = now;
		long longTime = now;

		int ticks = 0;

		while(rendering) {

			now = System.nanoTime();
			if(now - lastTime >= timer) {
				repaint();
				ticks++;
				lastTime = now;
			}

			if(now - longTime >= second) {
				FPS = ticks;
				ticks = 0;
				longTime = now;
				//System.out.println("FPS: " + FPS);
			}

		}
	}

	public void paint(Graphics g) {
		Dimension screen = Game.window.getContentPane().getSize();
		width = (int)screen.getWidth();
		height = (int)screen.getHeight();

		g.clearRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		if(debugging) {
			g.setColor(Color.green);

			//left side
			g.drawString("FPS: " + FPS, 10, 20);
			g.drawString("TPS: " + Game.TPS, 10, 30);
			g.drawString("Num Enemies: " + EnemyHandler.numEnemies, 10, 40);
			g.drawString("Is PacMan: " + Player.isPacMan, 10, 50);

			//right side
			g.drawString("Panel Width: " + width, width - 150, 20);
			g.drawString("Panel Height: " + height, width - 150, 30);
			g.drawString("Player X: " + (int)Game.player.x, width - 150, 40);
			g.drawString("Player Y: " + (int)Game.player.y, width - 150, 50);

		}

		g.setColor(Color.gray);
		g.setFont(new Font("Impact", Font.BOLD, 40));
		g.drawString("Score: " + Game.SCORE, 10, 50);
		g.drawString("High Score: " + HighScoreHandler.highScore, width - 400, height - 50);

		lives = "Lives: ";
		for(int i = 0; i < Player.lives; i++) {
			lives += "#";
		}
		g.drawString(lives, 20, height - 50);

		for(int i = 0; i < EnemyHandler.pickUps.size(); i++) {
			EnemyHandler.pickUps.get(i).render(g);
		}

		Game.player.render(g);
		for(int i = 0; i < EnemyHandler.enemies.size(); i++) {
			EnemyHandler.enemies.get(i).render(g);
		}


		if(!Player.isAlive) {
			g.setColor(Color.gray);
			g.setFont(new Font("Impact", Font.BOLD, 80));
			g.drawString("Game Over!", width / 4, height / 2);
		}

		g.dispose();
	}

	public synchronized void start() {
		if(!rendering) {
			rendering = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
		if(rendering) {
			try {
				rendering = false;
				thread.join();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}