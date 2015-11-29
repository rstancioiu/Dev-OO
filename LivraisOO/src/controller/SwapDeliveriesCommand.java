package controller;

import graph.Graph;
import model.Delivery;
import model.TimeWindow;
import model.DeliveryRound;

/**
 * Command to swap two deliveries in a time window and a delivery round
 * extending AstractCommand abstract class
 * @author Heptaswagnome
 *
 */
public class SwapDeliveriesCommand extends AbstractCommand {

	protected Delivery delivery2;
	
	/**
	 * Constructor of SwapDeliveriesCommand
	 * @param tw
	 * @param d
	 * @param d2
	 * @param dr
	 */
	public SwapDeliveriesCommand(TimeWindow tw, Delivery d, Delivery d2, DeliveryRound dr, Graph g){
		super(tw, d, dr, g);
		delivery2 = d2;
	}
	
	/**
	 * Swap the deliveries in the time window and delivery round
	 */
	@Override
	public void doCmd() {
		timeWindow.swapDeliveries(delivery, delivery2);
		//TODO : faire également dans DeliveryRound
	}

	/**
	 * Reversed command, which swap the deliveries in the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		timeWindow.swapDeliveries(delivery, delivery2);
		//TODO : faire également dans DeliveryRound
	}

}
