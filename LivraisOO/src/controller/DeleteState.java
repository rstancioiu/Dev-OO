package controller;

import graph.Graph;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TimeWindow;
import model.TypicalDay;
import view.Window;

public class DeleteState extends DefaultState {
	@Override
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph) {
		deliveryRound.deleteDelivery(delivery, graph);
		for (TimeWindow tm : typicalDay.getTimeWindows()) {
			tm.deleteDelivery(delivery);
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
