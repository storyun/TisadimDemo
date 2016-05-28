package ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import model.Shape;
import model.handler.ShapeList;
import model.shape.Circle;
import model.shape.Curve;
import model.shape.Line;
import model.shape.Polygon;
import model.shape.Rectangle;
import ui.SadimFrame;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Canvas;

public class SadimPanel extends JPanel implements ActionListener, MouseInputListener{
   
   private SadimFrame mainFrame;
   private MyCanvas canvas;
   private MyCanvas shapeCanvas;
   
   private int shapeId;
   private Color edgeColor;
   private Color fillColor;
   private float border;
   
   private Circle circle;
   private Rectangle rectangle;
   private Line line;
   private Curve curve;
   private Polygon polygon;
   private Shape shape;
   
   private int selectIndex;
   private int pointIndex;
   private int cmd;			// 1 : resize, 2, move
   private Point point;

   private ShapeList shapeList;
   
   private boolean isMakeShape;
   private boolean isImg;
   private boolean isIng;
   public SadimPanel(SadimFrame mainFrame) {
      super();
      this.setSize(new Dimension(800, 600));
      setLayout(null);
      
      //init
      this.mainFrame = mainFrame;
      shapeId = 0;
      edgeColor = Color.BLACK;
      fillColor = new Color(0, 0, 0, 0);
      border = 1;
      isMakeShape = false;
      selectIndex = -1;
      pointIndex = -1;
      cmd = 0;

      canvas = new MyCanvas();
      canvas.setBounds(0, 0, 800, 600);
      canvas.setBackground(Color.white);
      canvas.addMouseListener(this);
      canvas.addMouseMotionListener(this);
      add(canvas);
      
      shapeList = new ShapeList();
   }

   public void setShapeId(int a)
   {
      shapeId = a;
   }
   
   public void setEdgeColor(Color a)
   {
      edgeColor = a;
   }
   
   public void setFillColor(Color a)
   {
      fillColor = a;
   }
   
   public void setBorder(float a)
   {
      border = a;
   }
   
   
   
   /** 캔버스 클래스 입니다. **/
   
   private class MyCanvas extends Canvas {
     
	   
	// 더블버퍼링을 위한 변수
		private Image offImage;
		private Graphics bg;
	 BufferedImage bf = new BufferedImage( 800, 600, BufferedImage.TYPE_INT_ARGB );
	 BufferStrategy bs = getBufferStrategy(); //Gets the buffer strategy our canvas is currently using
	  public MyCanvas() {
		
	} 
	   
      @Override
      public void paint(Graphics g) {         
         super.paint(g);
         Graphics2D g2 = (Graphics2D)canvas.getGraphics();
    
            // 이전까지 그렸던 도형 그림
         for(int i=0; i<shapeList.size(); i++) {

            Shape s = shapeList.get(i);

            g2.setColor(s.getEdgeColor());
            g2.setStroke(new BasicStroke(s.getStroke()));
            s.drawEdge(g2);
            
            if( s.getId() != Shape.POLYGON) {
	            g2.setColor(s.getFillColor());
	            s.drawFill(g2);
            }
            else if( s.getId() == Shape.POLYGON) {
            	g2.setColor(s.getFillColor());
	            s.drawFill(g2);
            }

            if(selectIndex == i)
               s.drawSelect(g2,shapeList.get(i).getId());

         }
      }
   }

