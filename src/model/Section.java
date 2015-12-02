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
	 * @return the id of the arrival node.
	 */
	public int getArrival() {
		return arrival;
	}

	/**
	 * @return the street name.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the id of the departure node.
	 */
	public int getDeparture() {
		return departure;
	}

	/**
	 * @return the driving speed.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return the length of the street.
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