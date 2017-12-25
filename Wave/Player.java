import java.awt.*;

public class Player {
		
	public static double x = 200;
	public static double y = 400;
	public static int lives = 3;
	public static boolean isAlive = true;
	public static boolean isPacMan = false;
	private double speedScale = 250;

	private double scale = 42;

	private int width;
	private int height;
	private static int timerScore;
	private int pacManTime = 5;

	private static Rectangle rect;

	public Player() {
		width = Game.renderer.width;
		height = Game.renderer.height;
		rect = new Rectangle((int)x, (int)y, (int)(width / scale), (int)(width / scale));
	}

	public void tick() {

		if(lives <= 0) {
			isAlive = false;
			HighScoreHandler.recordNewHighScore(Game.SCORE);
			HighScoreHandler.displayHighScores();
			Game.resetGame();
		}

		if(isPacMan && Game.TIME - timerScore > pacManTime) {
			isPacMan = false;
		}

		width = Game.renderer.width;
		height = Game.renderer.height;

		double speed = Game.renderer.width / speedScale;
		if(Game.input.UP) {
			y -= speed;
		}else if(Game.input.DOWN) {
			y += speed;
		}
		if(Game.input.RIGHT) {
			x += speed;
		}else if(Game.input.LEFT) {
			x -= speed;
		}

		if(x < 0) {
			x = 0;
		}else if (x > width - ((int)(width / scale))) {
			x = width - ((int)(width / scale));
		}

		if(y < 0) {
			y = 0;
		}else if(y > height - ((int)(width / scale))) {
			y = height - ((int)(width / scale));
		}

		rect.setLocation((int)x, (int)y);


	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		if(isPacMan) {
			g.setColor(Color.black);
			g.setFont(new Font("Impact", Font.BOLD, 36));
			int time = 5 - (Game.TIME - timerScore);
			if(time < 0) {
				time = 0;
			}
			g.drawString("" + time, width / 2, 100);
			g.setColor(Color.yellow);
		}
		if(GraphicsHandler.debugging) {
			g.drawRect((int)x, (int)y, (int)(width /scale), (int)(width / scale));
		}else {
			g.fillRect((int)x, (int)y, (int)(width / scale), (int)(width / scale));
		}
		
	}

	public static Rectangle getBounds() {
		return rect;
	}

	public static void setIsPacMan(boolean b) {
		isPacMan = b;
		if(b) {
			timerScore = Game.TIME;
		}
	}
}