   @Override
   public void mouseClicked(MouseEvent e) 
   {

   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      border = Float.valueOf(mainFrame.getSpinner().getValue().toString()).floatValue();
      
      if( shapeId == Shape.CIRCLE) {
         isMakeShape = true;
         circle = new Circle(edgeColor, fillColor, border);
         shapeList.addShape(circle);
         
         shape = shapeList.get(shapeList.size()-1);
         shape.doClick(e.getPoint());
         
//         canvas.repaint();
      }
      else if(shapeId == Shape.CURVE) {
         isMakeShape = true;
         curve = new Curve(edgeColor, fillColor, border);
         shapeList.addShape(curve);

         shape = shapeList.get(shapeList.size()-1);
         shape.doClick(e.getPoint());
         
//         canvas.repaint();
      }
      else if(shapeId == Shape.LINE) {
         isMakeShape = true;
         line = new Line(edgeColor, fillColor, border);
         shapeList.addShape(line);
         
         shape = shapeList.get(shapeList.size()-1);
         shape.doClick(e.getPoint());
         
//         canvas.repaint();
      }
      else if(shapeId == Shape.POLYGON) {
         if(isMakeShape == false){
               //아직 클릭을 하지 않았다면
               isIng = true;
               isMakeShape = true;
               polygon = new Polygon(edgeColor, fillColor, border);
               shapeList.addShape(polygon);
               //도형을 추가하고'
         }
               shape = shapeList.get(shapeList.size()-1);
              Polygon poly = (Polygon)shape;
                  if(!poly.isIng()){   //그림이 끝났다면
                     isMakeShape=false;
                     isIng=false;
                  }
                  else      //폴리곤을 그리는 중이라면
                  {
                     shape.doClick(e.getPoint());                    
                  }
               
               
      }
      else if(shapeId == Shape.RECTANGLE) {
         isMakeShape = true;
         rectangle = new Rectangle(edgeColor, fillColor, border);
         shapeList.addShape(rectangle);         
         shape = shapeList.get(shapeList.size()-1);
         shape.doClick(e.getPoint());
         
      }
      else if(shapeId == Shape.SELECT)
      {
         if(shapeList.size() != 0)
         {
        	 if( selectIndex >= 0) {
        		 pointIndex = shape.selectPoint(e.getPoint());
        		 if( pointIndex >= 0) {
        			cmd = 1;
        		 }
        		 else if( selectIndex == shapeList.getSelectIndex(e.getPoint()) ) {
        			 // move
        			 cmd = 2;
        			 point = e.getPoint();
        			 
        		 }
        		 else {
        			 selectIndex = shapeList.getSelectIndex(e.getPoint());
            		 if( selectIndex >= 0) {
            			 shape = shapeList.get(selectIndex);
            		 }
        		 }
        	 }
        	 else {	// 도형 선택
        		 selectIndex = shapeList.getSelectIndex(e.getPoint());
        		 if( selectIndex >= 0) {
        			 shape = shapeList.get(selectIndex);
        		 }
        	 }
         }
      }
      canvas.repaint();
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      if( shapeId != Shape.SELECT &&shapeId !=Shape.POLYGON) {
         shape.doRelease(e.getPoint());
         canvas.repaint();
         isMakeShape=false;
         //다각형이나 선택 모드가 아닐때.
      }
      else if( shapeId == Shape.SELECT && pointIndex>= 0) {
         //shape.doResize(e.getPoint(), pointIndex);
      }      

      else if( shapeId == Shape.SELECT && cmd == 1) {
//          shape.doResize(e.getPoint(), pointIndex);
//          canvas.repaint();
    	  pointIndex = -1;
    	  cmd = 0;
       }   
      else if( shapeId == Shape.SELECT && cmd == 2 ) {
    	  cmd = 0;
    	  point = null;
      }
      else if(shapeId == Shape.POLYGON ){
               Polygon poly = (Polygon)shape;
               /**만약 다각형을 그리는 순간이끝났다면   **/
               if(!poly.isIng())
                  isMakeShape=false;                  
            }
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);   //부모페인트

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      if(shapeId != Shape.SELECT)
      {
         shape.doPress(e.getPoint());
         canvas.repaint();
      }
      else if( shapeId == Shape.SELECT && cmd == 1) {
          shape.doResize(e.getPoint(), pointIndex);
          canvas.repaint();
       }
      else if( shapeId == Shape.SELECT && cmd == 2) {
    	  shape.doMove(point, e.getPoint());
    	  
    	  canvas.repaint();
    	  point = e.getPoint();
      }
   }

   @Override
   public void mouseMoved(MouseEvent e) {
      // TODO Auto-generated method stub
      if( shapeId == Shape.POLYGON && isMakeShape == true) {         
         shape.doMove(e.getPoint());         
         canvas.repaint();      
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }
   // getter , setter

   public int getShapeId() {
      return shapeId;
   }

   public Color getEdgeColor() {
      return edgeColor;
   }

   public Color getFillColor() {
      return fillColor;
   }

   public ShapeList getShapeList() {
      return shapeList;
   }

   public void setShapeList(ShapeList shapeList) {
      this.shapeList = shapeList;
   }
   
   public void repaintCanvas() {
      canvas.repaint();
   }
   
   public void clearCmd() {
	   cmd = 0;
   }
}