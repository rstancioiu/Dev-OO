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
	private int duration;
	private ArrayList<Path> paths;

	/**
	 * Constructor of DeliveryRound
	 */
	public DeliveryRound() {
		paths = new ArrayList<Path>();
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
				Path path1 = graph.generatePath(previous, newDelivery);
				Path path2 = graph.generatePath(newDelivery, paths.get(i + 1).getDeparture());
				newPaths.add(path1);
				newPaths.add(path2);
			} else {
				newPaths.add(paths.get(i));
			}
		}
		setPaths(newPaths);
	}

	/**
	 * Deletes a delivery
	 * 
	 * @param d
	 */
	public void deleteDelivery(Delivery d, Graph graph) {
		ArrayList<Path> newPaths = new ArrayList<Path>();
		for (int i = 0; i < paths.size()-1; i++) {
			if (paths.get(i).getArrival().equals(d)) {
			 	Path path = graph.generatePath(paths.get(i).getDeparture(), paths.get(i+1).getArrival());
			 	newPaths.add(path);
			}
			else
			{
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
		ArrayList<Path> newPaths = new ArrayList<Path>();
		int i=0,j=0;
		for(int k=0;k<paths.size();++k){
			if(paths.get(k).getArrival().equals(first)){
				i=k;
			}
			if(paths.get(k).getArrival().equals(second)){
				j=k;
			}
		}
		if(i!=j){
			deleteDelivery(paths.get(i).getArrival(),graph);
			addDelivery(paths.get(i).getDeparture(),second,graph);
			deleteDelivery(paths.get(j).getArrival(),graph);
			addDelivery(paths.get(j).getDeparture(),first,graph);
		}
	}

	/**
	 * Paths setter
	 * 
	 * @param p
	 *            New paths
	 */
	public void setPaths(ArrayList<Path> p) {
		paths = p;
	}

	/**
	 * Start time setter
	 * 
	 * @param s
	 *            New start time
	 */
	public void setStart(String s) {
		start = s;
	}

	/**
	 * End time setter
	 * 
	 * @param e
	 *            New end time
	 */
	public void setEnd(String e) {
		end = e;
	}

	/**
	 * Duration setter
	 * 
	 * @param d
	 *            New duration
	 */
	public void setDuration(int d) {
		duration = d;
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
	 * start getter
	 * @return start
	 */
	public String getStart(){
		return this.start;
	}
	/**
	 * end getter
	 * @return end
	 */
	private String getEnd(){
		return this.end;
	}
	/**
	 * duration getter
	 * @return duration
	 */
	private int getDuration(){
		return this.duration;
	}
	/**
	 * paths getter
	 * @return paths
	 */
	private ArrayList<Path> getPaths(){
		return this.getPaths();
	}
}