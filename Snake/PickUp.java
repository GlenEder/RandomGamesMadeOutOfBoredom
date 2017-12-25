import java.awt.*;
import java.util.Random;

public class PickUp {
	public static int x;
	public static int y;

	private static Random rand = new Random();

	public PickUp(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void move() {
		System.out.println("Moving pick up...");
		x = rand.nextInt(Game.boardSize);
		y = rand.nextInt(Game.boardSize);

		while(isOnSnake(x, y)) {
			x = rand.nextInt(Game.boardSize);
			y = rand.nextInt(Game.boardSize);
		}
		
		System.out.println("Moved Pick Up To: " + x + ", " + y);
	}


	public static boolean isOnSnake(int x, int y) {
		for (int i = 0; i < Player.xPositions.size(); i++) {
			if(x == Player.xPositions.get(i) && y == Player.yPositions.get(i)) {
				return true;
			}
		}

		return false;
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x * Game.tileSize, y * Game.tileSize, Game.tileSize, Game.tileSize);
	}
}