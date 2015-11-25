package model;

import java.util.ArrayList;

public class TimeWindow {

	private String start;
	private String end;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

	public TimeWindow(String start, String end) {
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
	 * Return the starting hour of the time window
	 * 
	 * @return
	 */
	public String getStart() {
		return start;
	}

	/**
	 * Return the ending hour of the time window
	 * 
	 * @return
	 */
	public String getEnd() {
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
