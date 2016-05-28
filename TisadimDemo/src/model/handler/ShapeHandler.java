package model.handler;


import java.awt.Point;
import java.util.ArrayList;

public class ShapeHandler {
	
	/**
	 * 점이 다각형 안에 포함되어있는지 아닌지 고르는 함수 
	 * @param q    점이 찍혔을때 포인트
	 * @param p    다각형의 집합.
	 * @return     안쪽이면 true 바깥이면 false;
	 */
	
	boolean isInside(Point q, ArrayList<Point> p ){ //점 q 가 다각형 P 안에 포함되어있을 경우 참 아니면 거짓
		int cross =0;
		for(int i=0;  i<p.size(); i++){
			int j = (i+1) %p.size();
			if(  (p.get(i).getY() > q.getY())  != (p.get(j).getY() > q.getY() )){
				double atX = (p.get(j).getX() - p.get(i).getX()) *(q.getY() - p.get(i).getY()) 
						/ (p.get(j).getY() -p.get(i).getY()) + p.get(i).getX();
				if(q.getX() < atX)
					cross++;
			}
		}
		return cross % 2 >0;
	}
}
