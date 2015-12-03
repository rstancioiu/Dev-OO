package model;

/**
 * Section class
 * A section is determined by a street name, the departure node, the arrival node, the speed and the length
 */
public class Section {
	private String street;
	private int departure;
	private int arrival;
	private double speed;
	private double length;

	/**
	 * Constructor of section
	 * 
	 * @param street name of the street
	 * @param departure id of departure node
	 * @param arrival id of arrival node
	 * @param speed average speed of the section
	 * @param length length of the section
	 */
	public Section(String street, int departure, int arrival, double speed, double length) {
		this.street = street;
		this.departure = departure;
		this.arrival = arrival;
		this.speed = speed;
		this.length = length;
	}

	/**
	 * Get the id of the arrival node
	 * 
	 * @return the id of the arrival node.
	 */
	public int getArrival() {
		return arrival;
	}

	/**
	 * Get the street name
	 * 
	 * @return the street name.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Get the id of the departure node
	 * 
	 * @return the id of the departure node.
	 */
	public int getDeparture() {
		return departure;
	}

	/**
	 * Get the average speed of the section
	 * 
	 * @return the driving speed.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Get the length of the section
	 * 
	 * @return the length of the street.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Get the average time to browse the section
	 * 
	 * @return time (length divided by speed)
	 */
	public double getTime() {
		return length / speed;
	}
}