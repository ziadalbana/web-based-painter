package eg.edu.alexu.csd.oop.draw.Backend.controller;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import eg.edu.alexu.csd.oop.draw.Backend.service.Service;

@RestController
@CrossOrigin

public class Controller {
	
	@Autowired
	Service service ;
	
	
	@PostMapping ("/{shape}/{id}")
	public void create(@PathVariable String shape,@PathVariable String id) {
		service.addShape(shape, id);

	}
	
	@PostMapping ("/")
	public void update(@RequestBody String shape) {
		
		JSONParser p = new JSONParser() ; 
		JSONObject obj  = null ; 
		try {
			obj = (JSONObject) p.parse(shape) ; 
			service.updateShape(obj);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	 
	}
	
	@GetMapping ("/{name}")
	public ArrayList<Object> getShapes(@PathVariable("name") String name) throws Exception{
		return service.getArrayOfShapes(name) ; 
	}
	
	@DeleteMapping("/{id}")
	public void deleteShape(@PathVariable("id") String id) throws Exception{
		service.deleteShape(id) ;
	}
	@DeleteMapping("/deleteAll")
	public void deleteAll() throws Exception{
		service.deleteAll();
	}
	
	@PostMapping("/save/{filename}/{filetype}")
	public boolean save (@PathVariable("filename") String filename,@PathVariable("filetype") String filetype) throws Exception {

		String type = filetype.toLowerCase();
		String path = filename+'.'+type;
		if(type.equalsIgnoreCase("json"))
			return service.save(path);
		else if(type.equalsIgnoreCase("xml"))
			return service.saveXML(path);
		return false;
	}
	
	@PostMapping("/load/{filename}")
	public boolean load (@PathVariable("filename") String filename) {
		service.deleteAll();
		try {
			if(filename.substring(filename.length()-3).equalsIgnoreCase("xml"))
				return service.loadXML(filename);
			else if(filename.substring(filename.length()-4).equalsIgnoreCase("json"))
				return service.load(filename);
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	@PostMapping("/undo")
	public void undo () {
		service.Undo();
	}
	
	@PostMapping("/redo")
	public void redo () {
		service.redo();
	}
	
	@PostMapping("/copy/{id}")
	public void copy(@PathVariable("id") String id) throws JsonProcessingException, ParseException {
		service.copy(id) ; 
	}
	
	

}
