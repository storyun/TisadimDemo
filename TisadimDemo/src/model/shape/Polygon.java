package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import model.Shape;

public class Polygon extends Shape{

	public static final int START_POINT = 0;
	
	private boolean isIng;
	private Point currentPoint;
	
	public Polygon(Color edgeColor, Color fillColor, float stroke) {
		super(edgeColor, fillColor, stroke);
		
		isIng = true;
		currentPoint = new Point();
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		ArrayList<Point> pointList = getPointList();
		
		for(int i=1; i<pointList.size(); i++) {
			g.drawLine(pointList.get(i-1).x, pointList.get(i-1).y, pointList.get(i).x, pointList.get(i).y);
		}
		
		Point startPoint = getPointList().get(START_POINT);
		Point endPoint = getPointList().get(getPointList().size()-1);
		if(isIng) {
			g.drawLine(endPoint.x, endPoint.y, currentPoint.x, currentPoint.y);
		}
		else {
			g.drawLine(endPoint.x, endPoint.y, startPoint.x, startPoint.y);
		}
	}

	@Override
	public void doClick(Point point) {
		// TODO Auto-generated method stub		
		ArrayList<Point> pointList = getPointList();
		
		// 첫 입력인지 아닌지
		if(pointList.size() == 0) {
			pointList.add(point);
		}
		else {
			// 입력받은점이 시작점과 같은지
			if(pointList.get(START_POINT).x == point.x && pointList.get(START_POINT).y == point.y) {
				isIng = false;
				
				// 최소값 최대값 ,,
				initialStartEndPoint();
				// 중심점
			}
			else{
				pointList.add(point);
			}
		}
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

	@Override
	public void doPress(Point endPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRelease(Point endPoint) {
		// TODO Auto-generated method stub
		
	}
	
	public void doMove(Point currentPoint) {
		if(isIng) {
			this.currentPoint = currentPoint;
		}
	}
}
