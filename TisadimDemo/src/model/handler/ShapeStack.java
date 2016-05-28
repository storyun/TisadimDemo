package model.handler;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Stack;

import model.Shape;

public class ShapeStack implements Serializable{
	
	/**
	 *  지금까지 update 했던 Shape들의 스택이다.
	 *  가장 맨위에 있는것이 최근에 수정했던 도형으로
	 *  redo/undo 기능을 위해 존재함.
	 */
	
	
	private Stack<Shape> beforeStack;
	private Stack<Shape> afterStack;
	
	public ShapeStack() {
		beforeStack = new Stack<Shape>();
		afterStack = new Stack<>();
	}
	
	public int getBeforeStackSize() {
		return beforeStack.size();
	}
	
	public int getAfterStackSize() {
		return afterStack.size();
	}
	
	public void pushBeforeStack(Shape s) {
		afterStack.clear();
		beforeStack.push(s);
	}
	
	public Shape popBeforeStack() {
		Shape s = beforeStack.pop();
		afterStack.push(s);
		
		return s;
	}
	
	public Shape popAfterStack() {
		Shape s = afterStack.pop();
		beforeStack.push(s);
		return s;
	}
}
