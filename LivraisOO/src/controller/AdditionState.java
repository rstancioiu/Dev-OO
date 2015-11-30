package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TimeWindow;
import model.TypicalDay;
import view.Window;

public class AdditionState extends DefaultState {
	//State reached when the user clicks the add button
	//The user selects a node in the first list and a preceding delivery in the second list
	@Override
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery, Node node, TypicalDay typicalDay, Graph graph) {
		Delivery newDelivery = new Delivery(0, 0, node.getId(), new TimeWindow(0, 24));
		deliveryRound.addDelivery(delivery, newDelivery, graph, new TimeWindow(-1, -1));
		
		if(delivery.getAddress() == typicalDay.getWareHouse()) {
			Delivery firstReal = deliveryRound.getPaths().get(1).getArrival();
			firstReal.getTimeWindow().insertDelivery(firstReal, newDelivery);
			newDelivery.setTimeWindow(firstReal.getTimeWindow());
		} else {
			delivery.getTimeWindow().insertDelivery(delivery, newDelivery);
			newDelivery.setTimeWindow(delivery.getTimeWindow());
		}
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void updateVue(Window window){
		window.showButtons();
	}
}
