package controller;

import graph.Graph;
import model.TimeWindow;
import model.TypicalDay;
import model.Delivery;
import model.DeliveryRound;
import model.Node;

/**
 * Deletes a delivery from a time window and a delivery round
 */
public class DeleteDeliveryCommand extends AbstractCommand {
	private TypicalDay typicalDay;
	private Delivery deliveryPrev;
	private Node node;
	
	public DeleteDeliveryCommand(Delivery d, DeliveryRound dr, Graph g, TypicalDay typicalDay){
		super(d, dr, g);
		this.typicalDay = typicalDay;
	}

	/**
	 * Remove the delivery from the time window and delivery round
	 */
	@Override
	public void doCmd() {
		node = new Node(delivery.getAddress(),0,0);
		delivery.getTimeWindow().deleteDelivery(delivery);
		deliveryPrev = deliveryRound.deleteDelivery(delivery, graph);
	}

	/**
	 * Reversed command, add the delivery to the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		Delivery newDelivery = new Delivery(0, 0, node.getId(), new TimeWindow(0, 24));
		deliveryRound.addDelivery(deliveryPrev, newDelivery, graph, new TimeWindow(-1, -1));
		
		if(deliveryPrev.getAddress() == typicalDay.getWareHouse()) {
			Delivery firstReal = deliveryRound.getPaths().get(1).getArrival();
			firstReal.getTimeWindow().insertDelivery(firstReal, newDelivery);
			newDelivery.setTimeWindow(firstReal.getTimeWindow());
		} else {
			deliveryPrev.getTimeWindow().insertDelivery(deliveryPrev, newDelivery);
			newDelivery.setTimeWindow(deliveryPrev.getTimeWindow());
		}	
	}
}
