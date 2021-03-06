package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.CityMap;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

/**
 * Initial state of the controller, basically set when the application is
 * launched Extends DefaultState abstract class
 */
public class InitState extends DefaultState {
	// Initial State

	@Override
	public void loadMap(CityMap map, Window window) {
		try {
			XMLDeserializer.loadMap(map);
			Controller.setCurrentState(Controller.cityMapState);
			window.drawMap(map);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateVue(Window window) {
		window.disableAll();
		window.enableLoadMap(true);
	}

}
