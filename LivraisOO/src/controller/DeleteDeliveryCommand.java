package controller;

import graph.Graph;
import model.TimeWindow;
import model.Delivery;
import model.DeliveryRound;

/**
 * Deletes a delivery from a time window and a delivery round
 * @author Heptaswagnome
 *
 */
public class DeleteDeliveryCommand extends AbstractCommand {
	
	public DeleteDeliveryCommand(TimeWindow tw, Delivery d, DeliveryRound dr, Graph g){
		super(tw, d, dr, g);
	}
	
	/**
	 * Remove the delivery from the time window and delivery round
	 */
	@Override
	public void doCmd() {
		//timeWindow.deleteDelivery(delivery);
		deliveryRound.deleteDelivery(delivery, graph);
	}

	/**
	 * Reversed command, add the delivery to the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		timeWindow.addDelivery(delivery);
		//TODO : get clientID and address
		//Delivery previousDelivery = window.getPreviousDelivery();
		//int newID = deliveryRound.getNewID();
		//Delivery newDelivery = new Delivery(newID, clientID, address);
		//deliveryRound.addDelivery(newDelivery, previousDelivery);
	}

}
