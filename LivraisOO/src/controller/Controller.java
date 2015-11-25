package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Delivery;
import model.CityMap;
import model.TimeWindow;
import model.TypicalDay;
import graph.GraphComplete;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

public class Controller {
	private CityMap map;
	private CommandsList cmdList;
	private static State currentState;
	private Window window;
	private TypicalDay typicalDay;
	
	// Instances associated to each possible state of a controller
	protected static final InitState initState = new InitState();
	protected static final MapState mapState = new MapState();
	protected static final RequestState requestState = new RequestState();
	protected static final DeliveryState deliveryState = new DeliveryState();
	protected static final AditionState1 aditionState1 = new AditionState1();
	protected static final AditionState2 aditionState2 = new AditionState2();
	protected static final ModificationState modificationState = new ModificationState();
	protected static final DeleteState deleteState = new DeleteState();
	protected static final SwapState1 swapState2 = new SwapState1();
	protected static final SwapState2 swapState1 = new SwapState2();
	protected static final GenerationState generationState = new GenerationState();
	
	public Controller(){
		cmdList = new CommandsList();
		currentState = initState;
		window = new Window(this);
	}
	
	public void loadMap(){
		map = new CityMap();
		try {
			XMLDeserializer.loadMap(map);
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
	}
	
	public void loadDeliveries(){
		typicalDay = new TypicalDay();
		try {
			XMLDeserializer.loadDeliveries(typicalDay);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
		computeDeliveries();
	}
	
	public void computeDeliveries(){
		GraphComplete graph = new GraphComplete(map,typicalDay);
		graph.compute();
	}
}
