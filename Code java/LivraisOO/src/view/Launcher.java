package view;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;
import model.Map;

public class Launcher {

	public static void main(String[] args) {
		Window window = new Window();
		Map map = new Map();
		try {
			XMLDeserializer.load(map);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.display();
		window.drawMap(map);
	}
	
}
