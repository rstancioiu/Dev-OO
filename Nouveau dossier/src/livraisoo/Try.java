package livraisoo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Try {
	public static void main (String args[]){
		Plan plan = new Plan();
		try {
			DeserialiseurXML.charger(plan);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
