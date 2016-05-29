package model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import model.Shape;

public class Polygon extends Shape{

   public static final int START_POINT = 0;
   
   private ArrayList<Point> contactPointList = new ArrayList<Point>();
   private boolean isIng;
   private Point currentPoint;
   private ArrayList<Point> polyPointList;
   public ArrayList<Point> getPolyPointList() {
   return polyPointList;
}

public void setPolyPointList(ArrayList<Point> polyPointList) {
   this.polyPointList = polyPointList;
}
public Polygon(Color edgeColor, Color fillColor, float stroke) {
      super(edgeColor, fillColor, stroke);
      setId(Shape.POLYGON);
      isIng = true;
      currentPoint = new Point();
      polyPointList = new ArrayList<Point>();
   }
   /**
    * 마지막으로 점을 찍은 곳의 반경이 처음 시작점과 아주 근소한 차이에 있을때 true
    * @param startP = 시작점
    * @param curP   = 현재 점을 찍은 좌표
    * @return  현재점과 시작점의 차이가 근소하면 true
    */
   public boolean checkIsmadePolygon(Point startP, Point curP){
      
      double rad =  20.0;
      
      //내접하는 계산식
      double dx2 = (curP.getX()- startP.getX())*(curP.getX()- startP.getX()) ;
      double dy2 = (curP.getY()- startP.getY())*(curP.getY()- startP.getY());
      double result = dx2 +dy2;
              
      if( result <= (rad*rad) ){
         
         return true;
      }else{
       
         return false;
      }
         
      
      
      
   }
   
   @Override
   public void drawEdge(Graphics2D g) {
      
      ArrayList<Point> pointList = getPointList();
      
      for(int i=1; i<polyPointList.size(); i++) {         
         g.drawLine(polyPointList.get(i-1).x, polyPointList.get(i-1).y, polyPointList.get(i).x, polyPointList.get(i).y);
      }
      
      Point startPoint = polyPointList.get(START_POINT);
      Point endPoint = polyPointList.get(polyPointList.size()-1);
      if(isIng) {
         g.drawLine(endPoint.x, endPoint.y, currentPoint.x, currentPoint.y);
      }
      else {
         g.drawLine(endPoint.x, endPoint.y, startPoint.x, startPoint.y);
      }
   }
   
   
   @Override
   public void drawFill(Graphics2D g) {
     /* // TODO Auto-generated method stub
//      g.fillRect(getStartPoint().x, getStartPoint().y, width, height);
	   int[] xList = new int[polyPointList.size()];
	   int[] yList = new int[polyPointList.size()];
	   
	   for(int i=0; i<polyPointList.size(); i++) {
		   xList[i] = polyPointList.get(i).x;
		   yList[i] = polyPointList.get(i).y;
	   }
	   
	   
	   g.drawPolygon(xList, yList, polyPointList.size());*/
   }

   @Override
   public void doClick(Point point) {
      // TODO Auto-generated method stub      
      ArrayList<Point> pointList = getPointList();
      
      // 첫 입력인지 아닌지
      if(polyPointList.size() == 0) {
       
         polyPointList.add(point);   //우리가 그릴 다각형
         setPolyPointList(polyPointList);  //부모  Shape에서 접근할수 있도록 
         pointList.add(point);       //감싸는 사각형을 위한것
         setPointList(pointList);
         currentPoint = point;
         
      }
      else {
         
         if(checkIsmadePolygon(pointList.get(START_POINT), point)){   //시작점과 끝점이 맞닿았다면
         isIng = false;
            
            // 최소값 최대값 
            initialStartEndPoint();
         // point
            initialPointList();
            // 중심점
            initialCenterPoint();
            
            //pointList와 polyPointList 와의 접점을 찾는다.
            initialContactPoints();
            
         }
         else{
             pointList.add(point);       //감싸는 사각형을 위한것
             setPointList(pointList);         
             polyPointList.add(point);   // 다각형에 추가
             setPolyPointList(polyPointList);  //부모  Shape에서 접근할수 있도록              
         }
      }
   }
   private boolean checkPoint(Point p1, Point p2){	//두점이 같은 선상에있는지 체크하는 함수
	   if(p1.x == p2.x ){
		   return true;
	   }
	   else if(p1.y == p2.y){
		   return true;
	   }
	   return false;
   }
   
