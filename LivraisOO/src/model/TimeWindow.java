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
	
	public void insertDelivery(Delivery before, Delivery d){
		ArrayList<Delivery> newDeliveries = new ArrayList<Delivery>();
		for(int i=0;i<deliveries.size();++i)
		{
			newDeliveries.add(deliveries.get(i));
			if(deliveries.get(i).equals(before)){
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
			if (deliveries.get(i).equals(d)) {
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
	 * Return the starting time of the time window
	 * 
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Return the ending time of the time window
	 * 
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Return the list of deliveries
	 * 
	 * @return
	 */
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}

}
