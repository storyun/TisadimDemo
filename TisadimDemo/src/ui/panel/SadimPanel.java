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
	
	private MyCanvas canvas;
	
	private int shapeId;
	private Color edgeColor;
	private Color fillColor;
	private float border;

	public SadimPanel(int shapeId, Color edgeColor, Color fillcolor, String borderstring) {
		super();
		
		this.setSize(new Dimension(800, 600));
		setLayout(null);
		
		//init
		setShapeId(shapeId);
		setEdgeColor(edgeColor);
		setFillColor(fillcolor);
		setBorder(Float.valueOf(borderstring).floatValue());
		
		canvas = new MyCanvas();
		canvas.setBounds(0, 0, 800, 600);
		canvas.setBackground(Color.white);
		add(canvas);
	}

	public void setShapeId(int a)
	{
		shapeId = a;
	}
	
	public void setEdgeColor(Color a)
	{
		edgeColor = a;
	}
	
	public void setFillColor(Color a)
	{
		fillColor = a;
	}
	
	public void setBorder(float a)
	{
		border = a;
	}
	
	private class MyCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g) {			
			super.paint(g);
			Graphics2D g2 = (Graphics2D)canvas.getGraphics();

			// 현재 그리고 있는 도형 그림
			g2.setColor(edgeColor);
			g2.setStroke(new BasicStroke(border));
			/*
			if(shapeId == Shape.RECTANGLE) {
				g2.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			}
			else if(shapeId == Shape.CIRCLE) {
				g2.drawOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			}
			else if(shapeId == Shape.LINE) {
				g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			}
			else if(shapeId == Shape.CURVE) {
				for(int i=0; i<curvePoint.size()-1; i++) {
					Point p1 = curvePoint.get(i);
					Point p2 = curvePoint.get(i+1);
					g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
				}
			}*/
		}
	}
	
	
}