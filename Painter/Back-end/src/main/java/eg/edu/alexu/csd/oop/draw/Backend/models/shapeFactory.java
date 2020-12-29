package eg.edu.alexu.csd.oop.draw.Backend.models;

public class shapeFactory {
		
	public Shape createShape(String name , String id) {
		
		Shape shape = null  ; 
		
		if (name.equalsIgnoreCase("circle")) {
			shape = new Circle(id) ; 
		}else if (name.equalsIgnoreCase("square")) {
			shape = new Square(id);
		}else if (name.equalsIgnoreCase("elipse")) {
			shape = new Elipse(id) ; 
		}else if (name.equalsIgnoreCase("line")) {
			shape = new Line(id) ; 
		}else if (name.equalsIgnoreCase("rectangle")) {
			shape = new Rectangle(id);
		}else if (name.equalsIgnoreCase("triangle")) {
			shape = new Triangle(id) ; 
		}
		
		return shape ;
	}
	
	public Shape createShape(String id) {
		
		Shape shape = null  ; 
		
		if (id.charAt(0) == 'c') {
			shape = new Circle(id) ; 
		}else if (id.charAt(0) == 's') {
			shape = new Square(id);
		}else if (id.charAt(0) == 'e') {
			shape = new Elipse(id) ; 
		}else if (id.charAt(0) == 'l') {
			shape = new Line(id) ; 
		}else if (id.charAt(0) == 'r') {
			shape = new Rectangle(id);
		}else if (id.charAt(0) == 't') {
			shape = new Triangle(id) ; 
		}
		
		return shape ;
	}
	
}
