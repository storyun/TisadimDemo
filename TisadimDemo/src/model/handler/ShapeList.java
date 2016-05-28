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
		{System.out.println(shapeList.get(i).getId());
			switch(shapeList.get(i).getId())
			{
			case 1: //circle
			{
				double a = 0, b = 0, ellipse = 0; //a : 장축, b : 단축
				
				a = (double)(shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x)/(2.0);
				b = (double)(shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/(2.0);
				ellipse = (double)((point.x-shapeList.get(i).getCenterPoint().x)*(point.x-shapeList.get(i).getCenterPoint().x))/(a*a)
						+(double)((point.y-shapeList.get(i).getCenterPoint().y)*(point.y-shapeList.get(i).getCenterPoint().y))/(b*b);

				if(ellipse<=1)
				{
					return i;
				}
				break;
			}
			case 2: //rectangle
			{
				if(shapeList.get(i).getStartPoint().x<=point.x && point.x<=shapeList.get(i).getEndPoint().x)
				{
					if(shapeList.get(i).getStartPoint().y<=point.y && point.y<=shapeList.get(i).getEndPoint().y)
					{
						return i;
					}
				}
				break;
			}
			case 3: //polygon
			{
				break;
			}
			case 4: //line
			{	System.out.println(shapeList.get(i).getStartPoint().x+" "+shapeList.get(i).getEndPoint().x);
			System.out.println(point.x);
				if(shapeList.get(i).getStartPoint().x<=point.x && point.x<=shapeList.get(i).getEndPoint().x)
				{
					
					
					//double randa =  (double)(shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/(double)(shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x);
					//if((double)point.y-shapeList.get(i).getStartPoint().y - randa*(point.x-shapeList.get(i).getStartPoint().x) <10)
					//{
						return i;
					//}
				}
				break;
			}
			case 5:
			}
			
		}
	
		
		return index;
	}

	private Object getStartPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}
