package model.handler;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.LinkedList;

import model.Shape;

public class ShapeList implements Serializable{
	
	/** 빈번한 수정 삭제  때문 LinkedList **/
	private LinkedList<Shape> shapeList;	
	
	
	public ShapeList() {
		shapeList = new LinkedList<Shape>();		
	}
	
	public LinkedList<Shape> getShapeList() {
		return shapeList;
	}
	public void setShapeList(LinkedList<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
	public void clear() {
		shapeList = new LinkedList<Shape>();
	}	
	public void addShape(Shape s) {
		shapeList.add(s);
	}	
	public int size() {
		return shapeList.size();
	}	
	public Shape get(int i) {
		return shapeList.get(i);
	}
	
	public int getSelectIndex(Point point) {
		int index = -1;
		
		return index;
	}
}
