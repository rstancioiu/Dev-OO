package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.TypicalDay;
import view.Window;

public class DeleteState extends DefaultState {
	@Override
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph, CommandsList cmdList) {
		cmdList.add(new DeleteDeliveryCommand(delivery, deliveryRound, graph, typicalDay));
		delivery.getTimeWindow().deleteDelivery(delivery);
		deliveryRound.deleteDelivery(delivery, graph);
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void updateVue(Window window){
		window.showButtons();
	}
}
