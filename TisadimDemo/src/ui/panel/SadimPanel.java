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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import model.Shape;
import model.handler.ShapeList;
import model.shape.Circle;
import model.shape.Curve;
import model.shape.Line;
import model.shape.Polygon;
import model.shape.Rectangle;
import ui.SadimFrame;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Canvas;

public class SadimPanel extends JPanel implements ActionListener, MouseInputListener{
	
	private SadimFrame mainFrame;
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
	
	private boolean Selectshape;

	ShapeList shapeList;
	
	boolean isMakeShape;
	
	public SadimPanel(SadimFrame mainFrame) {
		super();
		this.setSize(new Dimension(800, 600));
		setLayout(null);
		
		//init
		this.mainFrame = mainFrame;
		shapeId = 0;
		edgeColor = Color.BLACK;
		fillColor = Color.black;
		border = 1;
		isMakeShape = false;
		Selectshape = false;
		
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

				// 이전까지 그렸던 도형 그림
			for(int i=0; i<shapeList.size(); i++) {
				
				Shape s = shapeList.get(i);

				g2.setColor(s.getEdgeColor());
				g2.setStroke(new BasicStroke(s.getStroke()));
				s.drawEdge(g2);
				
				g2.setColor(s.getFillColor());
				s.drawFill(g2);

				if(Selectshape && s.getIsSelect())
					s.drawSelect(g2);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		border = Float.valueOf(mainFrame.getSpinner().getValue().toString()).floatValue();
		
		if( shapeId == Shape.CIRCLE) {
			isMakeShape = true;
			circle = new Circle(edgeColor, fillColor, border);
			shapeList.addShape(circle);
			
			shape = shapeList.get(shapeList.size()-1);
			shape.doClick(e.getPoint());
			
			canvas.repaint();
		}
		else if(shapeId == Shape.CURVE) {
			isMakeShape = true;
			curve = new Curve(edgeColor, fillColor, border);
			shapeList.addShape(curve);

			shape = shapeList.get(shapeList.size()-1);
			shape.doClick(e.getPoint());
			
			canvas.repaint();
		}
		else if(shapeId == Shape.LINE) {
			isMakeShape = true;
			line = new Line(edgeColor, fillColor, border);
			shapeList.addShape(line);
			
			shape = shapeList.get(shapeList.size()-1);
			shape.doClick(e.getPoint());
			
			canvas.repaint();
		}
		else if(shapeId == Shape.POLYGON && isMakeShape == false) {
			isMakeShape = true;
			polygon = new Polygon(edgeColor, fillColor, border);
			shapeList.addShape(polygon);
			
			shape = shapeList.get(shapeList.size()-1);
			shape.doClick(e.getPoint());
			
			canvas.repaint();
		}
		else if(shapeId == Shape.RECTANGLE) {
			isMakeShape = true;
			rectangle = new Rectangle(edgeColor, fillColor, border);
			shapeList.addShape(rectangle);
			
			shape = shapeList.get(shapeList.size()-1);
			shape.doClick(e.getPoint());
			
			canvas.repaint();
		}
		else if(shapeId == 0)
		{
			if(shapeList.size() != 0)
			{
				for(int i=shapeList.size()-1;i>=0;i--)
				{
					switch(shapeList.get(i).getId())
					{
					case 1: //circle
					{
						int a = 0, b = 0; //a : 장축, b : 단축
						
						a = (shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x)/2;
						b = (shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/2;
						System.out.println(shapeList.get(i).getCenterPoint().x);
						if(((e.getX()-shapeList.get(i).getCenterPoint().x)*(e.getX()-shapeList.get(i).getCenterPoint().x))/(a*a)
						+((e.getY()-shapeList.get(i).getCenterPoint().y)*(e.getY()-shapeList.get(i).getCenterPoint().y))/(b*b)<=1)
						{
							System.out.println("ERER");
							Selectshape = true;
							shapeList.get(i).setSelect(true);
							break;
						}
					}
					case 2:
					case 3:
					case 4:
					case 5:
					}
					
				}
			}
		}
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
		if(shapeId != 0)
		{
			shape.doPress(e.getPoint());
			canvas.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if( shapeId == Shape.POLYGON && isMakeShape == true) {
			
			shape.doMove(e.getPoint());
			
			canvas.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	// getter , setter

	public int getShapeId() {
		return shapeId;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public ShapeList getShapeList() {
		return shapeList;
	}

	public void setShapeList(ShapeList shapeList) {
		this.shapeList = shapeList;
	}
	
	public void repaintCanvas() {
		canvas.repaint();
	}
	
}