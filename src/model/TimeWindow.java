package model;

import java.util.ArrayList;

/**
 * TimeWindow class
 * A time window  is determined by a starting hour, an ending hour, and a list of deliveries
 */
public class TimeWindow {

	private int start;
	private int end;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

	/**
	 * Constructor of TimeWindow
	 * 
	 * @param start starting hour
	 * @param end ending hour
	 */
	public TimeWindow(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Add a delivery to the time window
	 * 
	 * @param d new delivery
	 */
	public void addDelivery(Delivery d) {
		deliveries.add(d);
	}

	/**
	 * Insert a new delivery after another delivery
	 * 
	 * @param before delivery followed by new delivery
	 * @param d new delivery
	 */
	public void insertDelivery(Delivery before, Delivery d) {
		ArrayList<Delivery> newDeliveries = new ArrayList<Delivery>();
		for (int i = 0; i < deliveries.size(); ++i) {
			newDeliveries.add(deliveries.get(i));
			if (deliveries.get(i).getAddress() == before.getAddress()) {
				newDeliveries.add(d);
			}
		}
		deliveries = newDeliveries;
	}

	/**
	 * Delete a delivery from the list of deliveries
	 * 
	 * @param d delivery to delete
	 */
	public void deleteDelivery(Delivery d) {
		for (int i = 0; i < deliveries.size(); ++i) {
			if (deliveries.get(i).getAddress() == d.getAddress()) {
				deliveries.remove(i);
				break;
			}
		}
	}

	/**
	 * Swap two deliveries
	 * 
	 * @param d1 first delivery
	 * @param d2 second delivery
	 */
	public void swapDeliveries(Delivery d1, Delivery d2) {
		int i, j;
		for (i = 0; i < deliveries.size(); ++i)
			if (deliveries.get(i).equals(d1))
				break;
		for (j = 0; j < deliveries.size(); ++j)
			if (deliveries.get(j).equals(d2))
				break;
		if (i != deliveries.size() && j != deliveries.size()) {
			Delivery tmp = deliveries.get(i);
			deliveries.set(i, deliveries.get(j));
			deliveries.set(j, tmp);
		}
	}

	/**
	 * Get the position of a delivery in the time window
	 * 
	 * @param d delivery
	 * @return return the position of the delivery in the time window
	 */
	public int getDeliveryPos(Delivery d) {
		for (int i = 0; i < deliveries.size(); ++i) {
			if (d.equals(deliveries.get(i)))
				return i;
		}
		return deliveries.size();
	}

	/**
	 * Insert a delivery d at a given position
	 * 
	 * @param d new delivery
	 * @param pos id of the new delivery node
	 */
	public void insertAt(Delivery d, int pos) {
		ArrayList<Delivery> newDeliveries = new ArrayList<Delivery>();
		for (int i = 0; i < deliveries.size(); ++i) {
			if (pos == i) {
				newDeliveries.add(d);
			}
			newDeliveries.add(deliveries.get(i));
		}
		if (pos == deliveries.size()) {
			newDeliveries.add(d);
		}
		deliveries = newDeliveries;
	}

	/**
	 * Get the starting hour of the time window
	 * 
	 * @return the starting time of the time window
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Get the ending hour of a time window
	 * 
	 * @return the ending time of the time window
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Get the deliveries list of the time window
	 * 
	 * @return the list of deliveries
	 */
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
}
