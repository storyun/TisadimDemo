package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.Shape;

public class Circle extends Shape{
	public static final int START_POINT = 0;
	public static final int END_POINT = 4;
	
	private int width;
	private int height;
	
	public Circle(Color edgeColor, Color fillColor, float stroke) {
		super(edgeColor, fillColor, stroke);
		
		setId(Shape.RECTANGLE);
		width = 0;
		height = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawRect(getStartPoint().x, getStartPoint().y, width, height);
	}

	@Override
	public void doPress(Point endPoint) {
		// TODO Auto-generated method stub
		
		setEndPoint(endPoint);
		initialRealPoint();
		initialWidthHeight();
		
	}
	
	private void initialRealPoint() {
		
		if( getStartPoint().x < getEndPoint().x && getStartPoint().y < getEndPoint().y) return;
		
		Point realStartPoint = new Point();
		Point realEndPoint = new Point();
		
		if( getStartPoint().x >= getEndPoint().x) {
			realStartPoint.x = getEndPoint().x;
			realEndPoint.x = getStartPoint().x;
		}
		else if(getStartPoint().x < getEndPoint().x) {
			realStartPoint.x = getStartPoint().x;
			realEndPoint.x = getEndPoint().x;
		}
		
		if( getStartPoint().y >= getEndPoint().y) {
			realStartPoint.y = getEndPoint().y;
			realEndPoint.y = getStartPoint().y;
		}
		else if(getStartPoint().y < getEndPoint().y) {
			realStartPoint.y = getStartPoint().y;
			realEndPoint.y = getEndPoint().y;
		}
		
		setStartPoint(realStartPoint);
		setEndPoint(realEndPoint);
	}
	
	private void initialWidthHeight() {
		width = getEndPoint().x - getStartPoint().x;
		height = getEndPoint().y - getStartPoint().y;
	}

	@Override
	public void doRelease(Point endPoint) {
		// TODO Auto-generated method stub
		
		initialRealPoint();
		initialWidthHeight();
		initialCenterPoint();
		initialPointList();
	}

	@Override
	public void doClick(Point startPoint) {
		// TODO Auto-generated method stub
		setStartPoint(startPoint);
	}
}
