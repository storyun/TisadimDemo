package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import model.Shape;

public class Rectangle extends Shape{

	public static final int START_POINT = 0;
	public static final int END_POINT = 4;
	
	public Rectangle(Color edgeColor, Color fillColor, float stroke) {
		super(edgeColor, fillColor, stroke);
		
		setId(Shape.RECTANGLE);
		width = 0;
		height = 0;
	}
	
	@Override
	public void drawEdge(Graphics2D g) {
		if(width>0 && height>0)
			g.drawRect(getStartPoint().x, getStartPoint().y, width, height);
		else if(width>0 && height<0)
			g.drawRect(getStartPoint().x, getEndPoint().y, width, -height);
		else if(width<0 && height>0)
			g.drawRect(getEndPoint().x, getStartPoint().y, -width, height);
		else if(width<0 && height<0)
			g.drawRect(getEndPoint().x, getEndPoint().y, -width, -height);
	}
	
	@Override
	public void drawFill(Graphics2D g) {
		if(width>0 && height>0)
			g.fillRect(getStartPoint().x, getStartPoint().y, width, height);
		else if(width>0 && height<0)
			g.fillRect(getStartPoint().x, getEndPoint().y, width, -height);
		else if(width<0 && height>0)
			g.fillRect(getEndPoint().x, getStartPoint().y, -width, height);
		else if(width<0 && height<0)
			g.fillRect(getEndPoint().x, getEndPoint().y, -width, -height);
	}

	@Override
	public void doPress(Point endPoint) {
		// TODO Auto-generated method stub
		setEndPoint(endPoint);
		initialWidthHeight();
		
	}
	
	private void initialRealPoint() {
		
		if( getStartPoint().x < getEndPoint().x && getStartPoint().y < getEndPoint().y) return;
		
		Point realStartPoint =  new Point();
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
