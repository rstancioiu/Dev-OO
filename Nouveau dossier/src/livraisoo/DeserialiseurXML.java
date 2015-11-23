package livraisoo;

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


public class DeserialiseurXML {
	/**
	 * Ouvre un fichier xml et cree plan a partir du contenu du fichier
	 * @param plan
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void charger(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("Reseau")) {
           construireAPartirDeDOMXML(racine, plan);
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}

    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionXML, NumberFormatException{
    	NodeList nodeList = noeudDOMRacine.getElementsByTagName("Noeud");
       	for (int i = 0; i < nodeList.getLength(); i++) {
        	plan.add(createNode((Element) nodeList.item(i)));
       	}
    }
    
    private static Node createNode(Element elt) throws ExceptionXML{
    	int id = Integer.parseInt(elt.getAttribute("id"));
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		System.out.println(Integer.toString(id));
   		Node n = new Node(id, x, y);
   		NodeList sectionList = elt.getElementsByTagName("LeTronconSortant");
       	for (int i = 0; i < sectionList.getLength(); i++) {
        	n.add(createSection((Element) sectionList.item(i)));
       	}
       	/*
   		if (p == null)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Coordonnees d'un point en dehors du plan");
   		int rayon = Integer.parseInt(elt.getAttribute("rayon"));
   		if (rayon <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Cercle de rayon negatif ou nul");
   		*/
       	return n;
    }
    
    private static Section createSection(Element elt) throws ExceptionXML{
    	String name = elt.getAttribute("nomRue");
    	int destination = Integer.parseInt(elt.getAttribute("idNoeudDestination"));
    	double length=0;
		try {
			length =  NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("longueur")).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	double speed=0;
		try {
			speed = (double) NumberFormat.getNumberInstance(Locale.FRANCE).parse(elt.getAttribute("vitesse")).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
   		/*if (p == null)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Coordonnees d'un point en dehors du plan");
      	int largeurRectangle = Integer.parseInt(elt.getAttribute("largeur"));
   		if (largeurRectangle <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Rectangle de largeur negative ou nulle");
      	int hauteurRectangle = Integer.parseInt(elt.getAttribute("hauteur"));
   		if (hauteurRectangle <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Rectangle de hauteur negative ou nulle");
   		*/
   		return new Section(name, destination, speed, length);
    }
 
}
