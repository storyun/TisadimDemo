import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.SadimFrame;

public class TisadimDemo {
	public static void main(String[] args) {

		
		
		SadimFrame sadimFrame = new SadimFrame();
		sadimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sadimFrame.setVisible(true);
		sadimFrame.setResizable(false);
		try {
	         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	            | UnsupportedLookAndFeelException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
	}
}
