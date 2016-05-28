package file.handler;

import model.Shape;
import model.handler.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;



public  class  ImageHandler implements Serializable{
	/**
	 *  객체 핸들러
	 *  직렬화된 객체를 저장하거 불러올수 있다.
	 */	
	
	ShapeList model = null;
	/**
	 *  객체직렬화로 저장함수.
	 * @param  path
	 * @param  object
	 * @return  성공하면 true 실패하면 false
	 */
	
	 public  static  void ImageSave(String path, Canvas canvas){
		
		 try
			{
	 
				BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
				BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path +".jpg")) ;
	 
				Graphics2D graphics = image.createGraphics();
				canvas.paintAll(graphics);
				image.getGraphics();
				ImageIO.write(image, "jpg", fos);	 
				fos.close();
	 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 
	}
	 
 		/**
		 *  객체직렬화로 불러오기 함수.
		 * @param path		 * 
		 * @return  ShapeList
		 */
		 public static ShapeList ObjectLoad(String path){
			
			 try {       
		           FileInputStream in = new FileInputStream(path); 
		           ObjectInput s = new ObjectInputStream(in); 	           
		           ShapeList obj = new ShapeList(); 
		           obj = (ShapeList)s.readObject();
		           return obj;
		         } 
		         catch(IOException e) { }     
		         catch(ClassNotFoundException e) {} 
			 return null;
		    } 

		
		
	
}
