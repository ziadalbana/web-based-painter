package eg.edu.alexu.csd.oop.draw.Backend.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eg.edu.alexu.csd.oop.draw.Backend.models.*;

@org.springframework.stereotype.Service
public class Service {

    private Shape shape;
    ArrayList<Shape> circles = new ArrayList<>();
    ArrayList<Shape> squares = new ArrayList<>();
    ArrayList<Shape> rectangles = new ArrayList<>();
    ArrayList<Shape> elipses = new ArrayList<>();
    ArrayList<Shape> triangles = new ArrayList<>();
    ArrayList<Shape> lines = new ArrayList<>();
    private shapeFactory factory = new shapeFactory();

    Stack<String> mainUndo = new Stack<String>() ;
    Stack<String> mainRedo = new Stack<String>() ;

    Stack<ArrayList<Shape>> squaresUndo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> linesUndo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> rectanglesUndo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> trianglesUndo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> circlesUndo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> elipsesUndo = new Stack<ArrayList<Shape>>() ;

    Stack<ArrayList<Shape>> squaresRedo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> linesRedo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> rectanglesRedo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> trianglesRedo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> circlesRedo = new Stack<ArrayList<Shape>>() ;
    Stack<ArrayList<Shape>> elipsesRedo = new Stack<ArrayList<Shape>>() ;



   


    public void Undo(){
    	
        ArrayList<Shape> tmp = new ArrayList<Shape>() ;

        String name = "" ;

        if(mainUndo.size()==0)
            return ;

        name = mainUndo.pop() ;
        mainRedo.push(name) ;

        if (name.equalsIgnoreCase("circle")) {
            tmp = circlesUndo.pop() ;
            System.out.println("circlesundo size is " + circlesUndo.size());
            circlesRedo.push(tmp) ;

            if (circlesUndo.size()!=0) {


                System.out.println(circlesUndo.peek().size());
                circles = circlesUndo.peek();

            }
            else
                circles = new ArrayList<Shape>() ;
        }else if (name.equalsIgnoreCase("square")) {
            tmp = squaresUndo.pop() ;
            squaresRedo.push(tmp) ;
            if (squaresUndo.size()!=0)
                squares = squaresUndo.peek();
            else
                squares = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("rectangle")) {
            tmp = rectanglesUndo.pop() ;
            rectanglesRedo.push(tmp) ;
            if (rectanglesUndo.size()!=0)
                rectangles = rectanglesUndo.peek();
            else
                rectangles = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("line")) {
            tmp = linesUndo.pop() ;
            linesRedo.push(tmp) ;
            if (linesUndo.size()!=0)
                lines = linesUndo.peek();
            else
                lines = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("triangle")) {
            tmp = trianglesUndo.pop() ;
            trianglesRedo.push(tmp) ;
            if (trianglesUndo.size()!=0)
                triangles = trianglesUndo.peek();
            else
                triangles = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("elipse")) {
            tmp = elipsesUndo.pop() ;
            elipsesRedo.push(tmp) ;
            if (elipsesUndo.size()!=0)
                elipses = elipsesUndo.peek();
            else
                elipses = new ArrayList<Shape>() ;
        }


    }

    public void redo() {


        String name = "" ;
        ArrayList<Shape> tmp = new ArrayList<Shape>() ;

        if(mainRedo.size()==0)
            return ;

        name = mainRedo.pop() ;
        mainUndo.push(name) ;

        if (name.equalsIgnoreCase("circle")) {
            tmp = circlesRedo.pop() ;
            System.out.println("circlesundo size is " + circlesUndo.size());
            circlesUndo.push(tmp) ;

            if (circlesUndo.size()!=0) {


                System.out.println(circlesUndo.peek().size());
                circles = circlesUndo.peek();

            }
            else
                circles = new ArrayList<Shape>() ;
        }else if (name.equalsIgnoreCase("square")) {
            tmp = squaresRedo.pop() ;
            squaresUndo.push(tmp) ;
            if (squaresUndo.size()!=0)
                squares = squaresUndo.peek();
            else
                squares = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("rectangle")) {
            tmp = rectanglesRedo.pop() ;
            rectanglesUndo.push(tmp) ;
            if (rectanglesUndo.size()!=0)
                rectangles = rectanglesUndo.peek();
            else
                rectangles = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("line")) {
            tmp = linesRedo.pop() ;
            linesUndo.push(tmp) ;
            if (linesUndo.size()!=0)
                lines = linesUndo.peek();
            else
                lines = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("triangle")) {
            tmp = trianglesRedo.pop() ;
            trianglesUndo.push(tmp) ;
            if (trianglesUndo.size()!=0)
                triangles = trianglesUndo.peek();
            else
                triangles = new ArrayList<Shape>() ;
        }
        else if (name.equalsIgnoreCase("elipse")) {
            tmp = elipsesRedo.pop() ;
            elipsesUndo.push(tmp) ;
            if (elipsesUndo.size()!=0)
                elipses = elipsesUndo.peek();
            else
                elipses = new ArrayList<Shape>() ;
        }



    }

