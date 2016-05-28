package ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import model.Shape;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Canvas;

public class SadimPanel extends JPanel{
	
	private Canvas canvas;


	public SadimPanel(int shapeId, Color edgeColor, Color fillColor) {
		super();
		
		this.setSize(new Dimension(800, 600));
		setLayout(null);
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, 800, 600);
		add(canvas);
		
		
	}
	/*
	private class MyCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g) {			
			super.paint(g);
			Graphics2D g2 = (Graphics2D)canvas.getGraphics();
			
			// 이전까지 그렸던 도형 그림
			for(int i=0; i<sList.size(); i++) {
				Shape s = sList.get(i);
				
				g2.setColor(s.getColor());
				g2.setStroke(s.getStroke());
				s.draw(g2);
			}
			
			// 
			if( startPoint == null && endPoint == null ) return;
			
			// 현재 그리고 있는 도형 그림
			g2.setColor(currentColor);
			String s = borderSpinner.getValue().toString();
			currentStroke = Float.valueOf(s).floatValue();
			g2.setStroke(new BasicStroke(currentStroke));
			if(drawID == Shape.RECTANGLE) {
				
				g2.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			}
			else if(drawID == Shape.CIRCLE) {
				g2.drawOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			}
			else if(drawID == Shape.LINE) {
				g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			}
			else if(drawID == Shape.CURVE) {
				for(int i=0; i<curvePoint.size()-1; i++) {
					Point p1 = curvePoint.get(i);
					Point p2 = curvePoint.get(i+1);
					g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
				}
			}
		}
	}
	*/
	
	
	
	
}