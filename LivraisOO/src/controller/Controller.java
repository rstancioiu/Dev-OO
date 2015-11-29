package controller;

import model.DeliveryRound;
import model.Node;
import model.CityMap;
import model.TypicalDay;
import view.Window;

public class Controller {
	private CityMap map;
	private TypicalDay typicalDay;
	private CommandsList cmdList;
	private DeliveryRound deliveryRound;
	private static State currentState;
	private Window window;

	// Instances associated to each possible state of a controller
	protected static final InitState initState = new InitState();
	protected static final CityMapState mapState = new CityMapState();
	protected static final RequestState requestState = new RequestState();
	protected static final DeliveryState deliveryState = new DeliveryState();
	protected static final AdditionState additionState = new AdditionState();
	protected static final DeleteState deleteState = new DeleteState();
	protected static final SwapState swapState = new SwapState();
	protected static final GenerationState generationState = new GenerationState();

	public Controller(CityMap map, TypicalDay typicalDay) {
		this.map = map;
		this.typicalDay = typicalDay;
		cmdList = new CommandsList();
		currentState = initState;
		window = new Window(this);
	}

	/**
	 * Change the controller current state, by receiving the new state as
	 * parameter
	 *
	 * @param state
	 */
	protected static void setCurrentState(State state) {
		currentState = state;
	}

	/**
	 * Returns the controller's current state
	 * 
	 * @return current state
	 */
	protected static State getCurrentState() {
		return currentState;
	}
	
	public void computeDeliveries(){
		System.out.println(currentState.getClass());
		currentState.computeDeliveries(this.map, this.typicalDay, this.deliveryRound);
	}

	public void loadMap() {
		currentState.loadMap(this.map, this.window);
	}

	public void loadDeliveries() {
		currentState.loadDeliveries(this.typicalDay);
	}

	public void addDelivery() {
		currentState.addDelivery(deliveryRound, map);
	}

	public void modifyDelivery(Node d) {
		currentState.modifyDelivery(d);
	}

	public void deleteDelivery(Node d) {
		currentState.deleteDelivery(deliveryRound, d);
	}

	public void modifyDelivery(Node d1, Node d2) {
		currentState.modifyDelivery(deliveryRound, d1, d2);
	}

	public void generateRoadmap() {
		currentState.generateRoadmap();
	}

	public void rightClick(Window window, CommandsList cmdList) {
		currentState.rightClick(window, cmdList);
	}

	public void save(CityMap map, Window window) {
	}

	public void open(CityMap map, CommandsList cmdList, Window window) {
	}

	public void confirm(DeliveryRound deliveryRound, Window window) {
		currentState.confirm(deliveryRound, window);
	}

	public void cancel() {
		currentState.cancel();
	}
	
}
