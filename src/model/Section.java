package model;

public class Section {
	private String street;
	private int departure;
	private int arrival;
	private double speed;
	private double length;

	/**
	 * A section is determined by the street,the departure node, the arrival
	 * node, the speed, and the length
	 * 
	 * @param street
	 * @param departure
	 * @param arrival
	 * @param speed
	 * @param length
	 */
	public Section(String street, int departure, int arrival, double speed, double length) {
		this.street = street;
		this.departure = departure;
		this.arrival = arrival;
		this.speed = speed;
		this.length = length;
	}

	/**
	 * Returns the id of the arrival node.
	 * 
	 * @return
	 */
	public int getArrival() {
		return arrival;
	}

	/**
	 * Returns the street name.
	 * 
	 * @return
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Returns the id of the departure node.
	 * 
	 * @return
	 */
	public int getDeparture() {
		return departure;
	}

	/**
	 * Returns the driving speed.
	 * 
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Returns the length of the street.
	 * 
	 * @return
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @return time
	 */
	public double getTime() {
		return length / speed;
	}
}