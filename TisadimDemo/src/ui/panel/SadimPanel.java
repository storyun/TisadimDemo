package ui.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class SadimPanel extends JPanel{
	private JLabel lblNewLabel;

	public SadimPanel(int shapeId, Color edgeColor, Color fillColor) {
		super();
		
		this.setSize(new Dimension(800, 600));
		setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(284, 224, 137, 35);
		add(lblNewLabel);
		this.setVisible(true);
		
		
	}
}