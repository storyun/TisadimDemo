package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import model.shape.Curve;

public abstract class Shape implements Serializable{

	public static final int START_POINT = 0;
	public static final int END_POINT = 4;
	
	public static final int SELECT = 0;
	public static final int CIRCLE = 1;
	public static final int RECTANGLE = 2;
	public static final int POLYGON = 3;
	public static final int LINE = 4;
	public static final int CURVE = 5;
	
	private int id;
	private ArrayList<Point> pointList;
	

	private ArrayList<Point> polyPointList;		//폴리곤만 작동할수있다.
	private Color edgeColor;
	private Color fillColor;
	private float stroke;
	
	private boolean isSelect;
	
	protected int width;
	protected int height;
	
	private Point centerPoint;
	private Point startPoint;
	private Point endPoint;
	
	public Shape() {
		
	}
	
	public Shape(Color edgeColor, Color fillColor, float stroke) {
		
		initialValue(edgeColor, fillColor, stroke); 	// edgeColor, fillColor, stroke
	}
	
	private void initialValue(Color edgeColor, Color fillColor, float stroke) {
		this.edgeColor = edgeColor;
		this.fillColor = fillColor;
		this.stroke = stroke;
		
		pointList = new ArrayList<Point>();
	}
	
	public void drawFill(Graphics2D g) {}
	public abstract void drawEdge(Graphics2D g);
	public abstract void doClick(Point startPoint);
	public abstract void doPress(Point endPoint);
	public abstract void doRelease(Point endPoint);
	
	public void drawSelect(Graphics2D g, int shapeID) {
	
		g.setColor(Color.BLACK);
		float[] dash = new float[]{5,5,5,5};
		g.setStroke(new BasicStroke(1,0,BasicStroke.JOIN_MITER,1.0f,dash,0));
		
		switch(shapeID)
		{
		case CIRCLE:
		case RECTANGLE:
		{
			g.drawRect(pointList.get(START_POINT).x, pointList.get(START_POINT).y,
					pointList.get(END_POINT).x - pointList.get(START_POINT).x, pointList.get(END_POINT).y - pointList.get(START_POINT).y);
			
			g.setStroke(new BasicStroke(5.0f));
			for(int i=0; i<pointList.size(); i++) {
				g.drawRect(pointList.get(i).x, pointList.get(i).y, 1, 1);
			}
			break;
		}
		case POLYGON:
		case CURVE:
		{
			ArrayList<Point> polylist = new ArrayList<Point>();
			polylist.add(getStartPoint());
			
			Point startPoint = getStartPoint();
			Point endPoint = getEndPoint();
			
			Point p;
			// 1
			p = new Point();
			p.x = (startPoint.x + endPoint.x) / 2;
			p.y = startPoint.y;
			polylist.add(p);
			
			// 2
			p = new Point();
			p.x = endPoint.x;
			p.y = startPoint.y;
			polylist.add(p);
			
			// 3
			p = new Point();
			p.x = endPoint.x;
			p.y = (startPoint.y + endPoint.y)  / 2;
			polylist.add(p);
			
			// 4
			p = new Point(endPoint);
			polylist.add(p);
			
			// 5
			p = new Point();
			p.x = (startPoint.x + endPoint.x) / 2;
			p.y = endPoint.y;
			polylist.add(p);
			
			// 6
			p = new Point();
			p.x = startPoint.x;
			p.y = endPoint.y;
			polylist.add(p);
			
			// 7 
			p = new Point();
			p.x = startPoint.x;
			p.y = (startPoint.y + endPoint.y ) /2;
			polylist.add(p);
			
			g.drawRect(polylist.get(START_POINT).x, polylist.get(START_POINT).y,
					polylist.get(END_POINT).x - polylist.get(START_POINT).x, polylist.get(END_POINT).y - polylist.get(START_POINT).y);
			
			g.setStroke(new BasicStroke(5.0f));
			for(int i=0; i<polylist.size(); i++) {
				g.drawRect(polylist.get(i).x, polylist.get(i).y, 1, 1);
			}
			break;
		}
		case LINE:
		{
			g.setStroke(new BasicStroke(5.0f));
			g.drawRect(startPoint.x, startPoint.y, 1, 1);
			g.drawRect(endPoint.x, endPoint.y, 1, 1);
			break;
		}
		}
	}
	
