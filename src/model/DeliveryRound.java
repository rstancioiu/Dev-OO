package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import graph.Graph;

/**
 * DeliveryRound class
 * Represents a delivery round, with a list of paths, a start hour, an end hour
 * and a duration
 */
public class DeliveryRound {

	private int start;
	private int end;
	private double duration;
	private ArrayList<Path> paths;

	/**
	 * Constructor of DeliveryRound
	 */
	public DeliveryRound() {
		paths = new ArrayList<Path>();
		duration = 0;
	}

	/**
	 * Generate a formated hour from a number of seconds
	 * 
	 * @param seconds
	 * @return formated string
	 */
	private String formatHour(int seconds) {
		DecimalFormat formatter = new DecimalFormat("00");
		String result = formatter.format(seconds / 3600) + ":" + formatter.format(seconds % 3600 / 60) + ":"
				+ formatter.format(seconds % 60);
		return result;
	}

	/**
	 * Update delivery effective times
	 */
	public void updateTimes() {
		int STOPTIME = 600;
		Path first = paths.get(0);
		int firstTime = first.getArrival().getTimeWindow().getStart();
		System.out.println("First time : " + formatHour(firstTime));
		System.out.println("First path cost : " + first.getDuration());
		int departure = (int) (firstTime - first.getDuration() + 1);
		first.getDeparture().setTime(departure);
		start = departure;
		departure -= STOPTIME;
		for (Path p : paths) {
			departure += STOPTIME + p.getDuration();
			Delivery arrival = p.getArrival();
			arrival.setTime(departure);
			System.out.println("Found time for node " + arrival.getAddress() + " : " + formatHour(departure));
			TimeWindow currentWindow = arrival.getTimeWindow();
			// Wait until the window starts
			if (departure < currentWindow.getStart()) {
				int delta = currentWindow.getStart() - departure;
				departure += delta;
			}
		}
		end = departure;
		duration = end - start;
	}

	/**
	 * Adds a delivery after the "previous" delivery
	 * 
	 * @param previous
	 * @param newDelivery
	 */
	public void addDelivery(Delivery previous, Delivery newDelivery, Graph graph, TimeWindow tm) {
		ArrayList<Path> newPaths = new ArrayList<Path>();
		for (int i = 0; i < paths.size(); ++i) {
			System.out.print(paths.get(i).getDeparture().getAddress() + " ");
		}
		System.out.println(paths.get(paths.size() - 1).getArrival().getAddress());
		for (int i = 0; i < paths.size(); i++) {
			if (paths.get(i).getDeparture().equals(previous)) {
				Path path1 = graph.generatePath(paths.get(i).getDeparture(), newDelivery);

				if (tm.getStart() > 0 && i == 0) {
					// newDelivery.setTimeWindow(tm);
				} else {
					newDelivery.setTimeWindow(paths.get(i).getDeparture().getTimeWindow());
				}

				if (i == 0) {
					newDelivery.setTime(newDelivery.getTimeWindow().getStart());
				} else {
					newDelivery.setTime((int) (path1.getDeparture().getTime() + path1.getDuration()));
				}
				Path path2 = graph.generatePath(newDelivery, paths.get(i).getArrival());
				newPaths.add(path1);
				newPaths.add(path2);

			} else {
				newPaths.add(paths.get(i));
			}
		}
		setPaths(newPaths);
		for (int i = 0; i < paths.size(); ++i) {
			System.out.print(paths.get(i).getDeparture().getAddress() + " ");
		}
		System.out.println(paths.get(paths.size() - 1).getArrival().getAddress());
	}

	/**
	 * Deletes a delivery
	 * 
	 * @param delivery this delivery will be removed
	 * @param graph the delivery will also be removed from this graph
	 */
	public Delivery deleteDelivery(Delivery delivery, Graph graph) {
		ArrayList<Path> newPaths = new ArrayList<Path>();
		Delivery ret = null;
		System.out.println("Delete results : ");
		for (int i = 0; i < paths.size(); ++i) {
			System.out.print(paths.get(i).getDeparture().getAddress() + " ");
		}
		System.out.println(paths.get(paths.size() - 1).getArrival().getAddress());
		boolean found = false;
		for (int i = 0; i < paths.size(); i++) {
			if (paths.get(i).getArrival().getAddress() == delivery.getAddress() && !found) {
				Path path = graph.generatePath(paths.get(i).getDeparture(), paths.get(i + 1).getArrival());
				ret = paths.get(i).getDeparture();
				newPaths.add(path);
				found = true;
				++i;
			} else {
				newPaths.add(paths.get(i));
			}
		}
		setPaths(newPaths);
		for (int i = 0; i < paths.size(); ++i) {
			System.out.print(paths.get(i).getDeparture().getAddress() + " ");
		}
		System.out.println(paths.get(paths.size() - 1).getArrival().getAddress());
		return ret;
	}

	/**
	 * Swaps two deliveries
	 * 
	 * @param first delivery to swap
	 * @param second delivery to swap
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
		if (j > i) {
			Delivery tmp = first;
			first = second;
			second = tmp;
			int aux = i;
			i = j;
			j = aux;
		}
		if (i != j) {
			TimeWindow tm1 = paths.get(i).getArrival().getTimeWindow();
			deleteDelivery(paths.get(i).getArrival(), graph);
			addDelivery(paths.get(i).getDeparture(), second, graph, tm1);
			TimeWindow tm2 = paths.get(j).getArrival().getTimeWindow();
			deleteDelivery(paths.get(j).getArrival(), graph);
			addDelivery(paths.get(j).getDeparture(), first, graph, tm2);
		}
		System.out.println("Delivery " + first.getAddress() + " has now start " + first.getTimeWindow().getStart());
		System.out.println("Delivery " + second.getAddress() + " has now start " + second.getTimeWindow().getStart());
	}

	/**
	 * Replace the list of paths
	 * 
	 * @param paths new list of paths
	 */
	public void setPaths(ArrayList<Path> paths) {
		duration = 0;
		for (int i = 0; i < paths.size(); ++i) {
			duration += paths.get(i).getDuration();
		}
		this.paths = paths;
	}

	/**
	 * Return the paths
	 * 
	 * @return the list of paths
	 */
	public ArrayList<Path> getPaths() {
		return paths;
	}

	/**
	 * Replaces the start of a delivery round
	 * 
	 * @param start new start hour
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Replaces the end of a delivery round
	 * 
	 * @param end new end hour
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Replaces the duration of a delivery round
	 * 
	 * @param duration new duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Return start hour
	 * 
	 * @return start hour
	 */
	public int getStart() {
		return this.start;
	}

	/**
	 * Return end hour
	 * 
	 * @return end hour
	 */
	public int getEnd() {
		return this.end;
	}

	/**
	 * Return duration of the delivery round
	 * 
	 * @return duration of the delivery round
	 */
	public double getDuration() {
		return this.duration;
	}

}