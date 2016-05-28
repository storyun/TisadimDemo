package model;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public  class  MidasFileHandler implements Serializable{

	
	ShapeList model = null;
	/**
	 *  객체직렬화로 저장함수.
	 * @param path
	 * @param Object
	 * @return  성공하면 true 실패하면 false
	 */
	 public void ObjectSave(String path, Object object){
		
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
		 *  객체직렬화로 불러오기 함수.
		 * @param path
		 * 
		 * @return  Object
		 */
		 public Object ObjectLoad(String path){
			
			 try {       
		           FileInputStream in = new FileInputStream(path); 
		           ObjectInput s = new ObjectInputStream(in); 	           
		           Object obj = s.readObject();
		           return obj;
		         } 
		         catch(IOException e) { }     
		         catch(ClassNotFoundException e) {} 
			 return null;
		    } 

		
		 
	
}
