import java.awt.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable{

	private Thread thread;
	private Window window;
	private InputHandler input;
	private Player p1;
	private Player p2;
	private static Ball ball;
	private boolean running = false;
	private boolean paused = true;

	private int lastScore = -1;
	public static int FPS = 0;


	public void init() {
		window = new Window();
		window.createDisplay();	
		input = new InputHandler();
		window.getFrame().addKeyListener(input);
		window.getFrame().add(this);
		p1 = new Player(1, false);
		p2 = new Player(2, true);
		ball = new Ball();
	}

	public void run() {

		init();

		long now = System.nanoTime();
		long lastTime = now;
		long sec = 1000000000;
		long longTime = now;
		long timetime = now;
		long playerTimer = sec / 60;
		long timer = sec / 60;
		int ticks = 0;

		while(running) {
			now = System.nanoTime();
			if(now - lastTime >= playerTimer) {
				tick();
				repaint();
				ticks++;
				lastTime = now;
			}

			if(now - timetime >= timer) {
				longTick();
				timetime = now;
			}

			if(now - longTime >= sec) {
				FPS = ticks;
				ticks = 0;
				longTime = now;
				System.out.println("FPS: " + FPS);
			}

		}

		stop();
		
	}

	public void tick() {
		if(paused) {
			if(InputHandler.space) {
				paused = false;
			}
		}else {
			p1.tick();
			p2.tick();
			
		}
	}

	public void longTick() {
		if(!paused) {
			int n = ball.tick();
			if(ball.getBounds().intersects(p1.getBounds()) || ball.getBounds().intersects(p2.getBounds())) {
				ball.flipDirection();
				ball.increaseSpeed();
			}
			if(n == 1) {
				addPoint(1);
				lastScore = 1;
				ball.resetBall();
				paused = true;
			}else if(n == -1) {
				addPoint(2);
				lastScore = 2;
				ball.resetBall();
				paused = true;
			}
		}
		
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, Window.width, Window.height);

		//set court lines
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.width, Window.height);

		g.setColor(Color.white);
		g.drawLine(Window.width / 2, 0, Window.width / 2, Window.height);
		g.drawOval((Window.width / 2) - 50, (Window.height / 2) - 50, 100, 100);

		if(paused && lastScore > 0) {
			g.setFont(new Font("Impact", Font.BOLD, 50));
			g.drawString("Player " + lastScore + " Scored!", 355, 250);
		}

		//player updates
		p1.render(g);
		p2.render(g);
		ball.render(g);

		g.dispose();
	}

	public void addPoint(int id) {
		if(id == 1) {
			p1.addPoint();
		}else {
			p2.addPoint();
		}
	}

	public synchronized void stop() {
		System.out.println("Stopping game");
		if(thread != null) {
			running = false;
			try {
				thread.join();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.exit(0);
			}
		}
	}

	public synchronized void start() {
		if(thread == null) {
			thread = new Thread(this);
			running = true;
			thread.start();
		}
	}

	public static Ball getBall() {
		return ball;
	}
}