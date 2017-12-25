import java.awt.*;
import java.util.ArrayList;

public class Player {

	public static int x, y;
	public static int size = 1;

	public static boolean isAlive = true;

	public static ArrayList <Integer> xPositions = new ArrayList<>();
	public static ArrayList <Integer> yPositions = new ArrayList<>();
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		xPositions.add(x);
		yPositions.add(y);
	}

	public void tick() {

		//handle movement
		if(Input.UP) {
			y--;
		}else if(Input.DOWN) {
			y++;
		}else if (Input.RIGHT) {
			x++;
		}else if (Input.LEFT) {
			x--;
		}

		//check out of bounds
		if(x < 0 || x > (Game.boardSize - 1) || y < 0 || y > (Game.boardSize - 1)) {
			gameOver();
		}

		//check for autocanabalism?
		for(int i = 1; i < xPositions.size(); i ++) {
			if(x == xPositions.get(i) && y == yPositions.get(i)) {
				gameOver();
			}
		}

		//check on pickup
		if(!onPickUp()) {
			xPositions.remove(0);
			xPositions.add(x);

			yPositions.remove(0);
			yPositions.add(y);
		}else {
			getLonger();
		}
	}

	public boolean onPickUp() {
		if(x == PickUp.x && y == PickUp.y) {
			PickUp.move();
			return true;
		} 
		return false;
	}

	public void getLonger() {
		xPositions.add(x);
		yPositions.add(y);
		size++;

	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		for(int i = 0; i < xPositions.size(); i++) {
			g.fillRect(xPositions.get(i) * Game.tileSize, yPositions.get(i) * Game.tileSize, Game.tileSize, Game.tileSize);
		}
	}

	public void gameOver() {
		System.out.println("===========\n=GAME OVER=\n===========");
		isAlive = false;
	}

	public static void resetPlayer() {
		x = 10; 
		y = 10;
		xPositions.clear();
		yPositions.clear();
		size = 0;
		isAlive = true;
		xPositions.add(x);
		yPositions.add(y);
	}
}