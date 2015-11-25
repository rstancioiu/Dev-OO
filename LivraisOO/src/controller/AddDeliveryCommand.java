package controller;

import model.Delivery;
import model.TimeWindow;
import model.DeliveryRound;

/**
 * Command to add a delivery in a time window and a delivery round
 * extending AbstractCommand abstract class
 * @author Heptaswagnome
 *
 */
public class AddDeliveryCommand extends AbstractCommand {

	public AddDeliveryCommand(TimeWindow tw, Delivery d, DeliveryRound dr){
		super(tw, d, dr);
	}
	
	/**
	 * Add the delivery to the time window and delivery round
	 */
	@Override
	public void doCmd() {
		timeWindow.addDelivery(delivery);
		//TODO : get clientID and address
		//Delivery previousDelivery = window.getPreviousDelivery();
		//int newID = deliveryRound.getNewID();
		//Delivery newDelivery = new Delivery(newID, clientID, address);
		//deliveryRound.addDelivery(newDelivery, previousDelivery);
	}

	/**
	 * Reversed command, remove the delivery from the time window and delivery round
	 */
	@Override
	public void undoCmd() {
		timeWindow.deleteDelivery(delivery);
		deliveryRound.deleteDelivery(delivery);
	}
}
