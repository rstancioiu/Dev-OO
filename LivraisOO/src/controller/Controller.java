package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Delivery;
import model.Map;
import model.TimeWindow;
import model.TypicalDay;
import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;

public class Controller {
	private Map map;
	private CommandsList cmdList;
	private static State currentState;
	private Window window;
	// Instances associated to each possible state of a controller
	protected static final InitState initState = new InitState();
	protected static final MapState mapState = new MapState();
	protected static final RequestState requestState = new RequestState();
	protected static final DeliveryState deliveryState = new DeliveryState();
	protected static final AdditionState additionState = new AdditionState();
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
	
	/**
	 * Change l'etat courant du controleur
	 * @param etat le nouvel etat courant
	 */
	protected static void setCurrentState(State state){
		currentState = state;
	}
	
	public void loadMap(){
		initState.loadMap(window);
	}
	
	public void loadDeliveries(){
		TypicalDay typicalDay = new TypicalDay();
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
	}
	
	public void computeDeliveries(){
		
	}
}
