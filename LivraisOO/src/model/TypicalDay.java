package model;

import java.util.ArrayList;

public class TypicalDay {

	private ArrayList<TimeWindow> timeWindows = new ArrayList<TimeWindow>();

	public TypicalDay() {
		super();
	}

	/**
	 * Add a time window to the list of time windows
	 * 
	 * @param tm
	 */
	public void addTimeWindow(TimeWindow tm) {
		timeWindows.add(tm);
	}

	/**
	 * Return TimeWindow by id
	 * 
	 * @param id
	 * @return
	 */
	public TimeWindow getById(int id) {
		return timeWindows.get(id);
	}

	/**
	 * Return the list of time windows
	 * 
	 * @return
	 */
	public ArrayList<TimeWindow> getTimeWindows() {
		return timeWindows;
	}
}
