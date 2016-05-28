package handler;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import model.ShapeList;

public class ImagefileHandler {
	
	/* 저장하거나 불러올 이미지 파일 */
	Image img = null;
	
	
	/**
	 *  캔버스에 그려진 그림 저장함수.
	 * @param  path
	 * @param  object
	 * @return  성공하면 true 실패하면 false
	 */
	 public  static  void ObjectSave(String path, Object object){
		
		try {
			FileOutputStream f = new FileOutputStream(path);			
			ObjectOutput s = new ObjectOutputStream(f);
			s.writeObject(object);
			s.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