    public void addShape(String name, String id) {

        this.shape = factory.createShape(name, id);

        if(id.charAt(0)=='c') {

            for (Shape c : circles) {
                if (c.getId().equals(shape.getId())) {
                    circles.remove(c) ;
                    break ;
                }
            }
            circles.add(shape);

        }
        else if(id.charAt(0)=='s') {

            for (Shape c : squares) {
                if (c.getId().equals(shape.getId())) {
                    squares.remove(c) ;
                    break ;
                }
            }

            squares.add(shape);

        }
        else if(id.charAt(0)=='r') {

            for (Shape c : rectangles) {
                if (c.getId().equals(shape.getId())) {
                    rectangles.remove(c) ;
                    break ;
                }
            }
            rectangles.add(shape);

        }
        else if(id.charAt(0)=='e') {

            for (Shape c : elipses) {
                if (c.getId().equals(shape.getId())) {
                    elipses.remove(c) ;
                    break ;
                }
            }
            elipses.add(shape);}
        else if(id.charAt(0)=='t') {

            for (Shape c : triangles) {
                if (c.getId().equals(shape.getId())) {
                    triangles.remove(c) ;
                    break ;
                }
            }
            triangles.add(shape);
        }
        else if(id.charAt(0)=='l') {
            for (Shape c : lines) {
                if (c.getId().equals(shape.getId())) {
                    lines.remove(c) ;
                    break ;
                }
            }
            lines.add(shape);}


    }

