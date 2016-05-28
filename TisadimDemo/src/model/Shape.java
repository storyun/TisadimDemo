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
	private Point startPoint;
	private Point endPoint;
	private ArrayList<Point> pointList;
	private Color edgeColor;
	private Color fillColor;
	private float stroke;
	private BasicStroke basicStroke;
	private Point centerPoint;
	
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
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void doPress(Point endPoint);
	public abstract void dpRelease(Point endPoint);
	
	public void move(Graphics2D g, Point p) {
		
	}
	public void rotate(Graphics2D g, Point p) {
		
	}
}