   private void initialContactPoints(){
	   Point p;
	   // 접점 찾기
	   for(int i=0; i<polyPointList.size(); i++){ //다각형의 모든 점들중
		   p= polyPointList.get(i);		 
		   if( checkPoint(p, getStartPoint()))contactPointList.add(p);
		   else if(checkPoint(p, getEndPoint()))contactPointList.add(p);
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
   
   public void doResize(Point p, int pointIndex) {
	   int idx=-1;	//polyPointList 의 정보를 가지고있다.
		switch(pointIndex) {
		case 0:
			getStartPoint().setLocation(p);
			break;
		case 1:
			
			for(int i=0; i<polyPointList.size(); i++){ //스위치문마다 도는 최악의 알고리즘ㄴ
				if( polyPointList.get(i).y == getStartPoint().y){
					idx= i;
					break;
				}				
			}
			if(idx!= -1)
				polyPointList.set( idx, new Point(polyPointList.get(idx).x,p.y));
			
			getStartPoint().setLocation(getStartPoint().x, p.y);
			
			break;
		case 2:
			getStartPoint().setLocation(getStartPoint().x, p.y);
			getEndPoint().setLocation(p.x, getEndPoint().y);
			break;
		case 3:
			for(int i=0; i<polyPointList.size(); i++){ //스위치문마다 도는 최악의 알고리즘ㄴ
				if( polyPointList.get(i).x == getEndPoint().x){
					idx= i;
					break;
				}				
			}
			if(idx!= -1)
				polyPointList.set( idx, new Point(p.x,polyPointList.get(idx).y));			
			getEndPoint().setLocation(p.x, getEndPoint().y);			
			break;
		case 4:
			getEndPoint().setLocation(p);
			break;
		case 5:
			for(int i=0; i<polyPointList.size(); i++){ //스위치문마다 도는 최악의 알고리즘ㄴ
				if( polyPointList.get(i).y == getEndPoint().y){
					idx= i;
					break;
				}				
			}
			if(idx!= -1)
				polyPointList.set( idx, new Point(polyPointList.get(idx).x,p.y));
			
			getEndPoint().setLocation(getEndPoint().x, p.y);
			break;
		case 6:
			getStartPoint().setLocation(p.x, getStartPoint().y);
			getEndPoint().setLocation(getEndPoint().x, p.y);
			break;
		case 7:
			
			for(int i=0; i<polyPointList.size(); i++){ //스위치문마다 도는 최악의 알고리즘ㄴ
				if( polyPointList.get(i).x == getStartPoint().x){
					idx= i;
					break;
				}				
			}
			if(idx!= -1)
				polyPointList.set( idx, new Point(p.x, polyPointList.get(idx).y));	
			getStartPoint().setLocation(p.x, getStartPoint().y);
			break;
		}
		
		initialWidthHeight();
		initialCenterPoint();
		initialPointList();
	}
   
   public void doMove(Point currentPoint) {
      if(isIng) {
         this.currentPoint = currentPoint;
      }
   }
   
   public void doMove(Point currentPoint, Point movePoint) {
		int differX = movePoint.x - currentPoint.x;
		int differY = movePoint.y - currentPoint.y;
		
		getStartPoint().setLocation(getStartPoint().x+differX, getStartPoint().y+differY);
		getEndPoint().setLocation(getEndPoint().x+differX, getEndPoint().y+differY);
		
		initialCenterPoint();
		initialPointList();
		
		for(int i=0; i<getPolyPointList().size(); i++){
			Point p = (new Point((int)polyPointList.get(i).getX()+differX , 
					(int)polyPointList.get(i).getY()+differY));
			this.polyPointList.set(i ,  p);
		}
	}
   
   
   //getter, setter
   public boolean isIng() {
      return isIng;
   }
   public void setIng(boolean isIng) {
      this.isIng = isIng;
   }
   
   
}