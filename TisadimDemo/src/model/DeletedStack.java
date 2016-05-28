package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Stack;

public class DeletedStack implements Serializable{
	/**
	 *  지금까지 삭제했던 Shape들의 스택이다.
	 *  가장 맨위에 있는것이 삭제했던 도형으로
	 *  redo/undo 기능을 위해 존재함.
	 */
	
	
	private Stack<Shape> deletedstack;
	
	public DeletedStack() {
		deletedstack = new Stack<Shape>();		
	}
	
	public void clear() {
		deletedstack = new Stack<Shape>();
	}
	
	public void addShape(Shape s) {
		deletedstack.push(s);
	}
		
		
	public int size() {
		return deletedstack.size();
	}
	/**
	 * 
	 * @return 스택의 맨위값을 리턴
	 */
	public Shape pop() {
		return deletedstack.pop();
	}
	
	/**
	 * 
	 * @return 맨 위의 값이 무엇인지 확인만/ 없애지는 않는다.
	 */
	public Shape peek(){
		return deletedstack.peek();
	}
}
