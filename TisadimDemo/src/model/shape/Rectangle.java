package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import model.Shape;

public class Rectangle extends Shape{

	public static final int START_POINT = 0;
	public static final int END_POINT = 4;
	
	private int width;
	private int height;
	
	private Point startPoint;
	private Point endPoint;
	
	public Rectangle(Color edgeColor, Color fillColor, float stroke) {
		super(edgeColor, fillColor, stroke);
		
		setId(Shape.RECTANGLE);
		width = 0;
		height = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawRect(startPoint.x, startPoint.y, width, height);
	}

	@Override
	public void doPress(Point endPoint) {
		// TODO Auto-generated method stub
		
		this.endPoint = endPoint;
		initialRealPoint();
		initialWidthHeight();
		
	}
	
	private void initialRealPoint() {
		
		if( startPoint.x < endPoint.x && startPoint.y < endPoint.y) return;
		
		Point realStartPoint = new Point();
		Point realEndPoint = new Point();
		
		if( startPoint.x >= endPoint.x) {
			realStartPoint.x = endPoint.x;
			realEndPoint.x = startPoint.x;
		}
		else if(startPoint.x < endPoint.x) {
			realStartPoint.x = startPoint.x;
			realEndPoint.x = endPoint.x;
		}
		
		if( startPoint.y >= endPoint.y) {
			realStartPoint.y = endPoint.y;
			realEndPoint.y = startPoint.y;
		}
		else if(startPoint.y < endPoint.y) {
			realStartPoint.y = startPoint.y;
			realEndPoint.y = endPoint.y;
		}
		
		startPoint = realStartPoint;
		endPoint = realEndPoint;
	}
	
	private void initialWidthHeight() {
		width = endPoint.x - startPoint.x;
		height = endPoint.y - startPoint.y;
	}

	@Override
	public void doRelease(Point endPoint) {
		// TODO Auto-generated method stub
		
		initialRealPoint();
		initialWidthHeight();
		initialCenterPoint();
		
		initialPointList();
	}
	
	private void initialCenterPoint() {
		Point p = new Point();
		
		p.x = (startPoint.x + endPoint.y)/2;
		p.y = (startPoint.y + endPoint.y)/2;
		
		setCenterPoint(p);
	}
	
	private void initialPointList() {
		
	}

	@Override
	public void doClick(Point startPoint) {
		// TODO Auto-generated method stub
		this.startPoint = startPoint;
	}
}
