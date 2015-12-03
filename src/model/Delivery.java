package model;

/**
 * Delivery class, a delivery is represented by an id, a client, the address of a node.
 * It's included in a time window and has a delivery hour (time)
 */
public class Delivery {

	private int id;
	private int client;
	private int address;
	private TimeWindow timeWindow;
	private int time;

	/**
	 * Constructor of delivery
	 * 
	 * @param id id of delivery
	 * @param client client waiting for delivery
	 * @param address node where the client waits
	 */
	public Delivery(int id, int client, int address, TimeWindow timeWindow) {
		this.id = id;
		this.client = client;
		this.address = address;
		this.setTimeWindow(timeWindow);
		this.time = -1;
	}

	/**
	 * Return the id of a delivery
	 * 
	 * @return id of the delivery
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return the client of a delivery
	 * 
	 * @return the client of the delivery
	 */
	public int getClient() {
		return client;
	}

	/**
	 * Return the address of a delivery
	 * 
	 * @return the address, which is the id of a node
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * Return the time window containing the delivery
	 * 
	 * @return time window of the delivery
	 */
	public TimeWindow getTimeWindow() {
		return timeWindow;
	}

	/**
	 * Replace the timeWindow
	 * 
	 * @param timeWindow new time window
	 */
	public void setTimeWindow(TimeWindow timeWindow) {
		this.timeWindow = timeWindow;
	}

	/**
	 * Return the delivery hour
	 * 
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Replace the delivery hour
	 * 
	 * @param time new hour of the delivery
	 */
	public void setTime(int time) {
		this.time = time;
	}
}