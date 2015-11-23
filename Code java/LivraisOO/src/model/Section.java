package model;

public class Section {
	String name;
	int departure;
	int arrival;
	double speed;
	double length;
	
	public Section(String n, int d, int a, double s, double l){
		name = n;
		departure = d;
		arrival = a;
		speed = s;
		length = l;
	}
	
	public int getArrival(){
		return this.arrival;
	}
	public int getDeparture(){
		return this.departure;
	}
}