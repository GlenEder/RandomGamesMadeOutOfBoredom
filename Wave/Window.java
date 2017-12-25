import javax.swing.JFrame;
import java.awt.Dimension;

public class Window extends JFrame {

	private int width = 1920;
	private int height = 1080;
	
	public Window() {
		super("Wave");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(width, height));

		setVisible(true);
	}

}