package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import model.Shape;

public class Curve extends Shape{

	public static final int START_POINT = 0;
	
	public Curve(Color edgeColor, Color fillColor, float stroke) {
		super(edgeColor, fillColor, stroke);
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		ArrayList<Point> pointList = getPointList();
		for(int i=1; i<pointList.size(); i++) {
			Point startPoint = pointList.get(i-1);
			Point endPoint = pointList.get(i);
			
			g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		}
	}

	@Override
	public void doClick(Point startPoint) {
		// TODO Auto-generated method stub
		ArrayList<Point> pointList = getPointList();
		
		pointList.add(startPoint);
	}

	@Override
	public void doPress(Point endPoint) {
		// TODO Auto-generated method stub
		ArrayList<Point> pointList = getPointList();
		
		pointList.add(endPoint);
	}

	@Override
	public void doRelease(Point endPoint) {
		// TODO Auto-generated method stub
		ArrayList<Point> pointList = getPointList();
		
		pointList.add(endPoint);
		initialStartEndPoint();
		initialCenterPoint();
	}

	private void initialStartEndPoint() {
		int minX = getPointList().get(START_POINT).x;
		int maxX = getPointList().get(START_POINT).x;
		int minY = getPointList().get(START_POINT).y;
		int maxY = getPointList().get(START_POINT).y;
		
		ArrayList<Point> pointList = getPointList();
		for(int i=1; i<getPointList().size(); i++) {
			if( pointList.get(i).x < minX) {
				minX = pointList.get(i).x;
			}
			
			if( pointList.get(i).y < minY) {
				minY = pointList.get(i).y;
			}
			
			if( pointList.get(i).x > maxX) {
				maxX = pointList.get(i).x;
			}
			
			if( pointList.get(i).y > maxY) {
				maxY = pointList.get(i).y;
			}
		}
		
		Point startPoint = new Point(minX, minY);
		Point endPoint = new Point(maxX, maxY);
		
		setStartPoint(startPoint);
		setEndPoint(endPoint);
	}
	
	
}