    public void updateShape(JSONObject shape) {

        String id = (String)shape.get("id");

        ArrayList<Shape> tmp ;

        if(id.charAt(0)=='c')
            for(Shape circle : this.circles) {
                if(circle.getId().equals(id)) {
                    circle.updateShape(shape);
                    this.mainUndo.push("circle");
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : circles) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.circlesUndo.push(tmp) ;

                    break ;
                }
            }
        else if(id.charAt(0)=='s')
            for(Shape square : this.squares) {
                if(square.getId().equals(id)) {
                    square.updateShape(shape);
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : squares) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.squaresUndo.push(tmp);
                    this.mainUndo.push("square");
                    break ;
                }
            }
        else if(id.charAt(0)=='r')
            for(Shape rectangle : this.rectangles) {
                if(rectangle.getId().equals(id)) {
                    rectangle.updateShape(shape);
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : rectangles) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.rectanglesUndo.push(tmp) ;
                    this.mainUndo.push("rectangle");
                    break ;
                }
            }
        else if(id.charAt(0)=='e')
            for(Shape elipse : this.elipses) {
                if(elipse.getId().equals(id)) {
                    elipse.updateShape(shape);
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : elipses) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.elipsesUndo.push(tmp) ;
                    this.mainUndo.push("elipse");
                    break ;
                }
            }
        else if(id.charAt(0)=='t')
            for(Shape triangle : this.triangles) {
                if(triangle.getId().equals(id)) {
                    triangle.updateShape(shape);
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : triangles) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.trianglesUndo.push(tmp);
                    this.mainUndo.push("triangle");
                    break ;
                }
            }
        else if(id.charAt(0)=='l')
            for(Shape line : this.lines) {
                if(line.getId().equals(id)) {
                    line.updateShape(shape);
                    tmp = new ArrayList<Shape>() ;
                    for (Shape shapee : lines) {

                        Shape copy = shapee.copy(shapee) ;
                        tmp.add(copy);

                    }
                    this.linesUndo.push(tmp);
                    this.mainUndo.push("line");
                    break ;
                }
            }
    }

    public ArrayList<Object> getArrayOfShapes(String name) throws Exception {

        ArrayList<Object> ret = new ArrayList<Object>() ;
        ObjectMapper o = new ObjectMapper();
        JSONParser p = new JSONParser() ;
        JSONObject obj  = null ;

        if (name.equalsIgnoreCase("circles")) {

            for (Shape circle : circles) {
                String info = o.writeValueAsString(circle).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }
        }else if (name.equalsIgnoreCase("squares")) {

            for (Shape square : squares) {
                String info = o.writeValueAsString(square).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }

        }else if (name.equalsIgnoreCase("rectangles")) {
            for (Shape rectangle : rectangles) {
                String info = o.writeValueAsString(rectangle).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }
        }else if (name.equalsIgnoreCase("elipses")) {
            for (Shape elipse : elipses) {
                String info = o.writeValueAsString(elipse).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }
        }else if (name.equalsIgnoreCase("lines")) {
            for (Shape line : lines) {
                String info = o.writeValueAsString(line).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }
        }else if (name.equalsIgnoreCase("triangles")) {
            for (Shape triangle : triangles) {
                String info = o.writeValueAsString(triangle).toString();
                obj = (JSONObject) p.parse(info) ;
                ret.add(obj) ;
            }
        }


        return ret;



    }

    public void copy(String id) throws JsonProcessingException, ParseException {

        ObjectMapper o = new ObjectMapper();
        JSONParser p = new JSONParser() ;
        JSONObject json = new JSONObject() ;
        Shape cp = factory.createShape(id) ;

        if(id.charAt(0)=='c') {

            for (Shape c : circles) {

                if (c.getId().equals(id)){
                    id = "c"+((this.circles.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("circle",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }

        }
        else if(id.charAt(0)=='s') {

            for (Shape c : squares) {

                if (c.getId().equals(id)){
                    id = "s"+((this.squares.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("square",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }

        }
        else if(id.charAt(0)=='r') {

            for (Shape c : rectangles) {

                if (c.getId().equals(id)){
                    id = "r"+((this.rectangles.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("rectangle",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }

        }
        else if(id.charAt(0)=='e') {

            for (Shape c : elipses) {

                if (c.getId().equals(id)){
                    id = "e"+((this.elipses.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("elipse",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }


        }
        else if(id.charAt(0)=='t') {

            for (Shape c : triangles) {

                if (c.getId().equals(id)){
                    id = "t"+((this.triangles.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("triangle",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }
        }
        else if(id.charAt(0)=='l') {
            for (Shape c : lines) {

                if (c.getId().equals(id)){
                    id = "l"+((this.lines.size()+1)+"") ;
                    cp = cp.copy(c) ;
                    cp.setStartPointX(cp.getStartPointX()+10) ;
                    cp.setStartPointY(cp.getStartPointY()+10) ;
                    addShape("line",id) ;
                    cp.setId(id);
                    String info = o.writeValueAsString(cp).toString() ;
                    json = (JSONObject) p.parse(info);
                    updateShape(json);
                    break ;
                }
            }
        }

    }

    public ArrayList<Object> deleteShape (String id) throws Exception{

        ArrayList<Object> ret = new ArrayList<Object>();

        if(id.charAt(0)=='c') {
            System.out.println(circles.size());
            for(Shape circle : circles) {
                System.out.println("here");
                if(circle.getId().equals(id)) {
                    circles.remove(circle) ;
                    ret = getArrayOfShapes("circles") ;
                    break;
                }
            }
        }
        else if(id.charAt(0)=='s')
            for(Shape square : squares) {
                if(square.getId().equals(id)) {
                    squares.remove(square) ;
                    ret = getArrayOfShapes("squares") ;
                    break ;
                }
            }
        else if(id.charAt(0)=='r')
            for(Shape rectangle : rectangles) {
                if(rectangle.getId().equals(id)) {
                    rectangles.remove(rectangle) ;
                    ret = getArrayOfShapes("rectangles") ;
                    break;
                }
            }
        else if(id.charAt(0)=='e')
            for(Shape elipse : elipses) {
                if(elipse.getId().equals(id)) {
                    elipses.remove(elipse) ;
                    ret = getArrayOfShapes("elipses") ;
                    break;
                }
            }
        else if(id.charAt(0)=='t')
            for(Shape triangle : triangles) {
                if(triangle.getId().equals(id)) {
                    triangles.remove(triangle) ;
                    ret = getArrayOfShapes("triangles") ;
                    break ;
                }
            }
        else if(id.charAt(0)=='l')
            for(Shape line : lines) {
                if(line.getId().equals(id)) {
                    lines.remove(line) ;
                    ret = getArrayOfShapes("lines") ;
                    break ;
                }
            }

        return ret ;

    }

    @SuppressWarnings("unchecked")
    public boolean save(String path) throws Exception {
        JSONArray shapes = new JSONArray();
        getArrayOfShapes("circles").forEach(shapes::add);
        getArrayOfShapes("squares").forEach(shapes::add);
        getArrayOfShapes("rectangles").forEach(shapes::add);
        getArrayOfShapes("elipses").forEach(shapes::add);
        getArrayOfShapes("lines").forEach(shapes::add);
        getArrayOfShapes("triangles").forEach(shapes::add);
        try (FileWriter file = new FileWriter(path)){
            file.write(shapes.toJSONString());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    public boolean load(String path) throws ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)){

            JSONArray shapes = (JSONArray) parser.parse(reader);
            shapes.forEach(shape ->{
                String id = (String)((JSONObject)shape).get("id");
                
                if(id.charAt(0)=='c')
                    addShape("circle",id);
                else if(id.charAt(0)=='s')
                    addShape("square",id);
                else if(id.charAt(0)=='r')
                    addShape("rectangle",id);
                else if(id.charAt(0)=='e')
                    addShape("elipes",id);
                else if(id.charAt(0)=='t')
                    addShape("triangle",id);
                else if(id.charAt(0)=='l')
                    addShape("line",id);

                updateShape((JSONObject)shape);

            });


        }catch(IOException e) {
            return false;
        }
        return true;

    }

    public boolean saveXML(String path) {
        ArrayList<Shape> shapes = new ArrayList<>(circles.size()<<3);
        shapes.addAll(circles);
        shapes.addAll(squares);
        shapes.addAll(rectangles);
        shapes.addAll(triangles);
        shapes.addAll(elipses);
        shapes.addAll(lines);
        try{
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("shapes");
            doc.appendChild(rootElement);
            for(Shape shape : shapes){

                Element object = doc.createElement("shape");
                rootElement.appendChild(object);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(shape.getId()+""));
                object.appendChild(id);

                Element x = doc.createElement("startPointX");
                x.appendChild(doc.createTextNode(shape.getStartPointX()+""));
                object.appendChild(x);

                Element y = doc.createElement("startPointY");
                y.appendChild(doc.createTextNode(shape.getStartPointY()+""));
                object.appendChild(y);

                Element height = doc.createElement("height");
                height.appendChild(doc.createTextNode(shape.getHeight()+""));
                object.appendChild(height);

                Element width = doc.createElement("width");
                width.appendChild(doc.createTextNode(shape.getWidth()+""));
                object.appendChild(width);

                Element scaleX = doc.createElement("scaleX");
                scaleX.appendChild(doc.createTextNode(shape.getScaleX()+""));
                object.appendChild(scaleX);

                Element scaleY = doc.createElement("scaleY");
                scaleY.appendChild(doc.createTextNode(shape.getScaleY()+""));
                object.appendChild(scaleY);

                Element rotation = doc.createElement("rotation");
                rotation.appendChild(doc.createTextNode(shape.getRotation()+""));
                object.appendChild(rotation);

                Element colorfill = doc.createElement("colorfill");
                colorfill.appendChild(doc.createTextNode(shape.getColorfill()+""));
                object.appendChild(colorfill);

                Element colorstroke = doc.createElement("colorstroke");
                colorstroke.appendChild(doc.createTextNode(shape.getColorstroke()+""));
                object.appendChild(colorstroke);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(path));
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean loadXML(String path)  {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document =dBuilder.parse(new File(path));
            NodeList nList=  document.getElementsByTagName("shape");
            handleLoadXML(nList);
        }catch(Exception e) {
            return false;
        }
        return true;
    }
    private void handleLoadXML(NodeList shapes) {
        for(int i=0;i<shapes.getLength();i++) {
            Node node = shapes.item(i);
            String id = ((Element)node).getElementsByTagName("id").item(0).getTextContent();
            if(id.charAt(0)=='c') {
                addShape("circle",id);
                circles.get(circles.size()-1).updateShape(node);
            }else if (id.charAt(0)=='s') {
                addShape("square",id);
                squares.get(squares.size()-1).updateShape(node);
            }else if (id.charAt(0)=='r') {
                addShape("rectangle",id);
                rectangles.get(rectangles.size()-1).updateShape(node);
            }else if (id.charAt(0)=='e') {
                elipses.get(elipses.size()-1).updateShape(node);
            }else if (id.charAt(0)=='t') {
                addShape("triangle",id);
                triangles.get(triangles.size()-1).updateShape(node);
            }else if (id.charAt(0)=='l') {
                addShape("line",id);
                lines.get(lines.size()-1).updateShape(node);
            }


        }
    }
    public void deleteAll() {
        circles.clear();
        squares.clear();
        rectangles.clear();
        elipses.clear();
        triangles.clear();
        lines.clear();
    }


}
