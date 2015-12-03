package model;

import java.util.ArrayList;

/**
 * Path class
 * Represents a path between two deliveries points (departure & arrival). A path is a list of sections
 */
public class Path {

	private ArrayList<Section> sections;
	private Delivery arrival;
	private Delivery departure;
	private double duration;

	/**
	 * Path constructor
	 * 
	 * @param arrival second delivery linked by the path
	 * @param departure first delivery linked by the path
	 * @param sections ordinated list of sections constituting the path
	 * @param duration time between departure and arrival
	 */
	public Path(Delivery departure, Delivery arrival, ArrayList<Section> sections, double duration) {
		this.sections = sections;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
	}

	/**
	 * Return a boolean indicating if the delivery will be delayed
	 * 
	 * @return boolean indicating if the delivery will be delayed
	 */
	public boolean isLate() {
		int time = getArrival().getTime();
		return (time > getArrival().getTimeWindow().getEnd());
	}

	/**
	 * Return the list of sections constituting the path
	 * 
	 * @return sections list
	 */
	public ArrayList<Section> getSections() {
		return this.sections;
	}

	/**
	 * Return the departure of the path
	 * 
	 * @return departure
	 */
	public Delivery getDeparture() {
		return this.departure;
	}

	/**
	 * Return arrival of the path
	 * 
	 * @return arrival
	 */
	public Delivery getArrival() {
		return arrival;
	}

	/**
	 * Change sections
	 * 
	 * @param sections new list of sections
	 */
	public void SetSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	/**
	 * Change arrival
	 * 
	 * @param arrival arrival to set
	 */
	public void setArrival(Delivery arrival) {
		this.arrival = arrival;
	}

	/**
	 * Change departure
	 * 
	 * @param departure departure to set
	 */
	public void setDeparture(Delivery departure) {
		this.departure = departure;
	}

	/**
	 * Set a new duration
	 * 
	 * @return duration duration to set
	 */
	public double getDuration() {
		return duration;
	}
}