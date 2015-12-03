package model;

import java.util.ArrayList;

/**
 * TypicalDay class
 * A typical day is basically a working day, it contains several time window launched from a warehouse
 * It also contains the list of planned deliveries
 */
public class TypicalDay {

	private ArrayList<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
	private int nbDeliveries;
	private int wareHouse;

	/**
	 * Constructor of a typical day
	 */
	public TypicalDay() {
		nbDeliveries = 0;
		wareHouse = -1;
	}

	/**
	 * Add a time window to the list of time windows
	 * 
	 * @param tm new time window
	 */
	public void addTimeWindow(TimeWindow tm) {
		timeWindows.add(tm);
		nbDeliveries += tm.getDeliveries().size();
	}

	/**
	 * Get a time window from an id
	 * 
	 * @param id wanted id
	 * @return time window from an id
	 */
	public TimeWindow getById(int id) {
		return timeWindows.get(id);
	}

	/**
	 * Get every time windows
	 * 
	 * @return the list of time windows
	 */
	public ArrayList<TimeWindow> getTimeWindows() {
		return timeWindows;
	}

	/**
	 * Get number of deliveries
	 * 
	 * @return number of deliveries
	 */
	public int getNbDeliveries() {
		return nbDeliveries;
	}

	/**
	 * Get warehouse address
	 * 
	 * @return wareHouse
	 */
	public int getWareHouse() {
		return wareHouse;
	}

	/**
	 * Change wareHouse
	 * 
	 * @param wareHouse new warehouse
	 */
	public void setWareHouse(int wareHouse) {
		this.wareHouse = wareHouse;
	}

	/**
	 * Clear the typicalDay
	 */
	public void clear() {
		timeWindows.clear();
	}
}
