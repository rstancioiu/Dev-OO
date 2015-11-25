package model;

public class Delivery {

	private int id;
	private int client;
	private int address;

	/**
	 * A delivery is contained in a TimeWindow
	 * 
	 * @param id
	 * @param client
	 * @param address
	 */
	public Delivery(int id, int client, int address) {
		this.id = id;
		this.client = client;
		this.address = address;
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
}
