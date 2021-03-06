package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.TypicalDay;
import view.Window;

/**
 * State set when the user clicks on DeleteDeliveryButton
 * Extends DefaultState abstract class
 */
public class DeleteState extends DefaultState {

	@Override
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph,
			CommandsList cmdList) {
		delivery.getTimeWindow().deleteDelivery(delivery);
		Delivery deliveryPrev = deliveryRound.deleteDelivery(delivery, graph);
		cmdList.add(new DeleteDeliveryCommand(delivery, deliveryRound, graph, typicalDay, deliveryPrev));
		Controller.setCurrentState(Controller.deliveryState);
	}

	@Override
	public void cancel() {
		Controller.setCurrentState(Controller.deliveryState);
	}

	@Override
	public void updateVue(Window window) {
		window.showButtons();
		window.setMessage("Please select a delivery node to remove");
	}
}
