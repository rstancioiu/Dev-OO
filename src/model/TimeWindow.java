package model;

import java.util.ArrayList;

public class TimeWindow {

	private int start;
	private int end;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

	/**
	 * TimeWindow is determined by a starting hour and an ending hour
	 * 
	 * @param start
	 * @param end
	 */
	public TimeWindow(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Add a delivery to the time window
	 * 
	 * @param d
	 */
	public void addDelivery(Delivery d) {
		deliveries.add(d);
	}

	/**
	 * Insert a new delivery after another delivery
	 * 
	 * @param before
	 * @param d
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
	 * @param d
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
	 * @param d1
	 * @param d2
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
	 * @param d
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
	 * Insert a delivery d at a given position pos
	 * 
	 * @param d
	 * @param pos
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
	 * @return the starting time of the time window
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the ending time of the time window
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @return the list of deliveries
	 */
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
}
