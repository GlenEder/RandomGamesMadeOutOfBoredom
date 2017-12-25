
import java.awt.*;

public class Enemy {
	
	private double x;
	private double y;
	private double speedScale;
	private int size;
	private Color color;
	private boolean canTrack;
	private boolean hasHit = false;


	private int xDir = 1;
	private int yDir = 1;
	private int width;
	private int height;
	private Rectangle rect;

	public Enemy(double x, double y, double speedScale, int size, Color color, boolean canTrack) {
		this.x = x;
		this.y = y;
		this.speedScale = speedScale;
		this.size = size;
		this.color = color;
		this.canTrack = canTrack;


		width = Game.renderer.width;
		height = Game.renderer.height;
		rect = new Rectangle((int)x, (int)y, (int)(width / size), (int)(width / size));
	}

	public void tick() {
		double speed = Game.renderer.width / speedScale;
		width = Game.renderer.width;
		height = Game.renderer.height;

		if(canTrack) {
			if(x < Player.x) {
				x += speed;
			}else if(x > Player.x) {
				x -= speed;
			}

			if(y < Player.y) {
				y += speed;
			}else if(y > Player.y) {
				y -= speed;
			}

			
		}else {
			x += (speed * xDir);
			y += (speed * yDir);
		}
		


		//check bounds 
		if(x < 0) {
			x = 0;
			xDir *= -1;
		}else if(x > width  - ((int)(width / size))) {
			x = width - ((int)(width / size));
			xDir *= -1;
		}

		if(y < 0) {
			y = 0;
			yDir *= -1;
		}else if (y > height - ((int)(width / size))) {
			y = height - ((int)(width / size));
			yDir *= -1;
		}

		rect.setLocation((int)x, (int)y);

	}

	public void render(Graphics g) {
		g.setColor(color);
		if(Player.isPacMan) {
			g.setColor(Color.blue);
		}
		if(GraphicsHandler.debugging) {
			g.drawRect((int)x, (int)y, (int)(width /size), (int)(width / size));
		}else {
			g.fillRect((int)x, (int)y, (int)(width / size), (int)(width / size));
		}
	}

	public boolean hasHit() {
		return hasHit;
	}

	public void setHasHit(boolean b) {
		hasHit = b;
	}

	public Rectangle getBounds() {
		return rect;
	}

	public void flipDirection() {
		xDir *= -1;
		yDir *= -1;
	}
}


