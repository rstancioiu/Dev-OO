package controller;


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
	protected static final AdditionState additionState = new AdditionState();
	protected static final DeleteState deleteState = new DeleteState();
	protected static final SwapState swapState = new SwapState();
	protected static final GenerationState generationState = new GenerationState();
	
	public Controller(){
		cmdList = new CommandsList();
		currentState = initState;
		window = new Window(this);
	}
	
	/**
	 * 
	 * Change the controller current state, by receving the new state as parameter
	 *
	 * @param state
	 * 
	 */
	protected static void setCurrentState(State state){
		currentState = state;
	}
	
	/**
	 * 
	 * Returns the controller's current state
	 * 
	 * @return current state
	 * 
	 */
	protected static State getCurrentState(){
		return currentState;
	}

	public void loadMap(){
		initState.loadMap(window);
	}
	
	public void loadDeliveries(){
		mapState.loadDeliveries();
	}
}
