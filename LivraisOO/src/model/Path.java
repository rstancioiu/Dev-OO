package model;

import java.util.ArrayList;

/**
 * Represents a path between two deliveries points (departure & arrival)
 * A path is a list of sections
 * @author Heptaswagnome
 *
 */
public class Path {

	private ArrayList<Section> sections;
	private Delivery arrival;
	private Delivery departure;
	
	/**
	 * Path constructor
	 * @param a Arrival
	 * @param d Departure
	 * @param s Sections composing the path
	 */
	public Path(Delivery a, Delivery d, ArrayList<Section> s){
		this.sections = s;
		this.departure = d;
		this.arrival = a;
	}
	
	/**
	 * Sections getter
	 * @return Sections list
	 */
	public ArrayList<Section> getSections(){
		return this.sections;
	}
	
	/**
	 * Departure getter
	 * @return Departure
	 */
	public Delivery getDeparture(){
		return this.departure;
	}
	
	/**
	 * Arrival getter
	 * @return Arrival
	 */
	public Delivery getArrival(){
		return this.arrival;
	}
	
	/**
	 * Sections setter
	 * @param s New sections
	 */
	public void SetSections(ArrayList<Section> s){
		this.sections = s;
	}
	
	/**
	 * Arrival setter
	 * @param a New arrival
	 */
	public void setArrival(Delivery a){
		this.arrival = a;
	}
	
	/**
	 * Departure setter
	 * @param d New departure
	 */
	public void setDeparture(Delivery d){
		this.departure = d;
	}
}