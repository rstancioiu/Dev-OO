package model;

import java.util.ArrayList;

import graph.Graph;


/**
 * Represents a delivery round, with a list of paths, a start hour, an end hour
 * and a duration
 *
 */
public class DeliveryRound {

	private String start;
	private String end;
	private double duration;
	private ArrayList<Path> paths;
	private TypicalDay typicalDay;

	/**
	 * Constructor of DeliveryRound
	 */
	public DeliveryRound() {
		paths = new ArrayList<Path>();
		duration = 0;
	}

	/**
	 * Adds a delivery after the "previous" delivery
	 * 
	 * @param previous
	 * @param newDelivery
	 */
	public void addDelivery(Delivery previous, Delivery newDelivery, Graph graph) {
		ArrayList<Path> newPaths = new ArrayList<Path>();
		for (int i = 0; i < paths.size(); i++) {
			if (paths.get(i).getDeparture().equals(previous)) {
				duration -= paths.get(i).getDuration();
				Path path1 = graph.generatePath(previous, newDelivery);
				Path path2 = graph.generatePath(newDelivery, paths.get(i).getArrival());
				newPaths.add(path1);
				newPaths.add(path2);
				for(int k=0;k<typicalDay.getTimeWindows().size();++k){
					typicalDay.getTimeWindows().get(k).insertDelivery(paths.get(i).getDeparture(),newDelivery);
				}
				duration += path1.getDuration() + path2.getDuration();
			} else {
				newPaths.add(paths.get(i));
			}
		}
		setPaths(newPaths);
	}

	/**
	 * Deletes a delivery
	 * 
	 * @param delivery
	 * @param graph
	 */
	public void deleteDelivery(Delivery delivery, Graph graph) {
		ArrayList<Path> newPaths = new ArrayList<Path>();
		for (int i = 0; i < paths.size() - 1; i++) {
			if (paths.get(i).getArrival().equals(delivery)) {
				Path path = graph.generatePath(paths.get(i).getDeparture(), paths.get(i + 1).getArrival());
				for(int k=0;k<typicalDay.getTimeWindows().size();++k){
					typicalDay.getTimeWindows().get(k).deleteDelivery(paths.get(i).getArrival());
				}
				newPaths.add(path);
			} else {
				newPaths.add(paths.get(i));
			}
		}
		setPaths(newPaths);
	}

	/**
	 * Swaps two deliveries
	 * 
	 * @param first
	 * @param second
	 */
	public void swapDeliveries(Delivery first, Delivery second, Graph graph) {
		int i = 0, j = 0;
		System.out.println("swap Done");
		for (int k = 0; k < paths.size(); ++k) {
			if (paths.get(k).getArrival().equals(first)) {
				i = k;
			}
			if (paths.get(k).getArrival().equals(second)) {
				j = k;
			}
		}
		if (i != j) {
			deleteDelivery(paths.get(i).getArrival(), graph);
			for(int k=0;k<typicalDay.getTimeWindows().size();++k){
				typicalDay.getTimeWindows().get(k).deleteDelivery(paths.get(i).getArrival());
			}
			addDelivery(paths.get(i).getDeparture(), second, graph);
			for(int k=0;k<typicalDay.getTimeWindows().size();++k){
				typicalDay.getTimeWindows().get(k).insertDelivery(paths.get(i).getDeparture(),second);
			}
			deleteDelivery(paths.get(j).getArrival(), graph);
			for(int k=0;k<typicalDay.getTimeWindows().size();++k){
				typicalDay.getTimeWindows().get(k).deleteDelivery(paths.get(j).getArrival());
			}
			addDelivery(paths.get(j).getDeparture(), first, graph);
			for(int k=0;k<typicalDay.getTimeWindows().size();++k){
				typicalDay.getTimeWindows().get(k).insertDelivery(paths.get(j).getDeparture(),first);
			}
			System.out.println("swap Done");
		}
	}

	/**
	 * @param path
	 */
	public void setPaths(ArrayList<Path> paths) {
		duration = 0;
		for (int i = 0; i < paths.size(); ++i) {
			duration += paths.get(i).getDuration();
		}
		this.paths = paths;
	}
	
	public void setTypicalDay(TypicalDay typicalDay){
		this.typicalDay=typicalDay;
	}
	

	/**
	 * Paths getter
	 */
	public ArrayList<Path> getPaths() {
		return paths;
	}

	/**
	 * @param start
	 */
	public void setStart(String s) {
		this.start = s;
	}

	/**
	 * @param end
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Returns the first unused ID for a delivery
	 * 
	 * @return
	 */
	public int getNewID() {

		ArrayList<Integer> IDs = new ArrayList<Integer>();
		for (Path p : paths) {
			IDs.add(p.getArrival().getId());
		}

		int ID = 1;
		while (IDs.indexOf(ID) != -1)
			ID++;

		return ID;
	}

	/**
	 * @return start
	 */
	public String getStart() {
		return this.start;
	}

	/**
	 * @return end
	 */
	public String getEnd() {
		return this.end;
	}

	/**
	 * @return duration
	 */
	public double getDuration() {
		return this.duration;
	}

	public TypicalDay getTypicalDay() {
		return typicalDay;
	}
	
	
}