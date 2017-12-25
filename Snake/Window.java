import javax.swing.JFrame;

public class Window extends JFrame {
	
		public Window (){
				super("Snake by Glen Eder");
				setSize(Game.width + 5, Game.height + Game.tileSize - 10);
				setLocationRelativeTo(null);
				setResizable(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				setVisible(true);
		}
}