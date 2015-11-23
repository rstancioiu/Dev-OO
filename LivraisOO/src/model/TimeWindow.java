package model;

public class TimeWindow {
	private String name;
	private int departure;
	private int arrival;
	private double speed;
	private double length;

	public TimeWindow(String name, int departure, int arrival, double speed, double length) {
		this.name = name;
		this.departure = departure;
		this.arrival = arrival;
		this.speed = speed;
		this.length = length;
	}

	public int getArrival() {
		return arrival;
	}

	public String getName() {
		return name;
	}

	public int getDeparture() {
		return departure;
	}

	public double getSpeed() {
		return speed;
	}

	public double getLength() {
		return length;
	}
}