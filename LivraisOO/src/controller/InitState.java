package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.CityMap;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

public class InitState extends DefaultState {
	//Initial State

	@Override
	public void loadMap(CityMap map, Window window) {
		try {
			XMLDeserializer.loadMap(map);
			Controller.setCurrentState(Controller.mapState);
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
		System.out.println("Current state: cityMapState");
	}	

	public void updateVue(Window window){
		window.disableAll();
		window.enableLoadMap(true);
	}

}
