import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class EnemyHandler {

		public static ArrayList<Enemy> enemies = new ArrayList<>();
		public static ArrayList<PickUp> pickUps = new ArrayList<>();
		public static int numEnemies = 0;
		public static int numPickUps = 0;

		private static int width;
		private static int height;

		private static Random random = new Random();

		public static void createEnemy(double x, double y, double speedScale, int size, Color color, boolean canTrack) {
			Enemy e = new Enemy(x, y, speedScale, size, color, canTrack);
			enemies.add(e);
			numEnemies++;
		}

		public static void createPickUp(int x, int y, int size, Color color, int id) {
			PickUp p = new PickUp(x, y, size, color, id);
			pickUps.add(p);
			numPickUps++;
		}

		public static void longTick() {

			width = GraphicsHandler.width;
			height = GraphicsHandler.height;

			if(Game.TIME % 3 == 0) {
				createEnemy((double)(width - random.nextInt(width)), (double)(height - random.nextInt(height)), 400, 50, Color.red, false);
			}

			if(Game.TIME % 7 == 0 && Game.TIME != 0) {
				createEnemy((double)(width - random.nextInt(width)), (double)(height - random.nextInt(height)), 500, 20, Color.magenta, false);
			}

			if(Game.TIME % 15 == 0 && Game.TIME != 0) {
				createEnemy((double)(width - random.nextInt(width)), (double)(height - random.nextInt(height)), 1000.0, 100, Color.black, true);
			}

			if(Game.TIME % 17 == 0 && Game.TIME != 0) {
				createEnemy((double)(width - random.nextInt(width)), (double)(height - random.nextInt(height)), 200, 80, Color.green, false);
			}

			//pickups
			if(Game.TIME % 18 == 0 && Game.TIME != 0) {
				createPickUp(width - random.nextInt(width), height - random.nextInt(height), 80, Color.blue, 2);
			}

			if (Game.TIME % 13 == 0 && Game.TIME != 0) {
				createPickUp(width - random.nextInt(width), height - random.nextInt(height), 100, Color.yellow, 1);
			}
		}

		public static void tick() {
			for(int i = 0; i < enemies.size(); i++) {
				if(!Player.isPacMan) {
					if(Player.getBounds().intersects(enemies.get(i).getBounds()) && !enemies.get(i).hasHit()) {
						Player.lives--;
						enemies.get(i).setHasHit(true);
						enemies.remove(i);
					}else if(!Player.getBounds().intersects(enemies.get(i).getBounds())) {
						enemies.get(i).setHasHit(false);
					}
				}else {
					if(Player.getBounds().intersects(enemies.get(i).getBounds())) {
						enemies.remove(i);
						Game.SCORE += 120;
					}
				}



				
			}

			for(int i = 0; i < pickUps.size(); i++) {
				if(Player.getBounds().intersects(pickUps.get(i).getBounds())) {
					if(pickUps.get(i).isLife()) {
						Player.lives++;
					}else if(pickUps.get(i).isBlueMan()) {
						Player.setIsPacMan(true);
					}



					pickUps.remove(i);
					numPickUps--;
				}
			} 
		}

	
}