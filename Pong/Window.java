import javax.swing.*;
public class Window {
	JFrame frame;	
	public static int width = 1080;
	public static int height = 720;

	public void createDisplay() {
		frame = new JFrame("Pong by Glen");
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}