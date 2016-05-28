package ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.ShapeGraphicAttribute;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import model.Shape;
import model.handler.ShapeList;
import model.shape.Circle;
import model.shape.Curve;
import model.shape.Line;
import model.shape.Polygon;
import model.shape.Rectangle;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Canvas;

public class SadimPanel extends JPanel implements ActionListener, MouseInputListener{
	
	private MyCanvas canvas;
	
	private int shapeId;
	private Color edgeColor;
	private Color fillColor;
	private float border;
	
	private Circle circle;
	private Rectangle rectangle;
	private Line line;
	private Curve curve;
	private Polygon polygon;
	private Shape shape;

	ShapeList shapeList;
	
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
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		add(canvas);
		
		shapeList = new ShapeList();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if( shapeId == Shape.CIRCLE) {
			circle = new Circle(edgeColor, fillColor, border);
			shapeList.addShape(circle);
		}
		else if(shapeId == Shape.CURVE) {
			curve = new Curve(edgeColor, fillColor, border);
			shapeList.addShape(curve);
		}
		else if(shapeId == Shape.LINE) {
			line = new Line(edgeColor, fillColor, border);
			shapeList.addShape(line);
		}
		else if(shapeId == Shape.POLYGON) {
			polygon = new Polygon(edgeColor, fillColor, border);
			shapeList.addShape(polygon);
		}
		else if(shapeId == Shape.RECTANGLE) {
			rectangle = new Rectangle(edgeColor, fillColor, border);
		}
		shape = shapeList.get(shapeList.size()-1);
		shape.doClick(e.getPoint());
		
		canvas.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		shape.doPress(e.getPoint());
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		shape.doRelease(e.getPoint());
		canvas.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	//부모페인트

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if( shapeId == Shape.POLYGON) {
			shape.doMove(e.getPoint());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}