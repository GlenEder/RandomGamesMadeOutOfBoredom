import java.awt.*;

public class PickUp {
	

	private int x, y;
	private int size;
	private int id;
	private Color color;
	private Rectangle rect;

	private int width = GraphicsHandler.width;



	public PickUp(int x, int y, int size, Color color, int id) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.id = id;
		rect = new Rectangle(x, y, width / size, width / size);
	}

	public void render(Graphics g) {
		width = GraphicsHandler.width;
		g.setColor(color);
		g.fillOval(x, y, width / size, width / size);
	}

	public Rectangle getBounds() {
		return rect;
	}

	public boolean isLife() {
		if(id == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isBlueMan() {
		if(id == 2) {
			return true;
		}else {
			return false;
		}
	}
}