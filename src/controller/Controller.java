package controller;

import model.DeliveryRound;
import model.Node;
import graph.Graph;
import model.CityMap;
import model.Delivery;
import model.TypicalDay;
import view.Window;

public class Controller {
	private CityMap map;
	private TypicalDay typicalDay;
	private Graph graph;
	private CommandsList cmdList;
	private DeliveryRound deliveryRound;
	private static State currentState;
	private static Window window;

	// Instances associated to each possible state of a controller
	protected static final InitState initState = new InitState();
	protected static final CityMapState mapState = new CityMapState();
	protected static final RequestState requestState = new RequestState();
	protected static final DeliveryState deliveryState = new DeliveryState();
	protected static final AdditionState additionState = new AdditionState();
	protected static final DeleteState deleteState = new DeleteState();
	protected static final SwapState swapState = new SwapState();
	protected static final GenerationState generationState = new GenerationState();

	/**
	 * Constructor of the controller
	 * 
	 * @param map
	 * @param typicalDay
	 * @param deliveryRound
	 */
	public Controller(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound) {
		this.map = map;
		this.typicalDay = typicalDay;
		this.deliveryRound = deliveryRound;
		cmdList = new CommandsList();
		window = new Window(this);
		setCurrentState(initState);
	}

	/**
	 * Change the controller current state, by receiving the new state as
	 * parameter
	 *
	 * @param state
	 */
	protected static void setCurrentState(State state) {
		currentState = state;
		window.setMessage(" ");
		currentState.updateVue(window);
	}

	/**
	 * Returns the controller's current state
	 * 
	 * @return current state
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * Generate the delivery round
	 */
	public void computeDeliveries() {
		graph = new Graph(map, typicalDay);
		currentState.computeDeliveries(this.map, this.typicalDay, this.deliveryRound, this.window, this.graph);
	}

	/**
	 * Load a plan
	 */
	public void loadMap() {
		currentState.loadMap(this.map, this.window);
	}

	/**
	 * Load a list of deliveries
	 */
	public void loadDeliveries() {
		currentState.loadDeliveries(this.typicalDay, this.window);
	}

	/**
	 * Generate the RoadMap file from delivery round
	 */
	public void generateRoadmap() {
		currentState.generateRoadmap(this.deliveryRound);
	}

	/**
	 * Confirm the addition of a node to the delivery round
	 * 
	 * @param delivery
	 * @param node
	 */
	public void confirmAdd(Delivery delivery, Node node) {
		currentState.confirmAdd(deliveryRound, delivery, node, typicalDay, graph, cmdList);
		window.drawDeliveryRound(deliveryRound, typicalDay);
	}

	/**
	 * Confirm the delete a delivery from the delivery round
	 * 
	 * @param delivery
	 */
	public void confirmDelete(Delivery delivery) {
		currentState.confirmDelete(deliveryRound, delivery, typicalDay, graph, cmdList);
		window.drawDeliveryRound(deliveryRound, typicalDay);
		window.drawDeliveries(typicalDay);
	}

	/**
	 * Confirm the swap of two deliveries from the delivery round
	 * 
	 * @param start
	 * @param end
	 */
	public void confirmSwap(Delivery start, Delivery end) {
		System.out.println("Swap started");
		currentState.confirmSwap(deliveryRound, start, end, graph, cmdList);
		window.drawDeliveryRound(deliveryRound, typicalDay);
	}

	/**
	 * Cancel a add/delete/swap command
	 */
	public void cancel() {
		currentState.cancel();
	}

	/**
	 * Click on the add button
	 */
	public void clickAddButton() {
		currentState.clickAddButton();
	}

	/**
	 * Click on the delete button
	 */
	public void clickDeleteButton() {
		currentState.clickDeleteButton();
	}

	/**
	 * Click on the swap button
	 */
	public void clickSwapButton() {
		currentState.clickSwapButton();
	}

	/**
	 * Undo a command
	 */
	public void undo() {
		cmdList.undo();
		window.drawDeliveryRound(deliveryRound, typicalDay);
		window.drawDeliveries(typicalDay);
	}

	/**
	 * Redo a command
	 */
	public void redo() {
		cmdList.redo();
		window.drawDeliveryRound(deliveryRound, typicalDay);
		window.drawDeliveries(typicalDay);
	}
}
