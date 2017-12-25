
public class Game implements Runnable{

	private static boolean running = false;
	private Thread thread;


	public static Window window;
	public static Player player;
	public static InputHandler input;
	public static GraphicsHandler renderer;
	public static int SCORE = 0;
	public static int TIME = 0;

	private int ticksPerSecondCap = 60;
	
	public static int TPS = 0;
	
	public void init() {
		window = new Window();

		renderer = new GraphicsHandler();
		

		input = new InputHandler();
		player = new Player();

		HighScoreHandler.readScores();

		renderer.start();
	}

	public void run() {
		init();

		long second = 1000000000;
		long timer = second / ticksPerSecondCap;
		long now = System.nanoTime();
		long lastTime = now;
		long longTime = now;

		int ticks = 0;

		while(running) {

			now = System.nanoTime();
			if(now - lastTime >= timer) {
				if(Player.isAlive) {
					SCORE++;
				}
				tick();
				ticks++;
				lastTime = now;
			}

			if(now - longTime >= second) {
				TPS = ticks;
				ticks = 0;
				longTime = now;
				longTick();
				//System.out.println("TPS: " + TPS);
			}

		}

	}

		//ticks 60 times a second
	public void tick() {
		player.tick();
		for(int i = 0; i < EnemyHandler.enemies.size(); i++) {
			EnemyHandler.enemies.get(i).tick();
		}
		EnemyHandler.tick();
	}

		//ticks every second 
	public void longTick() {
			if(Player.isAlive) {
				EnemyHandler.longTick();
				TIME++;
			}
			
	}

	public synchronized void start() {
		if(!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
		if(running) {
			running = false;
			try {
				thread.join();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void resetGame() {
		SCORE = 0;
		TIME = 0;
		EnemyHandler.enemies.clear();
		EnemyHandler.pickUps.clear();
		Player.lives = 3;
		Player.isAlive = true;
		Player.x = 200;
		Player.y = 200;
		input.DOWN = false;
		input.UP = false;
		input.RIGHT = false;
		input.LEFT = false;
	}

	
}