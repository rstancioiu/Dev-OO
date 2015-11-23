package controller;

import model.Map;

public class Controller {
	
	private Map map;
	private CommandsList cmdList;
	private static state currentState;
	// Instances associated to each possible state of a controller
	protected static final InitState initState = new InitState();
	protected static final MapState mapState = new MapState();
	protected static final RequestState requestState = new RequestState();
	protected static final DeliveryState deliveryState = new DeliveryState();
	protected static final AditionState1 aditionState1 = new AditionState1();
	protected static final AditionSate2 aditionState2 = new AditionSate2();
	protected static final ModificationState modificationState = new ModificationState();
	protected static final DeleteState deleteState = new DeleteState();
	protected static final SwapState1 swapState2 = new SwapState1();
	protected static final SwapState2 swapState1 = new SwapState2();
	protected static final GenerationState generationState = new GenerationState();

}
