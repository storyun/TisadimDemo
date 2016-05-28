package file.handler;


import model.handler.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;



public class ImagefileHandler {
	
	/* 저장하거나 불러올 이미지 파일 */
	Image img = null;
	TestCanvas canvas; // 나중에 저장할 캔버스 이름은 따로 지정할것!! 테스트용
	
	
	
	/**
	 * 
	 * @param path  경로
	 * @param canvas  그림판
	 * @param format   확장자(jpeg,jpg,png..)
	 * @throws IOException
	 */
	 public  static  void ImageSave(String path, Canvas canvas, String format) throws IOException{
		
		 int width = canvas.getWidth();
		 int height = canvas.getHeight();



		 
		 	BufferedImage image = new BufferedImage(width, height , BufferedImage.TYPE_INT_RGB);
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path)) ;

			Graphics2D graphics = image.createGraphics();
			canvas.paintAll(graphics);			// canvas 에 있는 모든 객체들을 그린다.
			image.getGraphics();
			ImageIO.write(image, format, fos);
			fos.close();
	}
	 
	     /**
		 *  이미지를 캔버스로 불러오기 함수. 
		 *  이미지는 수정이 불가능합니다.
		 *  
		 * @param 불러올 경로		 * 
		 * @return  이미지파일
		 */
		 public static Image ObjectLoad(String path){
			
			 try {       
		           FileInputStream in = new FileInputStream(path); 
		           ObjectInput s = new ObjectInputStream(in); 	           
		           Object obj = s.readObject();
		           return (Image)obj;
		         } 
		         catch(IOException e) { }     
		         catch(ClassNotFoundException e) {} 
			 return null;
		    } 

		
	
}
