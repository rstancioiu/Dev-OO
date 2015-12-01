package controller;

import graph.Graph;
import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;
import model.DeliveryRound;
import model.Node;

/**
 * Command to add a delivery in a time window and a delivery round extending
 * AbstractCommand abstract class
 *
 */
public class AddDeliveryCommand extends AbstractCommand {
	private TypicalDay typicalDay;
	private Delivery newDelivery;

	/**
	 * Constructor of an addition command
	 * 
	 * @param delivery
	 * @param deliveryRound
	 * @param graph
	 * @param newDelivery
	 * @param typicalDay
	 */
	public AddDeliveryCommand(Delivery delivery, DeliveryRound deliveryRound, Graph graph, Delivery newDelivery,
			TypicalDay typicalDay) {
		super(delivery, deliveryRound, graph);
		this.typicalDay = typicalDay;
		this.newDelivery = newDelivery;
	}

	/**
	 * Add the delivery to the time window and delivery round
	 */
	@Override
	public void doCmd() {
		deliveryRound.addDelivery(delivery, newDelivery, graph, new TimeWindow(-1, -1));

		if (delivery.getAddress() == typicalDay.getWareHouse()) {
			Delivery firstReal = deliveryRound.getPaths().get(1).getArrival();
			firstReal.getTimeWindow().insertDelivery(firstReal, newDelivery);
			newDelivery.setTimeWindow(firstReal.getTimeWindow());
		} else {
			delivery.getTimeWindow().insertDelivery(delivery, newDelivery);
			newDelivery.setTimeWindow(delivery.getTimeWindow());
		}
	}

	/**
	 * Reversed command, remove the delivery from the time window and delivery
	 * round
	 */
	@Override
	public void undoCmd() {
		System.out.println("Trying to remove " + newDelivery.getAddress());
		newDelivery.getTimeWindow().deleteDelivery(newDelivery);
		deliveryRound.deleteDelivery(newDelivery, graph);
	}
}
