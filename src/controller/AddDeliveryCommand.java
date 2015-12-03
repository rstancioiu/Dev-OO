package controller;

import graph.Graph;
import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;
import model.DeliveryRound;

/**
 * Command that adds a delivery to a delivery round
 * Extends AbstractCommand abstract class 
 * Contains a typical day and a second delivery
 */
public class AddDeliveryCommand extends AbstractCommand {
	private TypicalDay typicalDay;
	private Delivery newDelivery;

	/**
	 * Constructor of an addDeliveryCommand
	 * @param delivery delivery followed by the new delivery
	 * @param deliveryRound the delivery will be added to this delivery round
	 * @param graph the delivery will be added to this graph
	 * @param newDelivery the new delivery
	 * @param typicalDay the delivery will be added to this typical day
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
	 * Reversed command, remove the delivery from the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		System.out.println("Trying to remove " + newDelivery.getAddress());
		newDelivery.getTimeWindow().deleteDelivery(newDelivery);
		deliveryRound.deleteDelivery(newDelivery, graph);
	}
}
