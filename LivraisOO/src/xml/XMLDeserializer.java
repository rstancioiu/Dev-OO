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

import model.Delivery;
import model.Node;
import model.Map;
import model.Section;
import model.TypicalDay;
import model.TimeWindow;

public class XMLDeserializer {

	public static void loadMap(Map map) throws ParserConfigurationException, SAXException, IOException, XMLException {
		File xml = XMLFileOpener.getInstance().ouvre(true);
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = docBuilder.parse(xml);
		Element root = document.getDocumentElement();
		if (root.getNodeName().equals("Reseau")) {
			buildMapFromDOMXML(root, map);
		} else
			throw new XMLException("Document non conforme");
	}

	private static void buildMapFromDOMXML(Element noeudDOMRacine, Map map) throws XMLException, NumberFormatException {
		NodeList nodeList = noeudDOMRacine.getElementsByTagName("Noeud");
		for (int i = 0; i < nodeList.getLength(); i++) {
			map.addNode(createNode((Element) nodeList.item(i), map));
		}

		for (Section s : map.getSections()) {
			map.getNodeById(s.getArrival()).addIncoming(s);
		}
	}

	private static Node createNode(Element elt, Map map) throws XMLException {
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

	private static Section createSection(Element elt, int departure) throws XMLException {
		double length = 0;
		double speed = 0;
		String name = elt.getAttribute("nomRue");
		int arrival = Integer.parseInt(elt.getAttribute("idNoeudDestination"));
		try {
			length = NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("longueur")).doubleValue();
			speed = (double) NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("vitesse"))
					.doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Section(name, departure, arrival, speed, length);
	}

	public static void loadDeliveries(TypicalDay typicalDay)
			throws ParserConfigurationException, SAXException, IOException, XMLException {
		File xml = XMLFileOpener.getInstance().ouvre(true);
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = docBuilder.parse(xml);
		Element root = document.getDocumentElement();
		if (root.getNodeName().equals("JourneeType")) {
			buildDeliveriesFromDOMXML(root, typicalDay);
		} else
			throw new XMLException("Document non conforme");
	}

	private static void buildDeliveriesFromDOMXML(Element noeudDOMRacine, TypicalDay typicalDay)
			throws XMLException, NumberFormatException {
		NodeList nodeList = noeudDOMRacine.getElementsByTagName("Plage");
		for (int i = 0; i < nodeList.getLength(); i++) {
			String start = ((Element) nodeList.item(i)).getAttribute("heureDebut");
			String end = ((Element) nodeList.item(i)).getAttribute("heureFin");
			TimeWindow tm = new TimeWindow(start, end);
			NodeList deliveries = ((Element) nodeList.item(i)).getElementsByTagName("Livraison");
			for (int j = 0; j < deliveries.getLength(); ++j) {
				tm.addDelivery(createDelivery((Element) deliveries.item(j)));
			}
			typicalDay.addTimeWindow(tm);
		}
	}

	private static Delivery createDelivery(Element elt) throws XMLException, NumberFormatException {
		int id = Integer.parseInt(elt.getAttribute("id"));
		int client = Integer.parseInt(elt.getAttribute("client"));
		int address = Integer.parseInt(elt.getAttribute("adresse"));
		return new Delivery(id, client, address);
	}
}
