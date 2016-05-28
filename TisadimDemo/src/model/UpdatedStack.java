package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Stack;

public class UpdatedStack implements Serializable{
	
	/**
	 *  지금까지 update 했던 Shape들의 스택이다.
	 *  가장 맨위에 있는것이 최근에 수정했던 도형으로
	 *  redo/undo 기능을 위해 존재함.
	 */
	
	
	private Stack<Shape> updatedstack;
	
	public UpdatedStack() {
		updatedstack = new Stack<Shape>();		
	}
	
	/**
	 * 전부 날린다.
	 */
	public void clear() {
		updatedstack = new Stack<Shape>();
	}
	
	/**
	 * 
	 * @param 도형 push
	 */
	public void addShape(Shape s) {
		updatedstack.push(s);
	}
		
	/**
	 * 
	 * @return stack의 사이즈 반환
	 */
		
	public int size() {
		return updatedstack.size();
	}
	/**
	 * 
	 * @return 스택의 맨위값을 리턴
	 */
	public Shape pop() {
		return updatedstack.pop();
	}
	/**
	 * 
	 * @return 맨 위의 값이 무엇인지 확인만/ 없애지는 않는다.
	 */
	public Shape peek(){
		return updatedstack.peek();
	}
}