	public void doMove(Point currentPoint) {
	}
	
	public void doMove(Point currentPoint, Point movePoint) {
		int differX = movePoint.x - currentPoint.x;
		int differY = movePoint.y - currentPoint.y;
		
		startPoint.setLocation(startPoint.x+differX, startPoint.y+differY);
		endPoint.setLocation(startPoint.x+width, startPoint.y+height);
		
		initialCenterPoint();
		initialPointList();
	}
	
	
	public void rotate(Graphics2D g, Point p) {
		
	}
	public void doResize(Point p, int pointIndex) {
		switch(pointIndex) {
		case 0:
			startPoint.setLocation(p);
			break;
		case 1:
			startPoint.setLocation(startPoint.x, p.y);
			break;
		case 2:
			startPoint.setLocation(startPoint.x, p.y);
			endPoint.setLocation(p.x, endPoint.y);
			break;
		case 3:
			endPoint.setLocation(p.x, endPoint.y);
			break;
		case 4:
			endPoint.setLocation(p);
			break;
		case 5:
			endPoint.setLocation(endPoint.x, p.y);
			break;
		case 6:
			startPoint.setLocation(p.x, startPoint.y);
			endPoint.setLocation(endPoint.x, p.y);
			break;
		case 7:
			startPoint.setLocation(p.x, startPoint.y);
			break;
		}
		
		initialWidthHeight();
		initialCenterPoint();
		initialPointList();
	}
	
	protected void initialWidthHeight() {
		width = getEndPoint().x - getStartPoint().x;
		height = getEndPoint().y - getStartPoint().y;
	}
	
	protected void initialCenterPoint() {
		Point p = new Point();
		
		p.x = (getStartPoint().x + getEndPoint().x)/2;
		p.y = (getStartPoint().y + getEndPoint().y)/2;
		
		centerPoint = p;
	}

	protected void initialPointList() {
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		pointList.add(getStartPoint());
		
		Point startPoint = getStartPoint();
		Point endPoint = getEndPoint();
		
		Point p;
		// 1
		p = new Point();
		p.x = (startPoint.x + endPoint.x) / 2;
		p.y = startPoint.y;
		pointList.add(p);
		
		// 2
		p = new Point();
		p.x = endPoint.x;
		p.y = startPoint.y;
		pointList.add(p);
		
		// 3
		p = new Point();
		p.x = endPoint.x;
		p.y = (startPoint.y + endPoint.y)  / 2;
		pointList.add(p);
		
		// 4
		p = new Point(endPoint);
		pointList.add(p);
		
		// 5
		p = new Point();
		p.x = (startPoint.x + endPoint.x) / 2;
		p.y = endPoint.y;
		pointList.add(p);
		
		// 6
		p = new Point();
		p.x = startPoint.x;
		p.y = endPoint.y;
		pointList.add(p);
		
		// 7 
		p = new Point();
		p.x = startPoint.x;
		p.y = (startPoint.y + endPoint.y ) /2;
		pointList.add(p);
		
		this.pointList = pointList;
		
		
	}
	
	public int selectPoint(Point p) {
		int index = -1;
		
		for(int i=0; i<pointList.size(); i++) {
			if( pointList.get(i).x-20 <= p.x && pointList.get(i).x+20 >= p.x
					&& pointList.get(i).y-20 <= p.y && pointList.get(i).y+20 >= p.y) {
				index = i;
			}
		}
		
		return index;
	}

	// setter, getter
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Point> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<Point> pointList) {
		this.pointList = pointList;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public float getStroke() {
		return stroke;
	}

	public void setStroke(float stroke) {
		this.stroke = stroke;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public boolean getIsSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
	public ArrayList<Point> getPolyPointList() {
		return polyPointList;
	}

	public void setPolyPointList(ArrayList<Point> polyPointList) {
		this.polyPointList = polyPointList;
	}
	
}