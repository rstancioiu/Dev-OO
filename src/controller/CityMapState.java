package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.CityMap;
import model.TypicalDay;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

/**
 * State set when the user has load the map and leaved when the user load the
 * deliveries file Extends DefaultState abstract class
 */
public class CityMapState extends DefaultState {

	@Override
	public void loadDeliveries(TypicalDay typicalDay, Window window) {
		try {
			XMLDeserializer.loadDeliveries(typicalDay);
			Controller.setCurrentState(Controller.requestState);
			window.clearDeliveries();
			window.drawDeliveries(typicalDay);
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
		window.enableLoadDeliveries(true);
	}

}
