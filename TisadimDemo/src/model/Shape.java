package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable{

	public static final int SELECT = 0;
	public static final int CIRCLE = 1;
	public static final int RECTANGLE = 2;
	public static final int POLYGON = 3;
	public static final int LINE = 4;
	public static final int CURVE = 5;
	
	private int id;
	private ArrayList<Point> pointList;
	private Color edgeColor;
	private Color fillColor;
	private float stroke;
	private BasicStroke basicStroke;
	
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
		
		basicStroke = new BasicStroke(stroke);
		
		pointList = new ArrayList<Point>();
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void doClick(Point startPoint);
	public abstract void doPress(Point endPoint);
	public abstract void doRelease(Point endPoint);
	
	public void doMove(Point currentPoint) {
	}
	
	public void move(Graphics2D g, Point p) {
		
	}
	public void rotate(Graphics2D g, Point p) {
		
	}
	
	protected void initialCenterPoint() {
		Point p = new Point();
		
		p.x = (getStartPoint().x + getEndPoint().y)/2;
		p.y = (getStartPoint().y + getEndPoint().y)/2;
		
		centerPoint = p;
	}

	protected void initialPointList() {
		ArrayList<Point> pointList = getPointList();
		
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
		p.y = startPoint.x;
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

	public BasicStroke getBasicStroke() {
		return basicStroke;
	}

	public void setBasicStroke(BasicStroke basicStroke) {
		this.basicStroke = basicStroke;
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
	
	
}