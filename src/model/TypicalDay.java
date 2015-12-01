package model;

import java.util.ArrayList;

public class TypicalDay {

	private ArrayList<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
	private int nbDeliveries;
	private int wareHouse;

	public TypicalDay() {
		nbDeliveries = 0;
		wareHouse = -1;
	}

	/**
	 * Add a time window to the list of time windows
	 * 
	 * @param tm
	 */
	public void addTimeWindow(TimeWindow tm) {
		timeWindows.add(tm);
		nbDeliveries += tm.getDeliveries().size();
	}

	/**
	 * @param id
	 * @return TimeWindow by id
	 */
	public TimeWindow getById(int id) {
		return timeWindows.get(id);
	}

	/**
	 * @return the list of time windows
	 */
	public ArrayList<TimeWindow> getTimeWindows() {
		return timeWindows;
	}

	/**
	 * @return number of deliveries
	 */
	public int getNbDeliveries() {
		return nbDeliveries;
	}

	/**
	 * @return wareHouse
	 */
	public int getWareHouse() {
		return wareHouse;
	}

	/**
	 * Change wareHouse
	 * 
	 * @param wareHouse
	 */
	public void setWareHouse(int wareHouse) {
		this.wareHouse = wareHouse;
	}
	
	/**
	 * Clear the typicalDay
	 */
	public void clear(){
		timeWindows.clear();
	}
}
