package model;

public class Delivery {

	private int id;
	private int client;
	private int address;
	private TimeWindow timeWindow;
	private int time;

	/**
	 * A delivery is contained in a TimeWindow
	 * 
	 * @param id
	 * @param client
	 * @param address
	 */
	public Delivery(int id, int client, int address, TimeWindow timeWindow) {
		this.id = id;
		this.client = client;
		this.address = address;
		this.setTimeWindow(timeWindow);
		this.time = -1;
	}

	/**
	 * Return id of the delivery
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return the client of the delivery
	 * 
	 * @return
	 */
	public int getClient() {
		return client;
	}

	/**
	 * Return the address of the delivery that corresponds to the id of the node
	 * 
	 * @return
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @return time window of the delivery
	 */
	public TimeWindow getTimeWindow() {
		return timeWindow;
	}

	/**
	 * Replaces the time window to a new one
	 * 
	 * @param timeWindow
	 */
	public void setTimeWindow(TimeWindow timeWindow) {
		this.timeWindow = timeWindow;
	}

	/**
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Replaces the time of the delivery
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
}