package controller;

import graph.Graph;
import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;
import model.DeliveryRound;
import model.Node;

/**
 * Command to add a delivery in a time window and a delivery round
 * extending AbstractCommand abstract class
 *
 */
public class AddDeliveryCommand extends AbstractCommand {
	private Node node;
	private TypicalDay typicalDay;

	public AddDeliveryCommand(Delivery delivery, DeliveryRound deliveryRound, Graph graph, Node node, TypicalDay typicalDay){
		super(delivery, deliveryRound, graph);
		this.node = node;
		this.typicalDay = typicalDay;
	}
	
	/**
	 * Add the delivery to the time window and delivery round
	 */
	@Override
	public void doCmd() {
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
	}

	/**
	 * Reversed command, remove the delivery from the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		delivery.getTimeWindow().deleteDelivery(delivery);
		deliveryRound.deleteDelivery(delivery, graph);
	}
}
