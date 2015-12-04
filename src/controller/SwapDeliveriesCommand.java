package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.TimeWindow;

/**
 * Command that swaps two deliveries in a delivery round
 * Extends AbstractCommand abstract class
 * Contains two deliveries
 */
public class SwapDeliveriesCommand extends AbstractCommand {
	private Delivery start;
	private Delivery end;

	/**
	 * Constructor of swap deliveries command
	 * @param start first delivery to swap
	 * @param end second delivery to swap
	 * @param dr delivery round containing the deliveries
	 * @param g graph containing the deliveries
	 */
	public SwapDeliveriesCommand(Delivery start, Delivery end, DeliveryRound dr, Graph g) {
		super(start, dr, g);
		this.end = end;
		this.start = start;
	}

	/**
	 * Swap the deliveries in the delivery round
	 */
	@Override
	public void doCmd() {
		if (start.getTimeWindow().equals(end.getTimeWindow())) {
			start.getTimeWindow().swapDeliveries(start, end);
		} else {
			TimeWindow timeWindow1 = start.getTimeWindow();
			TimeWindow timeWindow2 = end.getTimeWindow();

			int pos1 = timeWindow1.getDeliveryPos(start);
			int pos2 = timeWindow2.getDeliveryPos(end);
			timeWindow1.getDeliveries().remove(pos1);
			timeWindow2.getDeliveries().remove(pos2);
			timeWindow1.insertAt(end, pos1);
			timeWindow2.insertAt(start, pos2);

			end.setTimeWindow(timeWindow1);
			System.out.println("Delivery " + end.getAddress() + " has now start " + timeWindow1.getStart());
			System.out.println("Delivery " + end.getAddress() + " has now start " + end.getTimeWindow().getStart());
			start.setTimeWindow(timeWindow2);
			System.out.println("Delivery " + start.getAddress() + " has now start " + timeWindow2.getStart());
		}
		deliveryRound.swapDeliveries(start, end, graph);
	}

	/**
	 * Reversed command, which swaps the deliveries in delivery round
	 */
	@Override
	public void undoCmd() {
		if (start.getTimeWindow().equals(end.getTimeWindow())) {
			start.getTimeWindow().swapDeliveries(start, end);
		} else {
			TimeWindow timeWindow1 = start.getTimeWindow();
			TimeWindow timeWindow2 = end.getTimeWindow();

			int pos1 = timeWindow1.getDeliveryPos(start);
			int pos2 = timeWindow2.getDeliveryPos(end);
			timeWindow1.getDeliveries().remove(pos1);
			timeWindow2.getDeliveries().remove(pos2);
			timeWindow1.addDelivery(end);
			timeWindow2.addDelivery(start);

			end.setTimeWindow(timeWindow1);
			System.out.println("Delivery " + end.getAddress() + " has now start " + timeWindow1.getStart());
			System.out.println("Delivery " + end.getAddress() + " has now start " + end.getTimeWindow().getStart());
			start.setTimeWindow(timeWindow2);
			System.out.println("Delivery " + start.getAddress() + " has now start " + timeWindow2.getStart());
		}
		deliveryRound.swapDeliveries(start, end, graph);
	}

}
