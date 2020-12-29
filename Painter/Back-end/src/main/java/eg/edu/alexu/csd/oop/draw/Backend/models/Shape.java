package eg.edu.alexu.csd.oop.draw.Backend.models;

import java.awt.Point;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;



//@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = cat.class, name = "cat"),
//    @JsonSubTypes.Type(value = dog.class, name = "dog")
//})
public abstract class Shape {
	
	shapeFactory factory = new shapeFactory() ;
	private String id ;
	private double startPointX ; 
	private double startPointY ; 
	private double height ; 
	private double width ; 
	private double scaleX ; 
	private double scaleY ; 
	private double rotation ; 
	private String colorfill ; 
	private String colorstroke ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getStartPointX() {
		return startPointX;
	}
	public void setStartPointX(double x) {
		this.startPointX = x;
	}
	public double getScaleX() {
		return scaleX;
	}
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	public double getScaleY() {
		return scaleY;
	}
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public double getStartPointY() {
		return startPointY;
	}
	public void setStartPointY(double y) {
		this.startPointY = y;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public String getColorfill() {
		return colorfill;
	}
	public void setColorfill(String color) {
		this.colorfill = color;
	}
	public String getColorstroke() {
		return colorstroke;
	}
	public void setColorstroke(String strokeColor) {
		this.colorstroke = strokeColor;
	}
	
	public void updateShape(JSONObject shape) {

			
		this.setStartPointX(Double.parseDouble(shape.get("startPointX") + ""));
		this.setStartPointY(Double.parseDouble(shape.get("startPointY") + ""));
		this.setHeight(Double.parseDouble(shape.get("height")+""));
		this.setWidth(Double.parseDouble(shape.get("width")+""));
		this.setScaleX(Double.parseDouble(shape.get("scaleX")+""));
		this.setScaleY(Double.parseDouble(shape.get("scaleY")+""));
		this.setRotation(Double.parseDouble(shape.get("rotation")+""));
		this.setColorfill(shape.get("colorfill")+"");
		this.setColorstroke(shape.get("colorstroke")+"");
		
		
	}
	public void updateShape(Node shape) {

		Element element = (Element) shape;
		
		this.setStartPointX(Double.parseDouble(element.getElementsByTagName("startPointX").item(0).getTextContent()));
		this.setStartPointY(Double.parseDouble(element.getElementsByTagName("startPointY").item(0).getTextContent()));
		this.setHeight(Double.parseDouble(element.getElementsByTagName("height").item(0).getTextContent()));
		this.setWidth(Double.parseDouble(element.getElementsByTagName("width").item(0).getTextContent()));
		this.setScaleX(Double.parseDouble(element.getElementsByTagName("scaleX").item(0).getTextContent()));
		this.setScaleY(Double.parseDouble(element.getElementsByTagName("scaleY").item(0).getTextContent()));
		this.setRotation(Double.parseDouble(element.getElementsByTagName("rotation").item(0).getTextContent()));
		this.setColorfill(element.getElementsByTagName("colorfill").item(0).getTextContent());
		this.setColorstroke(element.getElementsByTagName("colorstroke").item(0).getTextContent());
		
	}
	
	public Shape copy (Shape shape) {
		
		Shape ret = factory.createShape(id);
		
		ret.setStartPointX(shape.getStartPointX());
		ret.setStartPointY(shape.getStartPointY());
		ret.setHeight(shape.getHeight());
		ret.setWidth(shape.getWidth());
		ret.setScaleX(shape.getScaleX());
		ret.setScaleY(shape.getScaleY());
		ret.setRotation(shape.getRotation());
		ret.setColorfill(shape.getColorfill());
		ret.setColorstroke(shape.getColorstroke());
		
		return ret ; 
		
	}
	
	
	
}
