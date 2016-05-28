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
   private ArrayList<Point> polyPointList;
   
   public Polygon(Color edgeColor, Color fillColor, float stroke) {
      super(edgeColor, fillColor, stroke);
      
      isIng = true;
      currentPoint = new Point();
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
         System.out.println(result - rad*rad);
         return true;
      }else{
         System.out.println(result - rad*rad);
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
      Point endPoint = polyPointList.get(getPointList().size()-1);
      if(isIng) {
         g.drawLine(endPoint.x, endPoint.y, currentPoint.x, currentPoint.y);
      }
      else {
         g.drawLine(endPoint.x, endPoint.y, startPoint.x, startPoint.y);
      }
   }
   
   
   
   @Override
   public void drawFill(Graphics2D g) {
      // TODO Auto-generated method stub
//      g.fillRect(getStartPoint().x, getStartPoint().y, width, height);
   }

   @Override
   public void doClick(Point point) {
      // TODO Auto-generated method stub      
      ArrayList<Point> pointList = getPointList();
      
      // 첫 입력인지 아닌지
      if(polyPointList.size() == 0) {
         polyPointList.add(point);   //우리가 그릴 다각형
         /*pointList.add(point);       //감싸는 사각형을 위한것
*/         currentPoint = point;
         
      }
      else {
         
         if(checkIsmadePolygon(pointList.get(START_POINT), point)){   //시작점과 끝점이 맞닿았다면
         isIng = false;
            
            // 최소값 최대값 
            initialStartEndPoint();
            // 중심점
            initialCenterPoint();
            // point
            initialPointList();
         }
         else{
            /*pointList.add(point);      // 감싸는 사각형에 추가
*/            polyPointList.add(point);   // 다각형에 추가
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
   

   //getter, setter
   public boolean isIng() {
      return isIng;
   }
   public void setIng(boolean isIng) {
      this.isIng = isIng;
   }
   
   
}