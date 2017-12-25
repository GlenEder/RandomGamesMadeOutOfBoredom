import java.io.*;
import javax.swing.JOptionPane;

public class HighScoreHandler {

		public static int highScore;			
		public static String highScoreName;

		public static int middleScore;
		public static String middleScoreName;

		public static int lowScore;
		public static String lowScoreName;


		public static void displayHighScores() {
			JOptionPane.showMessageDialog(null, "HIGH SCORES:\n" +
											highScoreName + " >>> " + highScore + "\n" +
											middleScoreName + " >>> " + middleScore + "\n" +
											lowScoreName + " >>> " + lowScore);

			JOptionPane.showMessageDialog(null, "PRESS ENTER TO PLAY AGAIN!!");
			return;
			
		}

		public static void recordNewHighScore(int score) {
			if(score > highScore) {
				lowScore = middleScore;
				lowScoreName = middleScoreName;

				middleScore = highScore;
				middleScoreName = highScoreName;

				highScore = score;
				highScoreName = JOptionPane.showInputDialog(null, "Enter Name: ");
			}else if(score > middleScore) {
				lowScore = middleScore;
				lowScoreName = middleScoreName;

				middleScore = score;
				middleScoreName = JOptionPane.showInputDialog(null, "Enter Name: ");
			}else if(score > lowScore) {
				lowScore = score;
				lowScoreName = JOptionPane.showInputDialog(null, "Enter Name: ");
			}

			try {
				PrintWriter out = new PrintWriter("HighScores.txt");
				out.println(highScoreName + ":" + highScore);
				out.println(middleScoreName + ":" + middleScore);
				out.println(lowScoreName + ":" + lowScore);

				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		public static void readScores() {
			try {
				BufferedReader br = new BufferedReader(new FileReader("HighScores.txt"));
				String line;
				int pos = 0;
				while((line = br.readLine()) != null) {
					String[] parts = line.split(":");
					if(pos == 0) {
						highScoreName = parts[0];
						highScore = Integer.parseInt(parts[1]);
					}else if(pos == 1) {
						middleScoreName = parts[0];
						middleScore = Integer.parseInt(parts[1]);
					}else {
						lowScoreName = parts[0];
						lowScore = Integer.parseInt(parts[1]);
					}

					pos++;
				}
				br.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}