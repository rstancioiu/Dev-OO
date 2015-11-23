package xml;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Node;
import model.Map;
import model.Section;


public class XMLDeserializer {
	
	public static void load(Map map) throws ParserConfigurationException, SAXException, IOException, XMLException{
		File xml = XMLFileOpener.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element root = document.getDocumentElement();
        if (root.getNodeName().equals("Reseau")) {
        	buildFromDOMXML(root, map);
        }
        else throw new XMLException("Document non conforme");
	}

    private static void buildFromDOMXML(Element noeudDOMRacine, Map map) throws XMLException, NumberFormatException{
    	NodeList nodeList = noeudDOMRacine.getElementsByTagName("Noeud");
       	for (int i = 0; i < nodeList.getLength(); i++) {
        	map.addNode(createNode((Element) nodeList.item(i), map));
       	}
       	
       	for(Section s:map.getSections()){
       		map.getNodeById(s.getArrival()).addIncoming(s);
       	}
       	
    }
    
    private static Node createNode(Element elt, Map map) throws XMLException{
    	int id = Integer.parseInt(elt.getAttribute("id"));
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		Node n = new Node(id, x, y);
   		NodeList sectionList = elt.getElementsByTagName("LeTronconSortant");
       	for (int i = 0; i < sectionList.getLength(); i++) {
        	n.addOutgoing(createSection((Element) sectionList.item(i), id));
        	map.addSection(createSection((Element) sectionList.item(i), id));
       	}
       	return n;
    }
    
    private static Section createSection(Element elt, int departure) throws XMLException{
    	String name = elt.getAttribute("nomRue");
    	int arrival = Integer.parseInt(elt.getAttribute("idNoeudDestination"));
    	double length=0;
		try {
			length =  NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("longueur")).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	double speed=0;
		try {
			speed = (double) NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("vitesse")).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
   		return new Section(name, departure, arrival, speed, length);
    }
 
}
