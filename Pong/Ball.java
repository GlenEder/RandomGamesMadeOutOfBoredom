import java.awt.*;
import java.util.Random;

public class Ball {

	private double x, y;
	private double speed = 6;
	private double xvel = speed;
	private double yvel = speed;
	private final int vertBuffer = 43;
	private final int horBuffer = 11;

	private Rectangle rect;
	private Random rand = new Random();

	public Ball() {
		resetBall();
		rect = new Rectangle((int)x, (int)y, 11, 11);
	}

	public int tick() {
		y += yvel;
		x += xvel;
		if(y <= 0) {
			y = 0;
			yvel *= -1;
		}else if (y >= Window.height - vertBuffer) {
			y = Window.height - vertBuffer;
			yvel *= -1;
		}

		if(x <= 0) {
			x = 0;
			xvel *= -1;
			return -1;
		}else if (x >= Window.width - horBuffer) {
			x = Window.width - horBuffer;
			xvel *= -1;
			return 1;
		}

		rect.setLocation((int)x, (int)y);

		return 0;

	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 11, 11);
	}

	public void resetBall() {
		x = Window.width / 2 - 5;
		y = Window.height / 2 - 5;

		int n = rand.nextInt(10) % 2;
		if(n == 1) {
			xvel = (speed *= -1);
		}else {
			xvel = speed;
		}
		
		n = rand.nextInt(10) % 2;
		if(n == 1) {
			yvel = (speed *= -1);
		}else {
			yvel = speed;
		}
	}

	public void increaseSpeed() {
		xvel *= 1.25;
		yvel *= 1.25;
	}

	public void flipDirection() {
		xvel *= -1;
	}

	public Rectangle getBounds() {
		return rect;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}