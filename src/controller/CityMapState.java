package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.TypicalDay;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

public class CityMapState extends DefaultState {

	@Override
	public void loadDeliveries(TypicalDay typicalDay, Window window){
		try {
			XMLDeserializer.loadDeliveries(typicalDay);
			Controller.setCurrentState(Controller.requestState);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
		window.drawDeliveries(typicalDay);
		System.out.println("Current state : requestState");
	}

	public void updateVue(Window window){
		window.disableAll();
		window.enableLoadMap(true);
		window.enableLoadDeliveries(true);
	}

}
