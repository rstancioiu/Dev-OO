package model;

import java.util.ArrayList;

/**
 * Represents a path between two deliveries points (departure & arrival) A path
 * is a list of sections
 */
public class Path {

	private ArrayList<Section> sections;
	private Delivery arrival;
	private Delivery departure;
	private double duration;

	/**
	 * Path constructor
	 * 
	 * @param arrival
	 * @param departure
	 * @param sections
	 * @param duration
	 */
	public Path(Delivery departure, Delivery arrival, ArrayList<Section> sections, double duration) {
		this.sections = sections;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
	}

	public boolean isLate() {
		int time = getArrival().getTime();
		return (time > getArrival().getTimeWindow().getEnd());
	}

	/**
	 * @return sections list
	 */
	public ArrayList<Section> getSections() {
		return this.sections;
	}

	/**
	 * @return departure
	 */
	public Delivery getDeparture() {
		return this.departure;
	}

	/**
	 * @return arrival
	 */
	public Delivery getArrival() {
		return arrival;
	}

	/**
	 * Change sections
	 * 
	 * @param sections
	 */
	public void SetSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	/**
	 * Change arrival
	 * 
	 * @param arrival
	 */
	public void setArrival(Delivery arrival) {
		this.arrival = arrival;
	}

	/**
	 * Change departure
	 * 
	 * @param departure
	 */
	public void setDeparture(Delivery departure) {
		this.departure = departure;
	}

	/**
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}
}