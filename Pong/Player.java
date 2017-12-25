import java.awt.*;

public class Player {

	private int id;
	private int score = 0;
	private double x;
	private double y = 100.0;
	private Color color;
	private boolean isAI;

	private final int width = 20;
	private int height = 100;
	private final double speed = 8;

	private Rectangle rect;

	public Player(int playerNumber, boolean isAI) {
		if(playerNumber == 1) {
			id = 1;
			x = 25.0;
			color = Color.red;
			rect = new Rectangle((int)x + 2, (int)y, width, height);
		}else {
			id = 2;
			x = Window.width - 50;
			color = Color.blue;
			rect = new Rectangle((int)x - 2, (int)y, width, height);
		}

		this.isAI = isAI;

	
	}

	public void tick() {

		height = 100 - (score * 5);
		rect.setSize(width, height);

		if(isAI) {
			double ballY = Game.getBall().getY();


			if(y - 10 > ballY) {
				y -= speed;
			}else if(y - 10 < ballY) {
				y += speed;
			}

			if(id == 1) {
				rect.setLocation((int)x + 2, (int)y);
			}else {
				rect.setLocation((int)x - 2, (int)y);
			}


		}else {
			if(id == 1) {
				if(InputHandler.w) {
					y -= speed;
				}else if(InputHandler.s) {
					y += speed;
				}

				rect.setLocation((int)x + 2, (int)y);
			}else {
				if(InputHandler.up) {
					y -= speed;
				}else if(InputHandler.down) {
					y += speed;
				}

				rect.setLocation((int)x - 2, (int)y);
			}
		}

		if(y <= 0) {
			y = 0.0;
		}else if(y >= Window.height - height - 35) {
			y = Window.height - height - 35;
		}


	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		//g.drawString("Ypos: " + y, (int)x, 10);


		//draw score
		g.setColor(Color.white);
		String points = String.valueOf(score);
		g.setFont(new Font("Impact", Font.BOLD, 40));
		if(id == 1) {
			g.drawString(points, (int)x + 30, 40);
		}else {
			g.drawString(points, (int)x - 30, 40);
		}
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	public void addPoint() {
		score++;
	}

	public Rectangle getBounds() {
		return rect;
	}

	public int getScore() {
		return score;
	}
}