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
		
		for(int i=shapeList.size()-1;i>=0;i--)
		{
			switch(shapeList.get(i).getId())
			{
			case 1: //circle
			{
				double a = 0, b = 0, ellipse = 0; //a : 장축, b : 단축
				
				a = (double)(shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x)/(2.0);
				b = (double)(shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/(2.0);
				ellipse = (double)((point.x-shapeList.get(i).getCenterPoint().x)*(point.x-shapeList.get(i).getCenterPoint().x))/(a*a)
						+(double)((point.y-shapeList.get(i).getCenterPoint().y)*(point.y-shapeList.get(i).getCenterPoint().y))/(b*b);
				System.out.println(ellipse);
				if(ellipse<=1)
				{
					return i;
				}
				break;
			}
			case 2: //
			{
				
			}
			case 3:
			case 4:
			case 5:
			}
			
		}
	
		
		return index;
	}
}
