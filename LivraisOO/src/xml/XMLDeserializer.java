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
import model.CityMap;
import model.Section;
import model.TypicalDay;
import model.TimeWindow;

public class XMLDeserializer {

	/**
	 * Loads a map. Calls the function buildMapFronDOMXML
	 * 
	 * @param map
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XMLException
	 */
	public static void loadMap(CityMap map)
			throws ParserConfigurationException, SAXException, IOException, XMLException {
		File xml = XMLFileOpener.getInstance().open(true);
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = docBuilder.parse(xml);
		Element root = document.getDocumentElement();
		if (root.getNodeName().equals("Reseau")) {
			buildMapFromDOMXML(root, map);
		} else
			throw new XMLException("Document non conforme");
	}

	/**
	 * Populates the map
	 * 
	 * @param nodeDOMRoot
	 * @param map
	 * @throws XMLException
	 * @throws NumberFormatException
	 */
	private static void buildMapFromDOMXML(Element nodeDOMRoot, CityMap map)
			throws XMLException, NumberFormatException {
		NodeList nodeList = nodeDOMRoot.getElementsByTagName("Noeud");
		for (int i = 0; i < nodeList.getLength(); i++) {
			map.addNode(createNode((Element) nodeList.item(i), map));
		}

		for (Section s : map.getSections()) {
			map.getNodeById(s.getArrival()).addIncoming(s);
		}
	}

	/**
	 * Creates a node and add the incomming/outgoing sections to the node
	 * 
	 * @param elt
	 * @param map
	 * @return
	 * @throws XMLException
	 */
	private static Node createNode(Element elt, CityMap map) throws XMLException {
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

	/**
	 * Creates a section
	 * 
	 * @param elt
	 * @param departure
	 * @return
	 * @throws XMLException
	 */
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

	/**
	 * Loads the list of deliveries adding it to the typicalDay
	 * 
	 * @param typicalDay
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XMLException
	 */
	public static void loadDeliveries(TypicalDay typicalDay)
			throws ParserConfigurationException, SAXException, IOException, XMLException {
		File xml = XMLFileOpener.getInstance().open(true);
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = docBuilder.parse(xml);
		Element root = document.getDocumentElement();
		if (root.getNodeName().equals("JourneeType")) {
			buildDeliveriesFromDOMXML(root, typicalDay);
		} else
			throw new XMLException("Document non conforme");
	}

	/**
	 * Creates the timeWindows and adds the newly formed deliveries to the
	 * timeWindows and eventually to the typicalDay
	 * 
	 * @param nodeDOMRoot
	 * @param typicalDay
	 * @throws XMLException
	 * @throws NumberFormatException
	 */
	private static void buildDeliveriesFromDOMXML(Element nodeDOMRoot, TypicalDay typicalDay)
			throws XMLException, NumberFormatException {
		NodeList nodeList = nodeDOMRoot.getElementsByTagName("Plage");
		NodeList wareHouse = nodeDOMRoot.getElementsByTagName("Entrepot");
		typicalDay.setWareHouse(Integer.parseInt(((Element) wareHouse.item(0)).getAttribute("adresse")));
		for (int i = 0; i < nodeList.getLength(); i++) {
			String start = ((Element) nodeList.item(i)).getAttribute("heureDebut");
			String end = ((Element) nodeList.item(i)).getAttribute("heureFin");
			TimeWindow tm = new TimeWindow(computeTime(start), computeTime(end));
			NodeList deliveries = ((Element) nodeList.item(i)).getElementsByTagName("Livraison");
			for (int j = 0; j < deliveries.getLength(); ++j) {
				tm.addDelivery(createDelivery((Element) deliveries.item(j)));
			}
			typicalDay.addTimeWindow(tm);
		}
	}

	/**
	 * Creates a delivery that will be added to the a time window
	 * 
	 * @param elt
	 * @return
	 * @throws XMLException
	 * @throws NumberFormatException
	 */
	private static Delivery createDelivery(Element elt) throws XMLException, NumberFormatException {
		int id = Integer.parseInt(elt.getAttribute("id"));
		int client = Integer.parseInt(elt.getAttribute("client"));
		int address = Integer.parseInt(elt.getAttribute("adresse"));
		return new Delivery(id, client, address);
	}

	/**
	 * Transforms a time from string to an integer which corresponds to the
	 * number of seconds
	 * 
	 * @param time
	 * @return
	 */
	private static int computeTime(String time) {
		int ret = 0;
		int multiply = 1;
		int decimals = 1;
		for (int i = time.length()-1; i >= 0; --i) {
			if (time.charAt(i) == ':') {
				multiply *= 60;
				decimals = 1;
			} else {
				ret += (time.charAt(i) - '0') * multiply * decimals;
				decimals *= 10;
			}
		}
		return ret;
	}
}
