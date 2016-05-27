import javax.swing.JFrame;

import ui.SadimFrame;

public class TisadimDemo {
	public static void main(String[] args) {
		SadimFrame sadimFrame = new SadimFrame();
		sadimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sadimFrame.setVisible(true);
		sadimFrame.setResizable(false);
	}
}
