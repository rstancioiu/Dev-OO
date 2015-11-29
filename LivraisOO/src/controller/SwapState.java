package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import view.Window;

public class SwapState extends DefaultState {
	
	//State reached when the user clicks the swap button
	//The user selects a node in the first list and a node in the second list then hits the confirm button
	@Override
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph) {
		deliveryRound.swapDeliveries(start, end, graph);
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void updateVue(Window window){
		window.showButtons();
	}
}
