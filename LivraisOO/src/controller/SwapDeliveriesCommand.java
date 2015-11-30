package controller;

import graph.Graph;
import model.Delivery;
import model.TimeWindow;
import model.DeliveryRound;

/**
 * Command to swap two deliveries in a time window and a delivery round
 * extending AstractCommand abstract class
 *
 */
public class SwapDeliveriesCommand extends AbstractCommand {
	private Delivery start;
	private Delivery end;

	/**
	 * 
	 * @param tw
	 * @param start
	 * @param end
	 * @param dr
	 * @param g
	 */
	public SwapDeliveriesCommand(Delivery start, Delivery end, DeliveryRound dr, Graph g){
		super(start, dr, g);
		this.end = end;
		this.start=start;
	}

	/**
	 * Swap the deliveries in the time window and delivery round
	 */
	@Override
	public void doCmd() {
		if(start.getTimeWindow().equals(end.getTimeWindow())){
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
	 * Reversed command, which swap the deliveries in the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		if(start.getTimeWindow().equals(end.getTimeWindow())){
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

}
