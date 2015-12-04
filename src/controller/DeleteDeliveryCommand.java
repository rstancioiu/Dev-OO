package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.TimeWindow;
import model.TypicalDay;

/**
 * Command that deletes a delivery from a delivery round
 * Extends AbstractCommand abstract class
 * Contains a typical day and a second delivery
 */
public class DeleteDeliveryCommand extends AbstractCommand {
	private TypicalDay typicalDay;
	private Delivery deliveryPrev;

	/**
	 * Constructor of a DeleteDeliveryCommand
	 * @param d delivery to delete
	 * @param dr the delivery will be removed from this delivery round
	 * @param g the delivery will be removed from this graph
	 * @param typicalDay the delivery will be removed from this typical day
	 * @param deliveryPrev the delivery to delete follows this delivery
	 */
	public DeleteDeliveryCommand(Delivery d, DeliveryRound dr, Graph g, TypicalDay typicalDay, Delivery deliveryPrev) {
		super(d, dr, g);
		this.typicalDay = typicalDay;
		this.deliveryPrev = deliveryPrev;
	}

	/**
	 * Remove the delivery from the delivery round
	 */
	@Override
	public void doCmd() {
		delivery.getTimeWindow().deleteDelivery(delivery);
		deliveryPrev = deliveryRound.deleteDelivery(delivery, graph);
	}

	/**
	 * Reversed command, add the delivery to the delivery round
	 */
	@Override
	public void undoCmd() {
		deliveryRound.addDelivery(deliveryPrev, delivery, graph, new TimeWindow(-1, -1));

		if (deliveryPrev.getAddress() == typicalDay.getWareHouse()) {
			Delivery firstReal = deliveryRound.getPaths().get(1).getArrival();
			firstReal.getTimeWindow().insertDelivery(firstReal, delivery);
			delivery.setTimeWindow(firstReal.getTimeWindow());
		} else {
			deliveryPrev.getTimeWindow().insertDelivery(deliveryPrev, delivery);
			delivery.setTimeWindow(deliveryPrev.getTimeWindow());
		}
	}
}
