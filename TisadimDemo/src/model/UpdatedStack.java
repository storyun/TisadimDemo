
import java.io.Serializable;
import java.util.LinkedList;
import java.util.LinkedList;

public class UpdatedStack implements Serializable{
	
	private LinkedList<Shape> shapeList;
	
	public UpdatedStack() {
		shapeList = new LinkedList<Shape>();
		
	}

	public LinkedList<Shape> getShapeList() {
		return shapeList;
	}

	public void setShapeList(LinkedList<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
	public void clear() {
		shapeList = new LinkedList<Shape>();
	}
	
	public void addShape(Shape s) {
		shapeList.add(s);
	}
	
	public int size() {
		return shapeList.size();
	}
	
	public Shape get(int i) {
		return shapeList.get(i);
	}
}
