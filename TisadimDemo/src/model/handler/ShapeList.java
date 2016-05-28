package model.handler;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

import model.Shape;
import model.shape.Circle;

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
			case Shape.CIRCLE:
			{
				double a = 0, b = 0, ellipse = 0; //a : 장축, b : 단축
				
				a = (double)(shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x)/(2.0);
				b = (double)(shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/(2.0);
				ellipse = (double)((point.x-shapeList.get(i).getCenterPoint().x)*(point.x-shapeList.get(i).getCenterPoint().x))/(a*a)
						+(double)((point.y-shapeList.get(i).getCenterPoint().y)*(point.y-shapeList.get(i).getCenterPoint().y))/(b*b);

				if(ellipse<=1)
					return i;

				break;
			}
			case Shape.RECTANGLE:
			{
				if(shapeList.get(i).getStartPoint().x<=point.x && point.x<=shapeList.get(i).getEndPoint().x)
				{
					if(shapeList.get(i).getStartPoint().y<=point.y && point.y<=shapeList.get(i).getEndPoint().y)
						return i;

				}
				break;
			}
			case Shape.POLYGON:
			{
				boolean out = false;

				int j = shapeList.get(i).getPolyPointList().size() - 1;
				for (int k = 0; k < shapeList.get(i).getPolyPointList().size(); k++)
				{
					//y가 사이에 있는 경우에만
					if (shapeList.get(i).getPolyPointList().get(k).getY() < point.y && shapeList.get(i).getPolyPointList().get(j).getY() >= point.y 
							|| shapeList.get(i).getPolyPointList().get(j).getY() < point.y && shapeList.get(i).getPolyPointList().get(k).getY() >= point.y)
					{
					//(x1,y1), (x2,y2)를 지나는 직선에 (x,y)대입
						if (shapeList.get(i).getPolyPointList().get(k).getX() + (point.y - shapeList.get(i).getPolyPointList().get(k).getY()) / (shapeList.get(i).getPolyPointList().get(j).getY() - shapeList.get(i).getPolyPointList().get(k).getY()) * (shapeList.get(i).getPolyPointList().get(j).getX() - shapeList.get(i).getPolyPointList().get(k).getX()) < point.x)
							out = !out;
					}
					j = k;
				}
						
				if(out)
					return i;
				   
				break;
			}
			case Shape.LINE:
			{	
				int a = shapeList.get(i).getStartPoint().x, b = shapeList.get(i).getEndPoint().x;
				if(a<b)
				{
					if(a<=point.x && point.x<=b)
						;
					else
						break;
				}
				else
				{
					if(b<=point.x && point.x<=a)
						;
					else
						break;
				}
						
				double randa =  (double)(shapeList.get(i).getEndPoint().y-shapeList.get(i).getStartPoint().y)/(double)(shapeList.get(i).getEndPoint().x-shapeList.get(i).getStartPoint().x);
				double result = (double)point.y-shapeList.get(i).getStartPoint().y - randa*(point.x-shapeList.get(i).getStartPoint().x);
				if(-20<= result && result <= 20)
					return i;
				break;
			}
			case Shape.CURVE:
			{
				for(int k=0;  k<shapeList.get(i).getPointList().size(); k++)
				{
					int xdis = (point.x-shapeList.get(i).getPointList().get(k).x)*(point.x-shapeList.get(i).getPointList().get(k).x),
							ydis = (point.y-shapeList.get(i).getPointList().get(k).y)*(point.y-shapeList.get(i).getPointList().get(k).y);
					
					if(Math.sqrt(xdis+ydis)<15)
					{	
						return i;
					}
				}
				break;
			}
			}
			
		}
	
		
		return index;
	}

	private Object getStartPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}
