package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Map;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

public class InitState extends DefaultState {
	//Initial State
	
	@Override
	public void loadMap(Window window) {
		Map map = new Map();
		try {
			XMLDeserializer.loadMap(map);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
		window.drawMap(map);
		
	}	
	
}
